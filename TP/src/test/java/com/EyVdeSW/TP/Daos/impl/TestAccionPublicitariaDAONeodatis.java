package com.EyVdeSW.TP.Daos.impl;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.EyVdeSW.TP.domainModel.AccionPublicitaria;
import com.EyVdeSW.TP.domainModel.AccionPublicitaria.TipoAccion;
import com.EyVdeSW.TP.domainModel.Usuario;
import com.EyVdeSW.TP.domainModel.Campania.EstadoCampania;

import properties.Parametros;

public class TestAccionPublicitariaDAONeodatis {

	private static String dbFilePath;
	private static AccionPublicitariaDAONeodatis accionDAO;

	@BeforeClass
	public static void setUpClass() {
		accionDAO = new AccionPublicitariaDAONeodatis();
		accionDAO.setBdConnector(new NeodatisLocalConnector());
		dbFilePath = Parametros.getProperty(Parametros.dbPath);
	}

	@Before
	public void setUp() throws Exception {
		File f = new File(dbFilePath);
		if (f.exists())
			f.delete();
	}
	
	@Before
	public void limpiarBD(){
		Collection<AccionPublicitaria> usuarios = accionDAO.traerTodos();
		usuarios.forEach(t -> accionDAO.borrar(t));
		usuarios = accionDAO.traerTodos();
	}
	
	@Test
	public void modificar(){
		AccionPublicitaria original = new AccionPublicitaria("original",TipoAccion.general);
		AccionPublicitaria modificacion = new AccionPublicitaria("modificacion",TipoAccion.particular);
		
		
	}
	
	public void agregarDatosDePrueba(ArrayList<AccionPublicitaria>lista){
		lista.forEach(ap -> accionDAO.guardar(ap));
	}
	
	public ArrayList<AccionPublicitaria>instancia(){
		ArrayList<AccionPublicitaria>ret= new ArrayList<>();
		ret.add(new AccionPublicitaria("ap1", TipoAccion.general));
		ret.add(new AccionPublicitaria("ap2", TipoAccion.general));
		ret.add(new AccionPublicitaria("ap3", TipoAccion.particular));
		return ret;
	}

}
