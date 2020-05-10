package raubach.sgud.server.resource;

import org.jooq.DSLContext;
import org.restlet.data.Status;
import org.restlet.resource.*;
import raubach.sgud.server.Database;
import raubach.sgud.server.database.tables.pojos.ViewRatings;
import raubach.sgud.server.database.tables.records.ItemRatingsRecord;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

import static raubach.sgud.server.database.tables.ItemRatings.ITEM_RATINGS;

public class ItemRatingIndividualServerResource extends PaginatedServerResource
{
	private Integer itemId;
	private Integer categoryId;

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
		try
		{
			this.categoryId = Integer.parseInt(getRequestAttributes().get("categoryId").toString());
		}
		catch (Exception e)
		{
		}
	}

	@Delete
	public boolean deleteJson(ViewRatings rating)
	{
		if (itemId == null || categoryId == null || rating == null || !Objects.equals(rating.getItemId(), itemId) || !Objects.equals(rating.getRatingCategoryId(), categoryId))
			throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST);

		try (Connection conn = Database.getConnection();
			 DSLContext context = Database.getContext(conn))
		{
			return context.deleteFrom(ITEM_RATINGS)
					.where(ITEM_RATINGS.ITEM_ID.eq(rating.getItemId()))
					.and(ITEM_RATINGS.RATINGCATEGORY_ID.eq(rating.getRatingCategoryId()))
					.execute() > 0;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ResourceException(Status.SERVER_ERROR_INTERNAL);
		}
	}

	@Put
	public boolean putJson(ViewRatings rating) {
		if (itemId == null || categoryId == null || rating == null || !Objects.equals(rating.getItemId(), itemId) || !Objects.equals(rating.getRatingCategoryId(), categoryId))
			throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST);

		try (Connection conn = Database.getConnection();
			 DSLContext context = Database.getContext(conn))
		{
			return context.insertInto(ITEM_RATINGS, ITEM_RATINGS.ITEM_ID, ITEM_RATINGS.RATINGCATEGORY_ID, ITEM_RATINGS.RATING)
					.values(rating.getItemId(), rating.getRatingCategoryId(), rating.getRating())
					.onDuplicateKeyUpdate()
					.set(ITEM_RATINGS.RATING, rating.getRating())
					.execute() > 0;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ResourceException(Status.SERVER_ERROR_INTERNAL);
		}
	}

	@Patch
	public boolean patchJson(ViewRatings rating)
	{
		if (itemId == null || categoryId == null || rating == null || !Objects.equals(rating.getItemId(), itemId) || !Objects.equals(rating.getRatingCategoryId(), categoryId))
			throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST);

		try (Connection conn = Database.getConnection();
			 DSLContext context = Database.getContext(conn))
		{
			ItemRatingsRecord record = context.selectFrom(ITEM_RATINGS)
					.where(ITEM_RATINGS.ITEM_ID.eq(itemId))
					.and(ITEM_RATINGS.RATINGCATEGORY_ID.eq(categoryId))
					.fetchAny();

			if (record == null)
				throw new ResourceException(Status.CLIENT_ERROR_NOT_FOUND);

			record.setRating(rating.getRating());
			return record.store() == 1;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ResourceException(Status.SERVER_ERROR_INTERNAL);
		}
	}
}
