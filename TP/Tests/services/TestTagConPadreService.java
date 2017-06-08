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
		TagConPadre original=new TagConPadre("original");
		TagConPadre modificacion=new TagConPadre("modificacion");
		EasyMock.expect(tagDAO.existe(modificacion.getNombre())).andReturn(true);
		EasyMock.expect(tagDAO.getTagPorNombre(original.getNombre())).andReturn(original);
		//el error que tira es porque esta el metodo void modificar (en el dao) y no se como testearlo
		
		replay(tagDAO);
		service.modificar(original.getNombre(), modificacion.getNombre());
		verify(tagDAO);
		
	}

}
