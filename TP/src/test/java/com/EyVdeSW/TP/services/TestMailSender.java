package com.EyVdeSW.TP.services;

import static org.junit.Assert.*;

import java.util.Properties;

import org.junit.Test;

public class TestMailSender {
	MailSender sender = new MailSender();
	
	@Test
	public void enviarMail(){
Properties p = System.getProperties();
//		System.out.println(System.getProperty("mail.smtp.port")+"sarasa");
//		System.clearProperty("mail.smtp.socketFactory.port");
//		System.clearProperty("mail.smtp.socketFactory.class");
//		System.clearProperty("mail.smtp.auth");
//		System.clearProperty("mail.smtp.port");
//		
//		props.put("mail.smtp.socketFactory.port", "465");
//		props.put("mail.smtp.socketFactory.class",
//				"javax.net.ssl.SSLSocketFactory");
//		props.put("mail.smtp.auth", "true");
//		props.put("mail.smtp.port", "465");

		
		sender.enviarMensaje("deidelson@mail.com", "Soy un test", "soy el texto");
		sender.enviarMensaje("danilo_eidelson@hotmail.com", "Soy un test", "soy el texto");
	}

}
