package com.EyVdeSW.TP.presentacion;

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
	private String seleccionado=null;

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
			if (tfNombre.getValue() == "") {
				Notification.show("Los campos estan vacios!", Type.WARNING_MESSAGE);
			}else if(seleccionado==null){ 
				Notification.show("No se seleccionó ningún tag", Type.WARNING_MESSAGE);
			}else {
				String nuevoPadre="";
				if(comboBoxTag.getValue()!=null){
					nuevoPadre=comboBoxTag.getValue().toString();
				}
				boolean resultado = tagService.modificar(seleccionado, tfNombre.getValue(), nuevoPadre);
				if (resultado) {
					Notification.show("Tag editado", Type.TRAY_NOTIFICATION);
					limpiarCampos(tfNombre, tags, comboBoxTag);
					updateTree(arbol);
				} else {
					Notification.show("Se ingresó incorrectamente algún campo", Type.WARNING_MESSAGE);
				}
			}
			tfNombre.focus();
		});

		Button btnBorrar = new Button("Borrar");
		btnBorrar.setStyleName(ValoTheme.BUTTON_DANGER);

		btnBorrar.addClickListener(e -> {
			if (tfNombre.getValue() != null) {
				tagService.borrar(tfNombre.getValue());
				Notification.show("Tag Borrado", Type.TRAY_NOTIFICATION);
				limpiarCampos(tfNombre, tags, comboBoxTag);
				updateTree(arbol);
			}
			tfNombre.focus();
		});
		
		arbol.addItemClickListener(click -> {
			Tag t = tagService.getTagPorNombre(click.getItemId().toString());
			tfNombre.setValue(t.getNombre());
			comboBoxTag.setValue(t.getPadre());
			seleccionado=t.getNombre();
		});

		HorizontalLayout hlBotones = new HorizontalLayout(btnAgregar, btnEditar, btnBorrar);
		hlBotones.setSpacing(true);

		FormLayout flFormTags = new FormLayout(tfNombre, comboBoxTag);
		flFormTags.setSpacing(true);
		HorizontalLayout forms= new HorizontalLayout(flFormTags);
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


	}

	private void updateTree(Tree arbol) {
		arbol.removeAllItems();
		cargarTree(arbol);
		expandirArbol(arbol);
	}

	private void limpiarCampos(TextField tfNombre, BeanItemContainer<Tag> tags, ComboBox comboBoxTag) {
		tfNombre.clear();
		comboBoxTag.removeAllItems();
		System.out.println("Cantidad de elementos: " + tagService.traerTodos().size());
		tagService.traerTodos().forEach(tag -> tags.addBean(tag));
		comboBoxTag.setContainerDataSource(tags);
		seleccionado=null;
	}

	@Override
	public void enter(ViewChangeEvent event) {
		

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
