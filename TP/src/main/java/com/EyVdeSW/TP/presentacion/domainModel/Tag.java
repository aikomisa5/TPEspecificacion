package com.EyVdeSW.TP.presentacion.domainModel;

import java.util.List;

public class Tag
{
	private Tag padre;
	private List<Tag> hijos;
	private String nombre;
	
	private List<AccionGeneral> accionesGenerales;

	public Tag getPadres()
	{
		return padre;
	}

	public void setPadres(Tag padres)
	{
		this.padre = padres;
	}

	public List<Tag> getHijos()
	{
		return hijos;
	}

	public void setHijos(List<Tag> hijos)
	{
		this.hijos = hijos;
	}

	public String getNombre()
	{
		return nombre;
	}

	public void setNombre(String nombre)
	{
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
	

}
