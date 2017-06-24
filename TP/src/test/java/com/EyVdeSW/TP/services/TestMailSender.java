package com.EyVdeSW.TP.services;

import static org.junit.Assert.*;

import java.util.Properties;

import org.junit.Test;

import com.EyVdeSW.TP.domainModel.AccionPublicitaria;
import com.EyVdeSW.TP.domainModel.AccionPublicitaria.TipoAccion;

public class TestMailSender {
	MailSender sender = new MailSender();
	
	@Test
	public void enviarMail(){
		AccionPublicitaria ac1= new AccionPublicitaria("deidelson@mail.com", "Soy un test", "soy el texto", TipoAccion.particular,
				2, "12", "23");
		sender.enviarMensaje(ac1.getDestinatario(), ac1.getTitulo(), ac1.getTexto());
		sender.enviarMensaje("danilo_eidelson@hotmail.com", "Soy un test", "soy el texto");
	}

}
