package com.EyVdeSW.TP.Daos.impl;

import java.util.Collection;

import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

import com.EyVdeSW.TP.Daos.CampañaDAO;
import com.EyVdeSW.TP.domainModel.Campaña;

public class CampañaDAONeodatis extends DAONeodatis <Campaña> implements CampañaDAO {

	@Override
	public void modificar(Campaña original, Campaña modificacion) {
		odb = null;
		try{			
			odb = bdConnector.getBDConnection();
			IQuery query = new CriteriaQuery(Campaña.class, Where.like("nombre", original.getNombre()));	
			Objects<Campaña>resultadoQuery=odb.getObjects(query);
			
			Campaña t=resultadoQuery.getFirst();
			t.setMensaje(modificacion.getMensaje());
			t.setAccionesPublicitarias(modificacion.getAccionesPublicitarias());
			t.setNombre(modificacion.getNombre());
			t.setDescripcion(modificacion.getDescripcion());
			t.setFechaDeInicio(modificacion.getFechaDeInicio());
			
			odb.store(t);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if (odb != null)
				odb.close();
		}
	}
	

	@Override
	public Collection<Campaña> traerTodos() {
		return consultar(new CriteriaQuery(Campaña.class));
	}

	@Override
	public Collection<Campaña> consultarPorNombre(String nombre) {
		IQuery query = new CriteriaQuery(Campaña.class, Where.like("nombre", "%"+nombre+"%"));
		return consultar(query);
	}

	@Override
	public Campaña getCampañaPorNombre(String nombreCampaña) {
		Campaña campaña=null;		
		Objects<Campaña> resultadoQuery = null;
		try{
			odb = bdConnector.getBDConnection();
			resultadoQuery = odb.getObjects(new CriteriaQuery(Campaña.class, Where.like("nombre", "%"+nombreCampaña+"%")));
			if(resultadoQuery.size() != 0)
				campaña= resultadoQuery.getFirst();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if (odb != null)
				odb.close();
		}
		return campaña;
	}

	@Override
	public boolean existe(String nombreCampaña) {
		boolean ret=false;
		Objects<Campaña> resultadoQuery = null;
		try{
			odb = bdConnector.getBDConnection();
			resultadoQuery = odb.getObjects(new CriteriaQuery(Campaña.class, Where.equal("nombre", nombreCampaña)));
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
