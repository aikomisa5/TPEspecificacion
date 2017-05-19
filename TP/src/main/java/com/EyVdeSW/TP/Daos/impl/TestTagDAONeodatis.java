package com.EyVdeSW.TP.Daos.impl;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

import com.EyVdeSW.TP.domainModel.Tag;

import static org.junit.Assert.*;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.management.Query;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import properties.Parametros;

public class TestTagDAONeodatis {

	 private static String dbFilePath;
	    private static TagDAONeodatis tagDAO;
	 
	    @BeforeClass
	    public static void setUpClass() {
	        tagDAO = new TagDAONeodatis();
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
	    public void limpiarBD()
	    {
	    	IQuery query = new CriteriaQuery(Tag.class);
	        Collection<Tag> tags = tagDAO.consultar(query);
	        assertEquals(tags.size(), 5);
	        tags.forEach(t -> tagDAO.borrar(t));
	        tags = tagDAO.consultar(query);
	        assertEquals(0, tags.size());
	    }
	 
	    // @After
	    public void tearDown() throws Exception {
	        File f = new File(dbFilePath);
	        if (f.exists())
	            f.delete();
	    }
	 
	    @Test
	    public void testBorrarPersonas() {
	    	IQuery query = new CriteriaQuery(Tag.class);
	        Collection<Tag> tags = tagDAO.consultar(query);
	        System.out.println(tags.size());
	        assertEquals(tags.size(), 5);
	        tags.forEach(t -> tagDAO.borrar(t));
	        tags = tagDAO.consultar(query);
	        System.out.println(tags.size());
	        assertEquals(0, tags.size());
	    }
	 
	    @Test
	    public void modificarTag(){
	    	
	    }
	 
	    private void agregarDatosDePrueba() {
	        ArrayList<Tag> tags = instanciaTags();
	        tags.forEach(t -> tagDAO.guardar(t));    
	    }
	 
	    private ArrayList<Tag> instanciaTags() {
	     Tag padre1 = new Tag("Padre1");
	     Tag padre2 = new Tag("Padre2");
	     Tag hijo11 = new Tag("Hijo11");
	     Tag hijo12 = new Tag("Hijo11");
	     Tag hijo21 = new Tag("Hijo21");
	   
	     List<Tag> hijos= new ArrayList<Tag>();
	     hijos.add(hijo11);
	     hijos.add(hijo12);
	     padre1.setHijos(hijos);
	     
	     hijos= new ArrayList<Tag>();
	     hijos.add(hijo21);
	     padre2.setHijos(hijos);
	     
	     ArrayList<Tag>ret = new ArrayList<>();
	     
	     ret.add(padre1);
	     ret.add(padre2);
	     
	     return ret;
	    }

}
