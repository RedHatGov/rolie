package gov.nist.rolie.polie.core.database;

import java.net.URI;

import gov.nist.rolie.polie.core.models.*;

public interface PersistenceMethod 
{
	AtomServiceDocument saveServiceDocument(AtomServiceDocument servicedocument);
	AtomServiceDocument loadServiceDocument(URI iri);
	
	AtomCategoryDocument saveCategoryDocument(AtomCategoryDocument categorydocument);
	AtomCategoryDocument loadCategoryDocument(URI iri);
	
	AtomResource loadResource(String id);
	AtomResource loadResource(URI iri);
	AtomResource createResource(AtomResource resource, URI uri);
	AtomResource updateResource(AtomResource resource, URI uri);
	AtomResource deleteResource(URI uri);
	AtomResource deleteResource(String id);
	
	
	
}
