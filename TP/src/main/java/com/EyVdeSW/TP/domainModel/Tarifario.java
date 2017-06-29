package com.EyVdeSW.TP.domainModel;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class Tarifario <T> {
	private Map<T, BigDecimal>tarifas;
	private Date fecha;
	private UUID idTarifario;
	private TipoTarifario tipo;
	
	
	
	public Tarifario(Map<T, BigDecimal> tarifas, Date fecha, TipoTarifario tipo) {
		super();
		this.tarifas = tarifas;
		this.fecha = fecha;
		this.tipo = tipo;
	}

	public Tarifario(Date fecha, TipoTarifario tipo) {
		super();
		this.fecha = fecha;
		this.tipo = tipo;
		this.tarifas=new HashMap<>();
	}
	
	public Tarifario(){}

	public Map<T, BigDecimal> getTarifas() {
		return tarifas;
	}

	public void setTarifas(Map<T, BigDecimal> tarifas) {
		this.tarifas = tarifas;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public TipoTarifario getTipo() {
		return tipo;
	}

	public void setTipo(TipoTarifario tipo) {
		this.tipo = tipo;
	}

	public void agregarTarifa(T objeto, BigDecimal Tarifas){
		this.tarifas.put(objeto, Tarifas);
	}
	
	public void quitarTarifa(T objeto){
		this.tarifas.remove(objeto);
	}
	
	public void editarTarifa(T objeto, BigDecimal precioNuevo){
		this.quitarTarifa(objeto);
		this.agregarTarifa(objeto, precioNuevo);
	}

	public enum TipoTarifario{
		Tag,
		AccionesPublicitarias,
		Duracion
	}
	
	
	
	@Override
	public int hashCode() {
		return Objects.hash(idTarifario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tarifario<?> other = (Tarifario<?>) obj;
		if (fecha == null) {
			if (other.fecha != null)
				return false;
		} else if (!fecha.equals(other.fecha))
			return false;
		if (tarifas == null) {
			if (other.tarifas != null)
				return false;
		} else if (!tarifas.equals(other.tarifas))
			return false;
		if (tipo != other.tipo)
			return false;
		return true;
	}


}
