package com.EyVdeSW.TP.Daos.impl;

import java.util.Collection;
import java.util.List;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

import com.EyVdeSW.TP.Daos.ArbolTagDAO;
import com.EyVdeSW.TP.domainModel.ArbolTag;
import com.EyVdeSW.TP.domainModel.Tag;

import properties.Parametros;

public class ArbolTagDAONeodatis extends DAONeodatis<ArbolTag> implements ArbolTagDAO {

	@Override
	public Collection<ArbolTag> traerArboles() {
		return consultar(new CriteriaQuery(ArbolTag.class));
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean esRaiz(Tag t) {
		List<ArbolTag>arboles= (List<ArbolTag>) consultar(new CriteriaQuery(ArbolTag.class));
		boolean ret=false;
		for(ArbolTag a:arboles){ret=ret||a.getRaiz().getNombre().equals(t.getNombre());}
		return ret;
	}

	@Override
	public ArbolTag getArbolPorNombreRaiz(String nombre) {
		ArbolTag arbol=null;
		ODB odb = null;
		Objects<ArbolTag> resultadoQuery = null;
		try{
			// Abrimos la bd
			odb = ODBFactory.open(Parametros.getProperties().getProperty(Parametros.dbPath));
			resultadoQuery = odb.getObjects(new CriteriaQuery(ArbolTag.class, Where.like("raiz.nombre", "%"+nombre+"%")));
			if(resultadoQuery.size() != 0)
				arbol= resultadoQuery.getFirst();
		}
		catch(Exception e){
			e.printStackTrace();
		}finally
		{
			if (odb != null)
				odb.close();
		}
		
		return arbol;
	}

	

}
