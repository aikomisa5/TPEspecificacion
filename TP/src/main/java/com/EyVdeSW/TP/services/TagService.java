package com.EyVdeSW.TP.services;

import java.util.Collection;

import com.EyVdeSW.TP.Daos.TagDAO;
import com.EyVdeSW.TP.Daos.impl.TagDAONeodatis;
import com.EyVdeSW.TP.domainModel.Tag;

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

	public void guardar(Tag t) {
		tagDAO.guardar(t);
	}

	public void guardar(String nombreTag, String padreTag) {
		if (!tagDAO.existe(nombreTag)){
			Tag nuevoTag = new Tag(nombreTag);
			if (padreTag != null)
				nuevoTag.setPadre(tagDAO.getTagPorNombre(padreTag));
			tagDAO.guardar(nuevoTag);
		}
	}

	public void borrar(String nombreTag) {
		Tag t = tagDAO.getTagPorNombre(nombreTag);
		tagDAO.borrar(t);
	}

	public boolean modificar(String nombreOriginal, String nombreNuevo) {
		boolean ret = true;
		if (!tagDAO.existe(nombreOriginal) || tagDAO.existe(nombreNuevo)) {
			ret = false;
		}else{
			String modificacionMinuscula = nombreNuevo.toLowerCase();
			Tag tagModificado = tagDAO.getTagPorNombre(nombreOriginal);
			tagModificado.setNombre(modificacionMinuscula);
			tagDAO.modificar(nombreOriginal, tagModificado);
		}
		return ret;
	}
	
	public boolean modificar(String nombreOriginal, String nombreNuevo, String nombrePadreNuevo){
		boolean ret = true;
		if (!tagDAO.existe(nombreOriginal) || (tagDAO.existe(nombreNuevo)&&!nombreNuevo.equals(nombreOriginal))
				|| nombreNuevo==null || nombreNuevo.equals(nombrePadreNuevo)) {
			System.out.println("entre aca :( "+nombreOriginal+" "+nombrePadreNuevo);
			ret = false;
		}else{
			Tag tagModificado = tagDAO.getTagPorNombre(nombreOriginal);
			tagModificado.setNombre(nombreNuevo);
			if(nombrePadreNuevo !=""){
				Tag nuevoPadre = tagDAO.getTagPorNombre(nombrePadreNuevo);
				tagModificado.setPadre(nuevoPadre);
			}
			else
				tagModificado.setPadre(null);
			
			tagDAO.modificar(nombreOriginal, tagModificado);
		}
		return ret;
	}


	public Collection<Tag> traerTodos() {
		return tagDAO.traerTodos();
	}
	
	public Tag getTagPorNombre(String padreTag){
		return tagDAO.getTagPorNombre(padreTag);
	}
	
	public Collection<Tag> traerHijosDe(Tag padre){
		return tagDAO.traerHijosDe(padre);
	}
	
	public Collection <Tag> traerRaices(){
		return tagDAO.traerRaices();
	}

}
