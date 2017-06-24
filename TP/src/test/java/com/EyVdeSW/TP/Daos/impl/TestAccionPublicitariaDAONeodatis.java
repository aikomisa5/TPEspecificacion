package com.EyVdeSW.TP.Daos.impl;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.EyVdeSW.TP.domainModel.AccionPublicitaria;
import com.EyVdeSW.TP.domainModel.AccionPublicitaria.TipoAccion;
import com.EyVdeSW.TP.domainModel.Usuario;
import com.EyVdeSW.TP.domainModel.Campania.EstadoCampania;

import properties.Parametros;

public class TestAccionPublicitariaDAONeodatis {

	private static String dbFilePath;
	private static AccionPublicitariaDAONeodatis accionDAO;

	@BeforeClass
	public static void setUpClass() {
		accionDAO = new AccionPublicitariaDAONeodatis();
		accionDAO.setBdConnector(new NeodatisLocalTestConnector());
		dbFilePath = Parametros.getProperty(Parametros.dbTestPath);
	}

	@Before
	public void setUp() throws Exception{
		File f = new File(dbFilePath);
		if (f.exists())
			f.delete();
	}
	
	@After
	public void limpiarBD(){
		Collection<AccionPublicitaria> usuarios = accionDAO.traerTodos();
		usuarios.forEach(t -> accionDAO.borrar(t));
		usuarios = accionDAO.traerTodos();
	}
	
	@Test
	public void getAccion(){
		agregarDatosDePrueba(instanciaCompleja());
		
		assertEquals(accionDAO.getAccion("ap3","t3","d3"), new AccionPublicitaria("ap3","t3","d3", TipoAccion.particular, 7, "12","34"));
		assertEquals(accionDAO.getAccion("ap1", "t1", "d1"), new AccionPublicitaria("ap1","t1","d1",  TipoAccion.general, 7, "17","35"));
		assertEquals(accionDAO.getAccion("a","dsad","dsad"), null);
		assertEquals(accionDAO.getAccion("ap1", "t2", "d1"), null);
		assertEquals(accionDAO.getAccion("ap1", "t1", "d2"), null);
	}
	
	@Test
	public void consultarAccionesPorDestinatario(){
		agregarDatosDePrueba(instanciaSimple());
		assertEquals(accionDAO.consultarPorDestinatario("ap").size(), 3);
		assertEquals(accionDAO.consultarPorDestinatario("ap2").size(), 1);
	}
	
	@Test
	public void existe(){
		agregarDatosDePrueba(instanciaCompleja());
		assertTrue(accionDAO.existe("ap3","t3","d3"));
		assertTrue(accionDAO.existe("ap2","t2","d2"));
		assertFalse(accionDAO.existe("ap1", "t2", "d1"));
		assertFalse(accionDAO.existe("ap1", "t1", "d2"));
		assertFalse(accionDAO.existe("ap1", "t3", "d2"));
	}
	
	@Test
	public void modificar(){
		AccionPublicitaria modificacion = new AccionPublicitaria("modificacion","Titulo","mensaje",TipoAccion.particular, 7, "12","44");
		
		agregarDatosDePrueba(instanciaCompleja());
		AccionPublicitaria original= accionDAO.getAccion("ap2","t2","d2");
		accionDAO.modificar(original, modificacion);
		//Traigo de la bd esperando que este modificada
		
		original=accionDAO.getAccion("modificacion","Titulo","mensaje");
		
		assertEquals(original, modificacion);
		assertEquals(accionDAO.traerTodos().size(), 3);
	}
	
	@Test
	public void modificarMasivo(){
		agregarDatosDePrueba(instanciaCompleja());
		List<AccionPublicitaria>acciones = (List<AccionPublicitaria>) accionDAO.traerTodos();
		accionDAO.modificarMasivo(acciones, "tituloNuevo", "msgNuevo");
		acciones = (List<AccionPublicitaria>) accionDAO.traerTodos();
		for(AccionPublicitaria a : acciones){
			assertEquals(a.getTitulo(), "tituloNuevo");
			assertEquals(a.getTexto(), "msgNuevo");
		}
	}
	
	public void agregarDatosDePrueba(ArrayList<AccionPublicitaria>lista){
		lista.forEach(ap -> accionDAO.guardar(ap));
	}
	
	public ArrayList<AccionPublicitaria>instanciaSimple(){
		ArrayList<AccionPublicitaria>ret= new ArrayList<>();
		ret.add(new AccionPublicitaria("ap1", TipoAccion.general));
		ret.add(new AccionPublicitaria("ap2", TipoAccion.general));
		ret.add(new AccionPublicitaria("ap3", TipoAccion.particular));
		return ret;
	}
	
	public ArrayList<AccionPublicitaria>instanciaCompleja(){
		ArrayList<AccionPublicitaria>ret= new ArrayList<>();
		ret.add(new AccionPublicitaria("ap1","t1","d1", TipoAccion.general,7, "12","31"));
		ret.add(new AccionPublicitaria("ap2","t2","d2",  TipoAccion.general, 3, "12","34"));
		ret.add(new AccionPublicitaria("ap3","t3","d3", TipoAccion.particular, 2, "12","34"));
		return ret;
	}

}
