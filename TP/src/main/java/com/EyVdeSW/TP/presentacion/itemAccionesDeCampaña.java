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

public class itemAccionesDeCampaña extends HorizontalLayout {
	private Button borrarAccion;
	private Label descripcionBreve;	
	private AccionPublicitariaService accionService = AccionPublicitariaService.getAccionPublicitariaService();

	public itemAccionesDeCampaña(AccionPublicitaria accion, List<AccionPublicitaria> accionesPublicitariasParaAsociar) {
		this.setMargin(false);		
		borrarAccion = new Button();
		borrarAccion.setIcon(FontAwesome.TRASH);
		borrarAccion.setStyleName(ValoTheme.BUTTON_DANGER + " " + ValoTheme.BUTTON_QUIET + " " + ValoTheme.BUTTON_TINY);
		borrarAccion.addClickListener(click -> {
			accionesPublicitariasParaAsociar.remove(accion);
			this.removeAllComponents();
		});

		descripcionBreve = new Label();		
		descripcionBreve.setStyleName(ValoTheme.LABEL_NO_MARGIN);
		descripcionBreve.setCaption("To: " + accion.getDestinatario() + " Header: " + accion.getTitulo());		
		addComponents(borrarAccion, descripcionBreve);
		setWidth("95%");
	}
}
