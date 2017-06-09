package com.EyVdeSW.TP.services;

import static org.junit.Assert.*;

import org.easymock.*;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.EyVdeSW.TP.Daos.AccionPublicitariaDAO;
import com.EyVdeSW.TP.domainModel.AccionPublicitaria;
import com.EyVdeSW.TP.domainModel.AccionPublicitaria.TipoAccion;

@RunWith(EasyMockRunner.class)
public class TestAccionPublicitariaService {
	
	@Mock
	AccionPublicitariaDAO accionDAO;
	
	@TestSubject
	AccionPublicitariaService service=AccionPublicitariaService.getAccionPublicitariaService();
	
	
	
	@Test
	public void guardar() {
		AccionPublicitaria accion = new AccionPublicitaria("sarasa", TipoAccion.general);
		//happy path
		EasyMock.expect(accionDAO.existe("sarasa")).andReturn(false);
		accionDAO.guardar(accion);
		EasyMock.replay(accionDAO);
		service.guardar("sarasa", "general");
		EasyMock.verify(accionDAO);
		
		//Si existe el tag
		EasyMock.reset(accionDAO);
		EasyMock.expect(accionDAO.existe("sarasa")).andReturn(true);
		EasyMock.replay(accionDAO);
		service.guardar("sarasa", "general");
		EasyMock.verify(accionDAO);
	}
	

	
	@Test 
	public void borrar(){
		AccionPublicitaria accion = new AccionPublicitaria("sarasa", TipoAccion.general);
		//happy path
		EasyMock.expect(accionDAO.existe("sarasa")).andReturn(true);
		EasyMock.expect(accionDAO.getAccionPorDestinatario("sarasa")).andReturn(accion);
		accionDAO.borrar(accion);
		EasyMock.replay(accionDAO);
		service.borrar("sarasa");
		EasyMock.verify(accionDAO);
		//reseteo el mock
		EasyMock.reset(accionDAO);
		//veo que pasa si no existe sarasa
		EasyMock.expect(accionDAO.existe("sarasa")).andReturn(false);
		EasyMock.replay(accionDAO);
		service.borrar("sarasa");
		EasyMock.verify(accionDAO);
	}
	
	
	@Test
	public void modificar(){
		AccionPublicitaria original = new AccionPublicitaria("orig", TipoAccion.general);
		AccionPublicitaria modificacion = new AccionPublicitaria("modi", TipoAccion.particular);
		
		//happy path
		EasyMock.expect(accionDAO.existe(original.getDestinatario())).andReturn(true);
		EasyMock.expect(accionDAO.existe(modificacion.getDestinatario())).andReturn(false);
		EasyMock.expect(accionDAO.getAccionPorDestinatario(original.getDestinatario())).andReturn(original);
		EasyMock.expect(accionDAO.getAccionPorDestinatario(original.getDestinatario())).andReturn(original);
		
		accionDAO.modificar(original, modificacion);
		
		EasyMock.replay(accionDAO);
		assertTrue(service.modificar(original.getDestinatario(), modificacion.getDestinatario(), modificacion.getTipo().toString()));
		EasyMock.verify(accionDAO);
		
		//si el original no existe
		EasyMock.reset(accionDAO);
		//Solo testeo este porque al fallar no llega al or (ademas se rompe el test por eso mismo)
		EasyMock.expect(accionDAO.existe(original.getDestinatario())).andReturn(false);
		EasyMock.replay(accionDAO);
		assertFalse(service.modificar(original.getDestinatario(), modificacion.getDestinatario(), modificacion.getTipo().toString()));
		EasyMock.verify(accionDAO);
		
		//si la modificacion ya existe
		EasyMock.reset(accionDAO);
		EasyMock.expect(accionDAO.existe(original.getDestinatario())).andReturn(true);
		EasyMock.expect(accionDAO.existe(modificacion.getDestinatario())).andReturn(true);
		EasyMock.replay(accionDAO);
		assertFalse(service.modificar(original.getDestinatario(), modificacion.getDestinatario(), modificacion.getTipo().toString()));
		EasyMock.verify(accionDAO);
	}
	
	
	
	

}
