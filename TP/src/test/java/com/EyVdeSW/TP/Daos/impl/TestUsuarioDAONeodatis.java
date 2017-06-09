package com.EyVdeSW.TP.Daos.impl;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.EyVdeSW.TP.Daos.impl.NeodatisLocalConnector;
import com.EyVdeSW.TP.Daos.impl.UsuarioDAONeodatis;
import com.EyVdeSW.TP.domainModel.Usuario;
import com.EyVdeSW.TP.domainModel.Usuario.TipoUsuario;


import properties.Parametros;

public class TestUsuarioDAONeodatis {

	private static String dbFilePath;
	private static UsuarioDAONeodatis usuarioDAO;
	
	@BeforeClass
	public static void setUpClass(){
		usuarioDAO = new UsuarioDAONeodatis();		
		usuarioDAO.setBdConnector( new NeodatisLocalConnector());
		dbFilePath = Parametros.getProperty(Parametros.dbPath);
	}
	
	@Before
	public void setUp() throws Exception{
		File f = new File(dbFilePath);
		if (f.exists())
			f.delete();
	}
	
	@Before
	public void limpiarBD(){
		Collection<Usuario> usuarios = usuarioDAO.traerTodosLosUsuarios();
		usuarios.forEach(t -> usuarioDAO.borrar(t));
		usuarios = usuarioDAO.traerTodosLosUsuarios();
	}
	
	@Test
	public void modificarSimple(){
		Usuario u1= new Usuario("soyUnNombreUsuario", "soyUnNombreReal","soyUnMail@gmail.com","soyUnaPassword",null);
		u1.setTipoUsuario(TipoUsuario.CLIENTE);
		usuarioDAO.guardar(u1);
		
		Usuario u2=usuarioDAO.getUsuarioPorNombreUsuario("soyUnNombreUsuario");
		
		assertEquals(u1, u2);
		
		u2.setNombreUsuario("fulano77");
		u2.setNombreReal("fulanito");
	
		
		usuarioDAO.modificar(u1, u2);
		//Aca ya tenemos testeado el getUsuarioPorNombre
		assertEquals(usuarioDAO.getUsuarioPorNombreUsuario("fulano77"), u2);
	}
	
	@Test
	public void consultarPorNombreDeUsuario(){
		agregarDatosDePrueba(instanciaUsuarios());
		assertEquals(usuarioDAO.consultarPorNombreUsuario("soy").size(), 5);
		assertEquals(usuarioDAO.consultarPorNombreUsuario("soyUnNombreUsuario3").size(), 1);
	}
	
	@Test
	public void existeUsuario(){
		agregarDatosDePrueba(instanciaUsuarios());
		assertTrue(usuarioDAO.existeUsuario("soyUnNombreUsuario1"));
		assertFalse(usuarioDAO.existeUsuario("soyUnNombreUsuario6"));
		assertFalse(usuarioDAO.existeUsuario("soyUnNombreUsuario"));
	}
	
	private void agregarDatosDePrueba(ArrayList<Usuario>instancia){
		instancia.forEach(u -> usuarioDAO.guardar(u));
	}
	
	private ArrayList<Usuario>instanciaUsuarios(){
		ArrayList<Usuario>ret=new ArrayList<>();
		ret.add(new Usuario("soyUnNombreUsuario1", "soyUnNombreReal","soyUnMail@gmail.com","soyUnaPassword",null));
		ret.add(new Usuario("soyUnNombreUsuario2", "soyUnNombreReal","soyUnMail@gmail.com","soyUnaPassword",null));
		ret.add(new Usuario("soyUnNombreUsuario3", "soyUnNombreReal","soyUnMail@gmail.com","soyUnaPassword",null));
		ret.add(new Usuario("soyUnNombreUsuario4", "soyUnNombreReal","soyUnMail@gmail.com","soyUnaPassword",null));
		ret.add(new Usuario("soyUnNombreUsuario5", "soyUnNombreReal","soyUnMail@gmail.com","soyUnaPassword",null));
		return ret;
	}
	
}
