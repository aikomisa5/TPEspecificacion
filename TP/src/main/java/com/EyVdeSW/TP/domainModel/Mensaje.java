package com.EyVdeSW.TP.domainModel;

public class Mensaje {
	
	//TODO
	private String nombre; //Para identificar al mensaje
							
							//O conviene un id?
	
	private String textoMensaje;

	public Mensaje(String nombre,String textoMensaje)
	{
		this.nombre=nombre;
		this.textoMensaje=textoMensaje;
	}
	
}
