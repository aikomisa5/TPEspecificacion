package com.EyVdeSW.TP.presentacion;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

@SuppressWarnings("serial")
public class DefaultMenuCliente  extends HorizontalLayout implements View {
	private Label text = new Label();
	private Button logout = new Button("Salir");
	private Button campañas = new Button("Creacion de Campaña");
	private Button misCampañas = new Button("Mis Campañas");

	public DefaultMenuCliente() {
		setSpacing(true);
		
		addComponents(text, logout, campañas, misCampañas);
		
		misCampañas.addClickListener(click -> getUI().getNavigator().navigateTo(PantallaMisCampañas.NAME));
		campañas.addClickListener(click -> getUI().getNavigator().navigateTo(PantallaCampañaCliente.NAME));
		logout.addClickListener(event -> {
			// "Logout" the user
			getSession().setAttribute("user", null);
			// Refresh this view, should redirect to login view
			getUI().getNavigator().navigateTo(PantallaLogin.NAME);			
		});
	}

	@Override
	public void enter(ViewChangeEvent event) {
	}
}