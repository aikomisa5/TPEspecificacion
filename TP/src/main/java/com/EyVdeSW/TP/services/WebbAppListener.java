package com.EyVdeSW.TP.services;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.ODBServer;

/**
 * Application Lifecycle Listener implementation class MiListener
 *
 */
@WebListener
public class WebbAppListener implements ServletContextListener
{
	private static final int	NEODATIS_SERVER_PORT	= 10001;
	private static ODBServer	server;
	private static boolean		isOk;

	/**
	 * Default constructor.
	 */
	public WebbAppListener()
	{
		// TODO Auto-generated constructor stub
	}

	public void contextDestroyed(ServletContextEvent sce)
	{
		bajarServerNeodatis();
	}

	private void bajarServerNeodatis()
	{
		System.out.println("Bajando NeoDatis server webapp");
		if (server != null)
		{
			try
			{
				server.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}

	public void contextInitialized(ServletContextEvent sce)
	{
		levantarServerNeodatis();
	}

	private void levantarServerNeodatis()
	{
		try
		{
			System.out.println("Iniciando server Neodatis webapp");
			server = ODBFactory.openServer(NEODATIS_SERVER_PORT);
			server.startServer(true);
			isOk = true;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		finally
		{
		}
	}

}
