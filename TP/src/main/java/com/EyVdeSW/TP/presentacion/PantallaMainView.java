package com.EyVdeSW.TP.presentacion;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SuppressWarnings("serial")
public class PantallaMainView extends CustomComponent implements View {

	public static final String NAME = "";

	Label text = new Label();	
	public PantallaMainView() {
		
		
		setCompositionRoot(new VerticalLayout(text));

		text.setStyleName(ValoTheme.LABEL_H3);
		

	}

	@Override
	public void enter(ViewChangeEvent event) {
		// Get the user name from the session
		String username = String.valueOf(getSession().getAttribute("user"));

		// And show the username
		text.setValue("Hello " + username);
		((MyUI) getUI()).showMenu();
	}

}
