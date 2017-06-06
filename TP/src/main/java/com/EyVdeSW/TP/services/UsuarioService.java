package com.EyVdeSW.TP.services;

import com.EyVdeSW.TP.Daos.UsuarioDAO;
import com.EyVdeSW.TP.Daos.impl.UsuarioDAONeodatis;

public class UsuarioService {
	private UsuarioDAO usuarioDAO;
	public static UsuarioService usuarioService;
	
	private UsuarioService(){
		usuarioDAO = new UsuarioDAONeodatis();
	}
	
	public static UsuarioService getUsuarioService() {
		if (usuarioService == null){
			usuarioService = new UsuarioService();
		}
		
		return usuarioService;
	}
	
	
}
