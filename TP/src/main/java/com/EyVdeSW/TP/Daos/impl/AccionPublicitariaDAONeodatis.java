package com.EyVdeSW.TP.Daos.impl;

import java.util.Collection;

import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

import com.EyVdeSW.TP.Daos.AccionPublicitariaDAO;
import com.EyVdeSW.TP.domainModel.AccionPublicitaria;
import com.EyVdeSW.TP.domainModel.Campania;

public class AccionPublicitariaDAONeodatis extends DAONeodatis<AccionPublicitaria> implements AccionPublicitariaDAO {

	@Override
	public void modificar(AccionPublicitaria original, AccionPublicitaria modificacion) {
		odb = null;
		try
		{
			odb = bdConnector.getBDConnection();
			IQuery query = new CriteriaQuery(AccionPublicitaria.class, Where.like("destinatario", original.getDestinatario()));
			Objects<AccionPublicitaria> resultadoQuery = odb.getObjects(query);

			AccionPublicitaria ap = resultadoQuery.getFirst();
			ap.setDestinatario(modificacion.getDestinatario());
			ap.setTipo(modificacion.getTipo());

			odb.store(ap);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (odb != null)
				odb.close();
		}
		
	}

	@Override
	public Collection<AccionPublicitaria> traerTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<AccionPublicitaria> consultarPorNombre(String destinatario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AccionPublicitaria getCampañaPorNombre(String destinatario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existe(String destinatario) {
		// TODO Auto-generated method stub
		return false;
	}

}
