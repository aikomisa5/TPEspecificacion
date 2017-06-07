package testDAOs;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.EyVdeSW.TP.Daos.ArbolTagDAO;
import com.EyVdeSW.TP.Daos.impl.ArbolTagDAONeodatis;
import com.EyVdeSW.TP.Daos.impl.DAONeodatis;
import com.EyVdeSW.TP.Daos.impl.NeodatisLocalConnector;
import com.EyVdeSW.TP.domainModel.ArbolTag;
import com.EyVdeSW.TP.domainModel.Tag;

import properties.Parametros;

public class TestArbolTagDAONeodatis {

	private static String dbFilePath;
	private static ArbolTagDAO arbolTagDAO;

	@SuppressWarnings("unchecked")
	@BeforeClass
	public static void setUpClass() {
		arbolTagDAO = new ArbolTagDAONeodatis();		
		((DAONeodatis<ArbolTag>) arbolTagDAO).setBdConnector(new NeodatisLocalConnector());		
		dbFilePath = Parametros.getProperties().getProperty(Parametros.dbPath);
	}

	@Before
	public void setUp() throws Exception {
		
		File f = new File(dbFilePath);
		if (f.exists())
			f.delete();
		agregarDatosDePrueba();
	}

	@After
	public void limpiarBD() {
		Collection<ArbolTag> arbolTags = arbolTagDAO.traerArboles();
		arbolTags.forEach(e -> arbolTagDAO.borrar(e));
	}	

	@Test
	public void testearArbol() {
		List<ArbolTag> ret = (List<ArbolTag>) arbolTagDAO.traerArboles();
		ArbolTag ab = ret.get(0);
		ab.getRaiz().getHijos().forEach(hijo -> System.out.println(hijo));
		assertEquals(ab.getRaiz().getHijos().size(), 2);
		assertEquals(ab.getRaiz().getHijos().get(0).getHijos().size(), 2);
		assertEquals(ab.getRaiz().getHijos().get(1).getHijos().size(), 1);

	}

	@Test
	public void esRaiz() {
		assertTrue(arbolTagDAO.esRaiz(new Tag("Raiz")));
		assertFalse(arbolTagDAO.esRaiz(new Tag("Padre1")));
		assertFalse(arbolTagDAO.esRaiz(new Tag("Padre2")));
		assertFalse(arbolTagDAO.esRaiz(new Tag("Hijo11")));
		assertFalse(arbolTagDAO.esRaiz(new Tag("sarasa")));// no existe, no
															// deberia crashear
		arbolTagDAO.guardar(new ArbolTag(new Tag("sarasa")));
		assertTrue(arbolTagDAO.esRaiz(new Tag("sarasa")));
	}

	@Test
	public void getPorNombreDeRaiz() {
		ArbolTag raiz1 = arbolTagDAO.getArbolPorNombreRaiz("Raiz");
		assertEquals(raiz1.getRaiz().getHijos().size(), 2);
		arbolTagDAO.guardar(new ArbolTag(new Tag("sarasa")));
		raiz1 = arbolTagDAO.getArbolPorNombreRaiz("sarasa");
		assertEquals(raiz1.getRaiz().getNombre(), "sarasa");
		raiz1 = arbolTagDAO.getArbolPorNombreRaiz("deportes");
		assertEquals(raiz1, null);
	}

	private void agregarDatosDePrueba() {
		ArbolTag ab = instanciaArbol();
		arbolTagDAO.guardar(ab);
	}

	private ArbolTag instanciaArbol() {
		ArbolTag ab = new ArbolTag(new Tag("Raiz"));
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

		List<Tag> padres = new ArrayList<Tag>();
		padres.add(padre1);
		padres.add(padre2);
		ab.getRaiz().setHijos(padres);

		return ab;
	}

}
