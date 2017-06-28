package com.EyVdeSW.TP.services;

import org.junit.Before;
import org.junit.Test;

import com.EyVdeSW.TP.domainModel.AccionPublicitaria;
import com.EyVdeSW.TP.domainModel.AccionPublicitaria.TipoAccion;

public class TestMailSenderSendGrid {
	MailSenderSendGrid sender = new MailSenderSendGrid();
	AccionPublicitariaService accionService=AccionPublicitariaService.getAccionPublicitariaService();
	
	@Before
	public void set(){
		accionService.setSender(new MailSenderSendGrid());
	}
	
	@Test
	public void enviarMail(){
		AccionPublicitaria ac1= new AccionPublicitaria("rober.gerszen@gmail.com", "De una accion", "soy el texto", TipoAccion.particular,
				2, "12", "23");
		accionService.enviarMensaje(ac1);
	}
	
	
	public void enviarMailDesdeService(){
		AccionPublicitaria ac1= new AccionPublicitaria("rober.gerszen@gmail.com", "Soy un test", "Enviado desde el service",
				TipoAccion.particular, 2, "12", "23");
		accionService.enviarMensaje(ac1.getDestinatario(), ac1.getTitulo(), ac1.getTexto());
	}

}
