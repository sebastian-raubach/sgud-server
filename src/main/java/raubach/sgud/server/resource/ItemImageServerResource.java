package raubach.sgud.server.resource;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SelectJoinStep;
import org.jooq.SelectSelectStep;
import org.restlet.data.Status;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ResourceException;
import raubach.sgud.resource.PaginatedRequest;
import raubach.sgud.resource.PaginatedResult;
import raubach.sgud.server.Database;
import raubach.sgud.server.database.tables.pojos.Images;
import raubach.sgud.server.database.tables.pojos.ViewItems;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static raubach.sgud.server.database.tables.Images.IMAGES;
import static raubach.sgud.server.database.tables.ItemImages.ITEM_IMAGES;
import static raubach.sgud.server.database.tables.ViewItems.VIEW_ITEMS;

public class ItemImageServerResource extends PaginatedServerResource
{
	private Integer itemId;

	@Override
	protected void doInit() throws ResourceException
	{
		super.doInit();

		try
		{
			this.itemId = Integer.parseInt(getRequestAttributes().get("itemId").toString());
		}
		catch (Exception e)
		{
		}
	}

	@Get
	public List<Images> postItems() {

		try (Connection conn = Database.getConnection();
			 DSLContext context = Database.getContext(conn))
		{
			SelectSelectStep<Record> select = context.select();

			if (previousCount == -1)
				select.hint("SQL_CALC_FOUND_ROWS");

			return context.select(IMAGES.fields())
					.from(IMAGES)
					.leftJoin(ITEM_IMAGES).on(IMAGES.ID.eq(ITEM_IMAGES.IMAGE_ID))
					.where(ITEM_IMAGES.ITEM_ID.eq(itemId))
					.fetchInto(Images.class);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ResourceException(Status.SERVER_ERROR_INTERNAL);
		}
	}
}
