package com.EyVdeSW.TP.services;

import org.quartz.Job;

import com.EyVdeSW.TP.domainModel.AccionPublicitaria;

public interface MessageSender extends Job{
	
	public void enviarMensaje(String mensaje, String encabezado, String destinatario);
	public void enviarMensaje(AccionPublicitaria accion);
}
