package com.EyVdeSW.TP.services;

import java.util.Collection;

import com.EyVdeSW.TP.Daos.TagDAO;
import com.EyVdeSW.TP.Daos.impl.TagDAONeodatis;
import com.EyVdeSW.TP.domainModel.Tag;

public class TagService
{
	private TagDAO tagDAO;
	private static TagService tagService;
	
	private TagService(){
		tagDAO= new TagDAONeodatis();
	}
	
	public static TagService getTagService(){
		if(tagService == null){
			tagService=new TagService();
		}
		return tagService;
	}
	
	public void guardar(Tag t){
		tagDAO.guardar(t);
	}
	
	public void guardar(String nombreTag, String padreTag){
		if(padreTag == null){
			tagDAO.guardar(new Tag(nombreTag));
		}
		else
		{
			Tag padre = tagDAO.getTagPorNombre(padreTag);
			padre.addHijo(new Tag(nombreTag));
			tagDAO.modificar(padre, padre);			
		}
	}
	
	public void borrar(Tag t){
		tagDAO.borrar(t);
	}
	
	public void modificar (Tag original, Tag modificacion)
	{
		tagDAO.modificar(original, modificacion);
	}
	
	public Collection<Tag>traerTodos(){
		return tagDAO.traerTodos();
	}
	
	public Collection<Tag> consultar(String nombre){
		return tagDAO.consultarPorNombre(nombre);
	}
	
}
