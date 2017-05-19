package com.EyVdeSW.TP.Daos;

import java.util.List;

import org.neodatis.odb.Objects;

public interface DAO<T>
{
	public void guardar(T t);

	public void borrar(T t);
	public void modificar(T actual, T nuevo);
	
}
