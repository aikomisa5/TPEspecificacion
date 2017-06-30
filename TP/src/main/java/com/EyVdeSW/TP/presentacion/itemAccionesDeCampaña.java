package com.EyVdeSW.TP.presentacion;

import java.util.List;

import com.EyVdeSW.TP.domainModel.AccionPublicitaria;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class itemAccionesDeCampaña extends HorizontalLayout {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Button borrarAccion;
	private Button editarAccion;
	private Label descripcionBreve;
	private AccionPublicitaria accion;

	public itemAccionesDeCampaña(AccionPublicitaria accion, List<AccionPublicitaria> accionesPublicitariasParaAsociar, VerticalLayout layoutAccionesDeCampaña) {
		this.accion = accion;
		this.setMargin(false);		
		borrarAccion = new Button();
		borrarAccion.setIcon(FontAwesome.TRASH);
		borrarAccion.setStyleName(ValoTheme.BUTTON_DANGER + " " + ValoTheme.BUTTON_QUIET + " " + ValoTheme.BUTTON_TINY);
		borrarAccion.setDescription("Borrar esta Acción");
		borrarAccion.addClickListener(click -> {
			accionesPublicitariasParaAsociar.remove(accion);			
			layoutAccionesDeCampaña.removeComponent(this);
		});
		
		editarAccion = new Button();
		editarAccion.setIcon(FontAwesome.EDIT);
		editarAccion.setDescription("Editar esta Acción");
		editarAccion.setStyleName(ValoTheme.BUTTON_FRIENDLY + " " + ValoTheme.BUTTON_QUIET + " " + ValoTheme.BUTTON_TINY);
		editarAccion.addClickListener(click -> {
			new SubMenuAccionesPublicitariasPersonalizadas(layoutAccionesDeCampaña, accionesPublicitariasParaAsociar, this);
		});

		descripcionBreve = new Label();		
		descripcionBreve.setStyleName(ValoTheme.LABEL_NO_MARGIN);
		descripcionBreve.setCaption("To: " + accion.getDestinatario() + " Header: " + accion.getTitulo());		
		addComponents(borrarAccion,editarAccion, descripcionBreve);
		setWidth("95%");
	}
	
	public AccionPublicitaria getAccion(){
		return accion;
	}
	
	public void updateItem(AccionPublicitaria accion)
	{
		descripcionBreve.setCaption("To: " + accion.getDestinatario() + " Header: " + accion.getTitulo());
	}
}
