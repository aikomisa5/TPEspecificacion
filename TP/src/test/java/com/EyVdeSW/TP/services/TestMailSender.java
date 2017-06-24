package com.EyVdeSW.TP.services;

import static org.junit.Assert.*;

import java.util.Properties;

import org.junit.Test;

public class TestMailSender {
	MailSender sender = new MailSender();
	
	@Test
	public void enviarMail(){
		sender.enviarMensaje("deidelson@mail.com", "Soy un test", "soy el texto");
		sender.enviarMensaje("danilo_eidelson@hotmail.com", "Soy un test", "soy el texto");
	}

}
