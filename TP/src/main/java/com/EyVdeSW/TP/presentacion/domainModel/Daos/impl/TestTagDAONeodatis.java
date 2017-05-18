package com.EyVdeSW.TP.presentacion.domainModel.Daos.impl;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.EyVdeSW.TP.presentacion.domainModel.Tag;

import static org.junit.Assert.*;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
 
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import properties.Parametros;

public class TestTagDAONeodatis {

	 private static String dbFilePath;
	    private static TagDAONeodatis pDAO;
	 
	    @BeforeClass
	    public static void setUpClass() {
	        pDAO = new TagDAONeodatis();
	        dbFilePath = Parametros.getProperties().getProperty(Parametros.dbPath);
	    }
	 
	    @Before
	    public void setUp() throws Exception {
	 
	        File f = new File(dbFilePath);
	        if (f.exists())
	            f.delete();
	        agregarDatosDePrueba();
	    }
	 
	    // @After
	    public void tearDown() throws Exception {
	        File f = new File(dbFilePath);
	        if (f.exists())
	            f.delete();
	    }
	 
	    @Test
	    public void testBorrarPersonas() {
	        Collection<Tag> personas = pDAO.getPersonas();
	        assertEquals(instanciaPersonas().size(), personas.size());
	        personas.forEach(p -> pDAO.borrar(p));
	        personas = pDAO.getPersonas();
	        assertEquals(0, personas.size());
	    }
	 
	    // a.- Todas las personas cuyo nombre sea exactamente ‘Juan’.
	    @Test
	    public void testPersonasConNombreJuan() {
	        Collection<Persona> personas = pDAO.getPersonasConNombre("Juan");
	        assertEquals(2, personas.size());
	    }
	 
	    // b.- Todas las personas cuyo nombre comience con ‘J’, ordenados por
	    // apellido y luego por nombre.
	    @Test
	    public void testPersonasConNombreEmpezadoEnJ() {
	        Collection<Tag> resultadoQuery = pDAO.getPersonasConNombreEmpezadoEn('j');
	        assertEquals(4, resultadoQuery.size());
	        ArrayList<String> personas = new ArrayList<>();
	        resultadoQuery.forEach(p -> personas.add(p.getApellido()));
	        assertEquals("Alvarez", personas.get(0));
	        assertEquals("Belladona", personas.get(1));
	        assertEquals("Cortez", personas.get(2));
	        assertEquals("Perez", personas.get(3));
	    }
	 
	    // c.- Todas las personas que tengan entre 25 y 30 años.
	    @Test
	    public void testPersonasConEdadEntre25y30() {
	        Collection<Tag> resultadoQuery = pDAO.getPersonasConEdadEntre(25, 30);
	        assertEquals(3, resultadoQuery.size());
	        ArrayList<String> personas = new ArrayList<>();
	        resultadoQuery.forEach(p -> personas.add(p.getApellido()));
	        assertTrue(personas.contains("Alvarez"));
	        assertTrue(personas.contains("Perez"));
	        assertTrue(personas.contains("Belladona"));
	    }
	 
	    // d.- Todas las personas que vivan en Buenos Aires.
	    @Test
	    public void testPersonasQueVivenEnBsAs() {
	        Collection<Tag> resultadoQuery = pDAO.getPersonasQueVivenEnProvincia("Buenos Aires");
	        assertEquals(2, resultadoQuery.size());
	        ArrayList<String> personas = new ArrayList<>();
	        resultadoQuery.forEach(p -> personas.add(p.getApellido()));
	        assertTrue(personas.contains("Alvarez"));
	        assertTrue(personas.contains("Perez"));
	    }
	 
	    // e.- La cantidad de personas que no tengan domicilio cargado
	    @Test
	    public void testPersonasSinDomicilio() {
	        Collection<Tag> resultadoQuery = pDAO.getPersonasSinDomicilio();
	        assertEquals(1, resultadoQuery.size());
	        ArrayList<String> personas = new ArrayList<>();
	        resultadoQuery.forEach(p -> personas.add(p.getApellido()));
	        assertTrue(personas.contains("Pizarro"));
	    }
	 
	    private void agregarDatosDePrueba() {
	        // a.- Todas las personas cuyo nombre sea exactamente ‘Juan’.
	        // b.- Todas las personas cuyo nombre comience con ‘J’, ordenados por
	        // apellido y luego por nombre.
	        // c.- Todas las personas que tengan entre 25 y 30 años.
	        // d.- Todas las personas que vivan en Buenos Aires.
	        // e.- La cantidad de personas que no tengan domicilio cargado
	 
	        ArrayList<Tag> personas = instanciaPersonas();
	        personas.forEach(p -> pDAO.guardar(p));    
	    }
	 
//	    private ArrayList<Persona> instanciaPersonas() {
//	        Provincia provBuenosAires = new Provincia("Buenos Aires");
//	        Provincia provSalta = new Provincia("Salta");
//	        Provincia provJujuy = new Provincia("Jujuy");
//	        Domicilio buenosAires = new Domicilio("Siempreviva", 123, "", "1232", "Una Localidad de bs as",
//	                provBuenosAires);
//	        Domicilio salta = new Domicilio("Saltando", 123, "", "1342", "Una Localidad salta", provSalta);
//	        Domicilio jujuy = new Domicilio("Jujüe", 123, "", "1342", "Una Localidad jujuy", provJujuy);
//	        LocalDate edadEntre25y30_1 = LocalDate.of(1992, 1, 1);
//	        LocalDate edadEntre25y30_2 = LocalDate.of(1987, 1, 1);
//	        LocalDate edadEntre25y30_3 = LocalDate.of(1990, 1, 1);
//	        LocalDate edadMenor25 = LocalDate.of(1997, 1, 1);
//	        LocalDate edadMayor30 = LocalDate.of(1986, 1, 1);
//	 
//	        ArrayList<Persona> personas = new ArrayList<>();
//	        personas.add(new Persona("Juan", "Alvarez", edadEntre25y30_1, buenosAires));
//	        personas.add(new Persona("Juan", "Perez", edadEntre25y30_2, buenosAires));
//	        personas.add(new Persona("Juana", "Belladona", edadEntre25y30_3, salta));
//	        personas.add(new Persona("Jose", "Cortez", edadMenor25, jujuy));
//	        personas.add(new Persona("Fernando", "Pizarro", edadMayor30, null));
//	        return personas;
//	    }

}
