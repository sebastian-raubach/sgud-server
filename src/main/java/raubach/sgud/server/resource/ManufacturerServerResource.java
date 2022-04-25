package raubach.sgud.server.resource;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import org.jooq.DSLContext;
import org.jooq.tools.StringUtils;
import raubach.sgud.server.Database;
import raubach.sgud.server.database.tables.pojos.Manufacturers;
import raubach.sgud.server.database.tables.records.ManufacturersRecord;

import java.io.IOException;
import java.sql.*;
import java.util.*;

import static raubach.sgud.server.database.tables.Manufacturers.*;

@Path("manufacturer")
public class ManufacturerServerResource extends ContextResource
{
	@PATCH
	@Path("/{manufacturerId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean patchManufacturer(@PathParam("manufacturerId") Integer manufacturerId, Manufacturers manufacturer)
		throws IOException, SQLException
	{
		if (manufacturer == null || manufacturerId == null || !Objects.equals(manufacturer.getId(), manufacturerId) || StringUtils.isEmpty(manufacturer.getName()))
		{
			resp.sendError(Response.Status.BAD_REQUEST.getStatusCode());
			return false;
		}

		try (Connection conn = Database.getConnection();
			 DSLContext context = Database.getContext(conn))
		{
			ManufacturersRecord record = context.newRecord(MANUFACTURERS, manufacturer);
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
	@Path("/{manufacturerId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean deleteManufacturer(@PathParam("manufacturerId") Integer manufacturerId)
		throws IOException, SQLException
	{
		if (manufacturerId == null)
		{
			resp.sendError(Response.Status.BAD_REQUEST.getStatusCode());
			return false;
		}

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
			resp.sendError(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
			return false;
		}
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public int postManufacturer(Manufacturers manufacturer)
		throws IOException, SQLException
	{
		if (manufacturer == null || StringUtils.isEmpty(manufacturer.getName()))
		{
			resp.sendError(Response.Status.BAD_REQUEST.getStatusCode());
			return -1;
		}
		if (manufacturer.getId() != null)
		{
			resp.sendError(Response.Status.CONFLICT.getStatusCode());
			return -1;
		}

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
			resp.sendError(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
			return -1;
		}
	}

	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Manufacturers> getManufacturers()
		throws IOException, SQLException
	{
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
			resp.sendError(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
			return null;
		}
	}
}
