package com.EyVdeSW.TP.presentacion;

import com.EyVdeSW.TP.presentacion.MyUI;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;

public class SimpleLoginMainView extends CustomComponent implements View {

	public static final String NAME = "";

	Label text = new Label();

	Button logout = new Button("Logout");
	Button tags = new Button("tags");
	Button tagsConPadre = new Button("tagsconPadre");

	public SimpleLoginMainView() {
		setCompositionRoot(new CssLayout(text, logout, tags, tagsConPadre));

		tags.addClickListener(click -> getUI().getNavigator().navigateTo(MyUI.TAGSVIEW));
		tagsConPadre.addClickListener(click -> getUI().getNavigator().navigateTo(PantallaTags.NAME));

		logout.addClickListener(event -> {// Java 8

			// "Logout" the user
			getSession().setAttribute("user", null);

			// Refresh this view, should redirect to login view
			getUI().getNavigator().navigateTo(NAME);
		});

	}

	@Override
	public void enter(ViewChangeEvent event) {
		// Get the user name from the session
		String username = String.valueOf(getSession().getAttribute("user"));

		// And show the username
		text.setValue("Hello " + username);
	}

}
