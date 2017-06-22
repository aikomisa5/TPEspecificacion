package com.EyVdeSW.TP.services;

import java.util.Date;
import java.util.List;

import org.w3c.dom.ls.LSException;

import com.EyVdeSW.TP.Daos.CampañaDAO;
import com.EyVdeSW.TP.Daos.impl.CampañaDAONeodatis;
import com.EyVdeSW.TP.domainModel.AccionPublicitaria;
import com.EyVdeSW.TP.domainModel.Campania;
import com.EyVdeSW.TP.domainModel.Duracion;
import com.EyVdeSW.TP.domainModel.Mensaje;
import com.EyVdeSW.TP.domainModel.Tag;
import com.EyVdeSW.TP.domainModel.Usuario;

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
	
	public void guardar (Usuario usuario, String nombre, String descripcion, List<AccionPublicitaria> accionesPublicitarias, List<Tag> tagsAsociados, Mensaje mensaje,
			Date fechaDeInicio, Duracion duracion){
		
		nombre=nombre.toLowerCase();
		
		if (!campañaDAO.existe(nombre)){
			campañaDAO.guardar(new Campania(usuario, nombre,descripcion, accionesPublicitarias, tagsAsociados, mensaje,
					fechaDeInicio, duracion));
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
		}else
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
