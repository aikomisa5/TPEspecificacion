package com.EyVdeSW.TP.domainModel;

import java.util.Date;
import java.util.List;

public class Campaña {
	
	private List<AccionPublicitaria> accionesPublicitarias;
	private Mensaje mensaje;
	private String nombre;
	private String descripcion;
	private Date fechaDeInicio;
	
	public Campaña(List<AccionPublicitaria> accionesPublicitarias,String nombre, String descripcion, Mensaje mensaje, Date fechaDeInicio){
		this.accionesPublicitarias=accionesPublicitarias;
		this.nombre=nombre;
		this.descripcion=descripcion;
		this.mensaje=mensaje;
		this.fechaDeInicio=fechaDeInicio;
	}
	
	public List<AccionPublicitaria> getAccionesPublicitarias() {
		return accionesPublicitarias;
	}

	public void setAccionesPublicitarias(List<AccionPublicitaria> accionesPublicitarias) {
		this.accionesPublicitarias = accionesPublicitarias;
	}

	public void addAccionPublicitaria(AccionPublicitaria accion){
		accionesPublicitarias.add(accion);
	}
	
	public Mensaje getMensaje() {
		return mensaje;
	}

	public void setMensaje(Mensaje mensaje) {
		this.mensaje = mensaje;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result + ((fechaDeInicio == null) ? 0 : fechaDeInicio.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Campaña other = (Campaña) obj;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (fechaDeInicio == null) {
			if (other.fechaDeInicio != null)
				return false;
		} else if (!fechaDeInicio.equals(other.fechaDeInicio))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}
	
}
