package com.EyVdeSW.TP.presentacion;

import com.EyVdeSW.TP.domainModel.Campania;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.PopupView;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalLayout;

public class PopupCampaña extends PopupView {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String descripcionBreve;
	private VerticalLayout mainLayout;
	private Label nombre = new Label();
	private TextArea descripcion= new TextArea("Descripción");
	private TextArea mensaje= new TextArea("Mensaje");
	private Label estado= new Label();
	
	public PopupCampaña(Campania campaña, VerticalLayout popupContent){
		super("",popupContent);
		this.mainLayout = new VerticalLayout();
		this.mainLayout.addComponents(nombre,descripcion,mensaje,estado);		
		updateDatos(campaña);
		this.addContextClickListener(click -> this.setPopupVisible(true));
		this.setHideOnMouseOut(false);
		this.setWidth("600px");
		popupContent.addComponent(this.mainLayout);
		popupContent.setComponentAlignment(this.mainLayout, Alignment.MIDDLE_CENTER);
		popupContent.setWidth("500px");
		popupContent.setDefaultComponentAlignment(Alignment.TOP_CENTER);
		nombre.setWidth("90%");
		descripcion.setWidth("90%");
		mensaje.setWidth("90%");
		estado.setWidth("90%");
		descripcion.setReadOnly(true);
		mensaje.setReadOnly(true);		
	}

	public void updateDatos(Campania campaña) {
		descripcionBreve = campaña.getNombre() + " Estado: " + campaña.getEstado().toString();
		this.setCaption(descripcionBreve);
		nombre.setCaption("Nombre: " + campaña.getNombre());
		descripcion.setValue(campaña.getDescripcion());
		mensaje.setValue(campaña.getMensaje().getTextoMensaje());
		estado.setCaption("Estado: " + campaña.getEstado().toString());		
	}

}
