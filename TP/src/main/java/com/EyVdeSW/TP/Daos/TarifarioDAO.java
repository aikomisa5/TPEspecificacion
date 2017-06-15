package com.EyVdeSW.TP.Daos;

import java.util.Collection;
import java.util.Date;

import com.EyVdeSW.TP.domainModel.Tarifario;
import com.EyVdeSW.TP.domainModel.Tarifario.TipoTarifario;

public interface TarifarioDAO<T> extends DAO<T>{
	public void modificar(T original, T modificacion);
	public Collection<T>traerTodos();
	public Collection<T>traerTraerPorTipo(TipoTarifario tipo);
	public T traerPorFecha(Date fecha, TipoTarifario tipo);
	public T traerUltimo(TipoTarifario tipo);
	public boolean existe(Date fecha, TipoTarifario tipo);
}
