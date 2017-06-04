package com.EyVdeSW.TP.Daos;

import java.util.Collection;

import com.EyVdeSW.TP.domainModel.Campaña;

public interface CampañaDAO extends DAO<Campaña>{
	
	public void modificar(Campaña original, Campaña modificacion);
	public Collection<Campaña>traerTodos();
	public Collection<Campaña>consultarPorNombre(String nombre);
	public Campaña getCampañaPorNombre(String nombreCampaña);
	public boolean existe(String nombreCampaña);
}
