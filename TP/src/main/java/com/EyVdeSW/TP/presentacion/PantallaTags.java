package com.EyVdeSW.TP.presentacion;

import java.util.List;

import com.EyVdeSW.TP.domainModel.ArbolTag;
import com.EyVdeSW.TP.domainModel.Tag;
import com.EyVdeSW.TP.services.TagService;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.themes.ValoTheme;

@SuppressWarnings("serial")
public class PantallaTags extends VerticalLayout implements View
{
	protected static final String	Name		= "";

	private TagService				tagService	= TagService.getTagService();

	public PantallaTags()
	{
		TextField textFieldTag = new TextField("Nuevo Tag:");
		Tree arbol = new Tree("Tags");

		agregarTags(arbol);
		asignarJerarquias(arbol);
		expandirArbol(arbol);

		BeanItemContainer<Tag> tags = new BeanItemContainer<Tag>(Tag.class);

		tagService.traerTodos().forEach(tag -> tags.addBean(tag));

		ComboBox comboBoxTag = new ComboBox("Seleccionar Tag Padre:", tags);

		Button btnAgregar = new Button("Agregar tag");
		btnAgregar.setStyleName(ValoTheme.BUTTON_PRIMARY);
		btnAgregar.setClickShortcut(ShortcutAction.KeyCode.ENTER);
		btnAgregar.addClickListener(e -> {
			String tagPadre = comboBoxTag.getValue() == null ? null : comboBoxTag.getValue().toString();
			tagService.guardar(textFieldTag.getValue(), tagPadre);
			Notification.show("Tag Guardado", Type.TRAY_NOTIFICATION);
			limpiarCampos(textFieldTag, tags, comboBoxTag);
			arbol.removeAllItems();
			agregarTags(arbol);
			asignarJerarquias(arbol);
			expandirArbol(arbol);
		});

		Button btnEditar = new Button("Editar tag");
		btnEditar.setStyleName(ValoTheme.BUTTON_PRIMARY);
		btnEditar.setClickShortcut(ShortcutAction.KeyCode.ENTER);
		btnEditar.addClickListener(e -> {
			tagService.modificar(comboBoxTag.getValue().toString(), textFieldTag.getValue());
			Notification.show("Tag editado", Type.TRAY_NOTIFICATION);
			limpiarCampos(textFieldTag, tags, comboBoxTag);
			arbol.removeAllItems();
			agregarTags(arbol);
			asignarJerarquias(arbol);
			expandirArbol(arbol);
		});

		Button btnBorrar = new Button("Borrar tag");
		btnBorrar.setStyleName(ValoTheme.BUTTON_PRIMARY);
		btnBorrar.setClickShortcut(ShortcutAction.KeyCode.ENTER);
		btnBorrar.addClickListener(e -> {
			if (comboBoxTag.getValue() != null)
			{
				List<Tag> t = (List<Tag>) tagService.consultar(comboBoxTag.getValue().toString());
				tagService.borrar(t.get(0));
				Notification.show("Tag Borrado", Type.TRAY_NOTIFICATION);
				limpiarCampos(textFieldTag, tags, comboBoxTag);
				arbol.removeAllItems();
				agregarTags(arbol);
				asignarJerarquias(arbol);
				expandirArbol(arbol);
			}
		});

		HorizontalLayout hl = new HorizontalLayout(btnAgregar, btnEditar, btnBorrar);
		hl.setSpacing(true);

		VerticalLayout vlFormTags = new VerticalLayout(textFieldTag, comboBoxTag, hl);
		VerticalLayout vlArbol = new VerticalLayout(arbol);
		vlFormTags.setSpacing(true);
		HorizontalLayout hlprincipal = new HorizontalLayout(vlArbol, vlFormTags);
		hlprincipal.setSpacing(true);
		addComponent(hlprincipal);

	}

	private void limpiarCampos(TextField textFieldTag, BeanItemContainer<Tag> tags, ComboBox comboBoxTag)
	{
		textFieldTag.clear();
		comboBoxTag.removeAllItems();
		System.out.println("Cantidad de elementos: " + tagService.traerTodos().size());
		tagService.traerTodos().forEach(tag -> tags.addBean(tag));
		comboBoxTag.setContainerDataSource(tags);
	}

	@Override
	public void enter(ViewChangeEvent event)
	{
		// TODO Auto-generated method stub

	}

	private void expandirArbol(Tree arbol)
	{
		arbol.getItemIds().forEach(item -> arbol.expandItem(item));
	}

	// llamar a este metodo para asignar los valores al tree
	private void agregarTags(Tree arbol)
	{
		List<ArbolTag> arboles = (List<ArbolTag>) tagService.traerArboles();

		for (ArbolTag a : arboles)
		{
			recorrerAgregar(a.getRaiz(), arbol);
		}

	}

	// auxiliar de agregarTags
	private void recorrerAgregar(Tag t, Tree arbol)
	{
		arbol.addItem(t.getNombre());
		if (t.getHijos() != null)
		{
			for (Tag hijo : t.getHijos())
			{
				recorrerAgregar(hijo, arbol);
			}
		}
	}

	// llamarlo para asignar jerarquias
	private void asignarJerarquias(Tree arbol)
	{
		List<ArbolTag> arboles = (List<ArbolTag>) tagService.traerArboles();

		for (ArbolTag a : arboles)
		{
			recorrerAsignar(null, a.getRaiz(), arbol);
		}

	}

	private void recorrerAsignar(Tag padre, Tag actual, Tree arbol)
	{
		if (padre != null)
			arbol.setParent(actual.getNombre(), padre.getNombre());
		if (actual.getHijos().size() == 0)
		{
			arbol.setChildrenAllowed(actual.getNombre(), false);
		}
		
		for (Tag tag : actual.getHijos())
		{
			recorrerAsignar(actual, tag, arbol);
		}
	}

}
