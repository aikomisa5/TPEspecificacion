package com.EyVdeSW.TP.Daos.impl;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.EyVdeSW.TP.domainModel.Campania;
import com.EyVdeSW.TP.domainModel.Campania.EstadoCampania;
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
		Collection<Campania> tags = campañaDAO.traerTodos();
		tags.forEach(t -> campañaDAO.borrar(t));
		tags = campañaDAO.traerTodos();
	}
	
	
	@Test
	public void testExiste(){
		Campania c1= new Campania("unaCampaña", "Soy una descripcion");
		campañaDAO.guardar(c1);
		
		assertEquals(true, campañaDAO.existe("unaCampaña"));
		assertEquals(false, campañaDAO.existe("unaCampañaInexistente"));
		
	}
	
	@Test
	public void modificarSimple(){
		Campania c1= new Campania("nombre", "Soy una descripcion");
		c1.setEstado(EstadoCampania.PLANIFICADA);
		campañaDAO.guardar(c1);
		
		Campania c2=campañaDAO.getCampañaPorNombre("nombre");
		
		assertEquals(c1, c2);
		
		c2.setNombre("sarasa");
		c2.setDescripcion("descripcion");
		
		campañaDAO.modificar(c1, c2);
		
		assertEquals(campañaDAO.getCampañaPorNombre("sarasa"), c2);
	}
	
	

	private void agregarDatosDePrueba(ArrayList<Campania> instancia){
		instancia.forEach(t -> campañaDAO.guardar(t));
	}

	private ArrayList<Campania> instanciaCampañas(){

		ArrayList<Campania> ret = new ArrayList<>();


		return ret;
	}

}
