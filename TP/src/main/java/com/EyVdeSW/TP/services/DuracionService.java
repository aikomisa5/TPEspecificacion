package com.EyVdeSW.TP.services;

import com.EyVdeSW.TP.Daos.DuracionDAO;
import com.EyVdeSW.TP.Daos.impl.DuracionDAONeodatis;

public class DuracionService {
	private DuracionDAO duracionDAO;
	private static DuracionService duracionService;

	private DuracionService() {
		duracionDAO = new DuracionDAONeodatis();
	}

	public static DuracionService getDuracionService() {
		if (duracionService == null) {
			duracionService = new DuracionService();
		}
		return duracionService;
	}
	
	

}
