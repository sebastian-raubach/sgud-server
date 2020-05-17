package raubach.sgud.server.resource;

import org.jooq.DSLContext;
import org.restlet.data.Status;
import org.restlet.resource.Get;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;
import raubach.sgud.resource.HeatmapData;
import raubach.sgud.server.Database;
import raubach.sgud.server.database.tables.pojos.Items;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static raubach.sgud.server.database.tables.ViewRatings.VIEW_RATINGS;

public class CategoryHeatmapServerResource extends ServerResource
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
	public List<HeatmapData> getJson()
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
			throw new ResourceException(Status.SERVER_ERROR_INTERNAL);
		}
	}
}
