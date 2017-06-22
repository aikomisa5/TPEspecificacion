package com.EyVdeSW.TP.services;

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
		pruebaPasando2Jobs();
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
		Trigger t = TriggerBuilder.newTrigger().withIdentity("CronTrigger")
				.withSchedule(CronScheduleBuilder.cronSchedule("0 0/1 * 1/1 * ? *")).build();
		
		Scheduler sc = StdSchedulerFactory.getDefaultScheduler();
		
		sc.start();
		sc.scheduleJob(j1, t);
	}
	
	public static void pruebaPasando2Jobs() throws SchedulerException{
		JobDetail j1=JobBuilder.newJob(JobToDelete.class)
				.usingJobData("texto1","hola soy el primer texto")
				.usingJobData("texto2","hola soy el segundo texto")
				.build();
		
		JobDetail j2=JobBuilder.newJob(JobToDelete.class).build();
		//Trigger t = TriggerBuilder.newTrigger().withIdentity("SimpleTrigger").startNow().build();
		Trigger t = TriggerBuilder.newTrigger().withIdentity("CronTrigger1")
				.withSchedule(CronScheduleBuilder.cronSchedule("0 0/1 * 1/1 * ? *")).build();
		
		Scheduler sc = StdSchedulerFactory.getDefaultScheduler();
		
		sc.start();
		sc.scheduleJob(j1, t);
		sc.scheduleJob(j2, TriggerBuilder.newTrigger().withIdentity("CronTrigger2")
				.withSchedule(CronScheduleBuilder.cronSchedule("0 0/1 * 1/1 * ? *")).build());
		
	}
	
	

}
