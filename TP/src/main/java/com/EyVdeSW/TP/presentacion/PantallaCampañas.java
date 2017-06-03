package com.EyVdeSW.TP.presentacion;

import java.util.Date;


import com.EyVdeSW.TP.services.CampañaService;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.themes.ValoTheme;

public class PantallaCampañas extends VerticalLayout implements View {

	protected static final String NAME = "";

	private CampañaService campañaService = CampañaService.getCampañaService();
	
	Button logout = new Button("Logout");

	public PantallaCampañas() {
		Label titulo = new Label("Gestión de Campañas");
		titulo.setStyleName(ValoTheme.LABEL_H1);
		HorizontalLayout hlTitulo = new HorizontalLayout(titulo);
		addComponent(hlTitulo);
		setComponentAlignment(hlTitulo, Alignment.MIDDLE_CENTER);

		
		TextField tfNombre = new TextField("Nombre Campaña");
		TextArea taDescripcion = new TextArea("Descripción Campaña");
		TextArea taTextoMensaje = new TextArea("Texto del Mensaje");
		
		// Create the selection component
		ComboBox DuracionCampaña = new ComboBox("Duración de Campaña");
		// Add some items (the given ID is used as item caption)
		DuracionCampaña.addItem("Una semana");
		DuracionCampaña.addItem("Un mes");
		DuracionCampaña.addItem("Un bimestre");
		DuracionCampaña.addItem("Un semestre");
		// User may not select a "null" item
		DuracionCampaña.setNullSelectionAllowed(false);
		

		/*
		BeanItemContainer<Campaña> tags = new BeanItemContainer<Campaña>(Campaña.class);
		tagService.traerTodos().forEach(tag -> tags.addBean(tag));
		*/
		
		
		Button btnCrear = new Button("Crear Campaña");

		btnCrear.setStyleName(ValoTheme.BUTTON_PRIMARY);
		btnCrear.setClickShortcut(ShortcutAction.KeyCode.ENTER);
		
		/*
		btnAgregar.addClickListener(e -> {
			if (tfNombre.getValue() == "") {
				Notification.show("El nombre esta vacío!", Type.WARNING_MESSAGE);
			} else {
				//String tagPadre = comboBoxTag.getValue() == null ? null : comboBoxTag.getValue().toString();
				tagService.guardar(tfNombre.getValue(), tagPadre);
				Notification.show("Tag Guardado", Type.TRAY_NOTIFICATION);
				limpiarCampos(tfNombre, tags, comboBoxTag);
				

			}
			tfNombre.focus();
		});

	*/
	
		//Calendario
		
		// Create a DateField with the default style
		DateField date = new DateField();

		// Set the date to present
		date.setValue(new Date());
		
		date.setDescription("Calendario para elegir la fecha de inicio de la campaña");
			
		Date fechaInicio = new Date();
		
		fechaInicio=date.getValue();
				
		System.out.print(fechaInicio.toString());
		
		
		
		
		//addComponent(date);
		
		
		HorizontalLayout hlBotones = new HorizontalLayout(btnCrear);
		hlBotones.setSpacing(true);

		FormLayout flFormCampos = new FormLayout(tfNombre,taDescripcion,taTextoMensaje,DuracionCampaña,date);
		flFormCampos.setSpacing(true);
		
		VerticalLayout vlFormTags = new VerticalLayout(flFormCampos, hlBotones);
		vlFormTags.setSpacing(true);

	
		HorizontalLayout hlPrincipal = new HorizontalLayout(vlFormTags);
		hlPrincipal.setSpacing(true);
		hlPrincipal.setWidth("80%");
		addComponent(hlPrincipal);
		setComponentAlignment(hlPrincipal, Alignment.TOP_CENTER);
		setMargin(true);
		
		//Para volver al main principal
		addComponent(logout);		
		setComponentAlignment(logout, Alignment.BOTTOM_LEFT);
		logout.addClickListener(event -> // Java 8
			getUI().getNavigator().navigateTo(""));
		
		

	}


/*
	private void limpiarCampos(TextField textFieldTag, BeanItemContainer<Tag> tags, ComboBox comboBoxTag) {
		textFieldTag.clear();
		comboBoxTag.removeAllItems();
		System.out.println("Cantidad de elementos: " + tagService.traerTodos().size());
		tagService.traerTodos().forEach(tag -> tags.addBean(tag));
		comboBoxTag.setContainerDataSource(tags);
	} */

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub

	}

	
	
	
}

