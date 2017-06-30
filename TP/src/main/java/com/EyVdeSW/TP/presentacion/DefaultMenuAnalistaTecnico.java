package com.EyVdeSW.TP.presentacion;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;


public class DefaultMenuAnalistaTecnico extends HorizontalLayout implements View {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Label text = new Label();
	private Button logout = new Button("Logout");
	private Button tags = new Button("Tags");
	private Button duraciones = new Button("Duraciones");

	public DefaultMenuAnalistaTecnico() {
		setSpacing(true);
		
		addComponents(text, logout, tags, duraciones);

		tags.addClickListener(click -> getUI().getNavigator().navigateTo(PantallaTagsAnalistaTecnico.NAME));
		duraciones.addClickListener(click -> getUI().getNavigator().navigateTo(PantallaDuracionAnalistaTecnico.NAME));
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
