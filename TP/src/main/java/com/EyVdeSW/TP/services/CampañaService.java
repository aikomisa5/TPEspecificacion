package com.EyVdeSW.TP.services;

import com.EyVdeSW.TP.Daos.CampañaDAO;
import com.EyVdeSW.TP.Daos.impl.CampañaDAONeodatis;
import com.EyVdeSW.TP.domainModel.Campaña;

public class CampañaService {

	private CampañaDAO campañaDAO;
	private static CampañaService campañaService;
	
	private CampañaService() {
		campañaDAO = new CampañaDAONeodatis();
	}

	public static CampañaService getCampañaService() {
		if (campañaService == null) {
			campañaService = new CampañaService();
		}
		return campañaService;
	}
	
	public void guardar(Campaña campaña){
		campañaDAO.guardar(campaña);
		
	}
		
}
