package properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

public class Parametros {
	private static Properties p = new Properties();
	//por si ser rompe, lo unico que hice es agregar la ruta C:\\TP\\
	public static final String PARAM_FILE = "C:\\TP\\TP.properties";
	
	//legacy code, ahora asigno las properties directamente
	public static final String dbPath = "ubicacion.bd";
	private static final String dbPathValue = "C:\\TP\\TPbd";
	
	public static void SetearParametros() {		
		p.setProperty("ubicacion.bd", "C:\\TP\\TPbd");		
		p.setProperty("email.user", "tpmailsender@mail.com");
		p.setProperty("email.pass", "especificacion");
		try {
			p.store(new FileOutputStream(PARAM_FILE), "");
		} catch (Exception e) {
			System.err.println(PARAM_FILE + ": no se pudo escribir en el archivo.");
		}
	}

	@Deprecated
	/**
	 * 
	 * usar getProperty(key) en su lugar
	 */
	public static Properties getProperties() {
		try {
			File f = new File(PARAM_FILE);
			if (!f.exists()) SetearParametros();
			
			FileInputStream fis = new FileInputStream(PARAM_FILE);
			p.load(fis);
		} catch (Exception e) {
			System.err.println(PARAM_FILE + ":archivo no encontrado.");
		}

		return p;
	}
	
	public static String getProperty(String key){
		try {
			File f = new File(PARAM_FILE);
			if (!f.exists()) SetearParametros();
			
			FileInputStream fis = new FileInputStream(PARAM_FILE);
			p.load(fis);
		} catch (Exception e) {
			System.err.println(PARAM_FILE + ":archivo no encontrado.");
		}

		return p.getProperty(key);
	}

}
