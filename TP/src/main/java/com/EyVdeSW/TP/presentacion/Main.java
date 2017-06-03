package com.EyVdeSW.TP.presentacion;

import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.themes.ValoTheme;
import com.vaadin.ui.VerticalLayout;

public class Main extends VerticalLayout implements View{
	
	protected static final String NAME = "";
	
	public Main() {
		
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
			
		
		Button buttonCampaña = new Button("Ir a Creación de Campañas");
				buttonCampaña.setStyleName(ValoTheme.BUTTON_PRIMARY);
				buttonCampaña.setClickShortcut(ShortcutAction.KeyCode.ENTER);
				buttonCampaña.addClickListener(e -> {
			getUI().getNavigator().navigateTo(MyUI.CAMPAÑASVIEW);
			
		});
			addComponent(buttonCampaña);
			setComponentAlignment(buttonCampaña, Alignment.MIDDLE_CENTER);
			

		}
		
		@Override
		public void enter(ViewChangeEvent event) {
		//si quiero código que se ejecute al ingresar.
		}
}
