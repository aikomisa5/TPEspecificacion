package com.EyVdeSW.TP.Daos;

import java.util.Collection;

import com.EyVdeSW.TP.domainModel.Tag;

public interface TagDAO extends DAO<Tag>
{
	public void modificar(String nombreOriginalTag, Tag modificacion);
	public Collection<Tag>traerTodos();
	public Tag getTagPorNombre(String padreTag);
	public boolean existe(String nombre);
	public Collection<Tag> traerHijosDe(Tag padre);
	public Collection <Tag> traerRaices();
	
	
}
