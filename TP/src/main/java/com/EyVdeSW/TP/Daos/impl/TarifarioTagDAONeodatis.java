package com.EyVdeSW.TP.Daos.impl;

import java.util.Collection;
import java.util.Date;

import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

import com.EyVdeSW.TP.Daos.TarifarioDAO;
import com.EyVdeSW.TP.domainModel.Campania;
import com.EyVdeSW.TP.domainModel.Tag;
import com.EyVdeSW.TP.domainModel.Tarifario;

public class TarifarioTagDAONeodatis extends DAONeodatis<Tarifario<Tag>> implements TarifarioDAO<Tarifario<Tag>>  {

	@Override
	public void modificar(Tarifario<Tag> original, Tarifario<Tag> modificacion) {
		odb=null;
		try {
			odb = bdConnector.getBDConnection();
			Tarifario<Tag> tarifario = new Tarifario<>(new Date());
			IQuery query = new CriteriaQuery(tarifario.getClass(), Where.equal("fecha", original.getFecha()));
			Objects<Tarifario<Tag>> resultadoQuery = odb.getObjects(query);
			
			Tarifario<Tag> t = resultadoQuery.getFirst();
			
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
	public Collection<Tarifario<Tag>> traerTodos() {
		Tarifario<Tag>t=new Tarifario<>(new Date());
		return consultar(new CriteriaQuery(t.getClass()));
	}

	@Override
	public Collection<Tarifario<Tag>> TraerPorFecha(Date fecha) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Tarifario<Tag>> TraerPorTextoFecha(String texto, Date fecha) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existe(Date fecha) {
		// TODO Auto-generated method stub
		return false;
	}




	

}
