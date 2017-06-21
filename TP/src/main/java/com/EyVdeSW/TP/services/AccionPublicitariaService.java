package com.EyVdeSW.TP.services;

import java.util.Collection;

import com.EyVdeSW.TP.Daos.AccionPublicitariaDAO;
import com.EyVdeSW.TP.Daos.impl.AccionPublicitariaDAONeodatis;
import com.EyVdeSW.TP.domainModel.AccionPublicitaria;
import com.EyVdeSW.TP.domainModel.AccionPublicitaria.TipoAccion;

public class AccionPublicitariaService {
	private AccionPublicitariaDAO accionDAO;
	private MessageSender sender;
	private static AccionPublicitariaService service;
	
	private AccionPublicitariaService(){
		accionDAO=new AccionPublicitariaDAONeodatis();
	}
	
	public static AccionPublicitariaService getAccionPublicitariaService(){
		if(service==null)
			service= new AccionPublicitariaService();
		return service;
	}
	
	public void setSender(MessageSender sender){
		this.sender=sender;
	}
	
	public void enviarMensaje(String mensaje, String encabezado, String destinatario){
		sender.enviarMensaje(mensaje, encabezado,destinatario);
	}
	
	public void guardar(String destinatario, String tipo){
		String destMinuscula = destinatario.toLowerCase();
		if(!accionDAO.existe(destMinuscula)){
			AccionPublicitaria ap = new AccionPublicitaria(destMinuscula, TipoAccion.valueOf(tipo));
			accionDAO.guardar(ap);
		}
	}
	
	public void borrar(String destinatario){
		String destMinuscula = destinatario.toLowerCase();
		if(accionDAO.existe(destMinuscula)){
			AccionPublicitaria ap = accionDAO.getAccionPorDestinatario(destMinuscula);
			accionDAO.borrar(ap);
		}
	}
	
	public boolean modificar(String orig, String modi, String tipo){
		boolean ret=true;
		orig=orig.toLowerCase();
		modi=modi.toLowerCase();
		if(!accionDAO.existe(orig) || accionDAO.existe(modi)){
			ret=false; 
		}else{
			AccionPublicitaria original = accionDAO.getAccionPorDestinatario(orig);
			AccionPublicitaria modificacion = accionDAO.getAccionPorDestinatario(orig);
			modificacion.setDestinatario(modi);
			modificacion.setTipo(TipoAccion.valueOf(tipo));
			accionDAO.modificar(original, modificacion);
		}
		return ret;
	}
	
	public Collection<AccionPublicitaria>traerTodos(){
		return accionDAO.traerTodos();
	}

	//XXX ver si es necesario! (Depende de si usamos combo)
	private boolean valorValido(String valor){
		boolean ret=false;
		for(TipoAccion tipo:TipoAccion.values()){
			ret = ret || tipo.name().equals(valor);
		}
		return ret;
	}
}
