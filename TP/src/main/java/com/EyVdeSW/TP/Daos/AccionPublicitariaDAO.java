package com.EyVdeSW.TP.Daos;

import java.util.Collection;

import com.EyVdeSW.TP.domainModel.AccionPublicitaria;
import com.EyVdeSW.TP.domainModel.Campania;

public interface AccionPublicitariaDAO extends DAO<AccionPublicitaria>{
	
	public void modificar(AccionPublicitaria original, AccionPublicitaria modificacion);
	public Collection<AccionPublicitaria>traerTodos();
	public Collection<AccionPublicitaria>consultarPorDestinatario(String destinatario);
	public AccionPublicitaria getAccionPorDestinatario(String destinatario);
	public boolean existe(String destinatario);
}