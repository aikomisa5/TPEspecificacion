package com.EyVdeSW.TP.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class SchedulerPropio {
	//XXX a tener en cuenta, cada trigger puede tener 1 solo job, si tenemos muchos triggers cada uno debe tener un nombre
	//diferente, podemos usar el destinatario que sabemos que no se repite
	//Para mandar los mails podemos crear una clase que se encargue de almacenar los datos necesarios para el mail 
	//(destinatario, titulo y texto) y usamos .usingJobData("destinatario",destinatarioTraidoDeLaBD) 
	//luego en el JobSender instanciamos el map y traemos esos datos para mandar el mail 
	//(miren el ejemplo) seria algo como dataMap.getString("destinatario");
	//tambien podemos agregarle una fecha de inicio y una de fin, estas despues las parseamos
	//a String y las usamos cuando construimos el Job con .startAt(startDate) y .endAt(endDate)
	//https://stackoverflow.com/questions/19051350/run-quartz-scheduler-job-with-specific-start-end-date-and-within-time-constrain
	
	public static void main(String[] args) throws SchedulerException {
		//prueba1();
		//prueba2();
		pruebaMail("deidelson@mail.com", "Scheduler", "Exito!");
		
	}
	
	public static void prueba1() throws SchedulerException{
		JobDetail j1=JobBuilder.newJob(JobToDelete.class).build();
		//Trigger t = TriggerBuilder.newTrigger().withIdentity("SimpleTrigger").startNow().build();
		Trigger t = TriggerBuilder.newTrigger().withIdentity("CronTrigger")
				.withSchedule(CronScheduleBuilder.cronSchedule("0 0/1 * 1/1 * ? *")).build();
		
		Scheduler sc = StdSchedulerFactory.getDefaultScheduler();
		
		sc.start();
		sc.scheduleJob(j1, t);
		//sc.shutdown();
	}
	
	public static void prueba2() throws SchedulerException{
		//El usingJobData almacena en un map las cosas que le paso, en el job podemos levantarlo desde context
		JobDetail j1=JobBuilder.newJob(JobToDelete.class)
				.usingJobData("texto1","hola soy el primer texto")
				.usingJobData("texto2","hola soy el segundo texto")
				.build();
		//Trigger t = TriggerBuilder.newTrigger().withIdentity("SimpleTrigger").startNow().build();
		String startDateStr = "2017-06-23 00:00:00.0";
        String endDateStr = "2017-06-24 00:00:00.0";

        Date startDate;
        Date endDate;
		try {
			startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(startDateStr);
			endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(endDateStr);
			Trigger t = TriggerBuilder.newTrigger().withIdentity("CronTrigger1")
					.startAt(startDate)
					.withSchedule(CronScheduleBuilder.cronSchedule("0 0/1 * 1/1 * ? *"))
					.endAt(endDate)
					.build();
			
			Scheduler sc = StdSchedulerFactory.getDefaultScheduler();
			
			sc.start();
			sc.scheduleJob(j1, t);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Trigger t = TriggerBuilder.newTrigger().withIdentity("SimpleTrigger").startNow().build();
	
	}
	
	public static void pruebaPasando2Jobs() throws SchedulerException{
		JobDetail j1=JobBuilder.newJob(JobToDelete.class)
				.usingJobData("texto1","hola soy el primer texto")
				.usingJobData("texto2","hola soy el segundo texto")
				.build();
		
		JobDetail j2=JobBuilder.newJob(JobToDelete.class).build();
		
		
		//Trigger t = TriggerBuilder.newTrigger().withIdentity("SimpleTrigger").startNow().build();
		Trigger t = TriggerBuilder.newTrigger().withIdentity("CronTrigger1")
				.withSchedule(CronScheduleBuilder.cronSchedule("0 0/1 * 1/1 * ? *"))
				.build();
		
		Scheduler sc = StdSchedulerFactory.getDefaultScheduler();
		
		sc.start();
		sc.scheduleJob(j1, t);
		sc.scheduleJob(j2, TriggerBuilder.newTrigger().withIdentity("CronTrigger2")
				.withSchedule(CronScheduleBuilder.cronSchedule("0 0/1 * 1/1 * ? *")).build());
		
	}
	
	public static void pruebaMail(String destinatario, String encabezado, String mensaje ) throws SchedulerException{
		JobDetail j1=JobBuilder.newJob(MailSender.class)
				.usingJobData("destinatario",destinatario)
				.usingJobData("encabezado",encabezado)
				.usingJobData("mensaje", mensaje)
				.build();
		//Trigger t = TriggerBuilder.newTrigger().withIdentity("SimpleTrigger").startNow().build();
		String startDateStr = "2017-06-23 00:00:00.0";
        String endDateStr = "2017-06-24 00:00:00.0";

        Date startDate;
        Date endDate;
		try {
			startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(startDateStr);
			endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(endDateStr);
			Trigger t = TriggerBuilder.newTrigger().withIdentity("CronTrigger1")
					.startAt(startDate)
					.withSchedule(CronScheduleBuilder.cronSchedule("0 0/1 * 1/1 * ? *"))
					.endAt(endDate)
					.build();
			
			Scheduler sc = StdSchedulerFactory.getDefaultScheduler();
			
			sc.start();
			sc.scheduleJob(j1, t);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	

}