package com.EyVdeSW.TP.presentacion;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.EyVdeSW.TP.domainModel.Tag;
import com.EyVdeSW.TP.services.TagService;
import com.vaadin.data.util.HierarchicalContainer;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;
import com.vaadin.ui.Notification.Type;

// Define a sub-window by inheritance
public class SubMenuTagsAsociadosCampaña extends Window {
	private Tree tagsDisponiblesTree;
	private Tree tagsSeleccionadosTree;

	public SubMenuTagsAsociadosCampaña(Tree tagsAgregadosHastaElMomento, TagService tagService,
			List<Tag> tagsParaAsociar, HierarchicalContainer tagContainer) {
		
		super("Asociar tags a la campaña"); // Set window caption
		
		center();
		this.setModal(true);
		this.setDraggable(false);


		tagsDisponiblesTree = new TagTree("Tags disponibles");
		tagsSeleccionadosTree = new TagTree("Tags seleccionados");
		List<Tag> tagsSeleccionados = new ArrayList<>();
		List<Tag> tagsDisponibles = tagService.traerTodos().stream().collect(Collectors.toList());

		tagsDisponibles.removeAll(tagsParaAsociar);
		tagsSeleccionados.addAll(tagsParaAsociar);

		updateArbolesDeSeleccion(tagsSeleccionados, tagsDisponibles);
			

		// Add it to the root component
		MyUI.getCurrent().addWindow(this);

		Button agregar = new Button("Guardar cambios");
		agregar.setIcon(FontAwesome.CHECK);
		agregar.setStyleName(ValoTheme.BUTTON_PRIMARY);
		agregar.setClickShortcut(KeyCode.ENTER);

		HorizontalLayout subContent = new HorizontalLayout();
		VerticalLayout main = new VerticalLayout(subContent);
		VerticalLayout root = new VerticalLayout(main);
		main.setWidth(subContent.getWidth(),subContent.getWidthUnits());
		root.setWidth((subContent.getWidth()*1.1f),subContent.getWidthUnits());
		root.setComponentAlignment(main, Alignment.TOP_CENTER);
		this.setContent(root);

		// Agregamos los componentes
		subContent.addComponent(tagsDisponiblesTree);
		subContent.addComponent(tagsSeleccionadosTree);

		subContent.addComponent(agregar);
		this.setHeight("450px");
		this.setWidth("700px");
		subContent.setMargin(true);
		subContent.setSpacing(true);

		tagsDisponiblesTree.addItemClickListener(click -> {
			Tag t = tagService.getTagPorNombre(click.getItemId().toString());
			Collection<Tag> arbolDeTagsSeleccionados = tagService.traerHijosDe(t);
			arbolDeTagsSeleccionados.forEach(tag -> {
				if (!tagsSeleccionados.contains(tag)) {
					tagsSeleccionados.add(tag);
				}
			});
			tagsDisponibles.removeAll(arbolDeTagsSeleccionados);
			updateArbolesDeSeleccion(tagsSeleccionados, tagsDisponibles);
		});

		tagsSeleccionadosTree.addItemClickListener(click -> {
			Tag t = tagService.getTagPorNombre(click.getItemId().toString());
			Collection<Tag> arbolDeTagsSeleccionados = tagService.traerHijosDe(t);
			arbolDeTagsSeleccionados.forEach(tag -> {
				if (!tagsDisponibles.contains(tag)) {
					tagsDisponibles.add(tag);
				}
			});
			tagsSeleccionados.removeAll(arbolDeTagsSeleccionados);
			updateArbolesDeSeleccion(tagsSeleccionados, tagsDisponibles);
		});

		

		agregar.addClickListener(event -> {

			TagTree.cargarTreeConTags(tagsAgregadosHastaElMomento, tagsSeleccionados);
			TagTree.expandirArbol(tagsAgregadosHastaElMomento);
			tagsParaAsociar.clear();
			tagsParaAsociar.addAll(tagsSeleccionados);
			Notification.show("Tags guardados", Type.TRAY_NOTIFICATION);
			this.close();

		});

	}

	private void updateArbolesDeSeleccion(List<Tag> tagsSeleccionados, List<Tag> tagsDisponibles) {
		TagTree.cargarTreeConTags(tagsDisponiblesTree, tagsDisponibles);
		TagTree.expandirArbol(tagsDisponiblesTree);
		TagTree.cargarTreeConTags(tagsSeleccionadosTree, tagsSeleccionados);
		TagTree.expandirArbol(tagsSeleccionadosTree);
	}

}
