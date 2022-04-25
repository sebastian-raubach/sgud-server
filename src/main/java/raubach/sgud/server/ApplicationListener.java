package raubach.sgud.server;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebListener;
import raubach.sgud.server.util.watcher.PropertyWatcher;

@WebListener
public class ApplicationListener implements ServletContextListener
{
	@Override
	public void contextInitialized(ServletContextEvent sce)
	{
		PropertyWatcher.initialize();
	}

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent)
	{
		PropertyWatcher.stopFileWatcher();
	}
}
