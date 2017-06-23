package com.EyVdeSW.TP.presentacion;

import com.EyVdeSW.TP.services.DuracionService;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;

import com.vaadin.ui.Alignment;
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
		Label titulo = new Label("Gestión de Duraciones");
		titulo.setStyleName(ValoTheme.LABEL_H1);
		HorizontalLayout hlTitulo = new HorizontalLayout(titulo);
		addComponent(hlTitulo);
		setComponentAlignment(hlTitulo, Alignment.MIDDLE_CENTER);

		TextField tfDescripcion = new TextField("Descripcion");
		// TODO binding a grid.
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub

	}

}
