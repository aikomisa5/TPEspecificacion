package com.EyVdeSW.TP.presentacion;

import com.EyVdeSW.TP.domainModel.Tag;
import com.EyVdeSW.TP.services.DuracionService;
import com.EyVdeSW.TP.services.TagService;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.HierarchicalContainer;
import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.themes.ValoTheme;

@SuppressWarnings("serial")
public class PantallaDuracionAnalistaTecnico extends VerticalLayout implements View {
	protected static final String NAME = "pantallaDuracionAnalistaTecnico";

	private DuracionService duracionService = DuracionService.getDuracionService();

	Button logout = new Button("Logout");

	public PantallaDuracionAnalistaTecnico() {
		Label titulo = new Label("Gestión de Duraciones");
		titulo.setStyleName(ValoTheme.LABEL_H1);
		HorizontalLayout hlTitulo = new HorizontalLayout(titulo);
		addComponent(hlTitulo);
		setComponentAlignment(hlTitulo, Alignment.MIDDLE_CENTER);

		TextField tfDescripcion = new TextField("Descripcion");
		//	TODO binding a grid.
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub

	}



}
