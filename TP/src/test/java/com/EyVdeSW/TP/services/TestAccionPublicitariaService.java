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
	public void guardarHappyPath() {
		AccionPublicitaria accion = new AccionPublicitaria("sarasa","t1","te1", TipoAccion.general);
		EasyMock.expect(accionDAO.existe("sarasa","t1","te1")).andReturn(false);
		accionDAO.guardar(accion);
		EasyMock.replay(accionDAO);
		service.guardar("sarasa","t1","te1", "general");
		EasyMock.verify(accionDAO);
	}
	
	@Test
	public void guardarExisteTag(){
		EasyMock.reset(accionDAO);
		EasyMock.expect(accionDAO.existe("sarasa","t1","te1")).andReturn(true);
		EasyMock.replay(accionDAO);
		service.guardar("sarasa","t1","te1", "general");
		EasyMock.verify(accionDAO);
	}
	

	
	@Test 
	public void borrarHappyPath(){
		AccionPublicitaria accion = new AccionPublicitaria("sarasa","t1","te1", TipoAccion.general);
		//happy path
		EasyMock.expect(accionDAO.existe("sarasa","t1","te1")).andReturn(true);
		EasyMock.expect(accionDAO.getAccion("sarasa","t1","te1")).andReturn(accion);
		accionDAO.borrar(accion);
		EasyMock.replay(accionDAO);
		service.borrar("sarasa","t1","te1", "general");
		EasyMock.verify(accionDAO);
	}
	
	@Test
	public void borrarNoExiste(){
		//veo que pasa si no existe sarasa
		EasyMock.expect(accionDAO.existe("sarasa","t1","te1")).andReturn(false);
		EasyMock.replay(accionDAO);
		service.borrar("sarasa","t1","te1", "general");
		EasyMock.verify(accionDAO);
	}
	
	
	@Test
	public void modificarHappyPath(){
		AccionPublicitaria original = new AccionPublicitaria("odest","otitulo","omsg", TipoAccion.general);
		AccionPublicitaria modificacion = new AccionPublicitaria("mdest","mtitulo","mmsg", TipoAccion.particular);
		
		//happy path
		EasyMock.expect(accionDAO.existe("odest","otitulo","omsg")).andReturn(true);
		EasyMock.expect(accionDAO.existe("mdest","mtitulo","mmsg")).andReturn(false);
		EasyMock.expect(accionDAO.getAccion("odest","otitulo","omsg")).andReturn(original);
		EasyMock.expect(accionDAO.getAccion("odest","otitulo","omsg")).andReturn(original);
		
		accionDAO.modificar(original, modificacion);
		
		EasyMock.replay(accionDAO);
		assertTrue(service.modificar(original.getDestinatario(), original.getTitulo(), original.getTexto(),
				modificacion.getDestinatario(),modificacion.getTitulo(), modificacion.getTexto(), modificacion.getTipo().toString()));
		EasyMock.verify(accionDAO);
		
		
		
	
	}
	
	@Test
	public void modificarOriginaNoExiste(){
		AccionPublicitaria original = new AccionPublicitaria("odest","otitulo","omsg", TipoAccion.general);
		AccionPublicitaria modificacion = new AccionPublicitaria("mdest","mtitulo","mmsg", TipoAccion.particular);
		EasyMock.reset(accionDAO);
		EasyMock.expect(accionDAO.existe("odest","otitulo","omsg")).andReturn(false);
		EasyMock.replay(accionDAO);
		assertFalse(service.modificar(original.getDestinatario(), original.getTitulo(), original.getTexto(),
				modificacion.getDestinatario(),modificacion.getTitulo(), modificacion.getTexto(), modificacion.getTipo().toString()));
		EasyMock.verify(accionDAO);
	}
	
	@Test
	public void modificarModificacionExiste(){
		AccionPublicitaria original = new AccionPublicitaria("odest","otitulo","omsg", TipoAccion.general);
		AccionPublicitaria modificacion = new AccionPublicitaria("mdest","mtitulo","mmsg", TipoAccion.particular);
		EasyMock.expect(accionDAO.existe("odest","otitulo","omsg")).andReturn(true);
		EasyMock.expect(accionDAO.existe("mdest","mtitulo","mmsg")).andReturn(true);
		EasyMock.replay(accionDAO);
		assertFalse(service.modificar(original.getDestinatario(), original.getTitulo(), original.getTexto(),
				modificacion.getDestinatario(),modificacion.getTitulo(), modificacion.getTexto(), modificacion.getTipo().toString()));
		EasyMock.verify(accionDAO);
	}
	
	

}
