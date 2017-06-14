package com.EyVdeSW.TP.Daos;

import java.util.Collection;
import java.util.Date;

public interface TarifarioDAO<T> extends DAO<T>{
	public void modificar(T original, T modificacion);
	public Collection<T>traerTodos();
	public Collection<T>TraerPorFecha(Date fecha);
	public Collection<T>TraerPorTextoFecha(String texto, Date fecha);
	public boolean existe(Date fecha);
}
