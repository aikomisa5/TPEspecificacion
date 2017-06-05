package com.EyVdeSW.TP.Daos.impl;

import java.util.Collection;

import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

import com.EyVdeSW.TP.Daos.UsuarioDAO;
import com.EyVdeSW.TP.domainModel.Usuario;

public class UsuarioDAONeodatis extends DAONeodatis<Usuario> implements UsuarioDAO{

	@Override
	public void modificar(Usuario original, Usuario modificacion) {
		odb = null;
		try{			
			odb = bdConnector.getBDConnection();
			IQuery query = new CriteriaQuery(Usuario.class, Where.like("usuario", original.getUsuario()));	
			Objects<Usuario>resultadoQuery=odb.getObjects(query);
			
			Usuario t=resultadoQuery.getFirst();
			t.setUsuario(modificacion.getUsuario());
			t.setNombre(modificacion.getNombre());
			t.setPassword(modificacion.getPassword());
			t.setTipoUsuario(modificacion.getTipoUsuario());
			
			odb.store(t);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if (odb != null)
				odb.close();
		}
	}
	

	@Override
	public Collection<Usuario> traerTodosLosUsuarios() {
		return consultar(new CriteriaQuery(Usuario.class));
	}

	@Override
	public Collection<Usuario> consultarPorNombre(String nombreUsuario) {
		IQuery query = new CriteriaQuery(Usuario.class, Where.like("usuario", "%"+nombreUsuario+"%"));
		return consultar(query);
	}

	@Override
	public Usuario getUsuarioPorNombre(String nombreUsuario) {
		Usuario usuario = null;		
		Objects<Usuario> resultadoQuery = null;
		odb=null;
		try{
			odb = bdConnector.getBDConnection();
			resultadoQuery = odb.getObjects(new CriteriaQuery(Usuario.class, Where.like("usuario", "%"+nombreUsuario+"%")));
			if(resultadoQuery.size() != 0)
				usuario= resultadoQuery.getFirst();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if (odb != null)
				odb.close();
		}
		return usuario;
	}

	@Override
	public boolean existe(String nombreUsuario) {
		boolean ret=false;
		Objects<Usuario> resultadoQuery = null;
		odb=null;
		try{
			odb = bdConnector.getBDConnection();
			resultadoQuery = odb.getObjects(new CriteriaQuery(Usuario.class, Where.equal("usuario", nombreUsuario)));
			ret = ret||(resultadoQuery.size() != 0);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if (odb != null)
				odb.close();
		}
		return ret;
	
	}
	
	
	
	
}
