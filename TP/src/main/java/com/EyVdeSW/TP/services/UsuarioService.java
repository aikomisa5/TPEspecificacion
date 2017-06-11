package com.EyVdeSW.TP.services;

import com.EyVdeSW.TP.Daos.UsuarioDAO;
import com.EyVdeSW.TP.Daos.impl.UsuarioDAONeodatis;
import com.EyVdeSW.TP.domainModel.Usuario;
import com.EyVdeSW.TP.domainModel.Usuario.TipoUsuario;

public class UsuarioService {	private UsuarioDAO usuarioDAO;
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
	
	if (!usuarioDAO.existeUsuarioPorNombreUsuario(nombreUsuarioMinuscula)){
		usuarioDAO.guardar(new Usuario(nombreUsuarioMinuscula,nombreRealMinuscula,mail,password,tipoUsuario));
	}
	
}

public void borrar(String nombreUsuario){
	String nombreUsuarioMinuscula = nombreUsuario.toLowerCase();
	
	if (usuarioDAO.existeUsuarioPorNombreUsuario(nombreUsuarioMinuscula)){
		Usuario usuario = usuarioDAO.getUsuarioPorNombreUsuario(nombreUsuarioMinuscula);
		usuarioDAO.borrar(usuario);
	}
}

public boolean modificar (String nombreUsuarioOri, String nombreUsuarioMod, 
		String nombreRealMod, String mailMod, String passwordMod, TipoUsuario tipoUsuarioMod){
	
	boolean ret = true;
	String nombreUsuarioModMinuscula = nombreUsuarioMod.toLowerCase();
	String nombreRealModMinuscula = nombreRealMod.toLowerCase();
	
	if (usuarioDAO.existeUsuarioPorNombreUsuario(nombreUsuarioMod)) {
		ret = false;
	}
	{
		
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

public boolean existeUsuarioPorNombreUsuario(String nombreUsuario){
	String nombreUsuarioMinuscula = nombreUsuario.toLowerCase();
	if (usuarioDAO.existeUsuarioPorNombreUsuario(nombreUsuarioMinuscula))
	{
		return true;
	}
	
	return false;
	
}

public boolean existeUsuarioPorMail(String mail){
	String correo = mail;
	if (usuarioDAO.existeUsuarioPorMail(correo))
	{
		return true;
	}
	
	return false;
	
}

public Usuario getUsuarioPorNombreUsuario(String nombreUsuario){
	return (usuarioDAO.getUsuarioPorNombreUsuario(nombreUsuario));
}

public Usuario getUsuarioPorMail (String mail){
	return (usuarioDAO.getUsuarioPorMail(mail));
}

}

