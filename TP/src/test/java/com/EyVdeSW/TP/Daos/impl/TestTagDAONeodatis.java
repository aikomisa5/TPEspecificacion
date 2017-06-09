package com.EyVdeSW.TP.Daos.impl;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.EyVdeSW.TP.Daos.impl.NeodatisLocalConnector;
import com.EyVdeSW.TP.Daos.impl.TagDAONeodatis;
import com.EyVdeSW.TP.domainModel.Tag;

import properties.Parametros;

public class TestTagDAONeodatis {

	private static String dbFilePath;
	private static TagDAONeodatis tagDAO;

	@BeforeClass
	public static void setUpClass() {
		tagDAO = new TagDAONeodatis();
		tagDAO.setBdConnector(new NeodatisLocalConnector());
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
		tagDAO.guardar(new Tag("unTag"));
		assertTrue(tagDAO.existe("unTag"));
		assertFalse(tagDAO.existe("unTagQueNoExiste"));
	}

	@Test
	public void testGetTagPorNombre() {
		Tag tagAGuardar = new Tag("unTag");
		tagDAO.guardar(tagAGuardar);

		Tag tagObtenido = tagDAO.getTagPorNombre("unTag");

		assertEquals(tagAGuardar, tagObtenido);
		assertFalse(tagDAO.existe("unTagQueNoExiste"));
	}

	@Test
	public void testModificar() {
		guardarInstanciaEnBD(instanciaSimple());
		List<Tag> tags = tagDAO.traerTodos().stream().collect(Collectors.toList());
		tags.forEach(tag -> {
			String nombreOriginal = tag.getNombre();
			tag.setNombre(tag.getNombre() + "Modificado");
			tagDAO.modificar(nombreOriginal, tag);
		});
		assertEquals(tags.size(), tagDAO.traerTodos().size());
	}

	@Test
	public void testTraerHijosDe() {
		List<Tag> tags = instanciaSimple();
		guardarInstanciaEnBD(tags);
		Collection<Tag> hijosDeRaiz = tagDAO.traerHijosDe(tags.get(0));
		assertEquals(3, hijosDeRaiz.size());
		assertTrue(hijosDeRaiz.contains(tags.get(1)));
		assertTrue(hijosDeRaiz.contains(tags.get(2)));
		assertTrue(hijosDeRaiz.contains(tags.get(3)));
		assertFalse(hijosDeRaiz.contains(tags.get(4)));

		Collection<Tag> hijosDe2 = tagDAO.traerHijosDe(tags.get(2));
		assertEquals(1, hijosDe2.size());
		assertTrue(hijosDe2.contains(tags.get(4)));
		assertFalse(hijosDe2.contains(tags.get(3)));
	}

	@Test
	public void testTraerRaices() {
		List<Tag> tags = instanciaBosque();
		guardarInstanciaEnBD(tags);

		Collection<Tag> raices = tagDAO.traerRaices();
		assertEquals(2, raices.size());
		assertTrue(raices.contains(tags.get(0)));
		assertTrue(raices.contains(tags.get(1)));
		assertFalse(raices.contains(tags.get(3)));
	}

	@Test
	public void testBorrar() {
		List<Tag> tags = instanciaBosque();
		guardarInstanciaEnBD(tags);

		// borrando una hoja
		tagDAO.borrar(tags.get(0));
		assertEquals(false, tagDAO.existe("hijo11"));

		// borrado recursivo de un subarbol
		tagDAO.borrar(tags.get(1));
		assertEquals(false, tagDAO.existe("hijo21"));
		assertEquals(false, tagDAO.existe("hijo211"));
		assertEquals(false, tagDAO.existe("hijo212"));
		assertEquals(false, tagDAO.existe("hijo2121"));

	}

	private List<Tag> instanciaSimple() {
		Tag raiz = new Tag("raiz");
		Tag hijo1 = new Tag("hijo1");
		Tag hijo2 = new Tag("hijo2");
		Tag hijo3 = new Tag("hijo3");
		Tag hijo21 = new Tag("hijo21");
		Tag hijo31 = new Tag("hijo31");
		Tag hijo311 = new Tag("hijo311");

		hijo1.setPadre(raiz);
		hijo2.setPadre(raiz);
		hijo3.setPadre(raiz);
		hijo21.setPadre(hijo2);
		hijo31.setPadre(hijo31);
		hijo311.setPadre(hijo31);

		return Arrays.asList(raiz, hijo1, hijo2, hijo3, hijo21, hijo31, hijo311);

	}

	private List<Tag> instanciaBosque() {
		Tag raiz1 = new Tag("raiz1");
		Tag raiz2 = new Tag("raiz2");

		Tag hijo11 = new Tag("hijo11");
		Tag hijo12 = new Tag("hijo12");
		Tag hijo13 = new Tag("hijo13");

		Tag hijo21 = new Tag("hijo21");
		Tag hijo22 = new Tag("hijo22");
		Tag hijo211 = new Tag("hijo211");
		Tag hijo212 = new Tag("hijo212");
		Tag hijo2121 = new Tag("hijo2121");

		hijo11.setPadre(raiz1);
		hijo12.setPadre(raiz1);
		hijo13.setPadre(raiz1);
		hijo21.setPadre(raiz2);
		hijo22.setPadre(raiz2);
		hijo211.setPadre(hijo21);
		hijo212.setPadre(hijo21);
		hijo2121.setPadre(hijo212);

		return Arrays.asList(raiz1, raiz2, hijo11, hijo12, hijo13, hijo21, hijo22, hijo211, hijo212, hijo2121);
	}

	private void guardarInstanciaEnBD(List<Tag> instancia) {
		instancia.forEach(tagDAO::guardar);
	}

}
