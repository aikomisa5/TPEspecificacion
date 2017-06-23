package com.EyVdeSW.TP.Daos.impl;

import java.util.Collection;

import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

import com.EyVdeSW.TP.Daos.DuracionDAO;
import com.EyVdeSW.TP.domainModel.Duracion;


public class DuracionDAONeodatis  extends DAONeodatis<Duracion> implements DuracionDAO {
	
	@Override
	public boolean existe(String descripcion) {
		boolean ret = false;
		Objects<Duracion> resultadoQuery = null;
		odb = null;
		try
		{
			odb = bdConnector.getBDConnection();
			resultadoQuery = odb.getObjects(new CriteriaQuery(Duracion.class, Where.equal("descripcion", descripcion)));
			ret = resultadoQuery.size() != 0;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (odb != null)
				odb.close();
		}
		return ret;
	}
	
	@Override
	public Duracion getDuracionPorDescripcion(String descripcion) {
		Duracion duracion = null;
		Objects<Duracion> resultadoQuery = null;
		odb = null;
		try {
			odb = bdConnector.getBDConnection();
			resultadoQuery = odb.getObjects(new CriteriaQuery(Duracion.class, Where.equal("descripcion", descripcion)));
			if (resultadoQuery.size() != 0)
				duracion = resultadoQuery.getFirst();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (odb != null)
				odb.close();
		}
		return duracion;		
	}
	
	@Override
	public Collection<Duracion> traerDuraciones() {
		return consultar(new CriteriaQuery(Duracion.class));
	}	

	@Override
	public void modificar(String descripcionOriginal, Duracion duracionModificada) {
		odb = null;
		try {
			odb = bdConnector.getBDConnection();
			IQuery query = new CriteriaQuery(Duracion.class, Where.equal("descripcion", descripcionOriginal));
			Objects<Duracion> resultadoQuery = odb.getObjects(query);

			Duracion d = resultadoQuery.getFirst();
			d.setDescripcion(duracionModificada.getDescripcion());
			d.setDuracion(duracionModificada.getDuracion());			

			odb.store(d);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (odb != null)
				odb.close();
		}
		
	}

	

}

