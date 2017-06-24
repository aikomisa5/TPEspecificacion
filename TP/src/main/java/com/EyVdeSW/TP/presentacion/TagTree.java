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
	
	private TagService tagService = TagService.getTagService();
	
	public TagTree (){
		super("Tags");
	}
	
	public void expandirArbol() {
		this.getItemIds().forEach(item -> this.expandItem(item));
	}

	public void cargarTree() {
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

		this.setContainerDataSource(tagContainer);
	}
	
	public void updateTree() {
		this.removeAllItems();
		cargarTree();
		expandirArbol();
	}
	
	public void cargarTreeConTagsAgregados(List<Tag>tags){
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

		this.setContainerDataSource(tagContainer);
	}
	
	public void cargarTreeConTagsDeCamapaña(Campania c){
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

		this.setContainerDataSource(tagContainer);
	}
	
	public void vaciarArbol(){
		this.removeAllItems();
	}
	
}
