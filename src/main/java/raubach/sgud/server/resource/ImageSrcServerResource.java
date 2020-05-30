package raubach.sgud.server.resource;

import org.jooq.*;
import org.restlet.data.Disposition;
import org.restlet.data.MediaType;
import org.restlet.data.Status;
import org.restlet.representation.FileRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;
import raubach.sgud.server.Database;
import raubach.sgud.server.database.tables.pojos.Images;
import raubach.sgud.server.util.ServerProperty;
import raubach.sgud.server.util.ThumbnailUtils;
import raubach.sgud.server.util.watcher.PropertyWatcher;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static raubach.sgud.server.database.tables.Images.IMAGES;

public class ImageSrcServerResource extends ServerResource
{
	public static final String PARAM_SIZE = "size";

	private ThumbnailUtils.Size size = ThumbnailUtils.Size.ORIGINAL;

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
		try
		{
			this.size = ThumbnailUtils.Size.valueOf(getQueryValue(PARAM_SIZE));
		}
		catch (Exception e)
		{
			this.size = ThumbnailUtils.Size.ORIGINAL;
		}
	}

	@Get
	public Representation getImage()
	{
		FileRepresentation representation = null;

		if (imageId != null)
		{
			try (Connection conn = Database.getConnection();
				 DSLContext context = Database.getContext(conn))
			{
				SelectConditionStep<Record> step = context.select().from(IMAGES)
						.where(IMAGES.ID.eq(imageId));

				Images image = step.fetchAnyInto(Images.class);

				if (image != null)
				{
					File file = new File(new File(PropertyWatcher.get(ServerProperty.CONFIG_PATH), "images"), image.getId() + ".jpg");
					String filename = file.getName();
					MediaType type;

					if (file.getName().toLowerCase().endsWith(".jpg"))
						type = MediaType.IMAGE_JPEG;
					else if (file.getName().toLowerCase().endsWith(".png"))
						type = MediaType.IMAGE_PNG;
					else
						type = MediaType.IMAGE_ALL;

					if (size != ThumbnailUtils.Size.ORIGINAL)
					{
						try
						{
							file = ThumbnailUtils.getOrCreateThumbnail(type, image.getId(), file, size);
						}
						catch (IOException e)
						{
							e.printStackTrace();
						}
					}
					// Check if the image exists
					if (file.exists() && file.isFile())
					{

						Disposition disposition = new Disposition(Disposition.TYPE_ATTACHMENT);
						disposition.setFilename(filename);
						disposition.setSize(file.length());
						representation = new FileRepresentation(file, type);
						representation.setSize(file.length());
						representation.setDisposition(disposition);
					}
					else
					{
						Logger.getLogger("").log(Level.WARNING, "File not found: " + file.getAbsolutePath());
						throw new ResourceException(Status.CLIENT_ERROR_NOT_FOUND);
					}
				}
			}
			catch (SQLException e)
			{
				throw new ResourceException(Status.SERVER_ERROR_INTERNAL);
			}
		}
		else
		{
			throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST);
		}

		return representation;
	}
}
