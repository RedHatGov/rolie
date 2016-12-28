package gov.nist.rolie.polie.core.database;

import java.net.URI;
import java.util.ArrayList;

import gov.nist.rolie.polie.core.models.APPCategoryDocument;
import gov.nist.rolie.polie.core.models.APPResource;
import gov.nist.rolie.polie.core.models.APPServiceDocument;
import gov.nist.rolie.polie.core.models.AtomEntry;
import gov.nist.rolie.polie.core.models.AtomFeed;
import gov.nist.rolie.polie.core.models.elements.APPCollection;
import gov.nist.rolie.polie.core.models.elements.APPWorkspace;
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
		entry.setTitle(new AtomTitle("I'm an entry"));
		feed.setTitle(new AtomTitle("I'm a feed"));
	}
	
	@Override
	public APPServiceDocument saveServiceDocument(APPServiceDocument servicedocument) {
		return servicedocument;
	}

	@Override
	public APPServiceDocument loadServiceDocument(URI iri) { 
		return service;
	}

	@Override
	public APPCategoryDocument saveCategoryDocument(APPCategoryDocument categorydocument) {
		return categorydocument;
	}

	@Override
	public APPCategoryDocument loadCategoryDocument(URI iri) {
		return categories;
	}

	@Override
	public APPResource loadResource(String id) {
		return null;
	}

	@Override
	public APPResource loadResource(URI iri) {
		if (iri.toString().equals("http://localhost:8080/polie-core/feed/entry"))
		{
			return entry;
		}
		if (iri.toString().equals("http://localhost:8080/polie-core/feed"))
		{
			return feed;
		}
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
		return null;
	}

}
