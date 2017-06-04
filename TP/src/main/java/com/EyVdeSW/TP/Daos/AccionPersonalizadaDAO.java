package com.EyVdeSW.TP.Daos;

import java.util.Collection;

import com.EyVdeSW.TP.domainModel.AccionPersonalizada;

public interface AccionPersonalizadaDAO extends DAO<AccionPersonalizada>{

	public void modificar (AccionPersonalizada original, AccionPersonalizada modificacion);
	public Collection<AccionPersonalizada>traerTodos();
	public Collection<AccionPersonalizada>consultarPorNombre(String nombreAccionPersonalizada);
	public AccionPersonalizada getAccionPersonalizadaPorNombre(String nombreAccionPersonalizada);
	public boolean existe(String nombreAccionPersonalizada);
}
