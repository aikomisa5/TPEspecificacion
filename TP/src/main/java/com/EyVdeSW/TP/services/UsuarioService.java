package com.EyVdeSW.TP.services;

import com.EyVdeSW.TP.Daos.UsuarioDAO;
import com.EyVdeSW.TP.Daos.impl.UsuarioDAONeodatis;
import com.EyVdeSW.TP.domainModel.Usuario;
import com.EyVdeSW.TP.domainModel.Usuario.TipoUsuario;

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

	public void guardar (Usuario usuario){
		usuarioDAO.guardar(usuario);
	}
	
	public void guardar (String nombreUsuario, String nombreReal, String mail, String password, TipoUsuario tipoUsuario){
		String nombreUsuarioMinuscula = nombreUsuario.toLowerCase();
		String nombreRealMinuscula = nombreReal.toLowerCase();
		
		if (!usuarioDAO.existeUsuario(nombreUsuarioMinuscula)){
			usuarioDAO.guardar(new Usuario(nombreUsuarioMinuscula,nombreRealMinuscula,mail,password,tipoUsuario));
		}
		
	}
	
	public void borrar(String nombreUsuario){
		String nombreUsuarioMinuscula = nombreUsuario.toLowerCase();
		
		if (usuarioDAO.existeUsuario(nombreUsuarioMinuscula)){
			Usuario usuario = usuarioDAO.getUsuarioPorNombreUsuario(nombreUsuarioMinuscula);
			usuarioDAO.borrar(usuario);
		}
	}
	
	public boolean modificar (String nombreUsuarioOri, String nombreUsuarioMod, 
			String nombreRealMod, String mailMod, String passwordMod, TipoUsuario tipoUsuarioMod){
		boolean ret = true;
		if (usuarioDAO.existeUsuario(nombreUsuarioMod)) {
			ret = false;
		}
		{
			String nombreUsuarioModMinuscula = nombreUsuarioMod.toLowerCase();
			String nombreRealModMinuscula = nombreRealMod.toLowerCase();
			
			Usuario orig = usuarioDAO.getUsuarioPorNombreUsuario(nombreUsuarioOri);
			Usuario modi = usuarioDAO.getUsuarioPorNombreUsuario(nombreUsuarioOri);
			
			modi.setNombreUsuario(nombreUsuarioModMinuscula);
			modi.setNombreReal(nombreRealModMinuscula);
			modi.setMail(mailMod);
			modi.setPassword(passwordMod);
			modi.setTipoUsuario(tipoUsuarioMod);
			
			usuarioDAO.modificar(orig, modi);
		}
		return ret;
		
	}
	
}
