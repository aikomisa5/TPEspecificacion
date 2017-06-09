package com.EyVdeSW.TP.services;

import java.util.Collection;

import com.EyVdeSW.TP.Daos.TagDAO;
import com.EyVdeSW.TP.Daos.impl.TagDAONeodatis;
import com.EyVdeSW.TP.domainModel.TagConPadre;

public class TagService {
	private TagDAO tagDAO;
	private static TagService tagService;

	private TagService() {
		tagDAO = new TagDAONeodatis();
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
		if (!tagDAO.existe(nombreTag)){
			TagConPadre nuevoTag = new TagConPadre(nombreTag);
			if (padreTag != null)
				nuevoTag.setPadre(tagDAO.getTagPorNombre(padreTag));
			tagDAO.guardar(nuevoTag);
		}
	}

	public void borrar(String nombreTag) {
		TagConPadre t = tagDAO.getTagPorNombre(nombreTag);
		tagDAO.borrar(t);
	}

	public boolean modificar(String nombreOriginal, String nombreNuevo) {
		boolean ret = true;
		if (tagDAO.existe(nombreNuevo)) {
			ret = false;
		}else{
			String modificacionMinuscula = nombreNuevo.toLowerCase();
			TagConPadre tagModificado = tagDAO.getTagPorNombre(nombreOriginal);
			tagModificado.setNombre(modificacionMinuscula);
			tagDAO.modificar(nombreOriginal, tagModificado);
		}
		return ret;
	}

	public Collection<TagConPadre> traerTodos() {
		return tagDAO.traerTodos();
	}

}
