package gov.nist.rolie.polie.core.database;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import gov.nist.rolie.polie.core.models.CategoryDocument;
import gov.nist.rolie.polie.core.models.Collection;
import gov.nist.rolie.polie.core.models.Entry;
import gov.nist.rolie.polie.core.models.Feed;
import gov.nist.rolie.polie.core.models.ServiceDocument;
import gov.nist.rolie.polie.core.models.Workspace;

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
	public Entry saveEntry(Entry entry) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Entry loadEntry(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection saveCollection(Collection collection) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection loadCollection(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Feed saveFeed(Feed feed) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Feed loadFeed(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Workspace saveWorkspace(Workspace workspace) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Workspace loadWorkspace(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceDocument saveServiceDocument(ServiceDocument servicedocument) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceDocument loadServiceDocument(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CategoryDocument saveCategoryDocument(CategoryDocument categorydocument) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CategoryDocument loadCategoryDocument(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
