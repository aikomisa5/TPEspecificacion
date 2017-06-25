package com.EyVdeSW.TP.Daos.impl;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.neodatis.odb.OID;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.core.query.nq.SimpleNativeQuery;
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
	
	@Override
	public void guardar(Duracion duracion) {
		if (!existe(duracion.getDescripcion())){
			super.guardar(duracion);
		}
	}

	@Override
	public void borrarDuraciones() {
		Collection<Duracion> todasLasDuraciones = traerDuraciones();
		
		odb = null;
		try {
			odb = bdConnector.getBDConnection();
			Set<OID> oids = new HashSet<>();
			todasLasDuraciones.forEach(duracion -> oids.add(odb.getObjectId(duracion)));
			oids.forEach(odb::deleteObjectWithId);
			odb.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (odb != null)
				odb.close();
		}
		
	}

	@Override
	public Duracion getDuracionPorCantidadDeDias(long duracion) {
		Duracion ret = null;
		Objects<Duracion> resultadoQuery = consultar(new SimpleNativeQuery(){
			public boolean match (Duracion d){
				return d.getDuracion() == duracion;
			}
		});
		if (resultadoQuery.size() !=  0)
			ret = (Duracion) resultadoQuery.getFirst();
		else
			throw new IllegalArgumentException("No existe una duración con la cantidad de dias ingresada:(" + duracion + ") dias.");
		return ret;
	}

	

}

