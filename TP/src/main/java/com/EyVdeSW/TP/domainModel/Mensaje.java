package com.EyVdeSW.TP.domainModel;

import java.util.Objects;
import java.util.UUID;

public class Mensaje {
	
	private String titulo; 
	private String cuerpo;
	private UUID idMensaje;

	public Mensaje(String nombre,String textoMensaje)
	{
		this.titulo=nombre;
		this.cuerpo=textoMensaje;
		idMensaje=UUID.randomUUID();
	}

	public String getNombre() {
		return titulo;
	}

	public void setNombre(String nombre) {
		this.titulo = nombre;
	}

	public String getTextoMensaje() {
		return cuerpo;
	}

	public void setTextoMensaje(String textoMensaje) {
		this.cuerpo = textoMensaje;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idMensaje);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Mensaje other = (Mensaje) obj;
		if (titulo == null) {
			if (other.titulo != null)
				return false;
		} else if (!titulo.equals(other.titulo))
			return false;
		if (cuerpo == null) {
			if (other.cuerpo != null)
				return false;
		} else if (!cuerpo.equals(other.cuerpo))
			return false;
		return true;
	}
	
	
	
}
