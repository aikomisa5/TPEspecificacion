package com.EyVdeSW.TP.presentacion;

import com.EyVdeSW.TP.domainModel.Tag;
import com.EyVdeSW.TP.services.TagService;

import com.vaadin.data.util.BeanItemContainer;
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
public class PantallaTags extends VerticalLayout implements View {
	protected static final String NAME = "";

	private TagService tagService = TagService.getTagService();
	
	Button logout = new Button("Logout");

	public PantallaTags() {
		Label titulo = new Label("Gestión de Tags");
		titulo.setStyleName(ValoTheme.LABEL_H1);
		HorizontalLayout hlTitulo = new HorizontalLayout(titulo);
		addComponent(hlTitulo);
		setComponentAlignment(hlTitulo, Alignment.MIDDLE_CENTER);

		Tree arbol = new Tree("Tags");
		agregarTags(arbol);
		asignarJerarquias(arbol);
		expandirArbol(arbol);

		TextField tfNombre = new TextField("Nombre");

		BeanItemContainer<Tag> tags = new BeanItemContainer<Tag>(Tag.class);
		tagService.traerTodos().forEach(tag -> tags.addBean(tag));
		ComboBox comboBoxTag = new ComboBox("Tag Padre", tags);

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
			if (tfNombre.getValue() == "" || comboBoxTag.getValue() == null) {
				Notification.show("Los campos estan vacios!", Type.WARNING_MESSAGE);
			} else {
				boolean resultado = tagService.modificar(comboBoxTag.getValue().toString(), tfNombre.getValue());
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

		HorizontalLayout hlBotones = new HorizontalLayout(btnAgregar, btnEditar, btnBorrar);
		hlBotones.setSpacing(true);

		FormLayout flFormTags = new FormLayout(tfNombre, comboBoxTag);
		flFormTags.setSpacing(true);
		VerticalLayout vlFormTags = new VerticalLayout(flFormTags, hlBotones);
		vlFormTags.setSpacing(true);

		VerticalLayout vlArbol = new VerticalLayout(arbol);
		HorizontalLayout hlPrincipal = new HorizontalLayout(vlArbol, vlFormTags);
		hlPrincipal.setSpacing(true);
		hlPrincipal.setWidth("80%");
		addComponent(hlPrincipal);
		setComponentAlignment(hlPrincipal, Alignment.TOP_CENTER);
		setMargin(true);
		
		//Para volver al main principal
		addComponent(logout);		
		logout.addClickListener(event -> // Java 8
			getUI().getNavigator().navigateTo(""));
         

	}

	private void updateTree(Tree arbol) {
		arbol.removeAllItems();
		agregarTags(arbol);
		asignarJerarquias(arbol);
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

	// llamar a este metodo para asignar los valores al tree
	private void agregarTags(Tree arbol) {
		tagService.traerArboles().forEach(a -> recorrerAgregar(a.getRaiz(), arbol));
	}

	// auxiliar de agregarTags
	private void recorrerAgregar(Tag t, Tree arbol) {
		arbol.addItem(t.getNombre());
		if (t.getHijos() != null) {
			for (Tag hijo : t.getHijos()) {
				recorrerAgregar(hijo, arbol);
			}
		}
	}

	// llamarlo para asignar jerarquias
	private void asignarJerarquias(Tree arbol) {
		tagService.traerArboles().forEach(a -> recorrerAsignar(null, a.getRaiz(), arbol));
	}

	private void recorrerAsignar(Tag padre, Tag actual, Tree arbol) {
		if (padre != null)
			arbol.setParent(actual.getNombre(), padre.getNombre());
		if (actual.getHijos().size() == 0) {
			arbol.setChildrenAllowed(actual.getNombre(), false);
		}

		for (Tag tag : actual.getHijos()) {
			recorrerAsignar(actual, tag, arbol);
		}
	}

}
