package raubach.sgud.server.resource;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SelectConditionStep;
import org.jooq.SelectSelectStep;
import org.restlet.data.Status;
import org.restlet.resource.Post;
import org.restlet.resource.Put;
import org.restlet.resource.ResourceException;
import raubach.sgud.resource.PaginatedRequest;
import raubach.sgud.resource.PaginatedResult;
import raubach.sgud.server.Database;
import raubach.sgud.server.database.tables.pojos.ViewRatings;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

import static raubach.sgud.server.database.tables.ItemRatings.ITEM_RATINGS;
import static raubach.sgud.server.database.tables.ViewRatings.VIEW_RATINGS;

public class ItemRatingServerResource extends PaginatedServerResource
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

	@Put
	public boolean putJson(ViewRatings[] ratings) {
		if (ratings == null || itemId == null)
			throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST);

		for (ViewRatings rating : ratings) {
			if (rating == null || rating.getRatingCategoryId() == null || !Objects.equals(rating.getItemId(), itemId))
				throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST);
		}

		try (Connection conn = Database.getConnection();
			 DSLContext context = Database.getContext(conn))
		{
			int counter = 0;

			for (ViewRatings rating : ratings)
			{
				counter += context.insertInto(ITEM_RATINGS, ITEM_RATINGS.ITEM_ID, ITEM_RATINGS.RATINGCATEGORY_ID, ITEM_RATINGS.RATING)
						.values(rating.getItemId(), rating.getRatingCategoryId(), rating.getRating())
						.onDuplicateKeyUpdate()
						.set(ITEM_RATINGS.RATING, rating.getRating())
						.execute();
			}

			return counter > 0;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ResourceException(Status.SERVER_ERROR_INTERNAL);
		}
	}

	@Post
	public PaginatedResult<List<ViewRatings>> postJson(PaginatedRequest request) {
		processRequest(request);

		processRequest(request);
		try (Connection conn = Database.getConnection();
			 DSLContext context = Database.getContext(conn))
		{
			SelectSelectStep<Record> select = context.select();

			if (previousCount == -1)
				select.hint("SQL_CALC_FOUND_ROWS");

			SelectConditionStep<Record> from = select.from(VIEW_RATINGS)
					.where(VIEW_RATINGS.ITEM_ID.eq(itemId));

			filter(from, filters);

			List<ViewRatings> result = setPaginationAndOrderBy(from)
					.fetch()
					.into(ViewRatings.class);

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
