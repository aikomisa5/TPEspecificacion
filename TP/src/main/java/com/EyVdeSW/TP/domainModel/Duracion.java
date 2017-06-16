package com.EyVdeSW.TP.domainModel;

import java.util.Objects;
import java.util.UUID;

public class Duracion {
	private String descripcion;
	private Integer duracion;
	private UUID idDuracion;

	public Duracion(String descripcion, Integer duracion) {
		setDescripcion(descripcion);
		setDuracion(duracion);
		idDuracion = UUID.randomUUID();
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		if (descripcion == null)
			throw new NullPointerException("La descripción es nula!");
		if (descripcion == "")
			throw new IllegalArgumentException("La descripción esta vacía!");
		this.descripcion = descripcion.toLowerCase();
	}

	public Integer getDuracion() {
		return duracion;
	}

	public void setDuracion(Integer cantidadDeDias) {
		if (cantidadDeDias <= 0)
			throw new IllegalArgumentException("La duracion debe ser positiva, Duración: " + cantidadDeDias);
		this.duracion = cantidadDeDias;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idDuracion);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Duracion other = (Duracion) obj;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		return true;
	}

}
