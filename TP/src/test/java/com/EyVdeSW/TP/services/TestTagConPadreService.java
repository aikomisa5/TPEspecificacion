package com.EyVdeSW.TP.services;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import static org.easymock.EasyMock.*;
import org.easymock.*;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.EyVdeSW.TP.Daos.TagDAO;
import com.EyVdeSW.TP.domainModel.TagConPadre;
import com.EyVdeSW.TP.services.TagService;

@RunWith(EasyMockRunner.class)
public class TestTagConPadreService {

	@Mock
	TagDAO tagDAO;
	
	@TestSubject
	private final TagService service = TagService.getTagService();
	
	
	@Test
	public void guardar(){
		TagConPadre t1=new TagConPadre("t1");
		tagDAO.guardar(t1);
		replay(tagDAO);
		service.guardar(t1);
		verify(tagDAO);
	}
	
	@Test
	public void guardarComplejo(){
		TagConPadre t1=new TagConPadre("t1");
		TagConPadre padre=new TagConPadre("padre");
		EasyMock.expect(tagDAO.getTagPorNombre(padre.getNombre())).andReturn(padre);
		EasyMock.expect(tagDAO.existe(t1.getNombre())).andReturn(false);
		tagDAO.guardar(t1);
		replay(tagDAO);
		service.guardar(t1.getNombre(), padre.getNombre());
		verify(tagDAO);
	}
	
	@Test
	public void borrar(){
		TagConPadre t1=new TagConPadre("t1");
		EasyMock.expect(tagDAO.getTagPorNombre(t1.getNombre())).andReturn(t1);
		tagDAO.borrar(t1);
		replay(tagDAO);
		service.borrar(t1.getNombre());
		verify(tagDAO);
	}
	
	@Test
	public void testTraerTodos() {
		List<TagConPadre>tags= new ArrayList<>();
		tags.add(new TagConPadre("t1"));
		tags.add(new TagConPadre("t2"));
		tags.add(new TagConPadre("t3"));
		EasyMock.expect(tagDAO.traerTodos()).andReturn(tags);
		replay(tagDAO);
		List<TagConPadre>t=(List<TagConPadre>) service.traerTodos();
		verify(tagDAO);
		assertEquals(3, t.size());
		assertEquals(t.get(0).getNombre(), "t1");
	}
	
	@Test
	public void modificarHP(){
		//TODO lean esto
		//Happy path
		TagConPadre original=new TagConPadre("original");
		TagConPadre modificacion=new TagConPadre("modificacion");
		//paso 1 ejecutamos todos los metodos del dao que aparecen en el service
		//si retornan algo los metemos en un expect indicando su debido retorno
		//en caso contrario (void) solo los ejecutamos
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
	}
	
	@Test
	public void modificarElse(){
		TagConPadre original=new TagConPadre("original");
		TagConPadre modificacion=new TagConPadre("modificacion");
		//ahora testeo el caso de que exista el tag (no deberian ejecutarse los demas metodos
		EasyMock.expect(tagDAO.existe(modificacion.getNombre())).andReturn(true);
		replay(tagDAO);
		assertFalse(service.modificar(original.getNombre(), modificacion.getNombre()));
	}

}
