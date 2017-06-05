package com.EyVdeSW.TP.Daos.impl;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.EyVdeSW.TP.domainModel.Campaña;
import com.EyVdeSW.TP.domainModel.Tag;

import properties.Parametros;

public class TestCampañaDAONeodatis {
	
	private static String dbFilePath;
	private static CampañaDAONeodatis campañaDAO;
	
	@BeforeClass
	public static void setUpClass(){
		campañaDAO = new CampañaDAONeodatis();		
		campañaDAO.setBdConnector( new NeodatisLocalConnector());
		dbFilePath = Parametros.getProperties().getProperty(Parametros.dbPath);
	}

	@Before
	public void setUp() throws Exception{
		File f = new File(dbFilePath);
		if (f.exists())
			f.delete();
	}

	public void limpiarBD(){
		Collection<Campaña> tags = campañaDAO.traerTodos();
		tags.forEach(t -> campañaDAO.borrar(t));
		tags = campañaDAO.traerTodos();
	}
	
	

	private void agregarDatosDePrueba(ArrayList<Campaña> instancia){
		instancia.forEach(t -> campañaDAO.guardar(t));
	}

	private ArrayList<Campaña> instanciaCampañas(){

		ArrayList<Campaña> ret = new ArrayList<>();


		return ret;
	}

}
