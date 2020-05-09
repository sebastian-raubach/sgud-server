package raubach.sgud.server.resource;

import org.jooq.*;
import org.jooq.tools.StringUtils;
import org.restlet.data.Status;
import org.restlet.resource.*;
import raubach.sgud.resource.PaginatedRequest;
import raubach.sgud.resource.PaginatedResult;
import raubach.sgud.server.Database;
import raubach.sgud.server.database.tables.pojos.Categories;
import raubach.sgud.server.database.tables.pojos.ViewCategories;
import raubach.sgud.server.database.tables.pojos.ViewItems;
import raubach.sgud.server.database.tables.records.CategoriesRecord;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

import static raubach.sgud.server.database.tables.Categories.CATEGORIES;
import static raubach.sgud.server.database.tables.ViewCategories.VIEW_CATEGORIES;
import static raubach.sgud.server.database.tables.ViewItems.VIEW_ITEMS;

public class CategoryItemServerResource extends PaginatedServerResource
{
	private Integer categoryId;

	@Override
	protected void doInit() throws ResourceException
	{
		super.doInit();

		try
		{
			this.categoryId = Integer.parseInt(getRequestAttributes().get("categoryId").toString());
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

			SelectConditionStep<Record> from = select.from(VIEW_ITEMS)
					.where(VIEW_ITEMS.CATEGORY_ID.eq(categoryId));

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
