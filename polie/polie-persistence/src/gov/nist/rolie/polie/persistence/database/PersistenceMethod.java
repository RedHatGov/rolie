package gov.nist.rolie.polie.persistence.database;

import java.net.URI;

import gov.nist.rolie.polie.model.models.*;
import gov.nist.rolie.polie.model.models.elements.APPCollection;
import gov.nist.rolie.polie.tools.exceptions.ResourceNotFoundInDatabaseException;

public interface PersistenceMethod 
{
	APPServiceDocument saveServiceDocument(APPServiceDocument servicedocument);
	APPServiceDocument loadServiceDocument(URI iri);
	
	APPCategories saveCategoryDocument(APPCategories categorydocument);
	APPCategories loadCategoryDocument(URI iri);
	
	APPResource loadResource(String id);
	APPResource loadResource(URI iri) throws ResourceNotFoundInDatabaseException;
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
	
	
	
}
