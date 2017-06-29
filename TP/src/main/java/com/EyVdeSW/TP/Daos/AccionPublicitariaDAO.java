package com.EyVdeSW.TP.Daos;

import java.util.Collection;
import java.util.List;

import com.EyVdeSW.TP.domainModel.AccionPublicitaria;

public interface AccionPublicitariaDAO extends DAO<AccionPublicitaria>{
	
	public void modificar(AccionPublicitaria original, AccionPublicitaria modificacion);
	public Collection<AccionPublicitaria>traerTodos();
	public Collection<AccionPublicitaria>consultarPorDestinatario(String destinatario);
	public AccionPublicitaria getAccion(String destinatario, String titulo, String descripcion);
	public boolean existe(String destinatario, String titulo, String descripcion);
	public void modificarMasivo(List<AccionPublicitaria>acciones, String tituloNuevo, String msgNuevo);
}
