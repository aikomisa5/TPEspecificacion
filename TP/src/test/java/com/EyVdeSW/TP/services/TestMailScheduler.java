package com.EyVdeSW.TP.services;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

public class TestMailScheduler {
	MailScheduler ms=MailScheduler.getMailScheduler();
	
	@Test
	public void pruebaRunTime() {
		ms.encender();
		//ms.agregarAccion(new Date(20170623), new Date(20170624), "deidelson@mail.com", 
				//"Prueba del service", "exito", "20 14");
	}

}
