package com.EyVdeSW.TP.domainModel;

import java.util.Date;

public class Campaña {
	
	private String nombre;
	private String descripcion;
	private Date fechaDeInicio;
	
	public Campaña(String nombre, String descripcion, Date fechaDeInicio){
		this.nombre=nombre;
		this.descripcion=descripcion;
		this.fechaDeInicio=fechaDeInicio;
	}
	

}
