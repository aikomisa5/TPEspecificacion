package com.EyVdeSW.TP.Daos.impl;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;

import properties.Parametros;

public class NeodatisLocalTestConnector implements neodatisBDConnector {

	@Override
	public ODB getBDConnection() {
		return ODBFactory.open(Parametros.getProperty(Parametros.dbTestPath));
	}

}
