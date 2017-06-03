package com.EyVdeSW.TP.Daos;

import com.EyVdeSW.TP.domainModel.Mensaje;

public interface MensajeDAO extends DAO<Mensaje>{
	
	public void modificar (String mensajeOriginal, String mensajeModificacion);

}
