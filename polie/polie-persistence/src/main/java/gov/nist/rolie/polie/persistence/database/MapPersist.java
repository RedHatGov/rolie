package gov.nist.rolie.polie.persistence.database;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

import org.apache.xmlbeans.XmlException;
import org.springframework.stereotype.Component;
import org.w3.x2005.atom.EntryDocument;
import org.w3.x2005.atom.FeedDocument;
import org.w3.x2007.app.ServiceDocument;

import gov.nist.rolie.polie.model.ResourceType;
import gov.nist.rolie.polie.model.models.APPCategories;
import gov.nist.rolie.polie.model.models.APPResource;
import gov.nist.rolie.polie.model.models.APPServiceDocument;
import gov.nist.rolie.polie.model.models.AtomEntry;
import gov.nist.rolie.polie.model.models.AtomFeed;
import gov.nist.rolie.polie.persistence.InvalidResourceTypeException;
import gov.nist.rolie.polie.persistence.ResourceAlreadyExistsException;
import gov.nist.rolie.polie.persistence.ResourceNotFoundException;

@Component
public class MapPersist implements PersistenceMethod {

	private static final boolean BOOTSTRAP = true;
	HashMap<String, MappedResource> map = new HashMap<>();
	
	public MapPersist()
	{
		if (BOOTSTRAP)
		{
			System.out.println("Bootstrapping...");
			Path pathFeed = Paths.get("C:\\Users\\sab3\\git\\IETF-ROLIE\\polie\\polie-server\\src\\main\\resources\\testFeed.xml");
			File fileFeed = pathFeed.toFile();
			Path pathService = Paths.get("C:\\Users\\sab3\\git\\IETF-ROLIE\\polie\\polie-server\\src\\main\\resources\\testService.xml");
			File fileService = pathService.toFile();
			AtomFeed feed = null;
			APPServiceDocument service = null;
			try {
				feed = new AtomFeed(FeedDocument.Factory.parse(fileFeed));
				service = new APPServiceDocument(ServiceDocument.Factory.parse(fileService));
			} catch (XmlException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				createFeed(feed, "http://localhost:8080/polie-server/rest/feed");
				URI serviceuri = new URI("http://localhost:8080/polie-server/rest/service");
				createServiceDocument(service, serviceuri);
System.out.println("Just created a service document at:" + serviceuri.toString());
			} catch (ResourceAlreadyExistsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	

	@Override
	public ResourceType identifyResouceType(URI iri) throws ResourceNotFoundException {
		return identifyResouceType(iri.toString());
	}

	@Override
	public ResourceType identifyResouceType(String id) throws ResourceNotFoundException {
		MappedResource resource = map.get(id);
		if (resource == null) {
			throw new ResourceNotFoundException();
		} else {
			return resource.getType();
		}
	}

	@Override
	public APPResource loadResource(URI iri) throws ResourceNotFoundException {
		System.out.println("Loading: " + iri.toString());
		return loadResource(iri.toString());
	}

	@Override
	public APPResource loadResource(String id) throws ResourceNotFoundException {
		MappedResource resource = map.get(id);
		if (resource == null) {
			throw new ResourceNotFoundException();
		} else {
			return resource.getResource();
		}
	}

	@Override
	public APPResource createResource(APPResource resource, URI uri) throws ResourceAlreadyExistsException {
		return createResource(resource, uri.toString());
	}

	@Override
	public APPResource createResource(APPResource resource, String id) throws ResourceAlreadyExistsException {
		if (map.containsKey(id)) {
			throw new ResourceAlreadyExistsException();
		} else {
			map.put(id, new MappedResource(resource, ResourceType.RESOURCE));
			return resource;
		}
	}

	@Override
	public APPResource updateResource(APPResource resource, URI uri) throws ResourceNotFoundException {
		return updateResource(resource, uri.toString());
	}

	@Override
	public APPResource updateResource(APPResource resource, String id) throws ResourceNotFoundException {
		MappedResource mappedResource = map.get(id);
		if (mappedResource == null) {
			throw new ResourceNotFoundException();
		} else {
			map.put(id, new MappedResource(resource, ResourceType.RESOURCE));
			return resource;
		}
	}

	@Override
	public boolean deleteResource(URI uri) throws ResourceNotFoundException {
		return deleteResource(uri.toString());
	}

	@Override
	public boolean deleteResource(String id) throws ResourceNotFoundException {
		if (map.remove(id) == null) {
			throw new ResourceNotFoundException();
		} else {
			return true;
		}
	}

	@Override
	public APPServiceDocument loadServiceDocument(URI iri)
			throws ResourceNotFoundException, InvalidResourceTypeException {
		MappedResource mappedResource = map.get(iri.toString());
		if (mappedResource == null) {
			throw new ResourceNotFoundException();
		} else if (mappedResource.getType() != ResourceType.SERVICE) {
			throw new InvalidResourceTypeException();
		} else {
			return (APPServiceDocument) mappedResource.getResource();
		}
	}

	@Override
	public APPServiceDocument createServiceDocument(APPServiceDocument serviceDoc, URI uri)
			throws ResourceAlreadyExistsException {
		if (map.containsKey(uri)) {
			throw new ResourceAlreadyExistsException();
		} else {
			map.put(uri.toString(), new MappedResource(serviceDoc, ResourceType.SERVICE));
			return serviceDoc;
		}
	}

	@Override
	public APPServiceDocument updateServiceDocument(APPServiceDocument serviceDoc, URI uri)
			throws ResourceNotFoundException, InvalidResourceTypeException {
		MappedResource mappedResource = map.get(uri.toString());
		if (mappedResource == null) {
			throw new ResourceNotFoundException();
		} else if (mappedResource.getType() != ResourceType.SERVICE) {
			throw new InvalidResourceTypeException();
		} else {
			map.put(uri.toString(), new MappedResource(serviceDoc, ResourceType.SERVICE));
			return serviceDoc;
		}
	}

	@Override
	public boolean deleteServiceDocument(URI uri) throws ResourceNotFoundException, InvalidResourceTypeException {
		MappedResource mappedResource = map.get(uri.toString());
		if (mappedResource == null) {
			throw new ResourceNotFoundException();
		} else if (mappedResource.getType() != ResourceType.SERVICE) {
			throw new InvalidResourceTypeException();
		} else {
			map.remove(uri.toString());
			return true;
		}
	}

	@Override
	public APPCategories loadCategoryDocument(URI iri) throws ResourceNotFoundException, InvalidResourceTypeException {
		MappedResource mappedResource = map.get(iri.toString());
		if (mappedResource == null) {
			throw new ResourceNotFoundException();
		} else if (mappedResource.getType() != ResourceType.CATEGORY) {
			throw new InvalidResourceTypeException();
		} else {
			return (APPCategories) mappedResource.getResource();
		}
	}

	@Override
	public APPCategories createCategoryDocument(APPCategories categoryDoc, URI uri)
			throws ResourceAlreadyExistsException {
		if (map.containsKey(uri)) {
			throw new ResourceAlreadyExistsException();
		} else {
			map.put(uri.toString(), new MappedResource(categoryDoc, ResourceType.CATEGORY));
			return categoryDoc;
		}
	}

	@Override
	public APPCategories updateCategoryDocument(APPCategories categoryDoc, URI uri)
			throws ResourceNotFoundException, InvalidResourceTypeException {
		MappedResource mappedResource = map.get(uri.toString());
		if (mappedResource == null) {
			throw new ResourceNotFoundException();
		} else if (mappedResource.getType() != ResourceType.SERVICE) {
			throw new InvalidResourceTypeException();
		} else {
			map.put(uri.toString(), new MappedResource(categoryDoc, ResourceType.CATEGORY));
			return categoryDoc;
		}
	}

	@Override
	public boolean deleteCategoryDocument(URI uri) throws ResourceNotFoundException, InvalidResourceTypeException {
		MappedResource mappedResource = map.get(uri.toString());
		if (mappedResource == null) {
			throw new ResourceNotFoundException();
		} else if (mappedResource.getType() != ResourceType.CATEGORY) {
			throw new InvalidResourceTypeException();
		} else {
			map.remove(uri.toString());
			return true;
		}
	}

	@Override
	public AtomFeed loadFeed(URI iri) throws ResourceNotFoundException, InvalidResourceTypeException {
		return loadFeed(iri.toString());
	}

	@Override
	public AtomFeed loadFeed(String id) throws ResourceNotFoundException, InvalidResourceTypeException {
		MappedResource mappedResource = map.get(id);
		if (mappedResource == null) {
			throw new ResourceNotFoundException();
		} else if (mappedResource.getType() != ResourceType.FEED) {
			throw new InvalidResourceTypeException();
		} else {
			return (AtomFeed) mappedResource.getResource();
		}
	}

	@Override
	public AtomFeed createFeed(AtomFeed feed, URI uri) throws ResourceAlreadyExistsException {
		return createFeed(feed, uri.toString());
	}

	@Override
	public AtomFeed createFeed(AtomFeed feed, String id) throws ResourceAlreadyExistsException {
		if (map.containsKey(id)) {
			throw new ResourceAlreadyExistsException();
		} else {
			map.put(id, new MappedResource(feed, ResourceType.FEED));
			return feed;
		}
	}

	@Override
	public AtomFeed updateFeed(AtomFeed feed, URI uri) throws ResourceNotFoundException, InvalidResourceTypeException {
		return updateFeed(feed, uri.toString());
	}

	@Override
	public AtomFeed updateFeed(AtomFeed feed, String id)
			throws ResourceNotFoundException, InvalidResourceTypeException {
		MappedResource mappedResource = map.get(id);
		if (mappedResource == null) {
			throw new ResourceNotFoundException();
		} else if (mappedResource.getType() != ResourceType.FEED) {
			throw new InvalidResourceTypeException();
		} else {
			map.put(id, new MappedResource(feed, ResourceType.FEED));
			return feed;
		}
	}

	@Override
	public boolean deleteFeed(URI uri) throws ResourceNotFoundException, InvalidResourceTypeException {
		return deleteFeed(uri.toString());
	}

	@Override
	public boolean deleteFeed(String id) throws ResourceNotFoundException, InvalidResourceTypeException {
		MappedResource mappedResource = map.get(id);
		if (mappedResource == null) {
			throw new ResourceNotFoundException();
		} else if (mappedResource.getType() != ResourceType.CATEGORY) {
			throw new InvalidResourceTypeException();
		} else {
			map.remove(id);
			return true;
		}
	}

	@Override
	public AtomEntry loadEntry(URI iri) throws ResourceNotFoundException, InvalidResourceTypeException {
		return loadEntry(iri.toString());
	}

	@Override
	public AtomEntry loadEntry(String id) throws ResourceNotFoundException, InvalidResourceTypeException {
		MappedResource mappedResource = map.get(id);
		if (mappedResource == null) {
			throw new ResourceNotFoundException();
		} else if (mappedResource.getType() != ResourceType.ENTRY) {
			throw new InvalidResourceTypeException();
		} else {
			return (AtomEntry) mappedResource.getResource();
		}
	}

	@Override
	public AtomEntry createEntry(AtomEntry entry, URI uri) throws ResourceAlreadyExistsException {
		return createEntry(entry, uri.toString());
	}

	@Override
	public AtomEntry createEntry(AtomEntry entry, String id) throws ResourceAlreadyExistsException {
		if (map.containsKey(id)) {
			throw new ResourceAlreadyExistsException();
		} else {
			map.put(id, new MappedResource(entry, ResourceType.ENTRY));
			return entry;
		}
	}

	@Override
	public AtomEntry updateEntry(AtomEntry entry, URI uri)
			throws ResourceNotFoundException, InvalidResourceTypeException {
		return updateEntry(entry, uri.toString());
	}

	@Override
	public AtomEntry updateEntry(AtomEntry entry, String id)
			throws ResourceNotFoundException, InvalidResourceTypeException {
		MappedResource mappedResource = map.get(id);
		if (mappedResource == null) {
			throw new ResourceNotFoundException();
		} else if (mappedResource.getType() != ResourceType.ENTRY) {
			throw new InvalidResourceTypeException();
		} else {
			map.put(id, new MappedResource(entry, ResourceType.ENTRY));
			return entry;
		}
	}

	@Override
	public boolean deleteEntry(URI uri) throws ResourceNotFoundException, InvalidResourceTypeException {
		return deleteEntry(uri.toString());
	}

	@Override
	public boolean deleteEntry(String id) throws ResourceNotFoundException, InvalidResourceTypeException {
		MappedResource mappedResource = map.get(id);
		if (mappedResource == null) {
			throw new ResourceNotFoundException();
		} else if (mappedResource.getType() != ResourceType.CATEGORY) {
			throw new InvalidResourceTypeException();
		} else {
			map.remove(id);
			return true;
		}
	}

	@Override
	public boolean resourceExists(URI iri) {
		return map.containsKey(iri.toString());

	}

	@Override
	public boolean resourceExists(String id) {
		return map.containsKey(id);
	}

	@Override
	public boolean resourceExists(APPResource resource,ResourceType type) {
		return map.containsValue(new MappedResource(resource,type));
	}
}
