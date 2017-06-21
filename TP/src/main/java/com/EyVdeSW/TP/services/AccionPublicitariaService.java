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
	
	public void guardar(String destinatario,String titulo,String msg, String tipo){
		String destMinuscula = destinatario.toLowerCase();
		String tituloMinuscula=titulo.toLowerCase();
		String msgMinuscula=msg.toLowerCase();
		if(!accionDAO.existe(destMinuscula, tituloMinuscula, msgMinuscula)){
			AccionPublicitaria ap = new AccionPublicitaria(destMinuscula,tituloMinuscula,msgMinuscula, TipoAccion.valueOf(tipo));
			accionDAO.guardar(ap);
		}
	}
	
	public void borrar(String destinatario, String titulo,String msg, String tipo){
		String destMinuscula = destinatario.toLowerCase();
		String tituloMinuscula=titulo.toLowerCase();
		String msgMinuscula=msg.toLowerCase();
		if(accionDAO.existe(destMinuscula, tituloMinuscula, msgMinuscula)){
			AccionPublicitaria ap = accionDAO.getAccion(destMinuscula, tituloMinuscula, msgMinuscula);
			accionDAO.borrar(ap);
		}
	}
	
	public boolean modificar(String oDest,String oTitulo,String oMsg, String mDest,String mTitulo,String mMsg, String mTipo){
		boolean ret=true;
		oDest=oDest.toLowerCase();
		oTitulo=oTitulo.toLowerCase();
		oMsg=oMsg.toLowerCase();
		mDest=mDest.toLowerCase();
		mTitulo=mTitulo.toLowerCase();
		mMsg=mMsg.toLowerCase();
		if(!accionDAO.existe(oDest, oTitulo, oMsg) || accionDAO.existe(mDest, mTitulo, mMsg)){
			ret=false; 
		}else{
			AccionPublicitaria original = accionDAO.getAccion(oDest, oTitulo, oMsg);
			AccionPublicitaria modificacion = accionDAO.getAccion(oDest, oTitulo, oMsg);
			modificacion.setDestinatario(mDest);
			modificacion.setTipo(TipoAccion.valueOf(mTipo));
			modificacion.setTitulo(mTitulo);
			modificacion.setTexto(mMsg);
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
