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
			ap.setTexto(modificacion.getTexto());
			ap.setTitulo(modificacion.getTitulo());

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
		IQuery query = new CriteriaQuery(AccionPublicitaria.class);
		return consultar(query);
	}

	@Override
	public Collection<AccionPublicitaria> consultarPorDestinatario(String destinatario) {
		IQuery query = new CriteriaQuery(AccionPublicitaria.class, Where.like("destinatario", destinatario));
		return consultar(query);
	}

	@Override
	public AccionPublicitaria getAccion(String destinatario, String titulo, String texto) {
		AccionPublicitaria accion = null;
		Objects<AccionPublicitaria> resultadoQuery = null;
		odb = null;
		try
		{
			odb = bdConnector.getBDConnection();
			resultadoQuery = odb.getObjects(new CriteriaQuery(AccionPublicitaria.class, 
					Where.and().add(Where.equal("destinatario", destinatario))
					.add(Where.equal("titulo", titulo))
					.add(Where.equal("texto", texto))
					));
			if (resultadoQuery.size() != 0)
				accion = resultadoQuery.getFirst();
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
		return accion;
	}

	@Override
	public boolean existe(String destinatario, String titulo, String texto) {

		boolean ret = false;
		Objects<AccionPublicitaria> resultadoQuery = null;
		odb = null;
		try
		{
			odb = bdConnector.getBDConnection();
			resultadoQuery = odb.getObjects(new CriteriaQuery(AccionPublicitaria.class, 
					Where.and().add(Where.equal("destinatario", destinatario))
					.add(Where.equal("titulo", titulo))
					.add(Where.equal("texto", texto))
					));
			ret = ret || (resultadoQuery.size() != 0);
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
		return ret;
	}

}
