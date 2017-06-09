package com.EyVdeSW.TP.services;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import static org.easymock.EasyMock.*;
import org.easymock.*;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.EyVdeSW.TP.Daos.TagDAO;
import com.EyVdeSW.TP.domainModel.Tag;
import com.EyVdeSW.TP.services.TagService;

@RunWith(EasyMockRunner.class)
public class TestTagConPadreService {

	@Mock
	TagDAO tagDAO;
	
	@TestSubject
	private final TagService service = TagService.getTagService();
	
	
	@Test
	public void guardar(){
		Tag t1=new Tag("t1");
		tagDAO.guardar(t1);
		replay(tagDAO);
		service.guardar(t1);
		verify(tagDAO);
	}
	
	@Test
	public void guardarComplejo(){
		Tag t1=new Tag("t1");
		Tag padre=new Tag("padre");
		//happy path
		EasyMock.expect(tagDAO.existe(t1.getNombre())).andReturn(false);
		EasyMock.expect(tagDAO.getTagPorNombre(padre.getNombre())).andReturn(padre);
		tagDAO.guardar(t1);
		replay(tagDAO);
		service.guardar(t1.getNombre(), padre.getNombre());
		verify(tagDAO);
		//else
		EasyMock.reset(tagDAO);
		EasyMock.expect(tagDAO.existe(t1.getNombre())).andReturn(true);
		replay(tagDAO);
		service.guardar(t1.getNombre(), padre.getNombre());
		verify(tagDAO);
	}
	
	@Test
	public void borrar(){
		Tag t1=new Tag("t1");
		EasyMock.expect(tagDAO.getTagPorNombre(t1.getNombre())).andReturn(t1);
		tagDAO.borrar(t1);
		replay(tagDAO);
		service.borrar(t1.getNombre());
		verify(tagDAO);
	}
	
	@Test
	public void testTraerTodos() {
		List<Tag>tags= new ArrayList<>();
		tags.add(new Tag("t1"));
		tags.add(new Tag("t2"));
		tags.add(new Tag("t3"));
		EasyMock.expect(tagDAO.traerTodos()).andReturn(tags);
		replay(tagDAO);
		List<Tag>t=(List<Tag>) service.traerTodos();
		verify(tagDAO);
		assertEquals(3, t.size());
		assertEquals(t.get(0).getNombre(), "t1");
	}
	
	@Test
	public void modificar(){
		//TODO lean esto
		//Happy path
		Tag original=new Tag("original");
		Tag modificacion=new Tag("modificacion");
		//paso 1 ejecutamos todos los metodos del dao que aparecen en el service
		//si retornan algo los metemos en un expect indicando su debido retorno
		//en caso contrario (void) solo los ejecutamos
		EasyMock.expect(tagDAO.existe(original.getNombre())).andReturn(true);
		EasyMock.expect(tagDAO.existe(modificacion.getNombre())).andReturn(false);
		EasyMock.expect(tagDAO.getTagPorNombre(original.getNombre())).andReturn(original);
		tagDAO.modificar(original.getNombre(), modificacion);
		//EasyMock.expectLastCall().times(1); averiguar
		
		//activamos el mock object en este caso el dao
		replay(tagDAO);
		//testeamos la funcionalidad
		assertTrue(service.modificar(original.getNombre(), modificacion.getNombre()));
		//verificamos si se hizo el llamado al mock object
		verify(tagDAO);
		
		//else no existe el tag que quiero modificar
		EasyMock.reset(tagDAO);
		EasyMock.expect(tagDAO.existe(original.getNombre())).andReturn(false);
		replay(tagDAO);
		assertFalse(service.modificar(original.getNombre(), modificacion.getNombre()));
		
		//else existe el nombre nuevo del tag
		EasyMock.reset(tagDAO);
		EasyMock.expect(tagDAO.existe(original.getNombre())).andReturn(false);
		EasyMock.expect(tagDAO.existe(modificacion.getNombre())).andReturn(true);
		replay(tagDAO);
		assertFalse(service.modificar(original.getNombre(), modificacion.getNombre()));
	}
	


}
