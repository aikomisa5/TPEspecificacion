package com.EyVdeSW.TP.domainModel;

import java.util.Objects;

public class Usuario {
	
	public enum TipoUsuario{
		CLIENTE,
		ANALISTATECNICO,
		ANALISTACOMERCIAL;
	}
	
	private String nombreUsuario;
	private String nombreReal;
	private String mail;
	private String password;
	private TipoUsuario tipoUsuario;
	
	public Usuario (String nombreUsuario, String nombreReal, String mail, String password, TipoUsuario tipoUsuario){
		
		this.nombreUsuario=nombreUsuario;
		this.nombreReal=nombreReal;
		this.mail=mail;
		this.password=password;
		this.tipoUsuario=tipoUsuario;
	}
	
	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getNombreReal() {
		return nombreReal;
	}

	public void setNombreReal(String nombreReal) {
		this.nombreReal = nombreReal;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nombreUsuario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (nombreUsuario == null) {
			if (other.nombreUsuario != null)
				return false;
		} else if (!nombreUsuario.equals(other.nombreUsuario))
			return false;
		return true;
	}

	
	
}
