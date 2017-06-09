package com.EyVdeSW.TP.presentacion;

import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.themes.ValoTheme;
import com.vaadin.ui.VerticalLayout;

public class PantallaMain extends VerticalLayout implements View{
	
	protected static final String NAME = "";
	
	public PantallaMain() {
		
		//setSizeFull();
		
		Label titulo = new Label("Pantalla Principal - Main");
		titulo.setStyleName(ValoTheme.LABEL_H1);
		HorizontalLayout hlTitulo = new HorizontalLayout(titulo);
		addComponent(hlTitulo);
		setComponentAlignment(hlTitulo, Alignment.MIDDLE_CENTER);

		Button buttonTags = new Button("Ir a Gestion de Tags");
		
		buttonTags.setStyleName(ValoTheme.BUTTON_PRIMARY);
		buttonTags.setClickShortcut(ShortcutAction.KeyCode.ENTER);
		buttonTags.addClickListener(e -> {
				getUI().getNavigator().navigateTo(MyUI.TAGSVIEW);
				
			});

			addComponent(buttonTags);
			setComponentAlignment(buttonTags, Alignment.MIDDLE_CENTER);
			
		
		Button buttonCampañas = new Button("Ir a Creación de Campañas");
				buttonCampañas.setStyleName(ValoTheme.BUTTON_PRIMARY);
				buttonCampañas.setClickShortcut(ShortcutAction.KeyCode.ENTER);
				buttonCampañas.addClickListener(e -> {
			getUI().getNavigator().navigateTo(MyUI.CAMPAÑASVIEW);
			
		});
			addComponent(buttonCampañas);
			setComponentAlignment(buttonCampañas, Alignment.MIDDLE_CENTER);
			
			VerticalLayout vlBotones = new VerticalLayout(buttonTags, buttonCampañas);
			vlBotones.setSpacing(true);
			vlBotones.setWidth("80%");
			addComponent(vlBotones);
			setComponentAlignment(vlBotones, Alignment.MIDDLE_CENTER);
			setMargin(true);

			
		}
		
		@Override
		public void enter(ViewChangeEvent event) {
		//si quiero código que se ejecute al ingresar.
		}
}
