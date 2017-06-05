package com.EyVdeSW.TP.domainModel;

public class Usuario {
	
	public enum UsuarioTipo{
		CLIENTE,
		ANALISTATECNICO,
		ANALISTACOMERCIAL;
	}
	
	private String usuario;
	private String nombre;
	private String password;
	

	private UsuarioTipo tipoUsuario;
	
	public Usuario (String usuario, String nombre, String password, UsuarioTipo tipoUsuario){
		
		this.usuario=usuario;
		this.nombre=nombre;
		this.password=password;
		this.tipoUsuario=tipoUsuario;
	}
	
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UsuarioTipo getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(UsuarioTipo tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	
}
