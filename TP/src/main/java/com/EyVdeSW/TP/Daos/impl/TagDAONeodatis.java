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
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if (odb != null)
				odb.close();
		}
	}

	@Override
	public Collection<Tag> traerTodos() {
		return consultar(new CriteriaQuery(Tag.class));
	}

	@Override
	public Collection<Tag> consultarPorNombre(String nombre) {
		IQuery query = new CriteriaQuery(Tag.class, Where.like("nombre", "%"+nombre+"%"));
		return consultar(query);
	}

	@Override
	public Tag getTagPorNombre(String nombre)
	{	
		Tag tag=null;
		ODB odb = null;
		Objects<Tag> resultadoQuery = null;
		try{
			// Abrimos la bd
			odb = ODBFactory.open(Parametros.getProperties().getProperty(Parametros.dbPath));
			resultadoQuery = odb.getObjects(new CriteriaQuery(Tag.class, Where.like("nombre", "%"+nombre+"%")));
			tag= (Tag) resultadoQuery.getFirst();
		}
		catch(Exception e){
			e.printStackTrace();
		}finally
		{
			if (odb != null)
				odb.close();
		}
		
		return tag;
		
	}

	
}
