package raubach.sgud.server.resource;

import org.jooq.DSLContext;
import org.restlet.data.Status;
import org.restlet.resource.Post;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;
import raubach.sgud.server.Database;
import raubach.sgud.server.database.tables.pojos.Items;
import raubach.sgud.server.database.tables.records.CategoriesRecord;
import raubach.sgud.server.database.tables.records.ItemsRecord;
import raubach.sgud.server.database.tables.records.SourcesRecord;
import raubach.sgud.server.database.tables.records.TypesRecord;

import java.sql.Connection;
import java.sql.SQLException;

import static raubach.sgud.server.database.tables.Categories.CATEGORIES;
import static raubach.sgud.server.database.tables.Items.ITEMS;
import static raubach.sgud.server.database.tables.Sources.SOURCES;
import static raubach.sgud.server.database.tables.Types.TYPES;

public class ItemServerResource extends ServerResource
{
	@Post
	public int postItems(Items item) {
		if (item == null || item.getCategoryId() == null || item.getManufacturerId() == null || item.getTypeId() == null)
			throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST);

		try (Connection conn = Database.getConnection();
			 DSLContext context = Database.getContext(conn))
		{
			CategoriesRecord category = context.selectFrom(CATEGORIES)
					.where(CATEGORIES.ID.eq(item.getCategoryId()))
					.fetchAny();
			TypesRecord type = context.selectFrom(TYPES)
					.where(TYPES.ID.eq(item.getTypeId()))
					.fetchAny();

			if (category == null || type == null)
				throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST);

			ItemsRecord record = context.newRecord(ITEMS, item);
			record.store();

			return record.getId();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ResourceException(Status.SERVER_ERROR_INTERNAL);
		}
	}
}
