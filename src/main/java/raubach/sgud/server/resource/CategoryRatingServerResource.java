package raubach.sgud.server.resource;

import org.jooq.DSLContext;
import org.restlet.data.Status;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;
import raubach.sgud.server.Database;
import raubach.sgud.server.database.tables.pojos.RatingCategories;
import raubach.sgud.server.database.tables.records.RatingCategoriesRecord;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static raubach.sgud.server.database.tables.RatingCategories.RATING_CATEGORIES;

public class CategoryRatingServerResource extends ServerResource
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
	public boolean postJson(RatingCategories category)
	{
		try (Connection conn = Database.getConnection();
			 DSLContext context = Database.getContext(conn))
		{
			RatingCategoriesRecord record = context.newRecord(RATING_CATEGORIES, category);
			return record.store() > 0;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ResourceException(Status.SERVER_ERROR_INTERNAL);
		}
	}

	@Get
	public List<RatingCategories> getJson()
	{
		try (Connection conn = Database.getConnection();
			 DSLContext context = Database.getContext(conn))
		{
			return context.selectFrom(RATING_CATEGORIES)
					.where(RATING_CATEGORIES.CATEGORY_ID.eq(categoryId))
					.fetchInto(RatingCategories.class);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ResourceException(Status.SERVER_ERROR_INTERNAL);
		}
	}
}
