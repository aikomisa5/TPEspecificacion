package com.EyVdeSW.TP.Daos.impl;

import org.neodatis.odb.ODB;

import org.neodatis.odb.Objects;
import org.neodatis.odb.OdbConfiguration;
import org.neodatis.odb.core.query.IQuery;

import com.EyVdeSW.TP.Daos.DAO;

public class DAONeodatis<T> implements DAO<T> {
	protected neodatisBDConnector bdConnector;
	protected ODB odb;

	public DAONeodatis() {
		super();
		// Por defecto el conector es server.
		this.bdConnector = new NeodatisServerConnector();
	}

	@Override
	public void guardar(T t) {
		odb = null;
		try {
			getConexion();
			odb.store(t);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (odb != null) {
				// Cerramos la bd
				odb.close();
			}
		}

	}

	@Override
	public void borrar(T t) {
		odb = null;
		try {
			getConexion();
			Objects<T> objects = odb.getObjects(t.getClass());

			objects.forEach(o -> {
				if (t.equals(o))
					odb.delete(o);
			});

			// Guardamos los cambios
			odb.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (odb != null)
				odb.close();
		}
	}

	Objects<T> consultar(IQuery query) {
		odb = null;
		Objects<T> resultadoQuery = null;
		try {
			// Abrimos la bd
			getConexion();
			resultadoQuery = odb.getObjects(query);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (odb != null)
				odb.close();
		}
		return resultadoQuery;
	}
	

	public void setBdConnector(neodatisBDConnector bdConnector) {
		this.bdConnector = bdConnector;
	}	

	private void getConexion() {
		odb = bdConnector.getBDConnection();	
		OdbConfiguration.setReconnectObjectsToSession(true);		
	}

}
