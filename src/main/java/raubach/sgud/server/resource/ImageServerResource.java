package raubach.sgud.server.resource;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.io.FileUtils;
import org.jooq.DSLContext;
import org.jooq.Record1;
import org.jooq.Result;
import org.restlet.data.MediaType;
import org.restlet.data.Status;
import org.restlet.ext.fileupload.RestletFileUpload;
import org.restlet.representation.Representation;
import org.restlet.resource.Delete;
import org.restlet.resource.Post;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;
import raubach.sgud.server.Database;
import raubach.sgud.server.database.tables.pojos.Images;
import raubach.sgud.server.database.tables.records.ImagesRecord;
import raubach.sgud.server.util.ServerProperty;
import raubach.sgud.server.util.ThumbnailUtils;
import raubach.sgud.server.util.watcher.PropertyWatcher;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import static raubach.sgud.server.database.tables.Images.IMAGES;

public class ImageServerResource extends ServerResource
{
	private Integer imageId;

	@Override
	protected void doInit() throws ResourceException
	{
		super.doInit();

		try
		{
			this.imageId = Integer.parseInt(getRequestAttributes().get("imageId").toString());
		}
		catch (Exception e)
		{
		}
	}

	@Delete
	public boolean deleteJson(Images image) {
		if (imageId == null || image == null || !Objects.equals(imageId, image.getId()))
			throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST);

		try (Connection conn = Database.getConnection();
			 DSLContext context = Database.getContext(conn))
		{
			ImagesRecord record = context.selectFrom(IMAGES)
					.where(IMAGES.ID.eq(imageId))
					.fetchAny();

			if (record == null)
				throw new ResourceException(Status.CLIENT_ERROR_NOT_FOUND);

			File imageFile = new File(PropertyWatcher.get(ServerProperty.IMAGE_FOLDER), record.getId() + ".jpg");

			if (imageFile.exists())
				imageFile.delete();

			for (ThumbnailUtils.Size size : ThumbnailUtils.Size.values())
			{
				imageFile = new File(PropertyWatcher.get(ServerProperty.IMAGE_FOLDER), record.getId() + size.getSuffix() + ".jpg");

				if (imageFile.exists())
					imageFile.delete();
			}

			return record.delete() > 0;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ResourceException(Status.SERVER_ERROR_INTERNAL);
		}
	}
}
