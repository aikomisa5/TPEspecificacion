package com.EyVdeSW.TP.Daos.impl;

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
import com.EyVdeSW.TP.domainModel.ArbolTag;
import com.EyVdeSW.TP.domainModel.Tag;

import properties.Parametros;

public class TestArbolTagDAONeodatis {

	 private static String dbFilePath;
	    private static ArbolTagDAO arbolTagDAO;
	    private static TagDAONeodatis	tagDAO;
	 
	    @BeforeClass
	    public static void setUpClass() {
	        arbolTagDAO = new ArbolTagDAONeodatis();
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
	        Collection<ArbolTag> arbolTags = arbolTagDAO.traerArboles();        
	        arbolTags.forEach(e -> arbolTagDAO.borrar(e));
	    }
	   
	   @Test
	   public void testearArbol(){
		   List<ArbolTag> ret= (List<ArbolTag>) arbolTagDAO.traerArboles();
		   ArbolTag ab= ret.get(0);
		   ab.getRaiz().getHijos().forEach(hijo -> System.out.println(hijo));
		   assertEquals(ab.getRaiz().getHijos().size(), 2);
		   assertEquals(ab.getRaiz().getHijos().get(0).getHijos().size(), 2);
		   assertEquals(ab.getRaiz().getHijos().get(1).getHijos().size(), 1);
		   
	 }
	   
	 @Test
	 public void agregandoTags()
	 {
		 
	 }
	
	
	private void agregarDatosDePrueba() {
        ArbolTag ab = instanciaTags();
        arbolTagDAO.guardar(ab);    
    }
 
    private ArbolTag instanciaTags() {
    ArbolTag ab= new ArbolTag(new Tag("Raiz"));
     Tag padre1 = new Tag("Padre1");
     Tag padre2 = new Tag("Padre2");
     Tag hijo11 = new Tag("Hijo11");	     
     Tag hijo12 = new Tag("Hijo12");
     Tag hijo21 = new Tag("Hijo21");
     
    
     List<Tag> hijos= new ArrayList<Tag>();
     hijos.add(hijo11);
     hijos.add(hijo12);
     padre1.setHijos(hijos);
     
     hijos= new ArrayList<Tag>();
     hijos.add(hijo21);
     padre2.setHijos(hijos);
     
     List<Tag> padres= new ArrayList<Tag>();
     padres.add(padre1);
     padres.add(padre2);
     ab.getRaiz().setHijos(padres);
     
     return ab;
    }
    
	private ArrayList<Tag> instanciaTags2()
	{

		Tag padre1 = new Tag("Padre1");
		Tag padre2 = new Tag("Padre2");
		Tag hijo11 = new Tag("Hijo11");
		Tag hijo12 = new Tag("Hijo12");
		Tag hijo111= new Tag("hijo111");
		Tag hijo112= new Tag("hijo112");
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
	
	private void agregarDatosDePrueba(ArrayList<Tag> instancia)
	{
		instancia.forEach(t -> tagDAO.guardar(t));
	}

}
