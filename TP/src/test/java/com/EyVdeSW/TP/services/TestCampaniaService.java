package com.EyVdeSW.TP.services;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.easymock.*;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.EyVdeSW.TP.Daos.CampañaDAO;
import com.EyVdeSW.TP.domainModel.AccionPublicitaria;
import com.EyVdeSW.TP.domainModel.Campania;
import com.EyVdeSW.TP.domainModel.Tag;
import com.EyVdeSW.TP.domainModel.Usuario;
import com.EyVdeSW.TP.domainModel.Usuario.TipoUsuario;

@RunWith(EasyMockRunner.class)
public class TestCampaniaService {

	@Mock
	CampañaDAO campaniaDAO;
	
	@TestSubject
	CampañaService service = CampañaService.getCampañaService();
	
	
	@Test
	public void modificarHappyPath(){
		Campania c1 = new Campania(new Usuario("sdas", "dasdas", "dasdas", "sdadas", TipoUsuario.CLIENTE), "misael", "sarasa");
		Campania c2 = new Campania(new Usuario("sdas", "dasdas", "dasdas", "sdadas", TipoUsuario.CLIENTE), "rober", "sarasa");
		EasyMock.expect(campaniaDAO.existe(c2.getNombre())).andReturn(false);
		EasyMock.expect(campaniaDAO.existe(c1.getNombre())).andReturn(true);
		EasyMock.expect(campaniaDAO.getCampañaPorNombre(c1.getNombre())).andReturn(c1);
		EasyMock.expect(campaniaDAO.getCampañaPorNombre(c1.getNombre())).andReturn(c1);
		campaniaDAO.modificar(c1, c2);
		
		EasyMock.replay(campaniaDAO);
		service.modificar(c1.getNombre(), c2.getNombre(), "dasdas", "", "", new Date());
		EasyMock.verify(campaniaDAO);
	}
	
	@Test
	public void modificarExisteModificacion(){
		EasyMock.expect(campaniaDAO.existe("modi")).andReturn(true);
		EasyMock.replay(campaniaDAO);
		service.modificar("original", "modi", "dasdas", "", "", new Date());
		EasyMock.verify(campaniaDAO);
	}
	
	@Test 
	public void modificarNoExisteCampaniaOriginal(){
		Campania c1 = new Campania(new Usuario("sdas", "dasdas", "dasdas", "sdadas", TipoUsuario.CLIENTE), "misael", "sarasa");
		Campania c2 = new Campania(new Usuario("sdas", "dasdas", "dasdas", "sdadas", TipoUsuario.CLIENTE), "rober", "sarasa");
		EasyMock.expect(campaniaDAO.existe(c2.getNombre())).andReturn(false);
		EasyMock.expect(campaniaDAO.existe(c1.getNombre())).andReturn(false);
		
		EasyMock.replay(campaniaDAO);
		service.modificar(c1.getNombre(), c2.getNombre(), "dasdas", "", "", new Date());
		EasyMock.verify(campaniaDAO);
	}
	
	@Test
	public void borrarHappyPath(){
		Campania c1 = new Campania(new Usuario("sdas", "dasdas", "dasdas", "sdadas", TipoUsuario.CLIENTE), "misael", "sarasa");
		EasyMock.expect(campaniaDAO.existe(c1.getNombre())).andReturn(true);
		EasyMock.expect(campaniaDAO.getCampañaPorNombre(c1.getNombre())).andReturn(c1);
		campaniaDAO.borrar(c1);
		
		EasyMock.replay(campaniaDAO);
		service.borrar("misael");
		EasyMock.verify(campaniaDAO);
	}
	
	@Test
	public void noBorrar(){
		Campania c1 = new Campania(new Usuario("sdas", "dasdas", "dasdas", "sdadas", TipoUsuario.CLIENTE), "misael", "sarasa");
		EasyMock.expect(campaniaDAO.existe(c1.getNombre())).andReturn(false);
		
		EasyMock.replay(campaniaDAO);
		service.borrar("misael");
		EasyMock.verify(campaniaDAO);
	}
	
	@Test
	public void agregarAccionesHappyPath(){
		Campania c1 = new Campania(new Usuario("sdas", "dasdas", "dasdas", "sdadas", TipoUsuario.CLIENTE), "misael", "sarasa");
		EasyMock.expect(campaniaDAO.existe(c1.getNombre())).andReturn(true);
		EasyMock.expect(campaniaDAO.getCampañaPorNombre(c1.getNombre())).andReturn(c1);
		EasyMock.expect(campaniaDAO.getCampañaPorNombre(c1.getNombre())).andReturn(c1);
		
		campaniaDAO.modificar(c1, c1);
		EasyMock.replay(campaniaDAO);
		List<AccionPublicitaria>acciones=new ArrayList<>();
		service.agregarAcciones(c1.getNombre(), acciones);
		EasyMock.verify(campaniaDAO);
	}
	
	@Test
	public void noAgregarAcciones(){
		Campania c1 = new Campania(new Usuario("sdas", "dasdas", "dasdas", "sdadas", TipoUsuario.CLIENTE), "misael", "sarasa");
		EasyMock.expect(campaniaDAO.existe(c1.getNombre())).andReturn(false);
		EasyMock.replay(campaniaDAO);
		List<AccionPublicitaria>acciones=new ArrayList<>();
		service.agregarAcciones(c1.getNombre(), acciones);
		EasyMock.verify(campaniaDAO);
	}
	
	@Test
	public void agregarTagsHappyPath(){
		Campania c1 = new Campania(new Usuario("sdas", "dasdas", "dasdas", "sdadas", TipoUsuario.CLIENTE), "misael", "sarasa");
		EasyMock.expect(campaniaDAO.existe(c1.getNombre())).andReturn(true);
		EasyMock.expect(campaniaDAO.getCampañaPorNombre(c1.getNombre())).andReturn(c1);
		EasyMock.expect(campaniaDAO.getCampañaPorNombre(c1.getNombre())).andReturn(c1);
		
		campaniaDAO.modificar(c1, c1);
		EasyMock.replay(campaniaDAO);
		List<Tag>acciones=new ArrayList<>();
		service.agregarTags(c1.getNombre(), acciones);
		EasyMock.verify(campaniaDAO);
	}
	
	@Test
	public void noAgregarTags(){
		Campania c1 = new Campania(new Usuario("sdas", "dasdas", "dasdas", "sdadas", TipoUsuario.CLIENTE), "misael", "sarasa");
		EasyMock.expect(campaniaDAO.existe(c1.getNombre())).andReturn(false);
		EasyMock.replay(campaniaDAO);
		List<Tag>acciones=new ArrayList<>();
		service.agregarTags(c1.getNombre(), acciones);
		EasyMock.verify(campaniaDAO);
	}

}
