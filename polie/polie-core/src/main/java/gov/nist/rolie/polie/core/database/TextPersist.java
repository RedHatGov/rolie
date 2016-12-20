package gov.nist.rolie.polie.core.database;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import gov.nist.rolie.polie.core.models.AtomCategoryDocument;
import gov.nist.rolie.polie.core.models.AtomCollection;
import gov.nist.rolie.polie.core.models.AtomEntry;
import gov.nist.rolie.polie.core.models.AtomFeed;
import gov.nist.rolie.polie.core.models.AtomServiceDocument;
import gov.nist.rolie.polie.core.models.AtomWorkspace;

public class TextPersist implements PersistenceMethod{

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

	@Override
	public AtomEntry saveEntry(AtomEntry entry) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AtomEntry loadEntry(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AtomCollection saveCollection(AtomCollection collection) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AtomCollection loadCollection(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AtomFeed saveFeed(AtomFeed feed) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AtomFeed loadFeed(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AtomWorkspace saveWorkspace(AtomWorkspace workspace) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AtomWorkspace loadWorkspace(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AtomServiceDocument saveServiceDocument(AtomServiceDocument servicedocument) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AtomServiceDocument loadServiceDocument(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AtomCategoryDocument saveCategoryDocument(AtomCategoryDocument categorydocument) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AtomCategoryDocument loadCategoryDocument(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
