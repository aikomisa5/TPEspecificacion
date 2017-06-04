package com.EyVdeSW.TP.Daos;

import java.util.Collection;

import com.EyVdeSW.TP.domainModel.Usuario;

public interface UsuarioDAO extends DAO<Usuario>{
	
	public void modificar(Usuario original, Usuario modificacion);
	public Collection<Usuario>traerTodosLosUsuarios();
	public Collection<Usuario>consultarPorNombre(String nombreUsuario);
	public Usuario getUsuarioPorNombre(String nombreUsuario);
	public boolean existe(String nombreUsuario);

}
