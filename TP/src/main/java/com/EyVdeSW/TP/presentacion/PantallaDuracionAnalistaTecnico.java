package com.EyVdeSW.TP.presentacion;

import com.EyVdeSW.TP.domainModel.Duracion;
import com.EyVdeSW.TP.services.DuracionService;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.themes.ValoTheme;


public class PantallaDuracionAnalistaTecnico extends VerticalLayout implements View {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected static final String NAME = "pantallaDuracionAnalistaTecnico";

	private DuracionService duracionService = DuracionService.getDuracionService();
	private String seleccionada=null;
	
	

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
		guardar.setStyleName(ValoTheme.BUTTON_PRIMARY);
		guardar.setClickShortcut(ShortcutAction.KeyCode.ENTER);
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
		eliminar.setStyleName(ValoTheme.BUTTON_DANGER);
		eliminar.addClickListener(event -> {
			if(tfDescripcion.getValue() == null){
				Notification.show("El nombre esta vacío!", Type.WARNING_MESSAGE);
			}else{
				if(duracionService.existe(tfDescripcion.getValue())){
					duracionService.borrar(tfDescripcion.getValue());
					Notification.show("Se borró correctamente", Type.TRAY_NOTIFICATION);
					limpiarActualizar(arbolDuraciones, tfDescripcion, tfCantidadDias, duraciones);
				}else{
					Notification.show("No existe dicha duracion", Type.WARNING_MESSAGE);
				}
				
			}
		});
		
		Button editar = new Button("Editar");
		editar.addClickListener(event -> {
			if(tfDescripcion.getValue()==null){
				Notification.show("El nombre esta vacío!", Type.WARNING_MESSAGE);
			}else if(seleccionada==null){
				Notification.show("No esta seleccionada ninguna duracion", Type.WARNING_MESSAGE);
			}else{
				Duracion modificada = new Duracion(tfDescripcion.getValue(), Integer.parseInt(tfCantidadDias.getValue()));
				duracionService.modificar(seleccionada, modificada);
				Notification.show("Se modificó correctamente", Type.TRAY_NOTIFICATION);
				limpiarActualizar(arbolDuraciones, tfDescripcion, tfCantidadDias, duraciones);
			}
		});
		
		
		arbolDuraciones.addItemClickListener(event ->{
			Duracion duracion = duracionService.getDuracionPorDescripcion(event.getItemId().toString());
			tfDescripcion.setValue(duracion.getDescripcion());
			tfCantidadDias.setValue(Integer.toString(duracion.getDuracion()));
			seleccionada=duracion.getDescripcion();
		});
		
		
		HorizontalLayout hlBotones = new HorizontalLayout(guardar, editar, eliminar);
		hlBotones.setSpacing(true);

		FormLayout flFormDescripcion = new FormLayout(tfDescripcion);
		FormLayout  flFormCantidadDias = new FormLayout(tfCantidadDias);
		flFormDescripcion.setSpacing(true);
		flFormCantidadDias.setSpacing(true);
		HorizontalLayout forms= new HorizontalLayout(flFormDescripcion, flFormCantidadDias);
		forms.setSpacing(true);
		VerticalLayout vlFormDuraciones = new VerticalLayout(forms, hlBotones);
		vlFormDuraciones.setSpacing(true);

		VerticalLayout vlArbolDuraciones = new VerticalLayout(arbolDuraciones);
		HorizontalLayout hlPrincipal = new HorizontalLayout(vlArbolDuraciones, vlFormDuraciones);
		hlPrincipal.setSpacing(true);
		hlPrincipal.setWidth("80%");
		addComponent(hlPrincipal);
		setComponentAlignment(hlPrincipal, Alignment.TOP_CENTER);
		setMargin(true);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		

	}
	
	public void limpiarActualizar(Tree tree, TextField tf1, TextField tf2, BeanItemContainer<Duracion>duraciones){
		tf1.clear();
		tf2.clear();
		tree.removeAllItems();
		duracionService.traerDuraciones().forEach(duracion -> {
			duraciones.addBean(duracion);
		});
		tree.setContainerDataSource(duraciones);
		seleccionada=null;
	}

}
