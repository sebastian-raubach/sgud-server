package raubach.sgud.server.resource;

import org.jooq.DSLContext;
import org.jooq.tools.StringUtils;
import org.restlet.data.Status;
import org.restlet.resource.*;
import raubach.sgud.server.Database;
import raubach.sgud.server.database.tables.pojos.Sources;
import raubach.sgud.server.database.tables.records.SourcesRecord;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Objects;

import static raubach.sgud.server.database.tables.Sources.SOURCES;

public class SourceServerResource extends ServerResource
{
	private Integer sourceId;

	@Override
	protected void doInit() throws ResourceException
	{
		super.doInit();

		try
		{
			this.sourceId = Integer.parseInt(getRequestAttributes().get("sourceId").toString());
		}
		catch (Exception e)
		{
		}
	}

	@Patch
	public boolean patchSource(Sources source)
	{
		if (source == null || sourceId == null || !Objects.equals(source.getId(), sourceId) || StringUtils.isEmpty(source.getName()))
			throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST);

		try (Connection conn = Database.getConnection();
			 DSLContext context = Database.getContext(conn))
		{
			SourcesRecord record = context.newRecord(SOURCES, source);
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
		if (sourceId == null)
			throw new ResourceException(Status.CLIENT_ERROR_NOT_FOUND);

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
			throw new ResourceException(Status.SERVER_ERROR_INTERNAL);
		}
	}

	@Post
	public int postManufacturer(Sources source)
	{
		if (source == null || StringUtils.isEmpty(source.getName()) || sourceId != null)
			throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST);
		if (source.getId() != null)
			throw new ResourceException(Status.CLIENT_ERROR_CONFLICT);

		try (Connection conn = Database.getConnection();
			 DSLContext context = Database.getContext(conn))
		{
			SourcesRecord newRecord = context.newRecord(SOURCES, source);
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
