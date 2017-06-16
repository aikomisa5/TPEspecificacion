package com.EyVdeSW.TP.Daos;

import java.util.Collection;

import com.EyVdeSW.TP.domainModel.Duracion;
import com.EyVdeSW.TP.domainModel.Mensaje;

public interface DuracionDAO extends DAO<Duracion>{
	
	public void modificar (String descripcion , Duracion Duracion);
	public Collection<Duracion>traerTodosLosMensajes();
	public Collection<Duracion>consultarPorNombre(String nombreMensaje);
	public Mensaje getMensajePorNombre(String nombreMensaje);
	public boolean existe(String nombreMensaje);
}
