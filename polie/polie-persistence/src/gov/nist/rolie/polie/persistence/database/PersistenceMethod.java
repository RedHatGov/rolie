package gov.nist.rolie.polie.persistence.database;

import java.net.URI;

import gov.nist.rolie.polie.model.ResourceType;
import gov.nist.rolie.polie.model.models.APPCategories;
import gov.nist.rolie.polie.model.models.APPResource;
import gov.nist.rolie.polie.model.models.APPServiceDocument;
import gov.nist.rolie.polie.model.models.AtomEntry;
import gov.nist.rolie.polie.model.models.AtomFeed;
import gov.nist.rolie.polie.persistence.ResourceNotFoundException;

public interface PersistenceMethod 
{
	/**
	 * 
	 * @param iri
	 * @return the type
	 * @throws ResourceNotFoundException if the resource identified by the iri parameter does not exist
	 */
	ResourceType identifyResouceType(URI iri) throws ResourceNotFoundException;
	APPResource loadResource(URI iri) throws ResourceNotFoundException;
	APPResource loadResource(String id);

	APPServiceDocument saveServiceDocument(APPServiceDocument servicedocument);
	APPServiceDocument loadServiceDocument(URI iri);
	
	APPCategories saveCategoryDocument(APPCategories categorydocument);
	APPCategories loadCategoryDocument(URI iri);

	
	APPResource createResource(APPResource resource, URI uri);
	APPResource updateResource(APPResource resource, URI uri);
	APPResource deleteResource(URI uri);
	APPResource deleteResource(String id);
	
	APPResource copyResource(APPResource resource);
	AtomFeed loadFeed(URI uri);
	AtomEntry loadEntry(URI iri);
	AtomFeed saveFeed(AtomFeed feed);
	APPServiceDocument loadServiceDocument();
	APPCategories loadCategoryDocument();
	
	void cleanup();
	
}
