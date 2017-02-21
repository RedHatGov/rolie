package gov.nist.rolie.polie.persistence.database;

import java.net.URI;

import org.springframework.stereotype.Component;

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
	APPResource saveResource(APPResource resource, URI uri);
	APPResource deleteResource(URI uri);
	APPResource deleteResource(String id);
	APPResource copyResource(APPResource resource);
	
	APPServiceDocument saveServiceDocument(APPServiceDocument servicedocument);
	APPServiceDocument loadServiceDocument(URI iri);
	
	APPCategories saveCategoryDocument(APPCategories categorydocument);
	APPCategories loadCategoryDocument(URI iri);


	AtomFeed loadFeed(URI uri);
	AtomFeed saveFeed(AtomFeed feed);
	
	AtomEntry loadEntry(URI iri);
	AtomEntry saveEntry(URI iri);
	
	void cleanup();
	
}
