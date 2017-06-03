package com.EyVdeSW.TP.Daos;

import com.EyVdeSW.TP.domainModel.AccionGeneral;

public interface AccionGeneralDAO extends DAO<AccionGeneral> {

	public void modificar (AccionGeneral original, AccionGeneral modificacion);
	
}
