package com.EyVdeSW.TP.Daos;

import java.util.Collection;

import com.EyVdeSW.TP.domainModel.TagConPadre;

public interface TagConPadreDAO extends DAO<TagConPadre>
{
	public void modificar(String nombreOriginalTag, TagConPadre modificacion);
	public Collection<TagConPadre>traerTodos();
	public TagConPadre getTagPorNombre(String padreTag);
	public boolean existe(String nombre);
	public Collection<TagConPadre> traerHijosDe(TagConPadre padre);
	public Collection <TagConPadre> traerRaices();
	
	
}
