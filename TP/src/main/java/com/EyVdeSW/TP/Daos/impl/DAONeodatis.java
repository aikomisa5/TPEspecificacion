package com.EyVdeSW.TP.Daos.impl;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;

import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;

import com.EyVdeSW.TP.Daos.DAO;

import properties.Parametros;

public class DAONeodatis<T> implements DAO<T>
{
	private neodatisBDConnector bdConector;
	private ODB odb;

	
	public DAONeodatis() {
		super();
		//Por defecto el conector es local.
		this.bdConector = new NeodatisLocalConnector();
	}
	
	public DAONeodatis(neodatisBDConnector bdConector) {
		super();
		this.bdConector = bdConector;
	}

	@Override
	public void guardar(T t){
		odb = null;
		try{
			getConexion();
			odb.store(t);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if (odb != null){
				// Cerramos la bd
				odb.close();
			}
		}

	}

	private void getConexion() {
		odb = bdConector.getBDConnection();
	}

	@Override
	public void borrar(T t){
		odb = null;
		try{
			getConexion();
			Objects<T> objects = odb.getObjects(t.getClass());

			objects.forEach(o -> {
				if (t.equals(o))
					odb.deleteCascade(o);
			});

			// Guardamos los cambios
			odb.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if (odb != null)
				odb.close();
		}
	}
	
	Objects<T> consultar(IQuery query){
		ODB odb = null;
		Objects<T> resultadoQuery = null;
		try{
			// Abrimos la bd
			odb = ODBFactory.open(Parametros.getProperties().getProperty(Parametros.dbPath));
			resultadoQuery = odb.getObjects(query);			
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			if (odb != null)
				odb.close();
		}
		return resultadoQuery;
	}


}
