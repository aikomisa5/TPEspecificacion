package com.EyVdeSW.TP.domainModel;

import java.util.Objects;
import java.util.UUID;

public class AccionPublicitaria 
{

	private String destinatario;
	private TipoAccion tipo;
	private String titulo;
	private String texto;
	private UUID idAccion;
	private int periodicidad;
	private String horaInicio;
	private String minutoInicio;
	
	

	public AccionPublicitaria(String destinatario, String titulo, String texto, TipoAccion tipo,
			int periodicidad, String horaInicio, String minutoInicio) {
		this.destinatario = destinatario;
		this.tipo = tipo;
		this.titulo = titulo;
		this.texto = texto;
		this.idAccion=UUID.randomUUID();
		this.periodicidad=periodicidad;
		this.horaInicio=horaInicio;
		this.minutoInicio=minutoInicio;
	}


	public AccionPublicitaria(String destinatario, TipoAccion tipo) {
		this.destinatario = destinatario;
		this.tipo = tipo;
		this.idAccion=UUID.randomUUID();
	}


	public String getDestinatario() {
		return destinatario;
	}



	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}



	public TipoAccion getTipo() {
		return tipo;
	}



	public void setTipo(TipoAccion tipo) {
		this.tipo = tipo;
	}
	
	public String getTitulo() {
		return titulo;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public String getTexto() {
		return texto;
	}


	public void setTexto(String texto) {
		this.texto = texto;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idAccion);
	}
	
	public int getPeriodicidad() {
		return periodicidad;
	}


	public void setPeriodicidad(int periodicidad) {
		this.periodicidad = periodicidad;
	}


	public String getHoraInicio() {
		return horaInicio;
	}


	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}


	public String getMinutoInicio() {
		return minutoInicio;
	}


	public void setMinutoInicio(String minutoInicio) {
		this.minutoInicio = minutoInicio;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AccionPublicitaria other = (AccionPublicitaria) obj;
		if (destinatario == null) {
			if (other.destinatario != null)
				return false;
		} else if (!destinatario.equals(other.destinatario))
			return false;
		if (texto == null) {
			if (other.texto != null)
				return false;
		} else if (!texto.equals(other.texto))
			return false;
		if (tipo != other.tipo)
			return false;
		if (titulo == null) {
			if (other.titulo != null)
				return false;
		} else if (!titulo.equals(other.titulo))
			return false;
		return true;
	}
	
	
	@Override
	public String toString() {
		return "destinatario: " + destinatario + ", titulo: " + titulo;
	}


	public enum TipoAccion{
		general,
		particular
	}
	
	public UUID getIdAccion() {
		return this.idAccion;
	}
	
}
