package com.EyVdeSW.TP.domainModel;

import java.util.Objects;
import java.util.UUID;

public abstract class AccionPublicitaria 
{

	private String destinatario;
	private TipoAccion tipo;
	private UUID idAccion;	

	public AccionPublicitaria(String destinatario, TipoAccion tipo) {
		this.destinatario = destinatario;
		this.tipo = tipo;
		this.idAccion=UUID.randomUUID();
	}

	

	public String getDestinatario() {
		return destinatario;
	}



	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}



	public TipoAccion getTipo() {
		return tipo;
	}



	public void setTipo(TipoAccion tipo) {
		this.tipo = tipo;
	}

	

	@Override
	public int hashCode() {
		return Objects.hash(idAccion);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AccionPublicitaria other = (AccionPublicitaria) obj;
		if (destinatario == null) {
			if (other.destinatario != null)
				return false;
		} else if (!destinatario.equals(other.destinatario))
			return false;
		if (idAccion == null) {
			if (other.idAccion != null)
				return false;
		} else if (!idAccion.equals(other.idAccion))
			return false;
		if (tipo != other.tipo)
			return false;
		return true;
	}



	public enum TipoAccion{
		general,
		particular
	}
}
