package com.EyVdeSW.TP.services;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class TestMailScheduler {
	MailScheduler ms=MailScheduler.getMailScheduler();
	
	@Test
	public void pruebaRunTime() throws ParseException {
		ms.encender();
		String startDateStr = "2017-06-24 00:00:00.0";
        String endDateStr = "2017-06-25 00:00:00.0";
        
		try {
			ms.agregarAccion(startDateStr, endDateStr, "deidelson@mail.com", 
						"Prueba del service", "exito", "12","54", "1");
			//Cambiame de acuerdo al horario
			
			 TimeUnit.SECONDS.sleep(300);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
