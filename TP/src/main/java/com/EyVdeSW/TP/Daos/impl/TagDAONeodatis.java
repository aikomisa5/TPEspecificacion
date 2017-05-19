package com.EyVdeSW.TP.Daos.impl;

import java.util.Collection;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

import com.EyVdeSW.TP.Daos.TagDAO;
import com.EyVdeSW.TP.domainModel.Tag;

import properties.Parametros;

public class TagDAONeodatis extends DAONeodatis<Tag> implements TagDAO{
	
	@Override
	public void modificar(Tag original, Tag modificacion)
	{
		ODB odb = null;
		Objects<Tag> resultadoQuery = null;
		try
		{
			odb = ODBFactory.open(Parametros.getProperties().getProperty(Parametros.dbPath));
			IQuery query = new CriteriaQuery(Tag.class, Where.like("nombre", original.getNombre()));	
			resultadoQuery=odb.getObjects(query);
			Tag t=(Tag)resultadoQuery.getFirst();
			t.setHijos(modificacion.getHijos());
			t.setAccionesGenerales(modificacion.getAccionesGenerales());
			t.setNombre(modificacion.getNombre());
			odb.store(t);
			odb.commit();
			
			//esto me tira error por el scope, perdon por no usar lambda 
//			resultadoQuery.forEach(
//					 t -> {
//						t.setHijos(modificacion.getHijos());
//						t.setAccionesGenerales(modificacion.getAccionesGenerales());
//						t.setNombre(modificacion.getNombre());
//						odb.store(t);
//					}
//			);
			
			
			
		}
		finally
		{
			if (odb != null)
				odb.close();
		}
	}

	
}
