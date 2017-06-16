package com.EyVdeSW.TP.Daos;

import java.util.Collection;

import com.EyVdeSW.TP.domainModel.Duracion;
import com.EyVdeSW.TP.domainModel.Mensaje;

public interface DuracionDAO extends DAO<Duracion>{
	
	public void modificar (String descripcion , Duracion duracion);
	public Collection<Duracion>traerDuraciones();	
	public Mensaje getDuracionPorDescripcion(String descripcion);
	public boolean existe(String descripcion);
}
