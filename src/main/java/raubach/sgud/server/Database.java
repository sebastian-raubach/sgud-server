package raubach.sgud.server;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.FlywayException;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.conf.MappedSchema;
import org.jooq.conf.RenderMapping;
import org.jooq.conf.Settings;
import org.jooq.exception.DataAccessException;
import org.jooq.impl.DSL;
import raubach.sgud.server.database.Sgud;
import raubach.sgud.server.util.ScriptRunner;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;

import static raubach.sgud.server.database.tables.Items.ITEMS;

/**
 * @author Sebastian Raubach
 */

/**
 * @author Sebastian Raubach
 */
public class Database
{
	private static String databaseServer;
	private static String databaseName;
	private static String databasePort;
	private static String username;
	private static String password;

	private static String utc = TimeZone.getDefault().getID();

	public static void init(String databaseServer, String databaseName, String databasePort, String username, String password, boolean initAndUpdate)
	{
		Database.databaseServer = databaseServer;
		Database.databaseName = databaseName;
		Database.databasePort = databasePort;
		Database.username = username;
		Database.password = password;

		try
		{
			// The newInstance() call is a work around for some
			// broken Java implementations
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
		}
		catch (Exception e)
		{
			Logger.getLogger("").log(Level.SEVERE, e.getMessage());
			e.printStackTrace();
		}

		int unsuccessfulConnectionCount = 0;

		while (unsuccessfulConnectionCount < 10)
		{
			// Get an initial connection to try if it works
			try (Connection conn = getConnection())
			{
				DSL.using(conn, SQLDialect.MYSQL).close();
				break;
			}
			catch (SQLException e)
			{
				Logger.getLogger("").log(Level.SEVERE, e.getMessage());
				e.printStackTrace();
				unsuccessfulConnectionCount++;
				try
				{
					Thread.sleep(5000);
				}
				catch (InterruptedException ex)
				{
					ex.printStackTrace();
				}
			}
		}

		if (initAndUpdate)
		{
			boolean databaseExists = true;
			try (Connection conn = getConnection();
				 DSLContext context = getContext(conn))
			{
				// Try and see if the `images` table exists
				context.selectOne()
						.from(ITEMS)
						.fetchAny();
			}
			catch (SQLException | DataAccessException e)
			{
				Logger.getLogger("").log(Level.SEVERE, e.getMessage());
				e.printStackTrace();
				databaseExists = false;
			}

			Logger.getLogger("").log(Level.INFO, "Database exists: " + databaseExists);

			if (!databaseExists)
			{
				// Set up the database initially
				try
				{
					URL url = Database.class.getClassLoader().getResource("raubach/sgud/server/util/database/init/sgud.sql");

					if (url != null)
					{
						Logger.getLogger("").log(Level.INFO, "RUNNING DATABASE CREATION SCRIPT!");
						executeFile(new File(url.toURI()));
					}
					else
					{
						throw new IOException("Setup SQL file not found!");
					}
				}
				catch (IOException | URISyntaxException e)
				{
					Logger.getLogger("").log(Level.SEVERE, e.getMessage());
					e.printStackTrace();
				}
			}
			else
			{
				Logger.getLogger("").log(Level.INFO, "DATABASE EXISTS, NO NEED TO CREATE IT!");
			}

			// Run database update
			try
			{
				Logger.getLogger("").log(Level.INFO, "RUNNING FLYWAY on: " + databaseName);
				Flyway flyway = Flyway.configure()
						.table("schema_version")
						.validateOnMigrate(false)
						.dataSource(getDatabaseUrl(), username, password)
						.locations("classpath:raubach.sgud.server.util.database.migration")
						.baselineOnMigrate(true)
						.load();
				flyway.migrate();
				flyway.repair();
			}
			catch (FlywayException e)
			{
				Logger.getLogger("").log(Level.SEVERE, e.getMessage());
				e.printStackTrace();
			}

			// Add/update all the views
			try
			{
				URL url = Database.class.getClassLoader().getResource("raubach/sgud/server/util/database/init/view_procedures.sql");

				if (url != null)
				{
					Logger.getLogger("").log(Level.INFO, "RUNNING VIEW CREATION SCRIPT!");
					executeFile(new File(url.toURI()));
				}
				else
				{
					throw new IOException("View SQL file not found!");
				}
			}
			catch (IOException | URISyntaxException e)
			{
				Logger.getLogger("").log(Level.SEVERE, e.getMessage());
				e.printStackTrace();
			}
		}
	}

	private static void executeFile(File sqlFile)
	{
		try (Connection conn = Database.getConnection();
			 BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(sqlFile), StandardCharsets.UTF_8)))
		{
			ScriptRunner runner = new ScriptRunner(conn, true, true);
			runner.runScript(br);
		}
		catch (SQLException | IOException e)
		{
			Logger.getLogger("").log(Level.SEVERE, e.getMessage());
			e.printStackTrace();
		}
	}

	private static String getDatabaseUrl()
	{
		return "jdbc:mysql://" + databaseServer + ":" + (databasePort != null ? databasePort : "3306") + "/" + databaseName + "?useUnicode=yes&characterEncoding=UTF-8&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=" + utc;
	}

	public static Connection getConnection()
			throws SQLException
	{
		return DriverManager.getConnection(getDatabaseUrl(), username, password);
	}

	public static DSLContext getContext(Connection connection)
	{
		Settings settings = new Settings()
				.withRenderMapping(new RenderMapping()
						.withSchemata(
								new MappedSchema().withInput(Sgud.SGUD.getQualifiedName().first())
										.withOutput(databaseName)));

		return DSL.using(connection, SQLDialect.MYSQL, settings);
	}
}
