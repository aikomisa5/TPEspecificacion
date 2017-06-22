package com.EyVdeSW.TP.presentacion;

import java.util.Date;

import com.EyVdeSW.TP.services.CampañaService;
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
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.themes.ValoTheme;

@SuppressWarnings("serial")
public class PantallaCampañasAnalistaTecnico extends VerticalLayout implements View {

	protected static final String NAME = "pantallaCampañas";

	private CampañaService campañaService = CampañaService.getCampañaService();
	
	Button logout = new Button("Logout");
	
	Date fechaInicio = new Date();
	
	public PantallaCampañasAnalistaTecnico() {
		Label titulo = new Label("Gestión de Campañas");
		titulo.setStyleName(ValoTheme.LABEL_H1);
		HorizontalLayout hlTitulo = new HorizontalLayout(titulo);
		addComponent(hlTitulo);
		setComponentAlignment(hlTitulo, Alignment.MIDDLE_CENTER);

		
		TextField tfNombre = new TextField("Nombre Campaña");
		TextArea taDescripcion = new TextArea("Descripción Campaña");
		TextField tfNombreMensaje = new TextField("Nombre Mensaje");
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
		

		//Calendario
		
		// Create a DateField with the default style
		DateField date = new DateField();

		// Set the date to present
		date.setValue(new Date());
		
		date.setDescription("Calendario para elegir la fecha de inicio de la campaña");
					
		fechaInicio=date.getValue();
				
		System.out.print(fechaInicio.toString());
		
		
		/*
		BeanItemContainer<Campaña> tags = new BeanItemContainer<Campaña>(Campaña.class);
		tagService.traerTodos().forEach(tag -> tags.addBean(tag));
		*/
		
		
		Button btnCrear = new Button("Crear Campaña");

		btnCrear.setStyleName(ValoTheme.BUTTON_PRIMARY);
		btnCrear.setClickShortcut(ShortcutAction.KeyCode.ENTER);
		btnCrear.addClickListener(e -> {
			if (tfNombre.getValue() == "") {
				Notification.show("El nombre está vacío!", Type.WARNING_MESSAGE);
			} 
			
			if (taDescripcion.getData().toString() == "") {
				Notification.show("La descripción está vacía!", Type.WARNING_MESSAGE);
			}
			
			if (tfNombreMensaje.getValue() == ""){
				Notification.show("El nombre del mensaje está vacío!", Type.WARNING_MESSAGE);
			}
			
			if (taTextoMensaje.getData().toString() == ""){
				Notification.show("El texto del mensaje está vacío!", Type.WARNING_MESSAGE);
			}
			else {
				//XXX fix me please
//				campañaService.guardar(tfNombre.getValue(),taDescripcion.getData().toString(),
//						tfNombreMensaje.getValue(),taTextoMensaje.getData().toString(),fechaInicio, null);
				Notification.show("Campaña Guardado", Type.TRAY_NOTIFICATION);
				limpiarCampos(tfNombre, taDescripcion, tfNombreMensaje, taTextoMensaje);
			}
			tfNombre.focus();
		});

	
	
		HorizontalLayout hlBotones = new HorizontalLayout(btnCrear);
		hlBotones.setSpacing(true);

		FormLayout flFormCampos = new FormLayout(tfNombre,taDescripcion,tfNombreMensaje,taTextoMensaje,DuracionCampaña,date);
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

	private void limpiarCampos(TextField textFieldNombre, TextArea textAreaDescripcion, 
			TextField textFieldNombreMensaje, TextArea textAreaTextoMensaje) {
		textFieldNombre.clear();
		textAreaDescripcion.clear();
		textFieldNombreMensaje.clear();
		textAreaTextoMensaje.clear();
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub

	}

	
	
	
}

