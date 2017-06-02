package com.EyVdeSW.TP.Daos.impl;

import java.util.Collection;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

import com.EyVdeSW.TP.Daos.TagDAO;
import com.EyVdeSW.TP.domainModel.Tag;

public class TagDAONeodatis extends DAONeodatis<Tag> implements TagDAO{
	
	@Override
	public void modificar(Tag original, Tag modificacion){
		odb = null;
		try{			
			odb = bdConnector.getBDConnection();
			IQuery query = new CriteriaQuery(Tag.class, Where.like("nombre", original.getNombre()));	
			Objects<Tag>resultadoQuery=odb.getObjects(query);
			
			Tag t=resultadoQuery.getFirst();
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
	public Tag getTagPorNombre(String nombre){	
		Tag tag=null;		
		Objects<Tag> resultadoQuery = null;
		try{
			odb = bdConnector.getBDConnection();
			resultadoQuery = odb.getObjects(new CriteriaQuery(Tag.class, Where.like("nombre", "%"+nombre+"%")));
			if(resultadoQuery.size() != 0)
				tag= resultadoQuery.getFirst();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if (odb != null)
				odb.close();
		}
		return tag;
	}	

	@Override
	public void borrar(Tag t) {
		super.borrar(t);		
		t.getHijos().forEach(tagHijo -> this.borrar(tagHijo));		
	}

	@Override
	public boolean existe(String nombre) {
		boolean ret=false;
		Objects<Tag> resultadoQuery = null;
		try{
			odb = bdConnector.getBDConnection();
			resultadoQuery = odb.getObjects(new CriteriaQuery(Tag.class, Where.equal("nombre", nombre)));
			ret = ret||(resultadoQuery.size() != 0);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if (odb != null)
				odb.close();
		}
		return ret;
	}

	
}
