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
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFechaDeInicio() {
		return fechaDeInicio;
	}

	public void setFechaDeInicio(Date fechaDeInicio) {
		this.fechaDeInicio = fechaDeInicio;
	}
	
}
