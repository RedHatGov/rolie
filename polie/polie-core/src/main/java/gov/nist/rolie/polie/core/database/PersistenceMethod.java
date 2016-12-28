package gov.nist.rolie.polie.core.database;

import java.net.URI;

import gov.nist.rolie.polie.core.models.*;

public interface PersistenceMethod 
{
	APPServiceDocument saveServiceDocument(APPServiceDocument servicedocument);
	APPServiceDocument loadServiceDocument(URI iri);
	
	APPCategoryDocument saveCategoryDocument(APPCategoryDocument categorydocument);
	APPCategoryDocument loadCategoryDocument(URI iri);
	
	APPResource loadResource(String id);
	APPResource loadResource(URI iri);
	APPResource createResource(APPResource resource, URI uri);
	APPResource updateResource(APPResource resource, URI uri);
	APPResource deleteResource(URI uri);
	APPResource deleteResource(String id);
	
	
	
}
