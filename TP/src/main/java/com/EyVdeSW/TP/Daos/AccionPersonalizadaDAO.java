package com.EyVdeSW.TP.Daos;

import com.EyVdeSW.TP.domainModel.AccionPersonalizada;

public interface AccionPersonalizadaDAO extends DAO<AccionPersonalizada>{

	public void modificar (AccionPersonalizada original, AccionPersonalizada modificacion);
	
}
