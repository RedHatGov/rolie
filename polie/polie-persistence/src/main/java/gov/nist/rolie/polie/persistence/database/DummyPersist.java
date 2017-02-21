package gov.nist.rolie.polie.persistence.database;

import java.io.IOException;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.xmlbeans.XmlException;
import org.springframework.stereotype.Component;
import org.w3.x2005.atom.EntryDocument;
import org.w3.x2005.atom.FeedDocument;
import org.w3.x2007.app.CategoriesDocument;
import org.w3.x2007.app.CollectionType;
import org.w3.x2007.app.ServiceDocument;
import org.w3.x2007.app.ServiceType;
import org.w3.x2007.app.WorkspaceType;

import gov.nist.rolie.polie.model.ResourceType;
import gov.nist.rolie.polie.model.models.APPCategories;
import gov.nist.rolie.polie.model.models.APPResource;
import gov.nist.rolie.polie.model.models.APPServiceDocument;
import gov.nist.rolie.polie.model.models.AtomEntry;
import gov.nist.rolie.polie.model.models.AtomFeed;
import gov.nist.rolie.polie.persistence.ResourceNotFoundException;

@Component
public class DummyPersist implements PersistenceMethod {

	private static APPServiceDocument serviceDocument = new APPServiceDocument();
	private static APPCategories categories = new APPCategories();
	private static AtomEntry entry = new AtomEntry();
	private static AtomFeed feed = new AtomFeed();
	
	static {
		ServiceType service = serviceDocument.getXmlObject().addNewService();
		WorkspaceType workspace = service.addNewWorkspace();
		CollectionType collection = workspace.addNewCollection();
		collection.addNewTitle();
		collection.setHref("here/there");
		
		entry.getXmlObject().addNewEntry().addNewTitle();//.set(XmlToken.Factory.newInstance().setStringValue("I'm a dummy entry"));
		feed.getXmlObject().addNewFeed().addNewTitle();//.setTitle("I'm a feed-dummypersist");
	}

	@Override
	public APPServiceDocument saveServiceDocument(APPServiceDocument servicedocument) {
		return servicedocument;
	}

	@Override
	public APPServiceDocument loadServiceDocument(URI iri) {
		try {
			return (APPServiceDocument) loadResource(new URI("http://localhost:8080/polie-core/serviceDocument"));
		} catch (ResourceNotFoundException e) {
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
			return (APPCategories) loadResource(new URI("http://localhost:8080/polie-core/category"));
		} catch (ResourceNotFoundException e) {
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

	private ResourceType iriToResourceType(URI iri) {
		ResourceType retval = null;
		switch (iri.toString()) {
		case "http://localhost:8080/polie-server/entry":
			retval = ResourceType.ENTRY;
			break;
		case "http://localhost:8080/polie-server/feed":
			retval = ResourceType.FEED;
			break;
		case "http://localhost:8080/polie-server/serviceDocument":
			retval = ResourceType.SERVICE;
			break;
		case "http://localhost:8080/polie-server/category":
			retval = ResourceType.CATEGORY;
			break;
		}
		return retval;
	}

	private Path iriToPath(URI iri) {
		String root = "C:\\Users\\sab3\\git\\IETF-ROLIE\\polie\\polie-server\\src\\main\\resources\\";
		Path file = null;
		switch (iri.toString()) {
		case "http://localhost:8080/polie-server/entry":
			file = Paths.get(root + "testEntry.xml");
			break;
		case "http://localhost:8080/polie-server/feed":
			file = Paths.get(root + "testFeed.xml");
			break;
		case "http://localhost:8080/polie-server/serviceDocument":
			file = Paths.get(root + "testService.xml");
			break;
		case "http://localhost:8080/polie-server/category":
			file = Paths.get(root + "testCategory.xml");
			break;
		}
		return file;
	}

	@Override
	public APPResource loadResource(URI iri) throws ResourceNotFoundException {
		String result = "";

		Path file = iriToPath(iri);
		try {
			APPResource retval = null;
			switch (iriToResourceType(iri)) {
			case CATEGORY:
				retval = new APPCategories(CategoriesDocument.Factory.parse(file.toFile()));
				break;
			case ENTRY:
				retval = new AtomEntry(EntryDocument.Factory.parse(file.toFile()));
				break;
			case FEED:
				retval = new AtomFeed(FeedDocument.Factory.parse(file.toFile()));
				break;
			case SERVICE:
				retval = new APPServiceDocument(ServiceDocument.Factory.parse(file.toFile()));
				break;
			
			}
			return retval;
		} catch (IOException | XmlException e) {
			e.printStackTrace();
			throw new ResourceNotFoundException();
		}
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

	public APPResource postEntryToCollection(APPResource resource, URI uri) {
		// make sure that uri is a valid feed
		// Make sure that feed can accept the entry
		// update entry meta data
		// Update feed with new entry
		// update feed metadata
		// return entry
		return null;
	}

	@Override
	public AtomFeed loadFeed(URI uri) {
		// TODO Auto-generated method stub
		return feed;
	}

	@Override
	public AtomEntry loadEntry(URI iri) {
		// TODO Auto-generated method stub
		return entry;
	}

	@Override
	public AtomFeed saveFeed(AtomFeed feed) {
		return feed;

	}


	@Override
	public ResourceType identifyResouceType(URI iri) {
		return iriToResourceType(iri);
	}

	@Override
	public void cleanup() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public APPResource saveResource(APPResource resource, URI uri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AtomEntry saveEntry(URI iri) {
		// TODO Auto-generated method stub
		return null;
	}

}
