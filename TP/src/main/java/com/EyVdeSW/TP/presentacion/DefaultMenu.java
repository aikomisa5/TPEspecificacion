package com.EyVdeSW.TP.presentacion;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

@SuppressWarnings("serial")
public class DefaultMenu extends HorizontalLayout implements View {
	private Label text = new Label();
	private Button logout = new Button("Logout");
	private Button tags = new Button("Tags");
	private Button campañas = new Button("Campañas");

	public DefaultMenu() {
		setSpacing(true);
		addComponents(text, logout, tags, campañas);

		tags.addClickListener(click -> getUI().getNavigator().navigateTo(PantallaTagsAnalistaTecnico.NAME));
		campañas.addClickListener(click -> getUI().getNavigator().navigateTo(PantallaCampañasAnalistaTecnico.NAME));
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
