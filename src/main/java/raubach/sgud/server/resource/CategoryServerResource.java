package raubach.sgud.server.resource;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import org.jooq.*;
import org.jooq.conf.ParamType;
import org.jooq.tools.StringUtils;
import raubach.sgud.resource.*;
import raubach.sgud.server.Database;
import raubach.sgud.server.database.tables.pojos.*;
import raubach.sgud.server.database.tables.pojos.Types;
import raubach.sgud.server.database.tables.records.*;

import java.io.IOException;
import java.sql.*;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static raubach.sgud.server.database.tables.Categories.*;
import static raubach.sgud.server.database.tables.RatingCategories.*;
import static raubach.sgud.server.database.tables.Types.*;
import static raubach.sgud.server.database.tables.ViewCategories.*;
import static raubach.sgud.server.database.tables.ViewCategoryStats.*;
import static raubach.sgud.server.database.tables.ViewItems.*;
import static raubach.sgud.server.database.tables.ViewRatings.*;

@Path("category")
public class CategoryServerResource extends BaseResource implements FilteredResource
{
	@PATCH
	@Path("/{categoryId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean patchCategory(@PathParam("categoryId") Integer categoryId, Categories category)
		throws IOException, SQLException
	{
		if (category == null || categoryId == null || !Objects.equals(category.getId(), categoryId) || StringUtils.isEmpty(category.getName()))
		{
			resp.sendError(Response.Status.BAD_REQUEST.getStatusCode());
			return false;
		}

		try (Connection conn = Database.getConnection();
			 DSLContext context = Database.getContext(conn))
		{
			CategoriesRecord record = context.newRecord(CATEGORIES, category);
			return record.update() > 0;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			resp.sendError(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
			return false;
		}
	}

	@DELETE
	@Path("/{categoryId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean deleteCategory(@PathParam("categoryId") Integer categoryId)
		throws IOException, SQLException
	{
		if (categoryId == null)

		{
			resp.sendError(Response.Status.NOT_FOUND.getStatusCode());
			return false;
		}

		try (Connection conn = Database.getConnection();
			 DSLContext context = Database.getContext(conn))
		{
			return context.deleteFrom(CATEGORIES)
						  .where(CATEGORIES.ID.eq(categoryId))
						  .execute() > 0;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			resp.sendError(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
			return false;
		}
	}

	@POST
	@Path("/{categoryId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public int postCategory(@PathParam("categoryId") Integer categoryId, Categories category)
		throws IOException, SQLException
	{
		if (category == null || StringUtils.isEmpty(category.getName()) || categoryId != null)
		{
			resp.sendError(Response.Status.BAD_REQUEST.getStatusCode());
			return -1;
		}
		if (category.getId() != null)
		{
			resp.sendError(Response.Status.CONFLICT.getStatusCode());
			return -1;
		}

		try (Connection conn = Database.getConnection();
			 DSLContext context = Database.getContext(conn))
		{
			CategoriesRecord newRecord = context.newRecord(CATEGORIES, category);
			newRecord.setCreatedOn(new Timestamp(System.currentTimeMillis()));
			newRecord.store();

			return newRecord.getId();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			resp.sendError(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
			return -1;
		}
	}

	@GET
	@Path("/{categoryId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<ViewCategories> getCategoryById(@PathParam("categoryId") Integer categoryId)
		throws IOException, SQLException
	{
		try (Connection conn = Database.getConnection();
			 DSLContext context = Database.getContext(conn))
		{
			SelectWhereStep<?> step = context.selectFrom(VIEW_CATEGORIES);

			if (categoryId != null)
				step.where(VIEW_CATEGORIES.ID.eq(categoryId));

			return step.orderBy(VIEW_CATEGORIES.COUNT.desc(), VIEW_CATEGORIES.NAME)
					   .fetchInto(ViewCategories.class);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			resp.sendError(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
			return null;
		}
	}

	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<ViewCategories> getCategories()
		throws IOException, SQLException
	{
		return this.getCategoryById(null);
	}

	@POST
	@Path("/{categoryId}/item")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public PaginatedResult<List<ViewItems>> postItems(@PathParam("categoryId") Integer categoryId, PaginatedRequest request)
		throws IOException, SQLException
	{
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

			Logger.getLogger("").info(select.getSQL(ParamType.INLINED));

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


	@POST
	@Path("/{categoryId}/rating")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public int postCategoryRating(@PathParam("categoryId") Integer categoryId, RatingCategories category)
		throws IOException, SQLException
	{
		if (categoryId == null || category == null || categoryId != category.getCategoryId())
		{
			resp.sendError(Response.Status.BAD_REQUEST.getStatusCode());
			return -1;
		}

		try (Connection conn = Database.getConnection();
			 DSLContext context = Database.getContext(conn))
		{
			RatingCategoriesRecord record = context.newRecord(RATING_CATEGORIES, category);
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

	@GET
	@Path("/{categoryId}/rating")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<RatingCategories> getCategoryRating(@PathParam("categoryId") Integer categoryId)
		throws IOException, SQLException
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
			resp.sendError(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
			return null;
		}
	}


	@GET
	@Path("/{categoryId}/heatmap")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<HeatmapData> getCategoryHeatmap(@PathParam("categoryId") Integer categoryId)
		throws IOException, SQLException
	{
		try (Connection conn = Database.getConnection();
			 DSLContext context = Database.getContext(conn))
		{
			Map<String, Items> items = new HashMap<>();
			Map<String, Map<String, Double>> ratings = new HashMap<>();

			context.selectFrom(VIEW_RATINGS)
				   .where(VIEW_RATINGS.CATEGORY_ID.eq(categoryId))
				   .forEach(r -> {
					   String itemId = Integer.toString(r.getItemId());
					   Items item = new Items();
					   item.setId(r.getItemId());
					   item.setName(r.getItemName());
					   item.setDescription(r.getItemDescription());
					   items.put(itemId, item);

					   Map<String, Double> rating = ratings.get(itemId);

					   if (rating == null)
						   rating = new HashMap<>();

					   rating.put(Integer.toString(r.getRatingCategoryId()), r.getRating());

					   ratings.put(itemId, rating);
				   });

			return items.entrySet()
						.stream()
						.map(i -> {
							HeatmapData data = new HeatmapData();
							data.setItem(i.getValue());
							data.setRatings(ratings.get(i.getKey()));
							return data;
						})
						.collect(Collectors.toList());
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			resp.sendError(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
			return null;
		}
	}

	@GET
	@Path("/{categoryId}/stats")
	public List<ViewCategoryStats> getCategoryStats(@PathParam("categoryId") Integer categoryId)
		throws IOException, SQLException
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
			resp.sendError(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
			return null;
		}
	}


	@POST
	@Path("/{categoryId}/type")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean postCategoryType(Types category)
		throws IOException, SQLException
	{
		try (Connection conn = Database.getConnection();
			 DSLContext context = Database.getContext(conn))
		{
			TypesRecord record = context.newRecord(TYPES, category);
			return record.store() > 0;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			resp.sendError(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
			return false;
		}
	}

	@GET
	@Path("/{categoryId}/type")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Types> getCategoryTypes(@PathParam("categoryId") Integer categoryId)
		throws IOException, SQLException
	{
		try (Connection conn = Database.getConnection();
			 DSLContext context = Database.getContext(conn))
		{
			return context.selectFrom(TYPES)
						  .where(TYPES.CATEGORY_ID.eq(categoryId))
						  .orderBy(TYPES.NAME)
						  .fetchInto(Types.class);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			resp.sendError(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
			return null;
		}
	}
}
