package com.EyVdeSW.TP.presentacion;

import com.EyVdeSW.TP.domainModel.Tag;
import com.EyVdeSW.TP.services.CampañaService;
import com.EyVdeSW.TP.services.TagService;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.themes.ValoTheme;

public class PantallaCampañas extends VerticalLayout implements View {

	protected static final String NAME = "";

	private CampañaService campañaService = CampañaService.getCampañaService();

	public PantallaCampañas() {
		Label titulo = new Label("Gestión de Campañas");
		titulo.setStyleName(ValoTheme.LABEL_H1);
		HorizontalLayout hlTitulo = new HorizontalLayout(titulo);
		addComponent(hlTitulo);
		setComponentAlignment(hlTitulo, Alignment.MIDDLE_CENTER);

		
		TextField tfNombre = new TextField("Nombre Campaña");
		TextField tfDescripcion = new TextField("Descripción Campaña");
		
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
		
		
		Button btnAgregar = new Button("Crear Campaña");

		btnAgregar.setStyleName(ValoTheme.BUTTON_PRIMARY);
		btnAgregar.setClickShortcut(ShortcutAction.KeyCode.ENTER);
		
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
	
		
		HorizontalLayout hlBotones = new HorizontalLayout(btnAgregar);
		hlBotones.setSpacing(true);

		FormLayout flFormTags = new FormLayout(tfNombre,tfDescripcion,DuracionCampaña);
		flFormTags.setSpacing(true);
		VerticalLayout vlFormTags = new VerticalLayout(flFormTags, hlBotones);
		vlFormTags.setSpacing(true);

	
		HorizontalLayout hlPrincipal = new HorizontalLayout(vlFormTags);
		hlPrincipal.setSpacing(true);
		hlPrincipal.setWidth("80%");
		addComponent(hlPrincipal);
		setComponentAlignment(hlPrincipal, Alignment.TOP_CENTER);
		setMargin(true);

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

