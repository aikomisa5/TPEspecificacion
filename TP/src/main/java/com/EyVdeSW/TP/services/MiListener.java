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
public class MiListener implements ServletContextListener {
	private static final int NEODATIS_SERVER_PORT = 10001;
	private static ODBServer server;
	private static boolean isOk;

	/**
	 * Default constructor.
	 */
	private MiListener() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("Bajando NeoDatis server");
		if (server != null) {
			try {
				server.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent arg0) {
		try {
			System.out.println("Iniciando server Neodatis");
			server = ODBFactory.openServer(NEODATIS_SERVER_PORT);
			server.startServer(true);
			isOk = true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
		}
	}

	public static ODBServer getServer() {		
		return server;
	}

	public static boolean isOk() {
		return isOk;
	}

}
