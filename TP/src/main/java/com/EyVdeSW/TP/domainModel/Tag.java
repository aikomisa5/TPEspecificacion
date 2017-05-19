package com.EyVdeSW.TP.domainModel;

import java.util.List;

public class Tag
{
	private List<Tag>			hijos;
	private String				nombre;

	private List<AccionGeneral>	accionesGenerales;

	public Tag(String nombre)
	{
		this.nombre = nombre;
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

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accionesGenerales == null) ? 0 : accionesGenerales.hashCode());
		result = prime * result + ((hijos == null) ? 0 : hijos.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
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
		return "Tag: " + nombre;
	}

}
