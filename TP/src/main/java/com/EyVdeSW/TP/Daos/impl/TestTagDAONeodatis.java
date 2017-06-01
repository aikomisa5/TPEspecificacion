package com.EyVdeSW.TP.Daos.impl;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import com.EyVdeSW.TP.domainModel.Tag;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import properties.Parametros;

public class TestTagDAONeodatis
{

	private static String			dbFilePath;
	private static TagDAONeodatis	tagDAO;

	@BeforeClass
	public static void setUpClass(){
		tagDAO = new TagDAONeodatis();		
		tagDAO.setBdConnector( new NeodatisLocalConnector());
		dbFilePath = Parametros.getProperties().getProperty(Parametros.dbPath);
	}

	@Before
	public void setUp() throws Exception{
		File f = new File(dbFilePath);
		if (f.exists())
			f.delete();
	}

	@After
	public void limpiarBD(){
		Collection<Tag> tags = tagDAO.traerTodos();
		tags.forEach(t -> tagDAO.borrar(t));
		tags = tagDAO.traerTodos();
	}

	//TODO @After
	public void tearDown() throws Exception{
		File f = new File(dbFilePath);
		if (f.exists())
			f.delete();
	}

	@Test
	public void testBorrarTags(){
		agregarDatosDePrueba(instanciaTags());
		Collection<Tag> tags = tagDAO.traerTodos();
		assertEquals(tags.size(), 5);
		tags.forEach(t -> tagDAO.borrar(t));
		tags = tagDAO.traerTodos();
		assertEquals(0, tags.size());
	}
	
	@Test
	public void borrarTags2(){
		limpiarBD();
		agregarDatosDePrueba(instanciaTags2());
		Tag padre1=tagDAO.getTagPorNombre("Padre1");
		tagDAO.borrar(padre1);
		assertEquals(tagDAO.traerTodos().size(), 2);
	}

	@Test
	public void modificarTag(){
		limpiarBD();
		agregarDatosDePrueba(instanciaTags());
		tagDAO.modificar(new Tag(null, "Padre1", null), new Tag(null, "sarasa", null));
		ArrayList<Tag> tags = (ArrayList<Tag>) tagDAO.traerTodos();
		tags.forEach(e -> System.out.println(e.getNombre()));
		assertEquals(new Tag(null, "sarasa", null), tags.get(0));
	}
	
	@Test
	public void modificarTag2(){
		limpiarBD();
		agregarDatosDePrueba(instanciaTags2());
		List<Tag>tags=(List<Tag>) tagDAO.traerTodos();
		assertEquals(tags.size(), 7);
		Tag t1= tagDAO.getTagPorNombre("Hijo111");
		t1.addHijo(new Tag("Hijo1111"));
		tagDAO.modificar(t1, t1);
		t1= tagDAO.getTagPorNombre("Hijo111");
		t1.addHijo(new Tag("Hijo1112"));
		tagDAO.modificar(t1, t1);
		tags=(List<Tag>) tagDAO.traerTodos();
		assertEquals(tags.size(), 9);
	}

	@Test
	public void consultarPorNombre(){
		limpiarBD();
		agregarDatosDePrueba(instanciaTags());

		ArrayList<Tag> tags = (ArrayList<Tag>) tagDAO.consultarPorNombre("adre");
		assertEquals(tags.size(), 2);
		assertEquals(tags.get(0).getNombre(), "Padre1");
		assertEquals(tags.get(1).getNombre(), "Padre2");
	}

	@Test
	public void getTagPorNombre(){
		limpiarBD();
		agregarDatosDePrueba(instanciaTags());

		Tag tag = tagDAO.getTagPorNombre("Padre1");
		assertEquals(tag.getNombre(), "Padre1");
	}
	
	@Test
	public void tagArbol(){
		ArrayList <Tag> tags = instanciaArbolTags();
		System.out.println("-----------------------------");
		tags.forEach(tag -> System.out.println(tag +
					" hijos: "+tag.getHijos()));
		System.out.println("-----------------------------");
	}
	
	@Test
	public void existe(){
		agregarDatosDePrueba(instanciaTags());
		
		assertTrue(tagDAO.existe("Padre1"));
		assertTrue(tagDAO.existe("Padre2"));
		assertTrue(tagDAO.existe("Hijo11"));
		assertTrue(tagDAO.existe("Hijo12"));
		assertTrue(tagDAO.existe("Hijo21"));
		assertFalse(tagDAO.existe("Padre"));
		assertFalse(tagDAO.existe("adre1"));
		assertFalse(tagDAO.existe("Padre12"));
		assertFalse(tagDAO.existe("padre1"));//Reconoce minusculas y mayusculas
	}

	private void agregarDatosDePrueba(ArrayList<Tag> instancia){
		instancia.forEach(t -> tagDAO.guardar(t));
	}

	private ArrayList<Tag> instanciaTags(){

		Tag padre1 = new Tag("Padre1");
		Tag padre2 = new Tag("Padre2");
		Tag hijo11 = new Tag("Hijo11");
		Tag hijo12 = new Tag("Hijo12");
		Tag hijo21 = new Tag("Hijo21");

		List<Tag> hijos = new ArrayList<Tag>();
		hijos.add(hijo11);
		hijos.add(hijo12);
		padre1.setHijos(hijos);

		hijos = new ArrayList<Tag>();
		hijos.add(hijo21);
		padre2.setHijos(hijos);

		ArrayList<Tag> ret = new ArrayList<>();

		ret.add(padre1);
		ret.add(padre2);

		return ret;
	}
	
	private ArrayList<Tag> instanciaTags2(){

		Tag padre1 = new Tag("Padre1");
		Tag padre2 = new Tag("Padre2");
		Tag hijo11 = new Tag("Hijo11");
		Tag hijo12 = new Tag("Hijo12");
		Tag hijo111= new Tag("Hijo111");
		Tag hijo112= new Tag("Hijo112");
		Tag hijo21 = new Tag("Hijo21");
		

		List<Tag> hijos = new ArrayList<Tag>();
		hijos.add(hijo11);
		hijos.add(hijo12);
		padre1.setHijos(hijos);

		hijos = new ArrayList<Tag>();
		hijos.add(hijo21);
		padre2.setHijos(hijos);
		
		hijos = new ArrayList<Tag>();
		hijos.add(hijo111);
		hijos.add(hijo112);
		hijo11.setHijos(hijos);

		ArrayList<Tag> ret = new ArrayList<>();

		ret.add(padre1);
		ret.add(padre2);

		return ret;
	}

	private ArrayList<Tag> instanciaArbolTags(){
		ArrayList<Tag> tags = new ArrayList<>();
		Tag raiz1 = new Tag("Raiz1");
		Tag hijo1 = new Tag("R1.Hijo1");
		hijo1.addHijo(new Tag("R1.H1.H1"));
		raiz1.addHijo(hijo1);
		raiz1.addHijo(new Tag("R1.Hijo2"));
		Tag raiz2 = new Tag("Raiz2");

		tags.add(raiz1);
		tags.add(raiz2);

		return tags;
	}

}
