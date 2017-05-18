package properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

public class Parametros {
	private static Properties p = new Properties();
	public static final String PARAM_FILE = "parametros";
	
	//Parametros a guardar.
	public static final String dbPath = "dbPath";
	private static final String dbPathValue = "neodatisDB";
	

	public static void SetearParametros() {
		p.setProperty(dbPath, dbPathValue);
		try {
			p.store(new FileOutputStream(PARAM_FILE), "");
		} catch (Exception e) {
			System.err.println(PARAM_FILE + ": no se pudo escribir en el archivo.");
		}
	}

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

}
