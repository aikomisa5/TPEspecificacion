package com.EyVdeSW.TP.Daos;

import java.util.Collection;

import com.EyVdeSW.TP.domainModel.ArbolTag;
import com.EyVdeSW.TP.domainModel.Tag;


public interface ArbolTagDAO extends DAO<ArbolTag> {
	
	public Collection<ArbolTag>traerArboles();
	public boolean esRaiz(Tag t);
	public ArbolTag getArbolPorNombreRaiz(String nombre);
	

}
