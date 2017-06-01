package com.EyVdeSW.TP.Daos.impl;

import java.util.Collection;
import java.util.List;

import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

import com.EyVdeSW.TP.Daos.ArbolTagDAO;
import com.EyVdeSW.TP.domainModel.ArbolTag;
import com.EyVdeSW.TP.domainModel.Tag;


public class ArbolTagDAONeodatis extends DAONeodatis<ArbolTag> implements ArbolTagDAO {

	@Override
	public Collection<ArbolTag> traerArboles() {		
		return consultar(new CriteriaQuery(ArbolTag.class));
	}

	
	
	@Override
	public boolean esRaiz(Tag t) {		
		Objects<ArbolTag>arboles= consultar(new CriteriaQuery(ArbolTag.class));
		boolean ret=false;		
		for(ArbolTag a:arboles){ret=ret||a.getRaiz().getNombre().equals(t.getNombre());}		
		return ret;
		
	}

	@Override
	public ArbolTag getArbolPorNombreRaiz(String nombre) {
		ArbolTag arbol=null;		
		Objects<ArbolTag> resultadoQuery = null;
		try{			
			odb = bdConnector.getBDConnection();
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
	
	@Override
	public void borrar(ArbolTag t) {
		super.borrar(t);
		TagDAONeodatis tagDao = new TagDAONeodatis();
		tagDao.setBdConnector(bdConnector);		
		tagDao.borrar(t.getRaiz());
		t.getRaiz().getHijos().forEach(hijo -> tagDao.borrar(hijo));
		
	}

	

}
