package com.EyVdeSW.TP.domainModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Tag implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3734241005474909743L;
	private List<Tag> hijos;
	private String nombre;
	private List<AccionGeneral>	accionesGenerales;

	public Tag(List<Tag> hijos, String nombre, List<AccionGeneral> accionesGenerales) {
		super();
		this.hijos = hijos;
		this.nombre = nombre;
		this.accionesGenerales = accionesGenerales;
	}	
	
	public Tag(String nombre) {
		super();
		this.nombre = nombre;
		this.hijos=new ArrayList<>();
		this.accionesGenerales=new ArrayList<>();
	}

	public List<Tag> getHijos()
	{
		return hijos;
	}

	public void setHijos(List<Tag> hijos)
	{
		this.hijos = hijos;
	}
	
	public void addHijo(Tag tag){
		hijos.add(tag);			
	}
	
	public void removeHijo (Tag tag){
		hijos.remove(tag);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<AccionGeneral> getAccionesGenerales()
	{
		return accionesGenerales;
	}

	public void setAccionesGenerales(List<AccionGeneral> accionesGenerales)
	{
		this.accionesGenerales = accionesGenerales;
	}
	
	public void addAccionGeneral(AccionGeneral accionGeneral){
		accionesGenerales.add(accionGeneral);
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tag other = (Tag) obj;
		if (accionesGenerales == null)
		{
			if (other.accionesGenerales != null)
				return false;
		}
		else if (!accionesGenerales.equals(other.accionesGenerales))
			return false;
		if (hijos == null)
		{
			if (other.hijos != null)
				return false;
		}
		else if (!hijos.equals(other.hijos))
			return false;
		if (nombre == null)
		{
			if (other.nombre != null)
				return false;
		}
		else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return nombre;
	}

}
