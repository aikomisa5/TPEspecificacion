package com.EyVdeSW.TP.presentacion;

import java.util.List;

import com.EyVdeSW.TP.domainModel.AccionPublicitaria;
import com.EyVdeSW.TP.services.AccionPublicitariaService;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class itemAccionesDeCampaña extends HorizontalLayout{
	private Button borrarAccion;
	private Label destinatario;
	private Label titulo;
	private	AccionPublicitariaService accionService = AccionPublicitariaService.getAccionPublicitariaService(); 
	
	public itemAccionesDeCampaña(AccionPublicitaria accion, List<AccionPublicitaria> accionesPublicitariasParaAsociar) {		
		
		borrarAccion = new Button();
		borrarAccion.setIcon(FontAwesome.TRASH);
		borrarAccion.setStyleName(ValoTheme.BUTTON_DANGER +" "+ValoTheme.BUTTON_QUIET);
		borrarAccion.addClickListener(click -> {
			accionesPublicitariasParaAsociar.remove(accion);
			this.removeAllComponents();
			});
		
		
		destinatario = new Label();
		destinatario.setCaption("To: " + accion.getDestinatario());
		titulo = new Label();
		titulo.setCaption("Header: " +accion.getTitulo());
		titulo.setStyleName(ValoTheme.LABEL_SMALL);
		VerticalLayout vlDescripcion = new VerticalLayout(destinatario,titulo);		
		addComponents(borrarAccion,vlDescripcion);		
		setWidth("95%");
	}
}
