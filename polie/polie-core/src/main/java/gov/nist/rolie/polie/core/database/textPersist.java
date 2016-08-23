package gov.nist.rolie.polie.core.database;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class textPersist {

	static String root = "C:\\Users\\sab3\\Textdatabase\\";
	
	public static String get(String uri) {
		String result = null;
		Path file = Paths.get(root+uri+".txt");
		try {
			BufferedReader reader = Files.newBufferedReader(file);
			String line = null;
			while ((line = reader.readLine()) != null)
			{
				result+=line;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public static boolean put(String uri, String data) {
		// TODO Auto-generated method stub
		return false;
	}

	public static boolean delete(String uri) {
		// TODO Auto-generated method stub
		return false;
	}

}
