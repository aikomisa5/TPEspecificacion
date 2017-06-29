package com.EyVdeSW.TP.presentacion;

import com.EyVdeSW.TP.domainModel.Campania;
import com.EyVdeSW.TP.domainModel.Campania.EstadoCampania;
import com.EyVdeSW.TP.services.CampañaService;
import com.EyVdeSW.TP.services.AccionPublicitariaScheduler;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.themes.ValoTheme;

public class itemMisCampañas extends HorizontalLayout {
	private Campania campaña;
	private Label lblCampaña;	
	private Button editar;
	private Button pagar;
	private Button cancelar;
	private Button borrar;
	private CampañaService campañaService = CampañaService.getCampañaService();
	private AccionPublicitariaScheduler ms = AccionPublicitariaScheduler.getMailScheduler();

	public itemMisCampañas(Campania campaña) {		
		setWidth("700px");
		this.campaña = campaña;
		lblCampaña = new Label();
		updateLblCampaña(campaña);
		
		
		editar = new Button();
		editar.setIcon(FontAwesome.EDIT);		
		editar.setDescription("Editar esta campaña");
		editar.addClickListener(e ->{
			getUI().getNavigator().navigateTo(PantallaCampañaCliente.NAME + "/"+ campaña.getIdCampania().toString());
			
		});
		
		pagar = new Button();
		pagar.setIcon(FontAwesome.MONEY);
		pagar.setDescription("Pagar esta campaña");
		pagar.setStyleName(ValoTheme.BUTTON_FRIENDLY);
		pagar.addClickListener(e ->{
			campaña.setEstado(EstadoCampania.PLANIFICADA);
			campañaService.modificar(campaña.getIdCampania(), campaña);
			ms.agregarAccionesDeCampaña(campaña);
			updateLblCampaña(campaña);
			//TODO integrar a scheduler.
			Notification.show("Campaña Planificada", Type.TRAY_NOTIFICATION);
			establecerVisibilidadBotones();
		});
		
		
		cancelar = new Button();
		cancelar.setIcon(FontAwesome.HAND_STOP_O);
		cancelar.setDescription("Cancelar esta campaña");
		cancelar.setStyleName(ValoTheme.BUTTON_DANGER);
		cancelar.addClickListener(e ->{
			campaña.setEstado(EstadoCampania.CANCELADA);
			campañaService.modificar(campaña.getIdCampania(), campaña);
			ms.cancelarCampaña(campaña);
			updateLblCampaña(campaña);
			//TODO integrar a scheduler.
			Notification.show("Campaña Cancelada", Type.TRAY_NOTIFICATION);
			establecerVisibilidadBotones();
		});
		
		
		borrar = new Button();
		borrar.setIcon(FontAwesome.TRASH);
		borrar.setDescription("Borrar esta campaña");
		borrar.setStyleName(ValoTheme.BUTTON_DANGER);
		borrar.addClickListener(e ->{
			campañaService.borrar(campaña.getIdCampania());
			//TODO integrar a scheduler. 
			Notification.show("Campaña Borrada", Type.TRAY_NOTIFICATION);
			this.removeAllComponents();
		});
		
		lblCampaña.setWidth("100%");		
		addComponents(lblCampaña, editar, pagar, cancelar, borrar);
		setExpandRatio(lblCampaña, 1.0f);
		
		establecerVisibilidadBotones();
	}

	private void establecerVisibilidadBotones() {
		switch (campaña.getEstado()){
		case CANCELADA:
			editar.setEnabled(false);
			pagar.setEnabled(false);
			cancelar.setEnabled(false);
			borrar.setEnabled(false);
			break;
		case PLANIFICADA:
			editar.setEnabled(false);
			pagar.setEnabled(false);
			borrar.setEnabled(false);
			cancelar.setEnabled(true);
			break;
		case PRELIMINAR:
			cancelar.setEnabled(false);
			break;
		case FINALIZADA:
			editar.setEnabled(false);
			pagar.setEnabled(false);
			cancelar.setEnabled(false);
			borrar.setEnabled(false);
			break;
		default:
			break;
		}
	}

	private void updateLblCampaña(Campania campaña) {
		lblCampaña.setCaption("Nombre: " + campaña.getNombre() + " Descripción: " + campaña.getDescripcion() + "Estado: " + campaña.getEstado());
	}
}
