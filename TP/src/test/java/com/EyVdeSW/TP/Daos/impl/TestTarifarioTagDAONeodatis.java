package com.EyVdeSW.TP.Daos.impl;

import static org.junit.Assert.*;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.EyVdeSW.TP.domainModel.Tarifario;
import com.EyVdeSW.TP.domainModel.Tarifario.TipoTarifario;

import properties.Parametros;

public class TestTarifarioTagDAONeodatis {
	private static TarifarioDAONeodatis tarifarioDAO;
	private static String dbFilePath;
	
	@BeforeClass
	public static void setUpClass(){
		tarifarioDAO = new TarifarioDAONeodatis();		
		tarifarioDAO.setBdConnector( new NeodatisLocalConnector());
		dbFilePath = Parametros.getProperty(Parametros.dbPath);
	}
	
	@Before
	public void setUp() throws Exception{
		File f = new File(dbFilePath);
		if (f.exists())
			f.delete();
	}
	
	@Before
	public void limpiarBD(){
		Collection<Tarifario<String>> tarifarios = tarifarioDAO.traerTodos();
		tarifarios.forEach(t -> tarifarioDAO.borrar(t));
	}
	
	@Test
	public void traerTodos(){
		agregarDatosDePrueba(instanciaTarifariosTag());
		Collection<Tarifario<String>> tarifarios = tarifarioDAO.traerTodos();
		assertEquals(tarifarios.size(), 4);
	}
	
	@Test
	public void traerTodosPorTipo(){
		agregarDatosDePrueba(instanciaTarifariosTag());
		Collection<Tarifario<String>> tarifarios = tarifarioDAO.traerTraerPorTipo(TipoTarifario.AccionesPublicitarias);
		assertEquals(tarifarios.size(), 2);
		tarifarios = tarifarioDAO.traerTraerPorTipo(TipoTarifario.Duracion);
		assertEquals(tarifarios.size(), 0);
		tarifarios = tarifarioDAO.traerTraerPorTipo(TipoTarifario.Tag);
		assertEquals(tarifarios.size(), 2);
	}
	
	@Test
	public void traerPorFecha(){
		agregarDatosDePrueba(instanciaTarifariosTag());
		Tarifario<String> esperado=new Tarifario<>(new Date(20170509), TipoTarifario.AccionesPublicitarias); 
		Tarifario<String> get = tarifarioDAO.traerUltimo(TipoTarifario.AccionesPublicitarias);
		assertEquals(get, esperado);
	}
	
	@Test
	public void traerUltimo(){
		agregarDatosDePrueba(instanciaTarifariosTag());
		Tarifario<String> esperado=new Tarifario<>(new Date(20170506), TipoTarifario.AccionesPublicitarias); 
		Tarifario<String> get = tarifarioDAO.traerPorFecha(new Date(20170506), TipoTarifario.AccionesPublicitarias);
		assertEquals(get, esperado);
	}
	
	@Test
	public void existe(){
		agregarDatosDePrueba(instanciaTarifariosTag());
		assertTrue(tarifarioDAO.existe(new Date(20170509), TipoTarifario.AccionesPublicitarias));
		assertFalse(tarifarioDAO.existe(new Date(20180509), TipoTarifario.AccionesPublicitarias));
		assertFalse(tarifarioDAO.existe(new Date(20170509), TipoTarifario.Tag));
		assertFalse(tarifarioDAO.existe(new Date(20180509), TipoTarifario.Tag));
	}
	
	@Test
	public void modificar(){
		agregarDatosDePrueba(instanciaTarifariosTag());
		List<Tarifario<String>> tarifarios = (List<Tarifario<String>>) tarifarioDAO.traerTodos();
		assertEquals(tarifarios.size(), 4);
		
		Tarifario<String>t1 = instanciaTarifariosTag().get(0);
		
		Tarifario<String>t2 = new Tarifario<String>(new Date(20000206), TipoTarifario.AccionesPublicitarias);
		t2.agregarTarifa("sarasa",  new BigDecimal("20.00"));
		
		
		tarifarioDAO.modificar(t1, t2);
		
		tarifarios = (List<Tarifario<String>>) tarifarioDAO.traerTodos();
		assertEquals(tarifarios.size(), 4);
		assertEquals(t2, tarifarios.get(0));
	}
	
	
	private void agregarDatosDePrueba(ArrayList<Tarifario<String>>instancia){
		instancia.forEach(t -> tarifarioDAO.guardar(t));
	}
	
	private ArrayList<Tarifario<String>>instanciaTarifariosTag(){
		ArrayList<Tarifario<String>> ret = new ArrayList<>();
		ret.add(new Tarifario<>(new Date(20170506), TipoTarifario.AccionesPublicitarias));
		ret.add(new Tarifario<>(new Date(20170507), TipoTarifario.Tag));
		ret.add(new Tarifario<>(new Date(20170508), TipoTarifario.Tag));
		ret.add(new Tarifario<>(new Date(20170509), TipoTarifario.AccionesPublicitarias));
		return ret;
	}

}
