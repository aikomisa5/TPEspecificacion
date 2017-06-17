package com.EyVdeSW.TP.presentacion;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

public class DefaultMenuAnalistaComercial  extends HorizontalLayout implements View {
	private Label text = new Label();
	private Button logout = new Button("Logout");
	private Button estadisticas = new Button("Estadisticas");
	private Button reporteAcciones = new Button("Reporte Acciones Personalizadas");
	private Button reporteVentas = new Button("Reporte de Ventas");

	public DefaultMenuAnalistaComercial() {
		setSpacing(true);
		
		addComponents(text, logout, estadisticas, reporteAcciones, reporteVentas);

		estadisticas.addClickListener(click -> getUI().getNavigator().navigateTo(PantallaEstadisticasAnalistaComercial.NAME));
		reporteAcciones.addClickListener(click -> getUI().getNavigator().navigateTo(PantallaReporteAccionesPersonalizadasAnalistaComercial.NAME));
		reporteVentas.addClickListener(click -> getUI().getNavigator().navigateTo(PantallaReporteDeVentasAnalistaComercial.NAME));
		logout.addClickListener(event -> {
			// "Logout" the user
			getSession().setAttribute("user", null);
			// Refresh this view, should redirect to login view
			getUI().getNavigator().navigateTo(PantallaLogin.NAME);			
		});
	}

	@Override
	public void enter(ViewChangeEvent event) {
	}


}
