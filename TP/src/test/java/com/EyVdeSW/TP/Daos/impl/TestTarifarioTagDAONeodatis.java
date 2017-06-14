package com.EyVdeSW.TP.Daos.impl;

import static org.junit.Assert.*;

import java.io.File;
import java.math.BigDecimal;
import java.security.KeyStore.PrivateKeyEntry;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.EyVdeSW.TP.domainModel.Tag;
import com.EyVdeSW.TP.domainModel.Tarifario;
import com.EyVdeSW.TP.domainModel.Usuario;

import properties.Parametros;

public class TestTarifarioTagDAONeodatis {
	private static TarifarioTagDAONeodatis tarifarioDAO;
	private static String dbFilePath;
	
	@BeforeClass
	public static void setUpClass(){
		tarifarioDAO = new TarifarioTagDAONeodatis();		
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
		Collection<Tarifario<Tag>> tarifarios = tarifarioDAO.traerTodos();
		tarifarios.forEach(t -> tarifarioDAO.borrar(t));
		tarifarios = tarifarioDAO.traerTodos();
	}
	
	@Test
	public void traerTodos(){
		agregarDatosDePrueba(instanciaTarifariosTag());
		Collection<Tarifario<Tag>> tarifarios = tarifarioDAO.traerTodos();
		assertEquals(tarifarios.size(), 4);
	}
	
	@Test
	public void modificar(){
		agregarDatosDePrueba(instanciaTarifariosTag());
		List<Tarifario<Tag>> tarifarios = (List<Tarifario<Tag>>) tarifarioDAO.traerTodos();
		assertEquals(tarifarios.size(), 4);
		
		Tarifario<Tag>t1 = instanciaTarifariosTag().get(0);
		
		Tarifario<Tag>t2 = new Tarifario<Tag>(new Date(20000206));
		t2.agregarTarifa(new Tag("sarasa"),  new BigDecimal("20.00"));
		
		
		tarifarioDAO.modificar(t1, t2);
		
		tarifarios = (List<Tarifario<Tag>>) tarifarioDAO.traerTodos();
		assertEquals(tarifarios.size(), 4);
		assertEquals(t2, tarifarios.get(0));
	}
	
	
	private void agregarDatosDePrueba(ArrayList<Tarifario<Tag>>instancia){
		instancia.forEach(t -> tarifarioDAO.guardar(t));
	}
	
	private ArrayList<Tarifario<Tag>>instanciaTarifariosTag(){
		ArrayList<Tarifario<Tag>> ret = new ArrayList<>();
		ret.add(new Tarifario<>(new Date(20170506)));
		ret.add(new Tarifario<>(new Date(20170507)));
		ret.add(new Tarifario<>(new Date(20170508)));
		ret.add(new Tarifario<>(new Date(20170509)));
		return ret;
	}

}
