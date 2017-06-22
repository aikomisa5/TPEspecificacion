package com.EyVdeSW.TP.domainModel;

import java.util.Objects;
import java.util.UUID;

public class Mensaje {
	
	private String nombre; 
	private String textoMensaje;
	private UUID idMensaje;

	public Mensaje(String nombre,String textoMensaje)
	{
		this.nombre=nombre;
		this.textoMensaje=textoMensaje;
		idMensaje=UUID.randomUUID();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTextoMensaje() {
		return textoMensaje;
	}

	public void setTextoMensaje(String textoMensaje) {
		this.textoMensaje = textoMensaje;
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
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (textoMensaje == null) {
			if (other.textoMensaje != null)
				return false;
		} else if (!textoMensaje.equals(other.textoMensaje))
			return false;
		return true;
	}
	
	
	
}
