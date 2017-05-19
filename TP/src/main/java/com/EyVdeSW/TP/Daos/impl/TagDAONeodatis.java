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
	public void modificar(Tag original, Tag modificacion){
		ODB odb = null;
		try{
			odb = ODBFactory.open(Parametros.getProperties().getProperty(Parametros.dbPath));
			IQuery query = new CriteriaQuery(Tag.class, Where.like("nombre", original.getNombre()));	
			Objects<Tag>resultadoQuery=odb.getObjects(query);
			
			Tag t=(Tag)resultadoQuery.getFirst();
			t.setHijos(modificacion.getHijos());
			t.setAccionesGenerales(modificacion.getAccionesGenerales());
			t.setNombre(modificacion.getNombre());
			odb.store(t);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			if (odb != null)
				odb.close();
		}
	}

	
}
