package raubach.sgud.server.resource;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import org.jooq.DSLContext;
import org.jooq.tools.StringUtils;
import raubach.sgud.server.Database;
import raubach.sgud.server.database.tables.pojos.Types;
import raubach.sgud.server.database.tables.records.TypesRecord;

import java.io.IOException;
import java.sql.*;
import java.util.Objects;

import static raubach.sgud.server.database.tables.Types.*;

@Path("type")
public class TypeServerResource extends ContextResource
{
	@PATCH
	@Path("/{typeId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean patchType(@PathParam("typeId") Integer typeId, Types type)
		throws IOException, SQLException
	{
		if (type == null || typeId == null || !Objects.equals(type.getId(), typeId) || StringUtils.isEmpty(type.getName()))
		{
			resp.sendError(Response.Status.BAD_REQUEST.getStatusCode());
			return false;
		}

		try (Connection conn = Database.getConnection();
			 DSLContext context = Database.getContext(conn))
		{
			TypesRecord record = context.newRecord(TYPES, type);
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
	@Path("/{typeId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean deleteType(@PathParam("typeId") Integer typeId)
		throws IOException, SQLException
	{
		if (typeId == null)
		{
			resp.sendError(Response.Status.BAD_REQUEST.getStatusCode());
			return false;
		}

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
			resp.sendError(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
			return false;
		}
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public int postType(Types type)
		throws IOException, SQLException
	{
		if (type == null || StringUtils.isEmpty(type.getName()))
		{
			resp.sendError(Response.Status.BAD_REQUEST.getStatusCode());
			return -1;
		}
		if (type.getId() != null)
		{
			resp.sendError(Response.Status.CONFLICT.getStatusCode());
			return -1;
		}

		try (Connection conn = Database.getConnection();
			 DSLContext context = Database.getContext(conn))
		{
			TypesRecord newRecord = context.newRecord(TYPES, type);
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
}
