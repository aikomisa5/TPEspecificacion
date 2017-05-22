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
	public static void setUpClass()
	{
		tagDAO = new TagDAONeodatis();
		dbFilePath = Parametros.getProperties().getProperty(Parametros.dbPath);
	}

	@Before
	public void setUp() throws Exception
	{

		File f = new File(dbFilePath);
		if (f.exists())
			f.delete();
		agregarDatosDePrueba();
	}

	@After
	public void limpiarBD()
	{
		Collection<Tag> tags = tagDAO.traerTodos();
		tags.forEach(t -> tagDAO.borrar(t));
		tags = tagDAO.traerTodos();
	}

	// @After
	public void tearDown() throws Exception
	{
		File f = new File(dbFilePath);
		if (f.exists())
			f.delete();
	}

	@Test
	public void testBorrarTags()
	{
		Collection<Tag> tags = tagDAO.traerTodos();
		// tags.forEach(t -> System.out.println(t));
		assertEquals(tags.size(), 5);
		tags.forEach(t -> tagDAO.borrar(t));
		tags = tagDAO.traerTodos();
		assertEquals(0, tags.size());
	}

	@Test
	public void modificarTag()
	{
		limpiarBD();
		agregarDatosDePrueba();

		tagDAO.modificar(new Tag(null, "Padre1", null), new Tag(null, "sarasa", null));
		ArrayList<Tag> tags = (ArrayList<Tag>) tagDAO.traerTodos();
		tags.forEach(e -> System.out.println(e.getNombre()));
		assertEquals(new Tag(null, "sarasa", null), tags.get(0));
	}

	@Test
	public void consultarPorNombre()
	{
		limpiarBD();
		agregarDatosDePrueba();

		ArrayList<Tag> tags = (ArrayList<Tag>) tagDAO.consultarPorNombre("adre");
		assertEquals(tags.size(), 2);
		assertEquals(tags.get(0).getNombre(), "Padre1");
		assertEquals(tags.get(1).getNombre(), "Padre2");
	}

	@Test
	public void getTagPorNombre()
	{
		limpiarBD();
		agregarDatosDePrueba();

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

	private void agregarDatosDePrueba()
	{
		ArrayList<Tag> tags = instanciaTags();
		tags.forEach(t -> tagDAO.guardar(t));
	}

	private ArrayList<Tag> instanciaTags()
	{

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

	private ArrayList<Tag> instanciaArbolTags()
	{
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
