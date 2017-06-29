package com.EyVdeSW.TP.services;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.easymock.*;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.EyVdeSW.TP.Daos.TarifarioDAO;
import com.EyVdeSW.TP.domainModel.Tarifario;
import com.EyVdeSW.TP.domainModel.Tarifario.TipoTarifario;

@RunWith(EasyMockRunner.class)
public class TestTarifarioService {
	
	@Mock
	TarifarioDAO<Tarifario<String>> tarifarioDAO;
	
	@TestSubject
	TarifarioService service = TarifarioService.getTarifarifarioService();

	@Test
	public void guardarHappyPath() {
		Date fecha = new Date(20170206);
		Map<String, BigDecimal>tarifas= new HashMap<>();
		Tarifario<String>tarifario= new Tarifario<>(tarifas, fecha, TipoTarifario.AccionesPublicitarias);
		tarifario.agregarTarifa("Mail", new BigDecimal("20.00"));
		
		EasyMock.expect(tarifarioDAO.existe(fecha, TipoTarifario.AccionesPublicitarias)).andReturn(false);
		tarifarioDAO.guardar(tarifario);
		
		EasyMock.replay(tarifarioDAO);
		service.agregar(fecha, TipoTarifario.AccionesPublicitarias, tarifas);
		EasyMock.verify(tarifarioDAO);
	}
	
	@Test
	public void guardarExiste() {
		Date fecha = new Date(20170206);
		Map<String, BigDecimal>tarifas= new HashMap<>();
		Tarifario<String>tarifario= new Tarifario<>(tarifas, fecha, TipoTarifario.AccionesPublicitarias);
		tarifario.agregarTarifa("Mail", new BigDecimal("20.00"));
		
		EasyMock.expect(tarifarioDAO.existe(fecha, TipoTarifario.AccionesPublicitarias)).andReturn(true);
		
		EasyMock.replay(tarifarioDAO);
		service.agregar(fecha, TipoTarifario.AccionesPublicitarias, tarifas);
		EasyMock.verify(tarifarioDAO);
	}
	
	@Test
	public void borrarHappyPath() {
		Date fecha = new Date(20170206);
		Map<String, BigDecimal>tarifas= new HashMap<>();
		Tarifario<String>tarifario= new Tarifario<>(tarifas, fecha, TipoTarifario.AccionesPublicitarias);
		tarifario.agregarTarifa("Mail", new BigDecimal("20.00"));
		
		EasyMock.expect(tarifarioDAO.existe(fecha, TipoTarifario.AccionesPublicitarias)).andReturn(true);
		EasyMock.expect(tarifarioDAO.traerPorFecha(fecha, TipoTarifario.AccionesPublicitarias)).andReturn(tarifario);
		
		tarifarioDAO.borrar(tarifario);
		
		EasyMock.replay(tarifarioDAO);
		service.borrar(fecha, TipoTarifario.AccionesPublicitarias);
		EasyMock.verify(tarifarioDAO);
	}
	
	@Test
	public void borrarNoExiste(){
		
			Date fecha = new Date(20170206);
			Map<String, BigDecimal>tarifas= new HashMap<>();
			Tarifario<String>tarifario= new Tarifario<>(tarifas, fecha, TipoTarifario.AccionesPublicitarias);
			tarifario.agregarTarifa("Mail", new BigDecimal("20.00"));
			
			EasyMock.expect(tarifarioDAO.existe(fecha, TipoTarifario.AccionesPublicitarias)).andReturn(false);
			
			
			EasyMock.replay(tarifarioDAO);
			service.borrar(fecha, TipoTarifario.AccionesPublicitarias);
			EasyMock.verify(tarifarioDAO);
	}
	
	@Test
	public void modificarHappyPath() {
		Date fechaOriginal = new Date(20170206);
		Map<String, BigDecimal>tarifasOriginal= new HashMap<>();
		Date fechaModificacion = new Date(20170206);
		Tarifario<String>original= new Tarifario<>(tarifasOriginal, fechaOriginal, TipoTarifario.AccionesPublicitarias);
		Tarifario<String>modificacion= new Tarifario<>(tarifasOriginal, fechaModificacion, TipoTarifario.Tag);
		original.agregarTarifa("Mail", new BigDecimal("20.00"));
		
		EasyMock.expect(tarifarioDAO.existe(fechaOriginal, TipoTarifario.AccionesPublicitarias)).andReturn(true);
		EasyMock.expect(tarifarioDAO.existe(fechaModificacion, TipoTarifario.Tag)).andReturn(false);
		
		EasyMock.expect(tarifarioDAO.traerPorFecha(fechaOriginal, TipoTarifario.AccionesPublicitarias)).andReturn(original);
		EasyMock.expect(tarifarioDAO.traerPorFecha(fechaOriginal, TipoTarifario.AccionesPublicitarias)).andReturn(original);
		
		tarifarioDAO.modificar(original, modificacion);
		
		
		EasyMock.replay(tarifarioDAO);
		assertEquals(true, service.modificar(fechaOriginal, TipoTarifario.AccionesPublicitarias, tarifasOriginal, fechaModificacion,
				TipoTarifario.Tag));
		EasyMock.verify(tarifarioDAO);
	}
	
	@SuppressWarnings("unused")
	@Test
	public void modificarExisteModificacion() {
		Date fechaOriginal = new Date(20170206);
		Map<String, BigDecimal>tarifasOriginal= new HashMap<>();
		Date fechaModificacion = new Date(20170206);
		Tarifario<String>original= new Tarifario<>(tarifasOriginal, fechaOriginal, TipoTarifario.AccionesPublicitarias);
		Tarifario<String>modificacion= new Tarifario<>(tarifasOriginal, fechaModificacion, TipoTarifario.Tag);
		original.agregarTarifa("Mail", new BigDecimal("20.00"));
		
		EasyMock.expect(tarifarioDAO.existe(fechaOriginal, TipoTarifario.AccionesPublicitarias)).andReturn(true);
		EasyMock.expect(tarifarioDAO.existe(fechaModificacion, TipoTarifario.Tag)).andReturn(true);
		
		EasyMock.replay(tarifarioDAO);
		assertEquals(false, service.modificar(fechaOriginal, TipoTarifario.AccionesPublicitarias, tarifasOriginal, fechaModificacion,
				TipoTarifario.Tag));
		EasyMock.verify(tarifarioDAO);
	}
	
	@Test
	public void modificarNoExisteOriginal() {
		Date fechaOriginal = new Date(20170206);
		Map<String, BigDecimal>tarifasOriginal= new HashMap<>();
		Date fechaModificacion = new Date(20170206);
		Tarifario<String>original= new Tarifario<>(tarifasOriginal, fechaOriginal, TipoTarifario.AccionesPublicitarias);
		original.agregarTarifa("Mail", new BigDecimal("20.00"));
		
		EasyMock.expect(tarifarioDAO.existe(fechaOriginal, TipoTarifario.AccionesPublicitarias)).andReturn(false);
		
		EasyMock.replay(tarifarioDAO);
		assertEquals(false, service.modificar(fechaOriginal, TipoTarifario.AccionesPublicitarias, tarifasOriginal, fechaModificacion,
				TipoTarifario.Tag));
		EasyMock.verify(tarifarioDAO);
	}
	

}
