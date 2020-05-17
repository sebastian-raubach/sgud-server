package raubach.sgud.server.resource;

import org.jooq.DSLContext;
import org.jooq.SelectWhereStep;
import org.jooq.tools.StringUtils;
import org.restlet.data.Status;
import org.restlet.resource.*;
import raubach.sgud.server.Database;
import raubach.sgud.server.database.tables.pojos.Categories;
import raubach.sgud.server.database.tables.pojos.ViewCategories;
import raubach.sgud.server.database.tables.records.CategoriesRecord;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

import static raubach.sgud.server.database.tables.Categories.CATEGORIES;
import static raubach.sgud.server.database.tables.ViewCategories.VIEW_CATEGORIES;

public class CategoryServerResource extends ServerResource
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

	@Patch
	public boolean patchCategory(Categories category)
	{
		if (category == null || categoryId == null || !Objects.equals(category.getId(), categoryId) || StringUtils.isEmpty(category.getName()))
			throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST);

		try (Connection conn = Database.getConnection();
			 DSLContext context = Database.getContext(conn))
		{
			CategoriesRecord record = context.newRecord(CATEGORIES, category);
			return record.update() > 0;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ResourceException(Status.SERVER_ERROR_INTERNAL);
		}
	}

	@Delete
	public boolean deleteCategory()
	{
		if (categoryId == null)
			throw new ResourceException(Status.CLIENT_ERROR_NOT_FOUND);

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
			throw new ResourceException(Status.SERVER_ERROR_INTERNAL);
		}
	}

	@Post
	public int postCategory(Categories category)
	{
		if (category == null || StringUtils.isEmpty(category.getName()) || categoryId != null)
			throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST);
		if (category.getId() != null)
			throw new ResourceException(Status.CLIENT_ERROR_CONFLICT);

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
			throw new ResourceException(Status.SERVER_ERROR_INTERNAL);
		}
	}

	@Get
	public List<ViewCategories> getJson()
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
			throw new ResourceException(Status.SERVER_ERROR_INTERNAL);
		}
	}
}
