package com.EyVdeSW.TP.Daos.impl;

import java.util.Collection;

import com.EyVdeSW.TP.Daos.UsuarioDAO;
import com.EyVdeSW.TP.domainModel.Usuario;

public class UsuarioDAONeodatis extends DAONeodatis<Usuario> implements UsuarioDAO{

	@Override
	public void modificar(Usuario original, Usuario modificacion) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Collection<Usuario> traerTodosLosUsuarios() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Usuario> consultarPorNombre(String nombreUsuario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario getUsuarioPorNombre(String nombreUsuario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existe(String nombreUsuario) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
}
