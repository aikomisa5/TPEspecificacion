package com.EyVdeSW.TP.Daos.impl;

import static org.junit.Assert.*;

import java.io.File;
import java.security.KeyStore.PrivateKeyEntry;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

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
