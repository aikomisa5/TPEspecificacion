package com.EyVdeSW.TP.services;

import java.util.Date;
import java.util.List;

import com.EyVdeSW.TP.Daos.CampañaDAO;
import com.EyVdeSW.TP.Daos.impl.CampañaDAONeodatis;
import com.EyVdeSW.TP.domainModel.AccionPublicitaria;
import com.EyVdeSW.TP.domainModel.Campania;
import com.EyVdeSW.TP.domainModel.Mensaje;

public class CampañaService {

	private CampañaDAO campañaDAO;
	private static CampañaService campañaService;
	
	private CampañaService() {
		campañaDAO = new CampañaDAONeodatis();
	}

	public static CampañaService getCampañaService() {
		if (campañaService == null) {
			campañaService = new CampañaService();
		}
		return campañaService;
	}
	
	public void guardar(Campania campaña){
		campañaDAO.guardar(campaña);
		
	}
	
	public void guardar (String nombreCampaña, String descripcionCampaña, 
			String nombreMensaje, String textoMensaje, Date fechaDeInicio){
		
		String nombreMinuscula = nombreCampaña.toLowerCase();
		String descripcionMinuscula = descripcionCampaña.toLowerCase();
		
		if (!campañaDAO.existe(nombreMinuscula)){
			List<AccionPublicitaria> accionesPublicitarias = null;
			//TODO
			//campañaDAO.guardar(new Campania(accionesPublicitarias, nombreMinuscula, descripcionMinuscula, new Mensaje(nombreMensaje, textoMensaje), fechaDeInicio));
			
		}
	}
	
	public void borrar (String nombreCampaña){
		String nombreMinuscula = nombreCampaña.toLowerCase();
		if (campañaDAO.existe(nombreMinuscula)){
			Campania campaña = campañaDAO.getCampañaPorNombre(nombreMinuscula);
			campañaDAO.borrar(campaña);
		}
	}
	
	public boolean modificar (String nombreCampañaOriginal, String nombreModificacion, 
			String descripcionCampañaMod, String nombreMensajeMod, String textoMensajeMod, Date fechaDeInicioMod){
		boolean ret = true;
		if (campañaDAO.existe(nombreModificacion)) {
			ret = false;
		}
		{
			String modificacionMinuscula = nombreModificacion.toLowerCase();
			String descripcionMinuscula = descripcionCampañaMod.toLowerCase();
			
			Campania orig = campañaDAO.getCampañaPorNombre(nombreCampañaOriginal);
			Campania modi = campañaDAO.getCampañaPorNombre(nombreCampañaOriginal);
			
			modi.setNombre(modificacionMinuscula);
			modi.setDescripcion(descripcionMinuscula);
			modi.setMensaje(new Mensaje(nombreMensajeMod, textoMensajeMod));
			modi.setFechaDeInicio(fechaDeInicioMod);
			
			campañaDAO.modificar(orig, modi);
		}
		return ret;
		
	}
		
}
