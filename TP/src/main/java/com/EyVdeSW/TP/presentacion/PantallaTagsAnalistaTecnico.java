package com.EyVdeSW.TP.presentacion;

import java.util.Collection;

import com.EyVdeSW.TP.domainModel.Tag;
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
public class PantallaTagsAnalistaTecnico extends VerticalLayout implements View {
	protected static final String NAME = "pantallaTagsAnalistaTecnico";

	private TagService tagService = TagService.getTagService();

	Button logout = new Button("Logout");

	public PantallaTagsAnalistaTecnico() {
		Label titulo = new Label("Gestión de Tags");
		titulo.setStyleName(ValoTheme.LABEL_H1);
		HorizontalLayout hlTitulo = new HorizontalLayout(titulo);
		addComponent(hlTitulo);
		setComponentAlignment(hlTitulo, Alignment.MIDDLE_CENTER);

		Tree arbol = new Tree("Tags");
		cargarTree(arbol);
		expandirArbol(arbol);
		
	

		TextField tfNombre = new TextField("Nombre");
		TextField tfNombreNuevo = new TextField("Nombre nuevo");

		BeanItemContainer<Tag> tags = new BeanItemContainer<Tag>(Tag.class);
		tagService.traerTodos().forEach(tag -> tags.addBean(tag));
		ComboBox comboBoxTag = new ComboBox("Tag Padre", tags);
		ComboBox comboBoxTagNuevo = new ComboBox("Nuevo Padre", tags);

		Button btnAgregar = new Button("Agregar");

		btnAgregar.setStyleName(ValoTheme.BUTTON_PRIMARY);
		btnAgregar.setClickShortcut(ShortcutAction.KeyCode.ENTER);
		btnAgregar.addClickListener(e -> {
			if (tfNombre.getValue() == "") {
				Notification.show("El nombre esta vacío!", Type.WARNING_MESSAGE);
			} else {
				String tagPadre = comboBoxTag.getValue() == null ? null : comboBoxTag.getValue().toString();
				tagService.guardar(tfNombre.getValue(), tagPadre);
				Notification.show("Tag Guardado", Type.TRAY_NOTIFICATION);
				limpiarCampos(tfNombre, tags, comboBoxTag);
				updateTree(arbol);
			}
			tfNombre.focus();
		});

		Button btnEditar = new Button("Editar");

		btnEditar.addClickListener(e -> {
			if (tfNombre.getValue() == "" || tfNombreNuevo.getValue()==null ) {
				Notification.show("Los campos estan vacios!", Type.WARNING_MESSAGE);
			} else {
				String nuevoPadre="";
				if(comboBoxTagNuevo.getValue()!=null)
					nuevoPadre=comboBoxTagNuevo.getValue().toString();
				boolean resultado = tagService.modificar(tfNombre.getValue(), tfNombreNuevo.getValue(), nuevoPadre);
				if (resultado) {
					Notification.show("Tag editado", Type.TRAY_NOTIFICATION);
					limpiarCampos(tfNombre, tags, comboBoxTag);
					updateTree(arbol);
				} else {
					Notification.show("El nuevo nombre del tag ya existe", Type.WARNING_MESSAGE);
				}
			}
			tfNombre.focus();
		});

		Button btnBorrar = new Button("Borrar");
		btnBorrar.setStyleName(ValoTheme.BUTTON_DANGER);

		btnBorrar.addClickListener(e -> {
			if (comboBoxTag.getValue() != null) {
				tagService.borrar(comboBoxTag.getValue().toString());
				Notification.show("Tag Borrado", Type.TRAY_NOTIFICATION);
				limpiarCampos(tfNombre, tags, comboBoxTag);
				updateTree(arbol);
			}
			tfNombre.focus();
		});
		
		arbol.addItemClickListener(click -> {
			Tag t = tagService.getTagPorNombre(click.getItemId().toString());
			tfNombre.setValue(t.getNombre());
			tfNombreNuevo.setValue(t.getNombre());
			comboBoxTag.setValue(t.getPadre());
		});

		HorizontalLayout hlBotones = new HorizontalLayout(btnAgregar, btnEditar, btnBorrar);
		hlBotones.setSpacing(true);

		FormLayout flFormTags = new FormLayout(tfNombre, comboBoxTag);
		FormLayout  flFormTagsEdicion = new FormLayout(tfNombreNuevo, comboBoxTagNuevo);
		flFormTags.setSpacing(true);
		flFormTagsEdicion.setSpacing(true);
		HorizontalLayout forms= new HorizontalLayout(flFormTags, flFormTagsEdicion);
		forms.setSpacing(true);
		VerticalLayout vlFormTags = new VerticalLayout(forms, hlBotones);
		vlFormTags.setSpacing(true);

		VerticalLayout vlArbol = new VerticalLayout(arbol);
		HorizontalLayout hlPrincipal = new HorizontalLayout(vlArbol, vlFormTags);
		hlPrincipal.setSpacing(true);
		hlPrincipal.setWidth("80%");
		addComponent(hlPrincipal);
		setComponentAlignment(hlPrincipal, Alignment.TOP_CENTER);
		setMargin(true);

		// Para volver al main principal
		addComponent(logout);
		logout.addClickListener(event -> // Java 8
		getUI().getNavigator().navigateTo(""));

	}

	private void updateTree(Tree arbol) {
		arbol.removeAllItems();
		cargarTree(arbol);
		expandirArbol(arbol);
	}

	private void limpiarCampos(TextField textFieldTag, BeanItemContainer<Tag> tags, ComboBox comboBoxTag) {
		textFieldTag.clear();
		comboBoxTag.removeAllItems();
		System.out.println("Cantidad de elementos: " + tagService.traerTodos().size());
		tagService.traerTodos().forEach(tag -> tags.addBean(tag));
		comboBoxTag.setContainerDataSource(tags);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub

	}

	private void expandirArbol(Tree arbol) {
		arbol.getItemIds().forEach(item -> arbol.expandItem(item));
	}

	private void cargarTree(Tree arbol) {
		HierarchicalContainer tagContainer = new HierarchicalContainer();

		tagService.traerTodos().forEach(tag -> {
			tagContainer.addItem(tag);
			tagContainer.setChildrenAllowed(tag, false);
		});

		tagContainer.getItemIds().forEach(item -> {
			Tag tagPadre = ((Tag) item).getPadre();
			tagContainer.setChildrenAllowed(tagPadre, true);
			tagContainer.setParent(item, tagPadre);
		});

		arbol.setContainerDataSource(tagContainer);
	}

}
