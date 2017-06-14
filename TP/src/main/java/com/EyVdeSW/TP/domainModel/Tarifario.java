package com.EyVdeSW.TP.domainModel;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class Tarifario <T> {
	private Map<T, BigDecimal>tarifas;
	private Date fecha;
	private UUID idTarifario;
	
	public Tarifario(Map<T, BigDecimal> tarifas, Date fecha) {
		this.tarifas = tarifas;
		this.fecha = fecha;
		idTarifario=UUID.randomUUID();
	}

	public Tarifario(Date fecha) {
		tarifas=new HashMap<>();
		this.fecha = fecha;
		idTarifario=UUID.randomUUID();
	}

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
	
	public void agregarTarifa(T elemento, BigDecimal tarifa){
		tarifas.put(elemento, tarifa);
	}
	
	public void quitarTarifa(T elemento){
		tarifas.remove(elemento);
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
		return true;
	}
	
	//TODO delete me please
	public static void main(String[] args) {
		Date f1 = new Date(20170206);
		Date f2 = new Date(20170206);
		Date f3 = new Date(20170202);
		Tarifario<Tag> t1 = new Tarifario<>(f1);
		Tarifario<Tag> t2 = new Tarifario<>(f2);
		Tarifario<Tag> t3 = new Tarifario<>(f3);
		System.out.println(t1.equals(t2));
		System.out.println(t1.equals(t3));
	}

	

}
