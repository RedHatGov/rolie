package gov.nist.rolie.polie.core.database;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import gov.nist.rolie.polie.core.XMLMangement.JAXBXMLResourceInterface;
import gov.nist.rolie.polie.core.XMLMangement.XMLResourceInterface;
import gov.nist.rolie.polie.core.exceptions.FailedToBuildResourceException;
import gov.nist.rolie.polie.core.exceptions.ResourceNotFoundInDatabaseException;
import gov.nist.rolie.polie.core.models.APPCategoryDocument;
import gov.nist.rolie.polie.core.models.APPResource;
import gov.nist.rolie.polie.core.models.APPServiceDocument;
import gov.nist.rolie.polie.core.models.AtomEntry;
import gov.nist.rolie.polie.core.models.AtomFeed;
import gov.nist.rolie.polie.core.models.elements.APPCollection;
import gov.nist.rolie.polie.core.models.elements.APPWorkspace;
import gov.nist.rolie.polie.core.models.elements.AtomId;
import gov.nist.rolie.polie.core.models.elements.AtomTitle;

public class DummyPersist implements PersistenceMethod {

	private static APPServiceDocument service = new APPServiceDocument();
	private static APPCategoryDocument categories = new APPCategoryDocument();
	private static AtomEntry entry = new AtomEntry();
	private static AtomFeed  feed = new AtomFeed();
	static {
		ArrayList<APPWorkspace> workspaces = new ArrayList<APPWorkspace>();
		APPWorkspace workspace = new APPWorkspace();
		APPCollection collection = new APPCollection();
		ArrayList<APPCollection> collections = new ArrayList<APPCollection>();
		collections.add(collection);
		workspace.setCollections(collections);
		service.setWorkspaces(workspaces);
		//entry.setTitle(new AtomTitle("I'm an entry"));
		feed.setTitle(new AtomTitle("I'm a feed"));
	}
	
	@Override
	public APPServiceDocument saveServiceDocument(APPServiceDocument servicedocument) {
		return servicedocument;
	}

	@Override
	public APPServiceDocument loadServiceDocument(URI iri) { 
		try {
			return (APPServiceDocument)loadResource(new URI("http://localhost:8080/polie-core/service"));
		} catch (ResourceNotFoundInDatabaseException e) {
			e.printStackTrace();
			return null;
		} catch (URISyntaxException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public APPCategoryDocument saveCategoryDocument(APPCategoryDocument categorydocument) {
		return categorydocument;
	}

	@Override
	public APPCategoryDocument loadCategoryDocument(URI iri) {
		try {
			return (APPCategoryDocument)loadResource(new URI("http://localhost:8080/polie-core/category"));
		} catch (ResourceNotFoundInDatabaseException e) {
			e.printStackTrace();
			return null;
		} catch (URISyntaxException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public APPResource loadResource(String id) {
		return null;
	}

	@Override
	public APPResource loadResource(URI iri) throws ResourceNotFoundInDatabaseException {
		String root = "C:\\Users\\sab3\\git\\IETF-ROLIE\\polie\\polie-core\\src\\main\\resources\\";
		String result = "";
		Path file = null;
		switch (iri.toString())
		{
		case "http://localhost:8080/polie-core/entry": file = Paths.get(root+"testEntry.xml"); break;
		case "http://localhost:8080/polie-core/feed": file = Paths.get(root+"testFeed.xml"); break;
		case "http://localhost:8080/polie-core/service": file = Paths.get(root+"testService.xml"); break;
		case "http://localhost:8080/polie-core/category": file = Paths.get(root+"testCategory.xml"); break;
		}

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
		XMLResourceInterface ri = new JAXBXMLResourceInterface();
		try {
			return ri.XMLToResource(ri.StringToXML(result));
		} catch (FailedToBuildResourceException e) {
			e.printStackTrace();
			throw new ResourceNotFoundInDatabaseException();
		}
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
		return null;
	}
	
	public APPResource postEntryToCollection(APPResource resource, URI uri)
	{
		//make sure that uri is a valid feed
		//Make sure that feed can accept the entry
		//update entry meta data
		//Update feed with new entry
		//update feed metadata
		//return entry
		return null;
	}

}
