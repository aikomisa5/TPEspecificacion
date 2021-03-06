package com.EyVdeSW.TP.domainModel;


import org.neodatis.odb.ODB;

import com.EyVdeSW.TP.services.WebAppListener;

//import com.EyVdeSW.TP.services.MiListener;

public class Cliente
{
	private String	nombre;
	private int		cuit;

	public Cliente()
	{

	}

	public Cliente(String cliente, int cuit)
	{
		this.nombre = cliente;
		this.cuit = cuit;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + cuit;
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
		Cliente other = (Cliente) obj;
		if (cuit != other.cuit)
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

	public static void guardarCliente()
	{
		if (!WebAppListener.isOk())
			throw new RuntimeException("El server no est� habilitado");
		try
		{
			ODB odb = WebAppListener.getServer().openClient(WebAppListener.getProperty("ubicacion.bd"));
			odb.store(new Cliente("pepe", 123456));
			odb.close();
		}
		catch (Exception e)
		{
		}
	}
}
