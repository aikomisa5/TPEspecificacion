package com.EyVdeSW.TP.presentacion;

import java.util.ArrayList;

import java.util.List;

import com.EyVdeSW.TP.domainModel.Campania;
import com.EyVdeSW.TP.domainModel.Usuario;
import com.EyVdeSW.TP.services.CampañaService;
import com.EyVdeSW.TP.services.UsuarioService;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SuppressWarnings("serial")
public class PantallaMisCampañas extends VerticalLayout implements View {

	protected static final String NAME = "misCampanias";
	
	CampañaService campañaService = CampañaService.getCampañaService();
	UsuarioService usuarioService = UsuarioService.getUsuarioService();
	List <Campania> campañas = new ArrayList<>();
	Usuario usuario;
	String username = "";	

	private VerticalLayout contenedorCampañas;
	
	public PantallaMisCampañas(){
		

		Label titulo = new Label("Mis Campañas");
		titulo.setStyleName(ValoTheme.LABEL_H1);
		
		
		
		HorizontalLayout hlTitulo = new HorizontalLayout(titulo);
		addComponent(hlTitulo);
		setComponentAlignment(hlTitulo, Alignment.MIDDLE_CENTER);				
		contenedorCampañas =  new VerticalLayout();	
		contenedorCampañas.setSpacing(true);
		contenedorCampañas.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
		addComponent(contenedorCampañas);		
		setComponentAlignment(contenedorCampañas, Alignment.MIDDLE_CENTER);
				
		
	}

	@Override
	public void enter(ViewChangeEvent event) {		
		String usernameMail = String.valueOf(getSession().getAttribute("user"));
		username=usernameMail;
		usuario = usuarioService.getUsuarioPorMail(username);
		campañas = campañaService.getCampañasDeUsuario(usuario);		
		contenedorCampañas.removeAllComponents();
		campañas.forEach(campaña -> contenedorCampañas.addComponent(new itemMisCampañas(campaña)));
	}

	
	
}
