package com.EyVdeSW.TP.presentacion;

import java.util.List;

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

import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.themes.ValoTheme;

@SuppressWarnings("serial")
public class PantallaTags extends VerticalLayout implements View
{
	protected static final String	Name		= "";

	private TagService						tagService	= TagService.getTagService();
	private ComboBox comboBoxTag;

	public PantallaTags()
	{
		TextField textFieldTag = new TextField("Nuevo Tag:");

		BeanItemContainer<Tag> tags = new BeanItemContainer<Tag>(Tag.class);

		tagService.traerTodos().forEach(tag -> tags.addBean(tag));

		comboBoxTag = new ComboBox("Seleccionar Tag Padre:", tags);
		//refreshComboBoxTag();

		Button btnAgregar = new Button("Agregar tag");
		btnAgregar.setStyleName(ValoTheme.BUTTON_PRIMARY);
		btnAgregar.setClickShortcut(ShortcutAction.KeyCode.ENTER);
		btnAgregar.addClickListener(e -> {
			String tagPadre = comboBoxTag.getValue() == null? null : comboBoxTag.getValue().toString(); 
			tagService.guardar(textFieldTag.getValue(), tagPadre);
			Notification.show("Tag Guardado", Type.TRAY_NOTIFICATION);
			limpiarCampos(textFieldTag, tags, comboBoxTag);
			refreshComboBoxTag();
		});

		Button btnEditar = new Button("Editar tag");
		btnEditar.setStyleName(ValoTheme.BUTTON_PRIMARY);
		btnEditar.setClickShortcut(ShortcutAction.KeyCode.ENTER);
		btnEditar.addClickListener(e -> {			
			tagService.guardar(textFieldTag.getValue(), comboBoxTag.getValue()==null?null:comboBoxTag.getValue().toString());
			Notification.show("Tag Guardado", Type.TRAY_NOTIFICATION);
		});

		Button btnBorrar = new Button("Borrar tag");
		btnBorrar.setStyleName(ValoTheme.BUTTON_PRIMARY);
		btnBorrar.setClickShortcut(ShortcutAction.KeyCode.ENTER);
		btnBorrar.addClickListener(e -> {

			List<Tag> t = (List<Tag>) tagService.consultar(comboBoxTag.getValue().toString());
			tagService.borrar(t.get(0));
			Notification.show("Tag Borrado", Type.TRAY_NOTIFICATION);
			limpiarCampos(textFieldTag, tags, comboBoxTag);
		});

		HorizontalLayout hl = new HorizontalLayout(btnAgregar, btnEditar, btnBorrar);
		hl.setSpacing(true);

		VerticalLayout vl = new VerticalLayout(textFieldTag, comboBoxTag, hl);
		vl.setSpacing(true);

		addComponent(vl);
	}

	private void refreshComboBoxTag()
	{
		comboBoxTag.setContainerDataSource(new BeanItemContainer<Tag>(
					Tag.class,tagService.traerTodos()));
	}

	private void limpiarCampos(TextField textFieldTag, BeanItemContainer<Tag> tags, ComboBox comboBoxTag)
	{
		textFieldTag.clear();
		comboBoxTag.removeAllItems();
		tagService.traerTodos().forEach(tag -> tags.addBean(tag));
		comboBoxTag.setContainerDataSource(tags);
	}

	@Override
	public void enter(ViewChangeEvent event)
	{
		// TODO Auto-generated method stub

	}

}
