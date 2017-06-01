package com.EyVdeSW.TP.services;

import java.util.Collection;
import java.util.List;

import com.EyVdeSW.TP.Daos.ArbolTagDAO;
import com.EyVdeSW.TP.Daos.TagDAO;
import com.EyVdeSW.TP.Daos.impl.ArbolTagDAONeodatis;
import com.EyVdeSW.TP.Daos.impl.TagDAONeodatis;
import com.EyVdeSW.TP.domainModel.ArbolTag;
import com.EyVdeSW.TP.domainModel.Tag;

public class TagService {
	private TagDAO tagDAO;
	private ArbolTagDAO arbolTagDAO;
	private static TagService tagService;

	private TagService() {
		tagDAO = new TagDAONeodatis();
		arbolTagDAO = new ArbolTagDAONeodatis();
	}

	public static TagService getTagService() {
		if (tagService == null) {
			tagService = new TagService();
		}
		return tagService;
	}

	public void guardar(Tag t) {
		tagDAO.guardar(t);
	}

	public void guardar(String nombreTag, String padreTag) {
		String nombreTagUpperCase = nombreTag.toUpperCase();
		if (!tagDAO.existe(nombreTagUpperCase)) {
			if (padreTag == null) {
				arbolTagDAO.guardar(new ArbolTag(new Tag(nombreTagUpperCase)));
			} else {
				Tag padre = tagDAO.getTagPorNombre(padreTag);
				padre.addHijo(new Tag(nombreTagUpperCase));
				tagDAO.modificar(padre, padre);
			}
		}
	}

	public void borrar(String nombreTag) {
		Tag t = tagDAO.getTagPorNombre(nombreTag);
		if (t != null) {
			if (!arbolTagDAO.esRaiz(t))
				tagDAO.borrar(t);
			else {
				ArbolTag aBorrar = arbolTagDAO.getArbolPorNombreRaiz(t.getNombre());
				arbolTagDAO.borrar(aBorrar);
			}
		}
	}

	public void modificar(String original, String modificacion) {
		Tag orig = tagDAO.getTagPorNombre(original);
		Tag modi = tagDAO.getTagPorNombre(original);
		modi.setNombre(modificacion.toUpperCase());
		tagDAO.modificar(orig, modi);
	}

	public Collection<Tag> traerTodos() {
		return tagDAO.traerTodos();
	}

	public Collection<Tag> consultar(String nombre) {
		return tagDAO.consultarPorNombre(nombre);
	}

	public Collection<ArbolTag> traerArboles() {
		return arbolTagDAO.traerArboles();
	}

}
