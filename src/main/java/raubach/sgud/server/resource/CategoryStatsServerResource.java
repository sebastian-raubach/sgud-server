package raubach.sgud.server.resource;

import org.jooq.DSLContext;
import org.restlet.data.Status;
import org.restlet.resource.Get;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;
import raubach.sgud.server.Database;
import raubach.sgud.server.database.tables.pojos.ViewCategoryStats;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static raubach.sgud.server.database.tables.ViewCategoryStats.VIEW_CATEGORY_STATS;

public class CategoryStatsServerResource extends ServerResource
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

	@Get
	public List<ViewCategoryStats> getJson()
	{
		try (Connection conn = Database.getConnection();
			 DSLContext context = Database.getContext(conn))
		{
			return context.selectFrom(VIEW_CATEGORY_STATS)
					.where(VIEW_CATEGORY_STATS.CATEGORY_ID.eq(categoryId))
					.fetchInto(ViewCategoryStats.class);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ResourceException(Status.SERVER_ERROR_INTERNAL);
		}
	}
}
