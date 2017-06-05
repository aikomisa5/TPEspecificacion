package com.EyVdeSW.TP.domainModel;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Campaña {
	
	private List<AccionPublicitaria> accionesPublicitarias;
	private Mensaje mensaje;
	private String nombre;
	private String descripcion;
	private Date fechaDeInicio;
	private EstadoCampaña estado;
	private UUID idCampaña;
	
	public enum EstadoCampaña{
		PLANIFICADA,
		PRELIMINAR,
		CANCELADA;
	}
	
	public Campaña(){
		
	}
	
	public Campaña(String nombre, String descripcion) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		estado=EstadoCampaña.PLANIFICADA;
		this.mensaje=null;
		this.fechaDeInicio=null;
		idCampaña= UUID.randomUUID();
	}

	public Campaña(List<AccionPublicitaria> accionesPublicitarias,String nombre, String descripcion, Mensaje mensaje, Date fechaDeInicio){
		this.accionesPublicitarias=accionesPublicitarias;
		this.nombre=nombre;
		this.descripcion=descripcion;
		this.mensaje=mensaje;
		this.fechaDeInicio=fechaDeInicio;
		estado=EstadoCampaña.CANCELADA;
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
	
	public EstadoCampaña getEstado() {
		return estado;
	}

	public void setEstado(EstadoCampaña estado) {
		this.estado = estado;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idCampaña);
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
		if (accionesPublicitarias == null) {
			if (other.accionesPublicitarias != null)
				return false;
		} else if (!accionesPublicitarias.equals(other.accionesPublicitarias))
			return false;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (estado != other.estado)
			return false;
		if (fechaDeInicio == null) {
			if (other.fechaDeInicio != null)
				return false;
		} else if (!fechaDeInicio.equals(other.fechaDeInicio))
			return false;
		if (mensaje == null) {
			if (other.mensaje != null)
				return false;
		} else if (!mensaje.equals(other.mensaje))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}

	
	
}
