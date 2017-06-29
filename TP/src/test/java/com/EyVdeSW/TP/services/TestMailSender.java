package com.EyVdeSW.TP.services;

import org.junit.Before;
import org.junit.Test;

import com.EyVdeSW.TP.domainModel.AccionPublicitaria;
import com.EyVdeSW.TP.domainModel.AccionPublicitaria.TipoAccion;

public class TestMailSender {
	MailSender sender = new MailSender();
	AccionPublicitariaService accionService=AccionPublicitariaService.getAccionPublicitariaService();
	
	@Before
	public void set(){
		accionService.setSender(new MailSender());
	}
	
	@Test
	public void enviarMail(){
		AccionPublicitaria ac1= new AccionPublicitaria("deidelson@mail.com", "De una accion", "soy el texto", TipoAccion.particular,
				2, "12", "23");
		accionService.enviarMensaje(ac1);
	}
	
	//@Test
	public void enviarMailDesdeService(){
		AccionPublicitaria ac1= new AccionPublicitaria("deidelson@mail.com", "Soy un test", "Enviado desde el service",
				TipoAccion.particular, 2, "12", "23");
		accionService.enviarMensaje(ac1.getDestinatario(), ac1.getTitulo(), ac1.getTexto());
	}

}
