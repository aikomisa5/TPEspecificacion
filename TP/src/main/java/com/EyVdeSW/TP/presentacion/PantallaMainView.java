package com.EyVdeSW.TP.presentacion;

import com.EyVdeSW.TP.domainModel.Usuario;
import com.EyVdeSW.TP.services.UsuarioService;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SuppressWarnings("serial")
public class PantallaMainView extends CustomComponent implements View {

	public static final String NAME = "";

	UsuarioService usuarioService = UsuarioService.getUsuarioService();
	
	Label text = new Label();	
	
	public PantallaMainView() {
		
		
		setCompositionRoot(new VerticalLayout(text));

		text.setStyleName(ValoTheme.LABEL_H3);
		

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
