package com.EyVdeSW.TP.Daos.impl;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

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
		accionDAO.setBdConnector(new NeodatisLocalConnector());
		dbFilePath = Parametros.getProperty(Parametros.dbPath);
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
	public void getAccionPorDestinatarios(){
		agregarDatosDePrueba(instancia());
		
		assertEquals(accionDAO.getAccionPorDestinatario("ap3"), new AccionPublicitaria("ap3", TipoAccion.particular));
		assertEquals(accionDAO.getAccionPorDestinatario("ap1"), new AccionPublicitaria("ap1", TipoAccion.general));
		assertEquals(accionDAO.getAccionPorDestinatario("a"), null);
		assertEquals(accionDAO.getAccionPorDestinatario("1"), null);
	}
	
	@Test
	public void consultarAccionesPorDestinatario(){
		agregarDatosDePrueba(instancia());
		assertEquals(accionDAO.consultarPorDestinatario("ap").size(), 3);
		assertEquals(accionDAO.consultarPorDestinatario("ap2").size(), 1);
	}
	
	@Test
	public void existe(){
		agregarDatosDePrueba(instancia());
		assertTrue(accionDAO.existe("ap1"));
		assertTrue(accionDAO.existe("ap3"));
		assertFalse(accionDAO.existe("ap4"));
		assertFalse(accionDAO.existe("ap0"));
		assertFalse(accionDAO.existe("ap"));
	}
	
	@Test
	public void modificar(){
		AccionPublicitaria modificacion = new AccionPublicitaria("modificacion",TipoAccion.particular);
		
		agregarDatosDePrueba(instancia());
		AccionPublicitaria original= accionDAO.getAccionPorDestinatario("ap2");
		accionDAO.modificar(original, modificacion);
		original=accionDAO.getAccionPorDestinatario(modificacion.getDestinatario());
		
		assertEquals(original, modificacion);
	}
	
	public void agregarDatosDePrueba(ArrayList<AccionPublicitaria>lista){
		lista.forEach(ap -> accionDAO.guardar(ap));
	}
	
	public ArrayList<AccionPublicitaria>instancia(){
		ArrayList<AccionPublicitaria>ret= new ArrayList<>();
		ret.add(new AccionPublicitaria("ap1", TipoAccion.general));
		ret.add(new AccionPublicitaria("ap2", TipoAccion.general));
		ret.add(new AccionPublicitaria("ap3", TipoAccion.particular));
		return ret;
	}

}
