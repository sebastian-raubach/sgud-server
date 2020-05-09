package raubach.sgud.server.resource;

import org.jooq.DSLContext;
import org.jooq.tools.StringUtils;
import org.restlet.data.Status;
import org.restlet.resource.*;
import raubach.sgud.server.Database;
import raubach.sgud.server.database.tables.pojos.Types;
import raubach.sgud.server.database.tables.records.TypesRecord;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Objects;

import static raubach.sgud.server.database.tables.Types.TYPES;

public class TypeServerResource extends ServerResource
{
	private Integer typeId;

	@Override
	protected void doInit() throws ResourceException
	{
		super.doInit();

		try
		{
			this.typeId = Integer.parseInt(getRequestAttributes().get("typeId").toString());
		}
		catch (Exception e)
		{
		}
	}

	@Patch
	public boolean patchType(Types type)
	{
		if (type == null || typeId == null || !Objects.equals(type.getId(), typeId) || StringUtils.isEmpty(type.getName()))
			throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST);

		try (Connection conn = Database.getConnection();
			 DSLContext context = Database.getContext(conn))
		{
			TypesRecord record = context.newRecord(TYPES, type);
			return record.update() > 0;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ResourceException(Status.SERVER_ERROR_INTERNAL);
		}
	}

	@Delete
	public boolean deleteManufacturer()
	{
		if (typeId == null)
			throw new ResourceException(Status.CLIENT_ERROR_NOT_FOUND);

		try (Connection conn = Database.getConnection();
			 DSLContext context = Database.getContext(conn))
		{
			return context.deleteFrom(TYPES)
					.where(TYPES.ID.eq(typeId))
					.execute() > 0;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ResourceException(Status.SERVER_ERROR_INTERNAL);
		}
	}

	@Post
	public int postManufacturer(Types type)
	{
		if (type == null || StringUtils.isEmpty(type.getName()) || typeId != null)
			throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST);
		if (type.getId() != null)
			throw new ResourceException(Status.CLIENT_ERROR_CONFLICT);

		try (Connection conn = Database.getConnection();
			 DSLContext context = Database.getContext(conn))
		{
			TypesRecord newRecord = context.newRecord(TYPES, type);
			newRecord.setCreatedOn(new Timestamp(System.currentTimeMillis()));
			return newRecord.store();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ResourceException(Status.SERVER_ERROR_INTERNAL);
		}
	}
}
