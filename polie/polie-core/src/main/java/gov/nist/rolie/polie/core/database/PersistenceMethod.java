package gov.nist.rolie.polie.core.database;

import java.net.URI;

import gov.nist.rolie.polie.core.exceptions.ResourceNotFoundInDatabaseException;
import gov.nist.rolie.polie.core.models.*;
import gov.nist.rolie.polie.core.models.elements.APPCollection;

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
	
	
	
}
