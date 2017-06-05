package com.EyVdeSW.TP.Daos;

import java.util.Collection;

import com.EyVdeSW.TP.domainModel.Campania;

public interface CampañaDAO extends DAO<Campania>{
	
	public void modificar(Campania original, Campania modificacion);
	public Collection<Campania>traerTodos();
	public Collection<Campania>consultarPorNombre(String nombre);
	public Campania getCampañaPorNombre(String nombreCampaña);
	public boolean existe(String nombreCampaña);
}
