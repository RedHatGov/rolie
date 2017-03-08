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

//@Component
public class DummyPersist implements PersistenceMethod {

	private static APPServiceDocument serviceDocument = new APPServiceDocument();
	private static APPCategories categories = new APPCategories();
	private static AtomEntry entry = new AtomEntry();
	private static AtomFeed feed = new AtomFeed();
	
	static {
		ServiceType service = serviceDocument.getXmlObject().addNewService();
		WorkspaceType workspace = service.addNewWorkspace();
		CollectionType collection = workspace.addNewCollection();

		collection.setHref("here/there");
		
		entry.getXmlObject().addNewEntry().addNewTitle();//.set(XmlToken.Factory.newInstance().setStringValue("I'm a dummy entry"));
		feed.getXmlObject().addNewFeed().addNewTitle();//.setTitle("I'm a feed-dummypersist");
	}


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
		case "http://localhost:8080/polie-server/rest/entry":
			retval = ResourceType.ENTRY;
			break;
		case "http://localhost:8080/polie-server/rest/feed":
			retval = ResourceType.FEED;
			break;
		case "http://localhost:8080/polie-server/rest/serviceDocument":
			retval = ResourceType.SERVICE;
			break;
		case "http://localhost:8080/polie-server/rest/category":
			retval = ResourceType.CATEGORY;
			break;
		default:
			throw new UnsupportedOperationException(iri.toString());
		}
		return retval;
	}

	private Path iriToPath(URI iri) {
		String root = "C:\\Users\\sab3\\git\\IETF-ROLIE\\polie\\polie-server\\src\\main\\resources\\";
		Path file = null;
		switch (iri.toString()) {
		case "http://localhost:8080/polie-server/rest/entry":
			file = Paths.get(root + "testEntry.xml");
			break;
		case "http://localhost:8080/polie-server/rest/feed":
			file = Paths.get(root + "testFeed.xml");
			break;
		case "http://localhost:8080/polie-server/rest/serviceDocument":
			file = Paths.get(root + "testService.xml");
			break;
		case "http://localhost:8080/polie-server/rest/category":
			file = Paths.get(root + "testCategory.xml");
			break;
		default:
			throw new UnsupportedOperationException(iri.toString());
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
			throw new ResourceNotFoundException(iri.toString());
		}
	}


	@Override
	public boolean deleteResource(URI uri) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteResource(String id) {
		// TODO Auto-generated method stub
		return false;
	}


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


	public AtomFeed saveFeed(AtomFeed feed) {
		return feed;

	}


	@Override
	public ResourceType identifyResouceType(URI iri) {
		return iriToResourceType(iri);
	}



	@Override
	public APPResource createResource(APPResource resource, URI uri) {
		// TODO Auto-generated method stub
		return null;
	}


	public AtomEntry saveEntry(URI iri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResourceType identifyResouceType(String id) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public APPResource createResource(APPResource resource, String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public APPResource updateResource(APPResource resource, URI uri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public APPResource updateResource(APPResource resource, String id) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public APPServiceDocument createServiceDocument(APPServiceDocument serviceDoc, URI uri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public APPServiceDocument updateServiceDocument(APPServiceDocument serviceDoc, URI uri)
			throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteServiceDocument(URI uri) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public APPCategories createCategoryDocument(APPCategories categoryDoc, URI uri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public APPCategories updateCategoryDocument(APPCategories categoryDoc, URI uri) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteCategoryDocument(URI uri) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public AtomFeed loadFeed(String id) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AtomFeed createFeed(AtomFeed feed, URI uri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AtomFeed createFeed(AtomFeed feed, String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AtomFeed updateFeed(AtomFeed resource, URI uri) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AtomFeed updateFeed(AtomFeed feed, String id) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteFeed(URI uri) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteFeed(String id) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public AtomEntry loadEntry(String id) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AtomEntry createEntry(AtomEntry entry, URI uri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AtomEntry createEntry(AtomEntry entry, String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AtomEntry updateEntry(AtomEntry entry, URI uri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AtomEntry updateEntry(AtomEntry entry, String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteEntry(URI uri) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteEntry(String id) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean resourceExists(URI iri) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean resourceExists(String id) {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public boolean resourceExists(APPResource resource, ResourceType type) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String generateNewEntryID(AtomEntry entry) {
		// TODO Auto-generated method stub
		return null;
	}

}
