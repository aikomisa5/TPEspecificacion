package com.EyVdeSW.TP.Daos.impl;

import java.util.Collection;

import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

import com.EyVdeSW.TP.Daos.ArbolTagDAO;
import com.EyVdeSW.TP.domainModel.ArbolTag;

public class ArbolTagDAONeodatis extends DAONeodatis<ArbolTag> implements ArbolTagDAO {

	@Override
	public Collection<ArbolTag> traerArboles() {
		return consultar(new CriteriaQuery(ArbolTag.class));
	}

	

}
