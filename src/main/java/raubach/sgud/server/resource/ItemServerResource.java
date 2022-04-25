package raubach.sgud.server.resource;

import com.google.gson.JsonArray;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import org.jooq.*;
import org.jooq.tools.StringUtils;
import raubach.sgud.resource.*;
import raubach.sgud.server.Database;
import raubach.sgud.server.database.tables.pojos.*;
import raubach.sgud.server.database.tables.records.*;

import java.io.IOException;
import java.sql.*;
import java.util.*;
import java.util.logging.Logger;

import static raubach.sgud.server.database.tables.Categories.*;
import static raubach.sgud.server.database.tables.Images.*;
import static raubach.sgud.server.database.tables.ItemImages.*;
import static raubach.sgud.server.database.tables.ItemRatings.*;
import static raubach.sgud.server.database.tables.Items.*;
import static raubach.sgud.server.database.tables.Types.*;
import static raubach.sgud.server.database.tables.ViewItems.*;
import static raubach.sgud.server.database.tables.ViewRatings.*;

@Path("item")
public class ItemServerResource extends BaseResource implements FilteredResource
{
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public int postItems(Items item)
		throws IOException, SQLException
	{
		if (item == null || item.getCategoryId() == null || item.getManufacturerId() == null || item.getTypeId() == null)
		{
			resp.sendError(Response.Status.BAD_REQUEST.getStatusCode());
			return -1;
		}

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
			{
				resp.sendError(Response.Status.BAD_REQUEST.getStatusCode());
				return -1;
			}

			ItemsRecord record = context.newRecord(ITEMS, item);
			record.store();

			return record.getId();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			resp.sendError(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
			return -1;
		}
	}

	@DELETE
	@Path("/{itemId}")
	public boolean deleteItem(@PathParam("itemId") Integer itemId)
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
			return context.deleteFrom(ITEMS)
						  .where(ITEMS.ID.eq(itemId))
						  .execute() > 0;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			resp.sendError(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
			return false;
		}
	}

	@PATCH
	@Path("/{itemId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean patchItem(@PathParam("itemId") Integer itemId, Items item)
		throws IOException, SQLException
	{
		if (itemId == null || item == null || item.getId() == null || !Objects.equals(itemId, item.getId()) || StringUtils.isEmpty(item.getName()))
		{
			resp.sendError(Response.Status.BAD_REQUEST.getStatusCode());
			return false;
		}

		try (Connection conn = Database.getConnection();
			 DSLContext context = Database.getContext(conn))
		{
			ItemsRecord record = context.selectFrom(ITEMS).where(ITEMS.ID.eq(itemId)).fetchAny();

			if (record == null)
			{
				resp.sendError(Response.Status.NOT_FOUND.getStatusCode());
				return false;
			}

			record.setName(item.getName());
			record.setDescription(item.getDescription());
			if (item.getTags() != null)
			{
				// Remove duplicates
				JsonArray array = item.getTags();
				JsonArray temp = new JsonArray();
				Set<String> set = new HashSet<>();
				array.forEach(t -> {
					if (!set.contains(t.getAsString()))
					{
						temp.add(t);
						set.add(t.getAsString());
					}
				});
				Logger.getLogger("").info("Setting tags: " + temp);
				record.setTags(temp);
			}
			else
			{
				record.setTags(null);
			}
			if (item.getTypeId() != null)
				record.setTypeId(item.getTypeId());
			if (item.getSourceId() != null)
				record.setSourceId(item.getSourceId());
			if (item.getManufacturerId() != null)
				record.setManufacturerId(item.getManufacturerId());
			return record.store() > 0;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			resp.sendError(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
			return false;
		}
	}

	@POST
	@Path("/{itemId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public PaginatedResult<List<ViewItems>> postItems(@PathParam("itemId") Integer itemId, PaginatedRequest request)
		throws IOException, SQLException
	{
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
			resp.sendError(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
			return null;
		}
	}

	@GET
	@Path("/{itemId}/image")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Images> getItemImages(@PathParam("itemId") Integer itemId)
		throws IOException, SQLException
	{

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
			resp.sendError(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
			return null;
		}
	}

	@PUT
	@Path("/{itemId}/rating")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean putJson(@PathParam("itemId") Integer itemId, ViewRatings[] ratings)
		throws IOException, SQLException
	{
		if (ratings == null || itemId == null)
		{
			resp.sendError(Response.Status.BAD_REQUEST.getStatusCode());
			return false;
		}

		for (ViewRatings rating : ratings)
		{
			if (rating == null || rating.getRatingCategoryId() == null || !Objects.equals(rating.getItemId(), itemId))
			{

				resp.sendError(Response.Status.BAD_REQUEST.getStatusCode());
				return false;
			}
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
			resp.sendError(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
			return false;
		}
	}

	@POST
	@Path("/{itemId}/rating")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public PaginatedResult<List<ViewRatings>> postJson(@PathParam("itemId") Integer itemId, PaginatedRequest request)
		throws IOException, SQLException
	{
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
			resp.sendError(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
			return null;
		}
	}

	@DELETE
	@Path("/{itemId}/rating/{categoryId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean deleteItemRatingCategory(@PathParam("itemId") Integer itemId, @PathParam("categoryId") Integer categoryId, ViewRatings rating)
		throws IOException, SQLException
	{
		if (itemId == null || categoryId == null || rating == null || !Objects.equals(rating.getItemId(), itemId) || !Objects.equals(rating.getRatingCategoryId(), categoryId))
		{
			resp.sendError(Response.Status.BAD_REQUEST.getStatusCode());
			return false;
		}

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
			resp.sendError(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
			return false;
		}
	}

	@PUT
	@Path("/{itemId}/rating/{categoryId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean putJson(@PathParam("itemId") Integer itemId, @PathParam("categoryId") Integer categoryId, ViewRatings rating)
		throws IOException, SQLException
	{
		if (itemId == null || categoryId == null || rating == null || !Objects.equals(rating.getItemId(), itemId) || !Objects.equals(rating.getRatingCategoryId(), categoryId))
		{
			resp.sendError(Response.Status.BAD_REQUEST.getStatusCode());
			return false;
		}

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
			resp.sendError(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
			return false;
		}
	}

	@PATCH
	@Path("/{itemId}/rating/{categoryId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean patchJson(@PathParam("itemId") Integer itemId, @PathParam("categoryId") Integer categoryId, ViewRatings rating)
		throws IOException, SQLException
	{
		if (itemId == null || categoryId == null || rating == null || !Objects.equals(rating.getItemId(), itemId) || !Objects.equals(rating.getRatingCategoryId(), categoryId))
		{

			resp.sendError(Response.Status.BAD_REQUEST.getStatusCode());
			return false;
		}

		try (Connection conn = Database.getConnection();
			 DSLContext context = Database.getContext(conn))
		{
			ItemRatingsRecord record = context.selectFrom(ITEM_RATINGS)
											  .where(ITEM_RATINGS.ITEM_ID.eq(itemId))
											  .and(ITEM_RATINGS.RATINGCATEGORY_ID.eq(categoryId))
											  .fetchAny();

			if (record == null)
			{
				resp.sendError(Response.Status.NOT_FOUND.getStatusCode());
				return false;
			}

			record.setRating(rating.getRating());
			return record.store() == 1;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			resp.sendError(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
			return false;
		}
	}
}
