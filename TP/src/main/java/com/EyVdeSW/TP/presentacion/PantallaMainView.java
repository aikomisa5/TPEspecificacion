package com.EyVdeSW.TP.presentacion;

import java.io.File;

import com.EyVdeSW.TP.domainModel.Usuario;
import com.EyVdeSW.TP.services.UsuarioService;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;


public class PantallaMainView extends VerticalLayout implements View {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String NAME = "";

	UsuarioService usuarioService = UsuarioService.getUsuarioService();
	
	Label text = new Label();	
	
	public PantallaMainView() {
		
		String basepath = VaadinService.getCurrent()
                .getBaseDirectory().getAbsolutePath();

		// Image as a file resource
		FileResource resource = new FileResource(new File(basepath +
		                      "/WEB-INF/images/fondo.jpg"));
		
		// Show the image in the application
		Image image = new Image("", resource);
	
				
		VerticalLayout subContent = new VerticalLayout();
		subContent.addComponent(text);
		subContent.addComponent(image);
		addComponent(subContent);

		text.setStyleName(ValoTheme.LABEL_COLORED);
		

	}

	@Override
	public void enter(ViewChangeEvent event) {
		// Get the user name from the session
		String username = String.valueOf(getSession().getAttribute("user"));
		
		Usuario usuario = usuarioService.getUsuarioPorMail(username);
		
		if (usuario.getTipoUsuario().toString().equals("CLIENTE")){
		// And show the username
		text.setValue("Hello " + username);
		((MyUI) getUI()).setMenuCliente();
		((MyUI) getUI()).showMenu();
		
		}
		
		else if (usuario.getTipoUsuario().toString().equals("ANALISTATECNICO")){
			// And show the username
			text.setValue("Hello " + username);
			((MyUI) getUI()).setMenuAnalistaTecnico();
			((MyUI) getUI()).showMenu();
			
			}
		
		
	}

}
