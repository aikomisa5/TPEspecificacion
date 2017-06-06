package com.EyVdeSW.TP.services;

import java.util.Collection;

import com.EyVdeSW.TP.Daos.ArbolTagDAO;
import com.EyVdeSW.TP.Daos.TagConPadreDAO;
import com.EyVdeSW.TP.Daos.TagDAO;
import com.EyVdeSW.TP.Daos.impl.ArbolTagDAONeodatis;
import com.EyVdeSW.TP.Daos.impl.TagConPadreDAONeodatis;
import com.EyVdeSW.TP.Daos.impl.TagDAONeodatis;
import com.EyVdeSW.TP.domainModel.ArbolTag;
import com.EyVdeSW.TP.domainModel.Tag;
import com.EyVdeSW.TP.domainModel.TagConPadre;

public class TagService {
	private TagConPadreDAO tagDAO;	
	private static TagService tagService;

	private TagService() {
		tagDAO = new TagConPadreDAONeodatis();		
	}

	public static TagService getTagService() {
		if (tagService == null) {
			tagService = new TagService();
		}
		return tagService;
	}

	public void guardar(TagConPadre t) {
		tagDAO.guardar(t);		
	}

	public void guardar(String nombreTag, String padreTag) {
		String nombreMinuscula = nombreTag.toLowerCase();
		if (!tagDAO.existe(nombreMinuscula)) {
			TagConPadre tag = new TagConPadre(nombreMinuscula);
			tag.setPadre(tagDAO.getTagPorNombre(padreTag));
			tagDAO.guardar(tag);
		}
	}

	public void borrar(String nombreTag) {
		TagConPadre t = tagDAO.getTagPorNombre(nombreTag);
		if (t != null) tagDAO.borrar(t);			
	}

	public boolean modificar(String nombreOriginal, String nombreNuevo) {
		boolean ret = true;
		nombreNuevo = nombreNuevo.toLowerCase();
		if (tagDAO.existe(nombreNuevo)) {
			ret = false;
		}
		{			
			TagConPadre tagModificado = tagDAO.getTagPorNombre(nombreOriginal);
			tagModificado.setNombre(nombreNuevo);
			tagDAO.modificar(nombreOriginal, tagModificado);
		}
		return ret;
	}

	public Collection<TagConPadre> traerTodos() {
		return tagDAO.traerTodos();
	}	

}
