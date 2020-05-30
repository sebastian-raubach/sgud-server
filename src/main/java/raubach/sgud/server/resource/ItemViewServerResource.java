package raubach.sgud.server.resource;

import org.jooq.*;
import org.restlet.data.Status;
import org.restlet.resource.Post;
import org.restlet.resource.ResourceException;
import raubach.sgud.resource.PaginatedRequest;
import raubach.sgud.resource.PaginatedResult;
import raubach.sgud.server.Database;
import raubach.sgud.server.database.tables.pojos.ViewItems;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static raubach.sgud.server.database.tables.ViewItems.*;

public class ItemViewServerResource extends PaginatedServerResource
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

	@Post
	public PaginatedResult<List<ViewItems>> postItems(PaginatedRequest request) {
		processRequest(request);

		try (Connection conn = Database.getConnection();
			 DSLContext context = Database.getContext(conn))
		{
			SelectSelectStep<Record> select = context.select();

			if (previousCount == -1)
				select.hint("SQL_CALC_FOUND_ROWS");

			SelectJoinStep<Record> from = select.from(VIEW_ITEMS);

			if (itemId != null)
				from.where(VIEW_ITEMS.ITEM_ID.eq(itemId));

			filter(from, filters);

			List<ViewItems> result = setPaginationAndOrderBy(from)
					.fetch()
					.into(ViewItems.class);

			long count = previousCount == -1 ? context.fetchOne("SELECT FOUND_ROWS()").into(Long.class) : previousCount;

			return new PaginatedResult<>(result, count);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ResourceException(Status.SERVER_ERROR_INTERNAL);
		}
	}
}
