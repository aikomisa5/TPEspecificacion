package com.EyVdeSW.TP.services;

import static org.junit.Assert.*;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.EyVdeSW.TP.Daos.impl.CampañaDAONeodatis;
import com.EyVdeSW.TP.Daos.impl.DuracionDAONeodatis;
import com.EyVdeSW.TP.Daos.impl.NeodatisLocalTestConnector;
import com.EyVdeSW.TP.domainModel.AccionPublicitaria;
import com.EyVdeSW.TP.domainModel.Campania;
import com.EyVdeSW.TP.domainModel.Campania.EstadoCampania;
import com.EyVdeSW.TP.domainModel.Mensaje;
import com.EyVdeSW.TP.domainModel.Tag;
import com.EyVdeSW.TP.domainModel.Usuario;
import com.EyVdeSW.TP.domainModel.AccionPublicitaria.TipoAccion;
import com.EyVdeSW.TP.domainModel.Usuario.TipoUsuario;

import properties.Parametros;

public class TestMailScheduler {
	static MailScheduler ms=MailScheduler.getMailScheduler();
	static CampañaDAONeodatis campañaDAO; 
	private static String dbFilePath;

	@BeforeClass
	public static void setUpClass() {
		campañaDAO = new CampañaDAONeodatis();
		campañaDAO.setBdConnector(new NeodatisLocalTestConnector());
		dbFilePath = Parametros.getProperty(Parametros.dbTestPath);
		
		File f = new File(dbFilePath);
		if (f.exists())
			f.delete();
	}

	@Before
	public void setUp() throws Exception {
		File f = new File(dbFilePath);
		if (f.exists())
			f.delete();
	}
	
	@AfterClass
	public static void tearDownClass() {		
		File f = new File(dbFilePath);
		if (f.exists())
			f.delete();
		ms.apagar();
	}

	
	
	public void borrarTodo(){
		campañaDAO.traerTodos().forEach(c -> campañaDAO.borrar(c));
	}
	
	//@Test
	public void pruebaRunTime() throws ParseException {
		ms.encender();
		String startDateStr = "2017-06-24 00:00:00.0";
        String endDateStr = "2017-06-25 00:00:00.0";
        
		try {
			ms.agregarAccion(startDateStr, endDateStr, "deidelson@mail.com", 
						"Prueba del service", "exito", "12","54", "1");
			//Cambiame de acuerdo al horario
			
			 TimeUnit.SECONDS.sleep(300);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//@Test
	public void agregarAccionesDeCampañas(){
		agregarDatos(instanciaCampañas());
		assertEquals(campañaDAO.getCampañasDe(new Usuario("misael", "britos", "misa@mail.com", "bases de datos", TipoUsuario.CLIENTE))
				.size(), 1);
		List<Campania>campañasVigentes=(List<Campania>) campañaDAO.getCampañasVigentes();
		assertEquals(campañasVigentes.size(), 1);
		ms.encender();
		ms.agregarAccionesDeCampañas(campañasVigentes);
		try {
			TimeUnit.SECONDS.sleep(300);//lo que necesite
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		borrarTodo();
	}
	
	@Test
	public void campañasVacias(){
		List<Campania>campañasVigentes= new ArrayList<>();
		ms.encender();
		ms.agregarAccionesDeCampañas(campañasVigentes);
		try {
			TimeUnit.SECONDS.sleep(300);//lo que necesite
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void agregarDatos(ArrayList<Campania>lista){
		lista.forEach(c -> campañaDAO.guardar(c));
	}
	
	public ArrayList<Campania> instanciaCampañas(){
		ArrayList<Campania>ret= new ArrayList<>();
		String startDateStr = "2017-06-24 00:00:00.0";
        String endDateStr = "2017-06-25 00:00:00.0";
        Date startDate=null;
        Date endDate=null;
		try {
			startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(startDateStr);
			endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(endDateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Usuario unico= new Usuario("misael", "britos", "misa@mail.com", "bases de datos", TipoUsuario.CLIENTE);
		AccionPublicitaria ac1 = new AccionPublicitaria("deidelson@mail.com", "titulo1", "texto1", TipoAccion.particular,
			1, "16", "5");
		AccionPublicitaria ac2 = new AccionPublicitaria("deidelson@mail.com", "titulo2", "texto2", TipoAccion.particular,
				1, "16", "5");
		Tag t= new Tag("Deportes");
		List<AccionPublicitaria>acciones = new ArrayList<>();
		List<Tag>tags= new ArrayList<>();
		acciones.add(ac1);
		acciones.add(ac2);
		tags.add(t);
		Mensaje m = new Mensaje("titulo", "texto");
		Campania c = new Campania(unico, "sarasa", "dasdas", acciones, tags, m, startDate, endDate);
		c.setEstado(EstadoCampania.PLANIFICADA);
		ret.add(c);
		return ret;
	}

}
