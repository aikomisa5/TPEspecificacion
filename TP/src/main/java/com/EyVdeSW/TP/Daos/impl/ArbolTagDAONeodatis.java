package com.EyVdeSW.TP.Daos.impl;

import java.util.Collection;
import java.util.List;

import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

import com.EyVdeSW.TP.Daos.ArbolTagDAO;
import com.EyVdeSW.TP.domainModel.ArbolTag;
import com.EyVdeSW.TP.domainModel.Tag;

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

	

}
