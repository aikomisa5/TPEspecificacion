package com.EyVdeSW.TP.services;

import java.util.List;
import java.util.stream.Collectors;

import com.EyVdeSW.TP.Daos.DuracionDAO;
import com.EyVdeSW.TP.Daos.impl.DuracionDAONeodatis;
import com.EyVdeSW.TP.domainModel.Duracion;

public class DuracionService {
	private DuracionDAO duracionDAO;
	private static DuracionService duracionService;

	private DuracionService() {
		duracionDAO = new DuracionDAONeodatis();
	}

	public static DuracionService getDuracionService() {
		if (duracionService == null) {
			duracionService = new DuracionService();
		}
		return duracionService;
	}

	public boolean existe(String descripcion) {
		return duracionDAO.existe(descripcion);
	}

	public void borrar(String descripcion) {
		if (existe(descripcion)) {
			duracionDAO.borrar(duracionDAO.getDuracionPorDescripcion(descripcion));
		}

	}

	public Duracion getDuracionPorDescripcion(String descripcion) {
		return duracionDAO.getDuracionPorDescripcion(descripcion);
	}

	public List<Duracion> traerDuraciones() {
		return duracionDAO.traerDuraciones().stream().collect(Collectors.toList());
	}

	public void modificar(String descripcionOriginal, Duracion duracionModificada) {
		if (existe(descripcionOriginal) && !existe(duracionModificada.getDescripcion()))
			duracionDAO.modificar(descripcionOriginal, duracionModificada);
	}
	
	public void guardar(String descripcion, Integer duracion){
		if (!existe(descripcion)){
			duracionDAO.guardar(new Duracion(descripcion, duracion));
		}
	}

}
