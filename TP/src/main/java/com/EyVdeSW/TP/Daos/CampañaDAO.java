package com.EyVdeSW.TP.Daos;

import java.util.Collection;
import java.util.UUID;

import com.EyVdeSW.TP.domainModel.Campania;
import com.EyVdeSW.TP.domainModel.Usuario;

public interface CampañaDAO extends DAO<Campania>{
	
	public void modificar(Campania original, Campania modificacion);
	public Collection<Campania>traerTodos();
	public Collection<Campania>consultarPorNombre(String nombre);
	public Campania getCampañaPorNombre(String nombreCampaña);
	public boolean existe(String nombreCampaña);
	public Usuario getOwner(String nombreCampaña);
	public Collection<Campania> getCampañasDe(Usuario user);
	public Collection<Campania> getCampañasVigentes();
	public Campania getCampañaPorId(UUID id);
	public void modificar(UUID idCampania, Campania campaña);
}
