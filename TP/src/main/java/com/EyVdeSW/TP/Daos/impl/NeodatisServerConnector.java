package com.EyVdeSW.TP.Daos.impl;

import org.neodatis.odb.ODB;

import com.EyVdeSW.TP.services.WebAppListener;

public class NeodatisServerConnector implements neodatisBDConnector {

	@Override
	public ODB getBDConnection() {		
		return WebAppListener.getServer().openClient(WebAppListener.getProperty("ubicacion.bd"));		
	}

}
