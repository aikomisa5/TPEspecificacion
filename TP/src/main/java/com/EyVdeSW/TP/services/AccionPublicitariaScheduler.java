package com.EyVdeSW.TP.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;

import com.EyVdeSW.TP.domainModel.AccionPublicitaria;
import com.EyVdeSW.TP.domainModel.Campania;

public class AccionPublicitariaScheduler {
	private static AccionPublicitariaScheduler mailScheduler;
	private Scheduler sc;
	private MessageSender sender;
	
	private AccionPublicitariaScheduler(){
		try {
			sc=StdSchedulerFactory.getDefaultScheduler();
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
	
	public void setSendender(MessageSender s){
		this.sender=s;
	}
	
	public static AccionPublicitariaScheduler getMailScheduler(){
		if(mailScheduler==null){
			mailScheduler= new AccionPublicitariaScheduler();
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
	
	public void agregarAccion(String fechaInicio, String fechaFin, String destinatario, String encabezado, String mensaje, String hora,
			String minuto, String perioricidad, String idCampaña, String idAccion){
	
		try {
			String horan="0 "+minuto+" "+hora;
			horan=horan+" 1/"+perioricidad;
			horan=horan+" * ? *";
			JobDetail j1=JobBuilder.newJob(sender.getClass())
					.usingJobData("destinatario",destinatario)
					.usingJobData("encabezado",encabezado)
					.usingJobData("mensaje", mensaje)
					.build();
			
			Date startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(fechaInicio);
			Date endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(fechaFin);
			
			String nombreUnico=idCampaña+idAccion;
			
			Trigger t = TriggerBuilder.newTrigger().withIdentity(nombreUnico)
					.startAt(startDate)
					.withSchedule(CronScheduleBuilder.cronSchedule(horan))
					.endAt(endDate)
					.build();
			sc.scheduleJob(j1, t);
		} catch (SchedulerException | ParseException e) {
			e.printStackTrace();
		}
	}
	
	public void agregarAccionesDeCampañas(List<Campania>campañas){
			if(campañas.size() != 0 && campañas != null){
				for(Campania c:campañas){
						agregarAccionesDeCampaña(c);
					}
				}
	}

	public void agregarAccionesDeCampaña(Campania campaña){
		if(campaña.getAccionesPublicitarias()!=null && campaña.getAccionesPublicitarias().size()!=0){
			for(AccionPublicitaria ac:campaña.getAccionesPublicitarias()){
				String fechaIncio=transformarFecha(campaña.getFechaDeInicio());
				String fechaFin=transformarFecha(campaña.getFechaDeFin());
				agregarAccion(fechaIncio, fechaFin, ac.getDestinatario(), ac.getTitulo(),
						ac.getTexto(), ac.getHoraInicio(), ac.getMinutoInicio(), Integer.toString(ac.getPeriodicidad()),
						campaña.getIdCampania().toString(), ac.getIdAccion().toString());
			}
		}
	}
	
	public void cancelarCampaña(Campania campaña){
		if(campaña.getAccionesPublicitarias()!=null && campaña.getAccionesPublicitarias().size()!=0){
			campaña.getAccionesPublicitarias().forEach(accion -> {
				quitarJobDePlanificacion(campaña.getIdCampania().toString(),accion.getIdAccion().toString());
			});
			System.out.println("La campaña se cancelo correctamente");
		}
	}
	
	public void quitarJobDePlanificacion(String claveCampaña,String claveAccion){
		try {
			sc.unscheduleJob(TriggerKey.triggerKey(claveCampaña+claveAccion));
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
	
	public static String transformarFecha(Date fecha){
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(fecha);
	    int year = cal.get(Calendar.YEAR);
	    int month = cal.get(Calendar.MONTH)+1;
	    int day = cal.get(Calendar.DAY_OF_MONTH);
	    String mes= Integer.toString(month);
	    if(mes.length()<2)
	    	mes = "0"+Integer.toString(month);
	    String ret = Integer.toString(year)+"-"+mes+"-"+Integer.toString(day)+" 00:00:00.0";
	    return ret;
	}
	

}