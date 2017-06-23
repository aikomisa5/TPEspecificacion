package com.EyVdeSW.TP.Daos;

import java.util.Collection;

import com.EyVdeSW.TP.domainModel.Duracion;

public interface DuracionDAO extends DAO<Duracion>{
	
	public void modificar (String descripcionOriginal , Duracion duracionModificada);
	public Collection<Duracion>traerDuraciones();	
	public Duracion getDuracionPorDescripcion(String descripcion);
	public boolean existe(String descripcion);
}
