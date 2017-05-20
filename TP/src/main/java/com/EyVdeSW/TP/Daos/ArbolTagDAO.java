package com.EyVdeSW.TP.Daos;

import java.util.Collection;

import com.EyVdeSW.TP.domainModel.ArbolTag;

public interface ArbolTagDAO extends DAO<ArbolTag> {
	
	public Collection<ArbolTag>traerArbol();
	

}
