package com.EyVdeSW.TP.presentacion;

import java.util.List;

import com.EyVdeSW.TP.domainModel.Campania;
import com.EyVdeSW.TP.domainModel.Tag;
import com.EyVdeSW.TP.services.CampañaService;
import com.EyVdeSW.TP.services.TagService;
import com.vaadin.data.util.HierarchicalContainer;
import com.vaadin.ui.Tree;

@SuppressWarnings("serial")
public class TagTree extends Tree{
	
	private static TagService tagService = TagService.getTagService();
	
	public TagTree (){
		super("Tags");
	}
	
	public TagTree (String descripcion){
		super(descripcion);
	}
	
	public static void expandirArbol(Tree arbol) {
		arbol.getItemIds().forEach(item -> arbol.expandItem(item));
	}

	public static void cargarTreeCompleto(Tree arbol) {
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
	
	public static void updateTree(Tree arbol) {
		arbol.removeAllItems();
		cargarTreeCompleto(arbol);
		expandirArbol(arbol);
	}
	
	public static void cargarTreeConTags(Tree arbol, List<Tag>tags){
		HierarchicalContainer tagContainer = new HierarchicalContainer();
		
		tags.forEach(tag -> {
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
	
	public static void cargarTreeConTagsDeCampaña(Tree arbol, Campania c){
		HierarchicalContainer tagContainer = new HierarchicalContainer();
		
		c.getTagsAsociados().forEach(tag -> {
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
	
	public static void vaciarArbol(Tree arbol){
		arbol.removeAllItems();
	}
	
	public static void updateTree(Tree arbol, Tag tagSeleccionado, HierarchicalContainer tagContainer) {
		cargarTree(arbol, tagSeleccionado, tagContainer);
		expandirArbol(arbol);
	}

//	private static void expandirArbol(Tree arbol) {
//		arbol.getItemIds().forEach(item -> arbol.expandItem(item));
//	}

	private static void cargarTree(Tree arbol, Tag tagSeleccionado, HierarchicalContainer tagContainer) {

		tagContainer.addItem(tagSeleccionado);
		tagService.traerHijosDe(tagSeleccionado).forEach(tag -> {
			tagContainer.addItem(tag);
			tagContainer.setChildrenAllowed(tag, false);
		});

		/*
		 * tagService.traerTodos().forEach(tag -> { tagContainer.addItem(tag);
		 * tagContainer.setChildrenAllowed(tag, false); });
		 */

		tagContainer.getItemIds().forEach(item -> {
			Tag tagPadre = ((Tag) item).getPadre();
			tagContainer.setChildrenAllowed(tagPadre, true);
			tagContainer.setParent(item, tagPadre);
		});

		arbol.setContainerDataSource(tagContainer);
	}
	
}
