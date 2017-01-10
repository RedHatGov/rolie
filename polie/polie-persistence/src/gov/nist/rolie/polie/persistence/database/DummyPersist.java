package gov.nist.rolie.polie.persistence.database;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import gov.nist.rolie.polie.model.models.APPCategories;
import gov.nist.rolie.polie.model.models.APPResource;
import gov.nist.rolie.polie.model.models.APPServiceDocument;
import gov.nist.rolie.polie.model.models.AtomEntry;
import gov.nist.rolie.polie.model.models.AtomFeed;
import gov.nist.rolie.polie.model.models.elements.APPCollection;
import gov.nist.rolie.polie.model.models.elements.APPWorkspace;
import gov.nist.rolie.polie.model.models.elements.AtomId;
import gov.nist.rolie.polie.model.models.elements.AtomTitle;
import gov.nist.rolie.polie.tools.XMLMangement.JAXBXMLResourceInterface;
import gov.nist.rolie.polie.tools.XMLMangement.XMLResourceInterface;
import gov.nist.rolie.polie.tools.exceptions.FailedToBuildResourceException;
import gov.nist.rolie.polie.tools.exceptions.ResourceNotFoundInDatabaseException;

public class DummyPersist implements PersistenceMethod {

	private static APPServiceDocument service = new APPServiceDocument();
	private static APPCategories categories = new APPCategories();
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
	public APPCategories saveCategoryDocument(APPCategories categorydocument) {
		return categorydocument;
	}

	@Override
	public APPCategories loadCategoryDocument(URI iri) {
		try {
			return (APPCategories)loadResource(new URI("http://localhost:8080/polie-core/category"));
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
		String root = "C:\\Users\\sab3\\git\\IETF-ROLIE\\polie\\polie-server\\src\\main\\resources\\";
		String result = "";
		Path file = null;
		switch (iri.toString())
		{
		case "http://localhost:8080/polie-server/entry": file = Paths.get(root+"testEntry.xml"); break;
		case "http://localhost:8080/polie-server/feed": file = Paths.get(root+"testFeed.xml"); break;
		case "http://localhost:8080/polie-server/service": file = Paths.get(root+"testService.xml"); break;
		case "http://localhost:8080/polie-server/category": file = Paths.get(root+"testCategory.xml"); break;
		}

		try (BufferedReader reader = Files.newBufferedReader(file)) {
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
