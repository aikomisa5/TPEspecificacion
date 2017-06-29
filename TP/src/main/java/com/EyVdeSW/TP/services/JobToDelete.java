package com.EyVdeSW.TP.services;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class JobToDelete implements Job{

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		//esta key es para las exceptions 
		 JobDataMap dataMap = context.getJobDetail().getJobDataMap();
		 if(dataMap.size() != 0){
			 String texto1 = dataMap.getString("texto1");
			 String texto2 = dataMap.getString("texto2");
			 System.out.println("Mi key es: "+"texto1"+" y mi texto "+texto1);
			 System.out.println("Mi key es: "+"texto2"+" y mi texto "+texto2);
		 }else{
			 System.out.println("soy el metodo sin parametros");
		 }
	     
	     
	     
	    
		
	}

}
