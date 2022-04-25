package raubach.sgud.server.resource;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import org.apache.commons.io.IOUtils;
import org.jooq.*;
import raubach.sgud.server.Database;
import raubach.sgud.server.database.tables.pojos.Images;
import raubach.sgud.server.database.tables.records.ImagesRecord;
import raubach.sgud.server.util.*;
import raubach.sgud.server.util.watcher.PropertyWatcher;

import java.io.*;
import java.sql.*;
import java.util.Objects;
import java.util.logging.*;

import static raubach.sgud.server.database.tables.Images.*;

@Path("image")
public class ImageServerResource extends BaseResource
{
	public static final String PARAM_SIZE = "size";

	@GET
	@Path("/{imageId}/src")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({"image/png", "image/jpeg", "image/svg+xml", "image/*"})
	public Response getImage(@PathParam("imageId") Integer imageId, @QueryParam("size") ThumbnailUtils.Size size)
		throws IOException, SQLException
	{
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
					String type;

					if (file.getName().toLowerCase().endsWith(".jpg"))
						type = "image/jpeg";
					else if (file.getName().toLowerCase().endsWith(".png"))
						type = "image/png";
					else
						type = "image/*";

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
						byte[] bytes = IOUtils.toByteArray(file.toURI());

						return Response.ok(new ByteArrayInputStream(bytes))
									   .header("Content-Type", type)
									   .header("content-disposition", "attachment;filename= \"" + file.getName() + "\"")
									   .header("content-length", file.length())
									   .build();
					}
					else
					{
						Logger.getLogger("").log(Level.WARNING, "File not found: " + file.getAbsolutePath());
						resp.sendError(Response.Status.NOT_FOUND.getStatusCode());
						return null;
					}
				}
			}
			catch (SQLException e)
			{
				resp.sendError(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
				return null;
			}
		}
		else
		{
			resp.sendError(Response.Status.BAD_REQUEST.getStatusCode());
			return null;
		}

		return null;
	}

	@DELETE
	@Path("/{imageId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean deleteImage(@PathParam("imageId") Integer imageId, Images image)
		throws IOException, SQLException
	{
		if (imageId == null || image == null || !Objects.equals(imageId, image.getId()))
		{
			resp.sendError(Response.Status.BAD_REQUEST.getStatusCode());
			return false;
		}

		try (Connection conn = Database.getConnection();
			 DSLContext context = Database.getContext(conn))
		{
			ImagesRecord record = context.selectFrom(IMAGES)
										 .where(IMAGES.ID.eq(imageId))
										 .fetchAny();

			if (record == null)
			{
				resp.sendError(Response.Status.NOT_FOUND.getStatusCode());
				return false;
			}

			File imageFile = new File(new File(PropertyWatcher.get(ServerProperty.CONFIG_PATH), "images"), record.getId() + ".jpg");

			if (imageFile.exists())
				imageFile.delete();

			for (ThumbnailUtils.Size size : ThumbnailUtils.Size.values())
			{
				imageFile = new File(new File(PropertyWatcher.get(ServerProperty.CONFIG_PATH), "images"), record.getId() + size.getSuffix() + ".jpg");

				if (imageFile.exists())
					imageFile.delete();
			}

			return record.delete() > 0;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			resp.sendError(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
			return false;
		}
	}
}
