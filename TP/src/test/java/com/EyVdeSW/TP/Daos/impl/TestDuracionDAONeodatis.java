package com.EyVdeSW.TP.Daos.impl;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.EyVdeSW.TP.domainModel.Duracion;

import properties.Parametros;

public class TestDuracionDAONeodatis {

	private static String dbFilePath;
	private static DuracionDAONeodatis duracionDAO;

	@BeforeClass
	public static void setUpClass() {
		duracionDAO = new DuracionDAONeodatis();
		duracionDAO.setBdConnector(new NeodatisLocalConnector());
		dbFilePath = Parametros.getProperty(Parametros.dbPath);
	}

	@Before
	public void setUp() throws Exception {
		File f = new File(dbFilePath);
		if (f.exists())
			f.delete();
	}

	@Test
	public void testExiste() {
		Duracion unaDuracion = new Duracion("semana", 7);
		duracionDAO.guardar(unaDuracion);
		assertNotNull(duracionDAO.getDuracionPorDescripcion(unaDuracion.getDescripcion()));
		assertNull(duracionDAO.getDuracionPorDescripcion(""));
	}

	@Test
	public void testGetDuracionPorDescripcion() {
		List<Duracion> duraciones = getInstancia1();
		guardarInstancia(duraciones);
		
		assertEquals(duraciones.get(0),
					duracionDAO.getDuracionPorDescripcion(duraciones.get(0).getDescripcion()));
		assertNotEquals(new Duracion("otra duracion",12),
				duracionDAO.getDuracionPorDescripcion(duraciones.get(0).getDescripcion()));
	}

	@Test
	public void testTraerDuracionesVacio() {		
		assertEquals(0, duracionDAO.traerDuraciones().size());
	}
	
	@Test
	public void testTraerDuraciones() {
		List<Duracion> duraciones = getInstancia1();
		guardarInstancia(duraciones);
		
		duraciones.forEach(duracion -> assertTrue(duracionDAO.existe(duracion.getDescripcion())));
		assertEquals(duraciones.size(), duracionDAO.traerDuraciones().size());
	}
	
	@Test
	public void testModificar() {
		List<Duracion> duraciones = getInstancia1();
		guardarInstancia(duraciones);
		
		Duracion duracionOriginal = duraciones.get(0);
		Duracion duracionModificada = new Duracion("mes de treinta dias", 30);
		
		duracionDAO.modificar(duracionOriginal.getDescripcion(), duracionModificada);
		
		assertFalse(duracionDAO.existe(duracionOriginal.getDescripcion()));
		assertTrue(duracionDAO.existe(duracionModificada.getDescripcion()));		
		
	}
	
	private List<Duracion> getInstancia1() {
		List<Duracion> duraciones = new ArrayList<>();
		duraciones.add(new Duracion("semana", 7));
		duraciones.add(new Duracion("mes", 28));
		duraciones.add(new Duracion("bimestre", 56));
		duraciones.add(new Duracion("semestre", 168));
		duraciones.add(new Duracion("año", 365));
		return duraciones;
	}
	
	private void guardarInstancia(List<Duracion> duraciones) {
		duraciones.forEach(duracion -> duracionDAO.guardar(duracion));		
	}

	
}
