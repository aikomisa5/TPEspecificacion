package com.EyVdeSW.TP.presentacion;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewChangeListener;
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
	
	protected static final String TAGSVIEW = "gestionTags";
	protected static final String CAMPAÑASVIEW = "gestionCampañas";
	protected static final String REGISTROVIEW = "altaRegistro";
	//protected static final String MAINVIEW = "main";
	
	@Override
	protected void init(VaadinRequest vaadinRequest)
	{
		
		final VerticalLayout layout = new VerticalLayout();		
		setContent(layout);
		getPage().setTitle("TP Especificaciónes y verificación de Software");
		// Creamos el navegador
		navigator = new Navigator(this, this);
		// Y creamos y registramos las views (pantallas)
		
		navigator.addView(CAMPAÑASVIEW, new PantallaCampañas());	
		navigator.addView(PantallaTags.NAME, new PantallaTags());	
		navigator.addView(REGISTROVIEW, new PantallaRegistro());
		navigator.addView(PantallaTags.NAME, new PantallaTags());
		//navigator.addView("", new PantallaMain());	
		
		 // The initial log view where the user can login to the application
        //
        getNavigator().addView(SimpleLoginView.NAME, SimpleLoginView.class);//

        //
        // Add the main view of the application
        //
        getNavigator().addView(SimpleLoginMainView.NAME,
                SimpleLoginMainView.class);

        //
        // We use a view change handler to ensure the user is always redirected
        // to the login view if the user is not logged in.
        //
        
        
        getNavigator().addViewChangeListener(new ViewChangeListener() {

            @Override
            public boolean beforeViewChange(ViewChangeEvent event) {

                // Check if a user has logged in
                boolean isLoggedIn = getSession().getAttribute("user") != null;
                boolean isLoginView = event.getNewView() instanceof SimpleLoginView;
                boolean isRegistroView = event.getNewView() instanceof PantallaRegistro;

                if (!isLoggedIn && !isLoginView && !isRegistroView) {
                    // Redirect to login view always if a user has not yet
                    // logged in
                    getNavigator().navigateTo(SimpleLoginView.NAME);
                    return false;

                } else if (isLoggedIn && isLoginView) {
                    // If someone tries to access to login view while logged in,
                    // then cancel
                    return false;
                }
                
                else if (!isLoggedIn && isRegistroView) {
                	getNavigator().navigateTo(PantallaRegistro.NAME);
                	return true;
                }

                return true;
            }

            @Override
            public void afterViewChange(ViewChangeEvent event) {

            }
        }); 
    
	
	}

	@WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
	@VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
	public static class MyUIServlet extends VaadinServlet
	{
	}
}
