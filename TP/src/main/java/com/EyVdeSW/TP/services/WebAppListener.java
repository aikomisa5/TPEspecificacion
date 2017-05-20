package com.EyVdeSW.TP.services;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

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
public class WebAppListener implements ServletContextListener
{
	private static final int	NEODATIS_SERVER_PORT	= 8000;
	private static ODBServer	server;
	private static boolean		isOk;
	private static Properties	properties;

	/**
	 * Default constructor.
	 */
	public WebAppListener()
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
		cargarProperties();
	}

	private void cargarProperties()
	{
		final Properties p = new Properties();
		System.out.println("Cargando Properties");
		try {
		p.load(new FileInputStream(new File(System.getProperty("ext.config"))));		
		properties = p;
		System.out.println("Properties cargadas OK.");
		System.out.println(properties);
		} catch (Exception e) {
		e.printStackTrace();
		}
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

	public static boolean isOk()
	{		
		return isOk;
	}

	public static ODBServer getServer()
	{		
		return server;
	}
	
	public static String getProperty(String key){
		return properties.getProperty(key);
	}

}
