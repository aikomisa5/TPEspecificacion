package com.EyVdeSW.TP.services;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;

import properties.Parametros;

public class MailSender implements MessageSender, Job {

	@Override
	public void enviarMensaje(String destinatario, String encabezado, String mensaje) {		
		 // Recipient's email ID needs to be mentioned.
	      String to = destinatario;

	      // Sender's email ID needs to be mentioned
	      String from = Parametros.getProperty("email.user");
	      String pass=Parametros.getProperty("email.pass");
	      // Assuming you are sending email from localhost
	      String host = "smtp.mail.com";
	      	
	      // Get system properties
	      Properties properties = System.getProperties();

	      // Setup mail server
	      properties.setProperty("mail.smtp.host", host);
	      properties.remove("mail.smtps.auth");
	      properties.remove("mail.user", from);
	      properties.remove("mail.password", pass);

	      // Get the default Session object.
	      Session session = Session.getDefaultInstance(properties);

	      try {
	         // Create a default MimeMessage object.
	         MimeMessage message = new MimeMessage(session);

	         // Set From: header field of the header.
	         message.setFrom(new InternetAddress(from));

	         // Set To: header field of the header.
	         message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

	         // Set Subject: header field
	         message.setSubject(encabezado);

	         // Now set the actual message
	         message.setText(mensaje);
	         
	         // importante message.setSentDate(saras);
	         System.out.println(Parametros.getProperty("email.user")+" "+Parametros.getProperty("email.pass"));
	         
	        // Transport.send(message, Parametros.getProperty("email.user"), Parametros.getProperty("email.pass"));
	         Transport.send(message, Parametros.getProperty("email.user"), Parametros.getProperty("email.pass"));
	         System.out.println("Sent message successfully....");
	      }catch (MessagingException mex) {
	         mex.printStackTrace();
	      }
		
	}

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		JobKey key = context.getJobDetail().getKey();
		 JobDataMap dataMap = context.getJobDetail().getJobDataMap();
		 if(dataMap.size() != 0){
			 String destinatario = dataMap.getString("destinatario");
			 String encabezado = dataMap.getString("encabezado");
			 String mensaje = dataMap.getString("mensaje");
			 
			 enviarMensaje(destinatario, encabezado, mensaje);
		 }else{
			 System.out.println("algo salio mal");
		 }
		
	}
		
}