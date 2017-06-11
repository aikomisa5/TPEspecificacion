package com.EyVdeSW.TP.Daos;

import java.util.Collection;

import com.EyVdeSW.TP.domainModel.Usuario;

public interface UsuarioDAO extends DAO<Usuario>{
	
	public void modificar(Usuario original, Usuario modificacion);
	public Collection<Usuario>traerTodosLosUsuarios();
	public Collection<Usuario>consultarPorNombreUsuario(String nombreUsuario);
	public Usuario getUsuarioPorMail(String mail);
	public Usuario getUsuarioPorNombreUsuario(String nombreUsuario);
	public boolean existeUsuarioPorMail(String mail);
	public boolean existeUsuarioPorNombreUsuario(String nombreUsuario);

}
