package com.EyVdeSW.TP.domainModel;

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

	//TODO //FixMe
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mail == null) ? 0 : mail.hashCode());
		result = prime * result + ((nombreReal == null) ? 0 : nombreReal.hashCode());
		result = prime * result + ((nombreUsuario == null) ? 0 : nombreUsuario.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((tipoUsuario == null) ? 0 : tipoUsuario.hashCode());
		return result;
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
		if (mail == null) {
			if (other.mail != null)
				return false;
		} else if (!mail.equals(other.mail))
			return false;
		if (nombreReal == null) {
			if (other.nombreReal != null)
				return false;
		} else if (!nombreReal.equals(other.nombreReal))
			return false;
		if (nombreUsuario == null) {
			if (other.nombreUsuario != null)
				return false;
		} else if (!nombreUsuario.equals(other.nombreUsuario))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (tipoUsuario != other.tipoUsuario)
			return false;
		return true;
	}
	
}
