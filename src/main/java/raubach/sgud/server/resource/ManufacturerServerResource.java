package raubach.sgud.server.resource;

import org.jooq.DSLContext;
import org.jooq.tools.StringUtils;
import org.restlet.data.Status;
import org.restlet.resource.*;
import raubach.sgud.server.Database;
import raubach.sgud.server.database.tables.pojos.Manufacturers;
import raubach.sgud.server.database.tables.records.ManufacturersRecord;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

import static raubach.sgud.server.database.tables.Manufacturers.MANUFACTURERS;

public class ManufacturerServerResource extends ServerResource
{
	private Integer manufacturerId;

	@Override
	protected void doInit() throws ResourceException
	{
		super.doInit();

		try
		{
			this.manufacturerId = Integer.parseInt(getRequestAttributes().get("manufacturerId").toString());
		}
		catch (Exception e)
		{
		}
	}

	@Patch
	public boolean patchManufacturer(Manufacturers manufacturer)
	{
		if (manufacturer == null || manufacturerId == null || !Objects.equals(manufacturer.getId(), manufacturerId) || StringUtils.isEmpty(manufacturer.getName()))
			throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST);

		try (Connection conn = Database.getConnection();
			 DSLContext context = Database.getContext(conn))
		{
			ManufacturersRecord record = context.newRecord(MANUFACTURERS, manufacturer);
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
		if (manufacturerId == null)
			throw new ResourceException(Status.CLIENT_ERROR_NOT_FOUND);

		try (Connection conn = Database.getConnection();
			 DSLContext context = Database.getContext(conn))
		{
			return context.deleteFrom(MANUFACTURERS)
					.where(MANUFACTURERS.ID.eq(manufacturerId))
					.execute() > 0;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ResourceException(Status.SERVER_ERROR_INTERNAL);
		}
	}

	@Post
	public int postManufacturer(Manufacturers manufacturer)
	{
		if (manufacturer == null || StringUtils.isEmpty(manufacturer.getName()) || manufacturerId != null)
			throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST);
		if (manufacturer.getId() != null)
			throw new ResourceException(Status.CLIENT_ERROR_CONFLICT);

		try (Connection conn = Database.getConnection();
			 DSLContext context = Database.getContext(conn))
		{
			ManufacturersRecord newRecord = context.newRecord(MANUFACTURERS, manufacturer);
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
	public List<Manufacturers> getJson() {
		try (Connection conn = Database.getConnection();
			 DSLContext context = Database.getContext(conn))
		{
			return context.selectFrom(MANUFACTURERS)
					.orderBy(MANUFACTURERS.NAME)
					.fetchInto(Manufacturers.class);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ResourceException(Status.SERVER_ERROR_INTERNAL);
		}
	}
}
