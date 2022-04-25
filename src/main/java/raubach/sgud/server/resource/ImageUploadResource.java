package raubach.sgud.server.resource;

import jakarta.servlet.annotation.MultipartConfig;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.jooq.*;
import raubach.sgud.server.Database;
import raubach.sgud.server.util.ServerProperty;
import raubach.sgud.server.util.watcher.PropertyWatcher;

import java.io.*;
import java.nio.file.*;
import java.sql.*;
import java.util.UUID;

import static raubach.sgud.server.database.tables.Images.*;
import static raubach.sgud.server.database.tables.ItemImages.*;

@Path("item/{itemId}/image/upload")
@MultipartConfig
public class ImageUploadResource extends ContextResource
{
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean postImages(@PathParam("itemId") Integer itemId, @FormDataParam("imageFile") InputStream fileIs)
		throws IOException, SQLException
	{

		if (itemId == null)
		{
			resp.sendError(Response.Status.BAD_REQUEST.getStatusCode());
			return false;
		}

		try (Connection conn = Database.getConnection();
			 DSLContext context = Database.getContext(conn))
		{
			Record1<Integer> image = context.insertInto(IMAGES, IMAGES.PATH)
											.values(UUID.randomUUID().toString())
											.returningResult(IMAGES.ID)
											.fetchOne();

			int id = image.component1();

			File targetFile = new File(new File(PropertyWatcher.get(ServerProperty.CONFIG_PATH), "images"), id + ".jpg");
			targetFile.getParentFile().mkdirs();

			Files.copy(fileIs, targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

			context.insertInto(ITEM_IMAGES, ITEM_IMAGES.ITEM_ID, ITEM_IMAGES.IMAGE_ID)
				   .values(itemId, id)
				   .execute();

			return true;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			resp.sendError(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
			return false;
		}
	}
}
