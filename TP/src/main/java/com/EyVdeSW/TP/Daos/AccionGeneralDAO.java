package com.EyVdeSW.TP.Daos;

import java.util.Collection;

import com.EyVdeSW.TP.domainModel.AccionGeneral;
import com.EyVdeSW.TP.domainModel.Campaña;

public interface AccionGeneralDAO extends DAO<AccionGeneral> {

	public void modificar (AccionGeneral original, AccionGeneral modificacion);
	public Collection<AccionGeneral>traerTodos();
	public Collection<AccionGeneral>consultarPorNombre(String nombreAccionGeneral);
	public AccionGeneral getAccionGeneralPorNombre(String nombreAccionGeneral);
	public boolean existe(String nombreAccionGeneral);
}
