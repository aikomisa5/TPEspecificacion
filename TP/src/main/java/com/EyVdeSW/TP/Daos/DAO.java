package com.EyVdeSW.TP.Daos;

public interface DAO<T>
{
	public void guardar(T t);

	public void borrar(T t);
	public void modificar(T actual, T nuevo);
	
}
