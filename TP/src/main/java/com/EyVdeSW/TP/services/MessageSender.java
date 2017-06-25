package com.EyVdeSW.TP.services;

import org.quartz.Job;

public interface MessageSender extends Job{
	
	public void enviarMensaje(String mensaje, String encabezado, String destinatario);
}
