package gov.nist.rolie.polie.persistence.database;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import gov.nist.rolie.polie.model.ResourceType;
import gov.nist.rolie.polie.model.models.APPCategories;
import gov.nist.rolie.polie.model.models.APPResource;
import gov.nist.rolie.polie.model.models.APPServiceDocument;
import gov.nist.rolie.polie.model.models.AtomEntry;
import gov.nist.rolie.polie.model.models.AtomFeed;
import gov.nist.rolie.polie.model.models.elements.APPCollection;


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
	public APPServiceDocument saveServiceDocument(APPServiceDocument servicedocument) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public APPServiceDocument loadServiceDocument(URI iri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public APPCategories saveCategoryDocument(APPCategories categorydocument) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public APPCategories loadCategoryDocument(URI iri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public APPResource loadResource(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public APPResource loadResource(URI iri) {
		return null;
	}

	@Override
	public APPResource createResource(APPResource resource, URI uri) {
		return resource;
	}

	@Override
	public APPResource updateResource(APPResource resource, URI uri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public APPResource deleteResource(URI uri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public APPResource deleteResource(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public APPResource copyResource(APPResource resource) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AtomFeed loadFeed(URI uri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AtomEntry loadEntry(URI iri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AtomFeed saveFeed(AtomFeed feed) {
		return feed;
		
	}

	@Override
	public APPServiceDocument loadServiceDocument() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public APPCategories loadCategoryDocument() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResourceType identifyResouceType(URI iri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void cleanup() {
		// TODO Auto-generated method stub
		
	}


}
