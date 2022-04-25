package raubach.sgud.server.resource;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import org.jooq.DSLContext;
import org.jooq.tools.StringUtils;
import raubach.sgud.server.Database;
import raubach.sgud.server.database.tables.pojos.Sources;
import raubach.sgud.server.database.tables.records.SourcesRecord;

import java.io.IOException;
import java.sql.*;
import java.util.*;

import static raubach.sgud.server.database.tables.Sources.*;

@Path("source")
public class SourceServerResource extends ContextResource
{
	@PATCH
	@Path("/{sourceId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean patchSource(@PathParam("sourceId") Integer sourceId, Sources source)
		throws IOException, SQLException
	{
		if (source == null || sourceId == null || !Objects.equals(source.getId(), sourceId) || StringUtils.isEmpty(source.getName()))
		{
			resp.sendError(Response.Status.BAD_REQUEST.getStatusCode());
			return false;
		}

		try (Connection conn = Database.getConnection();
			 DSLContext context = Database.getContext(conn))
		{
			SourcesRecord record = context.newRecord(SOURCES, source);
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
	@Path("/{sourceId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean deleteManufacturer(@PathParam("sourceId") Integer sourceId)
		throws IOException, SQLException
	{
		if (sourceId == null)
		{
			resp.sendError(Response.Status.BAD_REQUEST.getStatusCode());
			return false;
		}

		try (Connection conn = Database.getConnection();
			 DSLContext context = Database.getContext(conn))
		{
			return context.deleteFrom(SOURCES)
						  .where(SOURCES.ID.eq(sourceId))
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
	public int postManufacturer(Sources source)
		throws IOException, SQLException
	{
		if (source == null || StringUtils.isEmpty(source.getName()))
		{
			resp.sendError(Response.Status.BAD_REQUEST.getStatusCode());
			return -1;
		}
		if (source.getId() != null)
		{
			resp.sendError(Response.Status.CONFLICT.getStatusCode());
			return -1;
		}

		try (Connection conn = Database.getConnection();
			 DSLContext context = Database.getContext(conn))
		{
			SourcesRecord newRecord = context.newRecord(SOURCES, source);
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
	public List<Sources> getSources()
		throws IOException, SQLException
	{
		try (Connection conn = Database.getConnection();
			 DSLContext context = Database.getContext(conn))
		{
			return context.selectFrom(SOURCES)
						  .orderBy(SOURCES.NAME)
						  .fetchInto(Sources.class);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			resp.sendError(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
			return null;
		}
	}
}
