package com.EyVdeSW.TP.Daos.impl;

import java.util.Collection;

import com.EyVdeSW.TP.Daos.AccionPublicitariaDAO;
import com.EyVdeSW.TP.domainModel.AccionPublicitaria;

public class AccionPublicitariaDAONeodatis extends DAONeodatis<AccionPublicitaria> implements AccionPublicitariaDAO {

	@Override
	public void modificar(AccionPublicitaria original, AccionPublicitaria modificacion) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Collection<AccionPublicitaria> traerTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<AccionPublicitaria> consultarPorNombre(String destinatario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AccionPublicitaria getCampañaPorNombre(String destinatario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existe(String destinatario) {
		// TODO Auto-generated method stub
		return false;
	}

}
