package raubach.sgud.server.resource;

import com.google.gson.JsonArray;
import org.jooq.*;
import org.jooq.tools.StringUtils;
import org.json.JSONArray;
import org.json.JSONString;
import org.restlet.data.Status;
import org.restlet.resource.Delete;
import org.restlet.resource.Patch;
import org.restlet.resource.Post;
import org.restlet.resource.ResourceException;
import raubach.sgud.resource.PaginatedRequest;
import raubach.sgud.resource.PaginatedResult;
import raubach.sgud.server.Database;
import raubach.sgud.server.database.tables.pojos.Items;
import raubach.sgud.server.database.tables.pojos.ViewItems;
import raubach.sgud.server.database.tables.records.ItemsRecord;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.logging.Logger;

import static raubach.sgud.server.database.tables.Items.ITEMS;
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

	@Delete
	public boolean deleteItem() {
		if (itemId == null)
			throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST);

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
			throw new ResourceException(Status.SERVER_ERROR_INTERNAL);
		}
	}

	@Patch
	public boolean patchItem(Items item) {
		if (itemId == null || item == null || item.getId() == null || !Objects.equals(itemId, item.getId()) || StringUtils.isEmpty(item.getName()))
			throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST);

		try (Connection conn = Database.getConnection();
			 DSLContext context = Database.getContext(conn))
		{
			ItemsRecord record = context.selectFrom(ITEMS).where(ITEMS.ID.eq(itemId)).fetchAny();

			if (record == null)
				throw new ResourceException(Status.CLIENT_ERROR_NOT_FOUND);

			record.setName(item.getName());
			record.setDescription(item.getDescription());
			if (item.getTags() != null) {
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
			} else {
				record.setTags(null);
			}
			if(item.getTypeId() != null)
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
			throw new ResourceException(Status.SERVER_ERROR_INTERNAL);
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
