package services;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import static org.easymock.EasyMock.*;
import org.easymock.*;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.EyVdeSW.TP.Daos.TagConPadreDAO;
import com.EyVdeSW.TP.domainModel.TagConPadre;
import com.EyVdeSW.TP.services.TagConPadreService;

@RunWith(EasyMockRunner.class)
public class TestTagConPadreService {

	@Mock
	TagConPadreDAO tagDAO;
	
	@TestSubject
	private final TagConPadreService service = TagConPadreService.getTagService();
	
	
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
	public void modificar(){
		//TODO lean esto
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

}
