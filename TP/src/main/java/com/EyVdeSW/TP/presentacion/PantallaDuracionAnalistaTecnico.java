package com.EyVdeSW.TP.presentacion;

import com.EyVdeSW.TP.domainModel.Duracion;
import com.EyVdeSW.TP.services.DuracionService;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
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

		Tree arbolDuraciones = new Tree("Duraciones", duraciones);
		
		TextField tfDescripcion = new TextField("Descripcion");
		TextField tfCantidadDias= new TextField("Cantidad dias");
		Button guardar = new Button("Guardar");
		guardar.addClickListener( event -> {
			if(tfDescripcion.getValue() == null || tfCantidadDias.getValue() == null){
				Notification.show("El nombre esta vacío!", Type.WARNING_MESSAGE);
			}else{
				try {
					duracionService.guardar(tfDescripcion.getValue(), Integer.parseInt(tfCantidadDias.getValue()));
					Notification.show("Duracion Guardada", Type.TRAY_NOTIFICATION);
					limpiarActualizar(arbolDuraciones, tfDescripcion, tfCantidadDias, duraciones);
				} catch (Exception e) {
					e.printStackTrace();
					Notification.show("Algun dato es erroneo", Type.WARNING_MESSAGE);
				}
			}
		});
		
		
		Button eliminar = new Button("Eliminar");
		eliminar.addClickListener(event -> {
			if(tfDescripcion.getValue() == null){
				Notification.show("El nombre esta vacío!", Type.WARNING_MESSAGE);
			}else{
				if(duracionService.existe(tfDescripcion.getValue())){
					duracionService.borrar(tfDescripcion.getValue());
					Notification.show("El nombre esta vacío!", Type.WARNING_MESSAGE);
					limpiarActualizar(arbolDuraciones, tfDescripcion, tfCantidadDias, duraciones);
				}else{
					Notification.show("No existe dicha duracion", Type.WARNING_MESSAGE);
				}
				
			}
		});
		
		Button editar = new Button("Editar");
		
		
		arbolDuraciones.addItemClickListener(event ->{
			Duracion duracion = duracionService.getDuracionPorDescripcion(event.getItemId().toString());
			tfDescripcion.setValue(duracion.getDescripcion());
			tfCantidadDias.setValue(Integer.toString(duracion.getDuracion()));
		});
		
		HorizontalLayout hl = new HorizontalLayout(arbolDuraciones,tfDescripcion, tfCantidadDias);
		
		HorizontalLayout hl1 = new HorizontalLayout(guardar, editar, eliminar);
		hl.setSpacing(true);
		hl1.setSpacing(true);
		VerticalLayout vl = new VerticalLayout(hl, hl1);
		vl.setSpacing(true);
	
		
		this.addComponent(vl);
		setComponentAlignment(vl, Alignment.TOP_CENTER);
		this.setSpacing(true);
		// TODO binding a grid.
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub

	}
	
	public void limpiarActualizar(Tree tree, TextField tf1, TextField tf2, BeanItemContainer<Duracion>duraciones){
		tf1.clear();
		tf2.clear();
		tree.removeAllItems();
		duracionService.traerDuraciones().forEach(duracion -> {
			duraciones.addBean(duracion);
		});
		tree.setContainerDataSource(duraciones);
		
	}

}
