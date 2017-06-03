package com.EyVdeSW.TP.Daos;

import java.util.Collection;

import com.EyVdeSW.TP.domainModel.Mensaje;

public interface MensajeDAO extends DAO<Mensaje>{
	
	public void modificar (String mensajeOriginal, String mensajeModificacion);
	public Collection<Mensaje>traerTodosLosMensajes();
	public Collection<Mensaje>consultarPorNombre(String nombreMensaje);
	public Mensaje getMensajePorNombre(String nombreMensaje);
	public boolean existe(String nombreMensaje);
}
