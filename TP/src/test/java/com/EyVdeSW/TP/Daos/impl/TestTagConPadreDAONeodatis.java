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
import com.EyVdeSW.TP.Daos.impl.TagConPadreDAONeodatis;
import com.EyVdeSW.TP.domainModel.TagConPadre;

import properties.Parametros;

public class TestTagConPadreDAONeodatis {

	private static String dbFilePath;
	private static TagConPadreDAONeodatis tagDAO;

	@BeforeClass
	public static void setUpClass() {
		tagDAO = new TagConPadreDAONeodatis();
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
		tagDAO.guardar(new TagConPadre("unTag"));
		assertTrue(tagDAO.existe("unTag"));
		assertFalse(tagDAO.existe("unTagQueNoExiste"));
	}

	@Test
	public void testGetTagPorNombre() {
		TagConPadre tagAGuardar = new TagConPadre("unTag");
		tagDAO.guardar(tagAGuardar);

		TagConPadre tagObtenido = tagDAO.getTagPorNombre("unTag");

		assertEquals(tagAGuardar, tagObtenido);
		assertFalse(tagDAO.existe("unTagQueNoExiste"));
	}

	@Test
	public void testModificar() {
		guardarInstanciaEnBD(instanciaSimple());
		List<TagConPadre> tags = tagDAO.traerTodos().stream().collect(Collectors.toList());
		tags.forEach(tag -> {
			String nombreOriginal = tag.getNombre();
			tag.setNombre(tag.getNombre() + "Modificado");
			tagDAO.modificar(nombreOriginal, tag);
		});
		assertEquals(tags.size(), tagDAO.traerTodos().size());
	}

	@Test
	public void testTraerHijosDe() {
		List<TagConPadre> tags = instanciaSimple();
		guardarInstanciaEnBD(tags);
		Collection<TagConPadre> hijosDeRaiz = tagDAO.traerHijosDe(tags.get(0));
		assertEquals(3, hijosDeRaiz.size());
		assertTrue(hijosDeRaiz.contains(tags.get(1)));
		assertTrue(hijosDeRaiz.contains(tags.get(2)));
		assertTrue(hijosDeRaiz.contains(tags.get(3)));
		assertFalse(hijosDeRaiz.contains(tags.get(4)));

		Collection<TagConPadre> hijosDe2 = tagDAO.traerHijosDe(tags.get(2));
		assertEquals(1, hijosDe2.size());
		assertTrue(hijosDe2.contains(tags.get(4)));
		assertFalse(hijosDe2.contains(tags.get(3)));
	}

	@Test
	public void testTraerRaices() {
		List<TagConPadre> tags = instanciaBosque();
		guardarInstanciaEnBD(tags);

		Collection<TagConPadre> raices = tagDAO.traerRaices();
		assertEquals(2, raices.size());
		assertTrue(raices.contains(tags.get(0)));
		assertTrue(raices.contains(tags.get(1)));
		assertFalse(raices.contains(tags.get(3)));
	}

	@Test
	public void testBorrar() {
		List<TagConPadre> tags = instanciaBosque();
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

	private List<TagConPadre> instanciaSimple() {
		TagConPadre raiz = new TagConPadre("raiz");
		TagConPadre hijo1 = new TagConPadre("hijo1");
		TagConPadre hijo2 = new TagConPadre("hijo2");
		TagConPadre hijo3 = new TagConPadre("hijo3");
		TagConPadre hijo21 = new TagConPadre("hijo21");
		TagConPadre hijo31 = new TagConPadre("hijo31");
		TagConPadre hijo311 = new TagConPadre("hijo311");

		hijo1.setPadre(raiz);
		hijo2.setPadre(raiz);
		hijo3.setPadre(raiz);
		hijo21.setPadre(hijo2);
		hijo31.setPadre(hijo31);
		hijo311.setPadre(hijo31);

		return Arrays.asList(raiz, hijo1, hijo2, hijo3, hijo21, hijo31, hijo311);

	}

	private List<TagConPadre> instanciaBosque() {
		TagConPadre raiz1 = new TagConPadre("raiz1");
		TagConPadre raiz2 = new TagConPadre("raiz2");

		TagConPadre hijo11 = new TagConPadre("hijo11");
		TagConPadre hijo12 = new TagConPadre("hijo12");
		TagConPadre hijo13 = new TagConPadre("hijo13");

		TagConPadre hijo21 = new TagConPadre("hijo21");
		TagConPadre hijo22 = new TagConPadre("hijo22");
		TagConPadre hijo211 = new TagConPadre("hijo211");
		TagConPadre hijo212 = new TagConPadre("hijo212");
		TagConPadre hijo2121 = new TagConPadre("hijo2121");

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

	private void guardarInstanciaEnBD(List<TagConPadre> instancia) {
		instancia.forEach(tagDAO::guardar);
	}

}
