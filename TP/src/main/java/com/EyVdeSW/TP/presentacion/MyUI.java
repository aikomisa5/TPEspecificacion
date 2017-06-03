package com.EyVdeSW.TP.presentacion;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;

import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * This UI is the application entry point. A UI may either represent a browser
 * window (or tab) or some part of a html page where a Vaadin application is
 * embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is
 * intended to be overridden to add component to the user interface and
 * initialize non-component functionality.
 */
@SuppressWarnings("serial")
@Theme("valo")
public class MyUI extends UI
{
	private Navigator navigator;
	@Override
	protected void init(VaadinRequest vaadinRequest)
	{
		
		final VerticalLayout layout = new VerticalLayout();		
		setContent(layout);
		getPage().setTitle("TP Especificaciónes y verificación de Software");
		// Creamos el navegador
		navigator = new Navigator(this, this);
		// Y creamos y registramos las views (pantallas)		
		navigator.addView("", new PantallaCampañas());		
	}

	@WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
	@VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
	public static class MyUIServlet extends VaadinServlet
	{
	}
}
