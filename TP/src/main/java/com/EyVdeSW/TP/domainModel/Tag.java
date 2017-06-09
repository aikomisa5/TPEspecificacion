package com.EyVdeSW.TP.domainModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Tag implements Serializable
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= -3734241005474909743L;
	private Tag			padre;
	private String				nombre;
	private List<AccionPublicitaria>	accionesGenerales;
	private UUID				idTag;

	public Tag(String nombre, Tag padre, List<AccionPublicitaria> accionesGenerales){
		super();
		idTag = UUID.randomUUID();
		this.padre = padre;
		this.nombre = nombre;
		this.accionesGenerales = accionesGenerales;

	}

	public Tag(String nombre){
		super();
		idTag = UUID.randomUUID();
		this.nombre = nombre;
		this.padre = null;
		this.accionesGenerales = new ArrayList<>();
	}

	public Tag getPadre(){
		return padre;
	}

	public void setPadre(Tag padre){
		this.padre = padre;
	}

	public String getNombre(){
		return nombre;
	}

	public void setNombre(String nombre){
		this.nombre = nombre;
	}

	public List<AccionPublicitaria> getAccionesGenerales(){
		return accionesGenerales;
	}

	public void setAccionesGenerales(List<AccionPublicitaria> accionesGenerales){
		this.accionesGenerales = accionesGenerales;
	}

	public void addAccionGeneral(AccionPublicitaria accionGeneral){
		accionesGenerales.add(accionGeneral);
	}	

	@Override
	public int hashCode() {
		return Objects.hash(idTag);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tag other = (Tag) obj;
		if (accionesGenerales == null) {
			if (other.accionesGenerales != null)
				return false;
		} else if (!accionesGenerales.equals(other.accionesGenerales))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}

	@Override
	public String toString(){
		return nombre;
	}

}
