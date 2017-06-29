package com.EyVdeSW.TP.presentacion;

import com.EyVdeSW.TP.domainModel.Duracion;
import com.EyVdeSW.TP.services.DuracionService;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SuppressWarnings("serial")
public class PantallaDuracionAnalistaTecnico extends VerticalLayout implements View {
	protected static final String NAME = "pantallaDuracionAnalistaTecnico";

	private DuracionService duracionService = DuracionService.getDuracionService();
	
	

	public PantallaDuracionAnalistaTecnico() {
		BeanItemContainer<Duracion>duraciones = new BeanItemContainer<>(Duracion.class);
		duracionService.traerDuraciones().forEach(duracion -> {
			duraciones.addBean(duracion);
		});
		
		Label titulo = new Label("Gestión de Duraciones");
		titulo.setStyleName(ValoTheme.LABEL_H1);
		HorizontalLayout hlTitulo = new HorizontalLayout(titulo);
		addComponent(hlTitulo);
		setComponentAlignment(hlTitulo, Alignment.MIDDLE_CENTER);

		ComboBox d = new ComboBox("Duraciones", duraciones);
		
		TextField tfDescripcion = new TextField("Descripcion");
		TextField tfCantidadDias= new TextField("Cantidad dias");
		this.addComponent(d);
		
		
		// TODO binding a grid.
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub

	}

}
