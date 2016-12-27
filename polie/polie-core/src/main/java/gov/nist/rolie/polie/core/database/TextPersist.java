package gov.nist.rolie.polie.core.database;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import gov.nist.rolie.polie.core.models.AtomCategoryDocument;
import gov.nist.rolie.polie.core.models.AtomEntry;
import gov.nist.rolie.polie.core.models.AtomResource;
import gov.nist.rolie.polie.core.models.AtomServiceDocument;


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
	public AtomServiceDocument saveServiceDocument(AtomServiceDocument servicedocument) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AtomServiceDocument loadServiceDocument(URI iri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AtomCategoryDocument saveCategoryDocument(AtomCategoryDocument categorydocument) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AtomCategoryDocument loadCategoryDocument(URI iri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AtomResource loadResource(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AtomResource loadResource(URI iri) {
		return null;
	}

	@Override
	public AtomResource createResource(AtomResource resource, URI uri) {
		return resource;
	}

	@Override
	public AtomResource updateResource(AtomResource resource, URI uri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AtomResource deleteResource(URI uri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AtomResource deleteResource(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
