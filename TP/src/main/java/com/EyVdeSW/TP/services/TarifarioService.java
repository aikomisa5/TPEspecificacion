package com.EyVdeSW.TP.services;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import com.EyVdeSW.TP.Daos.TarifarioDAO;
import com.EyVdeSW.TP.Daos.impl.TarifarioDAONeodatis;
import com.EyVdeSW.TP.domainModel.Tarifario;
import com.EyVdeSW.TP.domainModel.Tarifario.TipoTarifario;

public class TarifarioService {
	private TarifarioDAO<Tarifario<String>> tarifarioDAO;
	private static TarifarioService tarifarioService=null;
	
	private TarifarioService(){
		tarifarioDAO= new TarifarioDAONeodatis();
	}
	
	public static TarifarioService getTarifarifarioService(){
		if(tarifarioService == null){
			tarifarioService = new TarifarioService();
		}
		return tarifarioService;
	}
	
	public void agregar(Tarifario<String>tarifario){
		if(!tarifarioDAO.existe(tarifario.getFecha(), tarifario.getTipo()))
			tarifarioDAO.guardar(tarifario);
	}
	
	public void borrar(Date fecha, TipoTarifario tipo){
		if(tarifarioDAO.existe(fecha,tipo)){
			Tarifario<String>aBorrar=tarifarioDAO.traerPorFecha(fecha, tipo);
			tarifarioDAO.borrar(aBorrar);
		}
	}
	
	public boolean modificar(Date fechaOriginal, TipoTarifario tipoOriginal, Map<String, BigDecimal>nuevasTarifas, Date nuevaFecha, TipoTarifario nuevoTipo){
		boolean ret=true;
		if(!tarifarioDAO.existe(fechaOriginal, tipoOriginal) || tarifarioDAO.existe(nuevaFecha, nuevoTipo)){
			ret=false;
		} else {
			Tarifario<String>original=tarifarioDAO.traerPorFecha(fechaOriginal, tipoOriginal);
			Tarifario<String>modificacion=tarifarioDAO.traerPorFecha(fechaOriginal, tipoOriginal);
			if(modificacion.getFecha()!=null)
				modificacion.setFecha(nuevaFecha);
			if(modificacion.getTarifas()!= null)
				modificacion.setTarifas(nuevasTarifas);
			if(modificacion.getTipo()!=null)
				modificacion.setTipo(nuevoTipo);
			tarifarioDAO.modificar(original, modificacion);
		}
		return ret;
	}
	
	public Tarifario<String>getUltimo(TipoTarifario tipo){
		return tarifarioDAO.traerUltimo(tipo);
	}
	
	
	
}
