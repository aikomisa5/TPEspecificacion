package com.EyVdeSW.TP.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import com.EyVdeSW.TP.Daos.CampañaDAO;
import com.EyVdeSW.TP.Daos.impl.CampañaDAONeodatis;

public class MailScheduler {
	private static MailScheduler mailScheduler;
	private CampañaDAO campañaDAO;
	private Scheduler sc;
	
	private MailScheduler(){
		campañaDAO= new CampañaDAONeodatis();
		try {
			sc=StdSchedulerFactory.getDefaultScheduler();
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static MailScheduler getMailScheduler(){
		if(mailScheduler==null){
			mailScheduler= new MailScheduler();
		}
		return mailScheduler;
	}
	
	public void encender(){
		try {
			sc.start();
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
	
	public void apagar(){
		try {
			sc.shutdown();
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
	
	public void agregarAccion(String fechaInicio, String fechaFin, String destinatario, String encabezado, String mensaje, String hora) throws ParseException{
	
		try {
			String horan="0 "+hora;
			horan=horan+" 1/1 * ? *";
			JobDetail j1=JobBuilder.newJob(MailSender.class)
					.usingJobData("destinatario",destinatario)
					.usingJobData("encabezado",encabezado)
					.usingJobData("mensaje", mensaje)
					.build();
			
			Date startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(fechaInicio);
			Date endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(fechaFin);
			
			Trigger t = TriggerBuilder.newTrigger().withIdentity("CronTrigger1")
					.startAt(startDate)
					.withSchedule(CronScheduleBuilder.cronSchedule(horan))
					.endAt(endDate)
					.build();
			sc.scheduleJob(j1, t);
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void agregarAccionesDeCampañasVigentes(){
		
	}

	public static void main(String[] args) {
		MailScheduler ms=MailScheduler.getMailScheduler();
		ms.encender();
		String startDateStr = "2017-06-23 00:00:00.0";
        String endDateStr = "2017-06-24 00:00:00.0";//sad
        
		try {
			ms.agregarAccion(startDateStr, endDateStr, "deidelson@mail.com", 
						"Prueba del service", "exito", "43 14");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
