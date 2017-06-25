package com.EyVdeSW.TP.presentacion;

import com.EyVdeSW.TP.domainModel.Campania;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.themes.ValoTheme;

public class itemMisCampañas extends HorizontalLayout {
	private Campania campaña;
	private Label lblCampaña;	
	private Button editar;
	private Button pagar;
	private Button cancelar;
	private Button borrar;

	public itemMisCampañas(Campania campaña) {		
		setWidth("700px");
		this.campaña = campaña;
		lblCampaña = new Label();
		lblCampaña.setCaption("Nombre: " + campaña.getNombre() + " Descripción: " + campaña.getDescripcion());
		
		
		editar = new Button();
		editar.setIcon(FontAwesome.EDIT);		
		editar.setDescription("Editar esta campaña");		
		
		pagar = new Button();
		pagar.setIcon(FontAwesome.MONEY);
		pagar.setDescription("Pagar esta campaña");
		pagar.setStyleName(ValoTheme.BUTTON_FRIENDLY);
		
		cancelar = new Button();
		cancelar.setIcon(FontAwesome.HAND_STOP_O);
		cancelar.setDescription("Cancelar esta campaña");
		cancelar.setStyleName(ValoTheme.BUTTON_DANGER);
		
		borrar = new Button();
		borrar.setIcon(FontAwesome.TRASH);
		borrar.setDescription("Borrar esta campaña");
		borrar.setStyleName(ValoTheme.BUTTON_DANGER);
		
		lblCampaña.setWidth("100%");		
		addComponents(lblCampaña, editar, pagar, cancelar, borrar);
		setExpandRatio(lblCampaña, 1.0f);
	}
}
