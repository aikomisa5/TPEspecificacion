package com.EyVdeSW.TP.presentacion.domainModel.Daos;

import java.util.Collection;

import com.EyVdeSW.TP.presentacion.domainModel.Tag;

public interface TagDAO extends DAO<Tag>
{
	public void modificar(Tag original, Tag modificacion);
}
