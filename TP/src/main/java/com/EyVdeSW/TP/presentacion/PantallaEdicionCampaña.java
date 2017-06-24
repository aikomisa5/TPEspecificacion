package com.EyVdeSW.TP.presentacion;

import com.EyVdeSW.TP.services.CampañaService;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class PantallaEdicionCampaña extends VerticalLayout implements View {

	protected static final String NAME = "pantallaEdicionCampaña";
	
	CampañaService campañaService = CampañaService.getCampañaService();
	
	public PantallaEdicionCampaña(){
		
		TextField tfNombre = new TextField("Nombre Campaña");
		TextArea taDescripcion = new TextArea("Descripción Campaña");
		TextField tfNombreMensaje = new TextField("Nombre Mensaje");
		TextArea taTextoMensaje = new TextArea("Texto del Mensaje");
		
		
		
		
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}

	
}
