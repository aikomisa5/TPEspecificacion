package com.EyVdeSW.TP.services;

import java.util.Collection;
import java.util.List;

import com.EyVdeSW.TP.Daos.ArbolTagDAO;
import com.EyVdeSW.TP.Daos.TagDAO;
import com.EyVdeSW.TP.Daos.impl.ArbolTagDAONeodatis;
import com.EyVdeSW.TP.Daos.impl.TagDAONeodatis;
import com.EyVdeSW.TP.domainModel.ArbolTag;
import com.EyVdeSW.TP.domainModel.Tag;

public class TagService
{
	private TagDAO tagDAO;
	private ArbolTagDAO arbolTagDAO;
	private static TagService tagService;
	
	private TagService(){
		tagDAO= new TagDAONeodatis();
		arbolTagDAO= new ArbolTagDAONeodatis();
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
			arbolTagDAO.guardar(new ArbolTag(new Tag(nombreTag)));
		}
		else
		{
			System.out.println(padreTag);
			Tag padre = tagDAO.getTagPorNombre(padreTag);
			padre.addHijo(new Tag(nombreTag));
			tagDAO.modificar(padre, padre);			
		}
	}
	
	public void borrar(Tag t){
		//TODO verificar que el tag no sea raiz de un arbol, en caso de serlo eliminar el arbol
		//Sino eliminar el tag directamente
		//Podemos crear un metodo booleano "esRaiz(Tag t)" en arbol y usarlo aca(agregar a la interfaz)
		List<ArbolTag>arboles=(List<ArbolTag>) arbolTagDAO.traerArboles();
		tagDAO.borrar(t);
	}
	
	//REVISAR
	public void borrar (String nombreTag, String padreTag){
		Tag padre = tagDAO.getTagPorNombre(padreTag);
		List <Tag> hijos = padre.getHijos();
		
		for (Tag i : hijos){
			if (i.equals(nombreTag)==true){
				padre.removeHijo(i);
				tagDAO.borrar(i);
			}
		}
	}	
	
	public void borrarRecursivo(String nombreTag){
		
		
	}
	
	public void modificar (String original, String modificacion)
	{
		//TODO
		//Tag orig = tagDAO.getTagPorNombre(original);
		//tagDAO.modificar(original, modificacion);
	}
	
	public Collection<Tag>traerTodos(){
		return tagDAO.traerTodos();
	}
	
	public Collection<Tag> consultar(String nombre){
		return tagDAO.consultarPorNombre(nombre);
	}
	
	public Collection<ArbolTag>traerArboles(){
		return arbolTagDAO.traerArboles();
	}
	
}
