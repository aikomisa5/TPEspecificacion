package com.EyVdeSW.TP.services;

import java.util.Collection;

import com.EyVdeSW.TP.Daos.TagConPadreDAO;
import com.EyVdeSW.TP.Daos.impl.TagConPadreDAONeodatis;
import com.EyVdeSW.TP.domainModel.TagConPadre;

public class TagConPadreService {
	private TagConPadreDAO tagDAO;
	private static TagConPadreService tagService;

	private TagConPadreService() {
		tagDAO = new TagConPadreDAONeodatis();
	}

	public static TagConPadreService getTagService() {
		if (tagService == null) {
			tagService = new TagConPadreService();
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
		}
		{
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
