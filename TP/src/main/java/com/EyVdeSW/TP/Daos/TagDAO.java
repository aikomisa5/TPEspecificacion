package com.EyVdeSW.TP.Daos;

import java.util.Collection;

import com.EyVdeSW.TP.domainModel.Tag;

public interface TagDAO extends DAO<Tag>
{
	public void modificar(Tag original, Tag modificacion);
	public Collection<Tag>traerTodos();
	public Collection<Tag>consultarPorNombre(String nombre);
	public Tag getTagPorNombre(String padreTag);
}
