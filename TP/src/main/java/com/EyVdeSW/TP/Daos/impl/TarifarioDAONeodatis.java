package com.EyVdeSW.TP.Daos.impl;

import java.util.Collection;
import java.util.Date;

import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

import com.EyVdeSW.TP.Daos.TarifarioDAO;
import com.EyVdeSW.TP.domainModel.Tarifario;
import com.EyVdeSW.TP.domainModel.Tarifario.TipoTarifario;

public class TarifarioDAONeodatis extends DAONeodatis<Tarifario<String>> implements TarifarioDAO<Tarifario<String>>  {

	@Override
	public void modificar(Tarifario<String> original, Tarifario<String> modificacion) {
		odb=null;
		try {
			odb = bdConnector.getBDConnection();
			Tarifario<String> tarifario = new Tarifario<>();
			IQuery query = new CriteriaQuery(tarifario.getClass(), Where.equal("fecha", original.getFecha()));
			Objects<Tarifario<String>> resultadoQuery = odb.getObjects(query);
			
			Tarifario<String> t = resultadoQuery.getFirst();
			
			t.setFecha(modificacion.getFecha());
			t.setTarifas(modificacion.getTarifas());
			
			odb.store(t);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (odb != null)
				odb.close();
		}
	}

	@Override
	public Collection<Tarifario<String>> traerTodos() {
		Tarifario<String>t=new Tarifario<>();
		return consultar(new CriteriaQuery(t.getClass()));
	}
	
	@Override
	public Collection<Tarifario<String>> traerTraerPorTipo(TipoTarifario tipo) {
		Tarifario<String>t=new Tarifario<>();
		return consultar(new CriteriaQuery(t.getClass(), Where.equal("tipo", tipo)));
	}

	@Override
	public Tarifario<String> traerPorFecha(Date fecha, TipoTarifario tipo) {
		Tarifario<String>t=new Tarifario<>();
		IQuery query = new CriteriaQuery(t.getClass(), Where.and().add(Where.equal("tipo", tipo))
				.add(Where.equal("fecha", fecha)));
		return consultar(query).getFirst();
	}
	
	@Override
	public Tarifario<String> traerUltimo(TipoTarifario tipo) {
		Tarifario<String>t=new Tarifario<>();
		IQuery query = new CriteriaQuery(t.getClass(), Where.equal("tipo", tipo));
		query.orderByDesc("fecha");
		return consultar(query).getFirst();
	}

	@Override
	public boolean existe(Date fecha, TipoTarifario tipo) {
		Tarifario<String>t=new Tarifario<>();
		IQuery query = new CriteriaQuery(t.getClass(), Where.and().add(Where.equal("tipo", tipo))
				.add(Where.equal("fecha", fecha)));
		return consultar(query).size() != 0;
	}

	






	

}
