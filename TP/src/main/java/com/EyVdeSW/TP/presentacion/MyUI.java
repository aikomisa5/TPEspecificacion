package com.EyVdeSW.TP.presentacion;

import javax.servlet.annotation.WebServlet;

import com.EyVdeSW.TP.services.WebAppListener;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.HorizontalLayout;
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
	private HorizontalLayout menu;
	@Override
	protected void init(VaadinRequest vaadinRequest)
	{
		WebAppListener.iniciarScheduler();
		final VerticalLayout rootLayout = new VerticalLayout();		
		setContent(rootLayout);
		menu = new HorizontalLayout();
		//menu.addComponent(new DefaultMenuAnalistaTecnico());
		
		VerticalLayout content = new VerticalLayout();
		rootLayout.addComponent(menu);
		rootLayout.addComponent(content);
		menu.setVisible(false);
		
		
		
		getPage().setTitle("TP Especificaciones y Verificación de Software");
		// Creamos el navegador
			navigator = new Navigator(this, content);
		// Y creamos y registramos las views (pantallas)
			
		//	navigator.addView(PantallaMisCampañas.NAME, new PantallaMisCampañas());
			navigator.addView(PantallaDuracionAnalistaTecnico.NAME, new PantallaDuracionAnalistaTecnico());
		navigator.addView(PantallaTagsAnalistaTecnico.NAME, new PantallaTagsAnalistaTecnico());	
		navigator.addView(PantallaRegistro.NAME, new PantallaRegistro());
		navigator.addView(PantallaCampañaCliente.NAME, new PantallaCampañaCliente());
		navigator.addView(PantallaMisCampañas.NAME, new PantallaMisCampañas());
		
		navigator.addView(PantallaAccionesPublicitariasAnalistaTecnico.NAME, new PantallaAccionesPublicitariasAnalistaTecnico());
		navigator.addView(PantallaAsignadorDeRolesAnalistaTecnico.NAME, new PantallaAsignadorDeRolesAnalistaTecnico());
		
		//navigator.addView("", new PantallaMain());	
		
		 // The initial log view where the user can login to the application
        //
        getNavigator().addView(PantallaLogin.NAME, PantallaLogin.class);//

        //
        // Add the main view of the application
        //
        getNavigator().addView(PantallaMainView.NAME,
                PantallaMainView.class);

        //
        // We use a view change handler to ensure the user is always redirected
        // to the login view if the user is not logged in.
        //
        
        
        getNavigator().addViewChangeListener(new ViewChangeListener() {

            @Override
            public boolean beforeViewChange(ViewChangeEvent event) {

                // Check if a user has logged in
                boolean isLoggedIn = getSession().getAttribute("user") != null;
                boolean isLoginView = event.getNewView() instanceof PantallaLogin;
                boolean isRegistroView = event.getNewView() instanceof PantallaRegistro;

                if (!isLoggedIn && !isLoginView && !isRegistroView) {
                    // Redirect to login view always if a user has not yet
                    // logged in
                    navigator.navigateTo(PantallaLogin.NAME);
                    return false;

                } else if (isLoggedIn && isLoginView) {
                    // If someone tries to access to login view while logged in,
                    // then cancel
                    return false;
                }
                
                else if (!isLoggedIn && isRegistroView) {
                	//navigator.navigateTo(PantallaRegistro.NAME);
                	return true;
                }

                return true;
            }

            @Override
            public void afterViewChange(ViewChangeEvent event) {

            }
        }); 
        
	
	}
	
	public void showMenu(){
		menu.setVisible(true);
	}
	
	public void hideMenu(){
		menu.setVisible(false);
	}

	public void setMenuAnalistaTecnico(){
		menu.removeAllComponents();
		menu.addComponent(new DefaultMenuAnalistaTecnico());
	}
	
	public void setMenuCliente(){
		menu.removeAllComponents();
		menu.addComponent(new DefaultMenuCliente());
	}
	
	@WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
	@VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
	public static class MyUIServlet extends VaadinServlet
	{
	}
}
