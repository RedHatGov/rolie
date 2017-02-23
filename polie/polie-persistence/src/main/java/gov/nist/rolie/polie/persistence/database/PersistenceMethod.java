package gov.nist.rolie.polie.persistence.database;

import java.net.URI;
import gov.nist.rolie.polie.model.ResourceType;
import gov.nist.rolie.polie.model.models.APPCategories;
import gov.nist.rolie.polie.model.models.APPResource;
import gov.nist.rolie.polie.model.models.APPServiceDocument;
import gov.nist.rolie.polie.model.models.AtomEntry;
import gov.nist.rolie.polie.model.models.AtomFeed;
import gov.nist.rolie.polie.persistence.InvalidResourceTypeException;
import gov.nist.rolie.polie.persistence.ResourceAlreadyExistsException;
import gov.nist.rolie.polie.persistence.ResourceNotFoundException;


public interface PersistenceMethod 
{
	
	//Utility methods
	/**
	 * 
	 * @param iri
	 * @return the type
	 * @throws ResourceNotFoundException if the resource identified by the iri parameter does not exist
	 */
	ResourceType identifyResouceType(URI iri) throws ResourceNotFoundException;
	ResourceType identifyResouceType(String id) throws ResourceNotFoundException;
		
	boolean resourceExists(URI iri);
	boolean resourceExists(String id);
	boolean resourceExists(APPResource resource,ResourceType type);
	
	//General resource methods. Use carefully
	APPResource loadResource(URI iri) throws ResourceNotFoundException;
	APPResource loadResource(String id) throws ResourceNotFoundException;
	
	APPResource createResource(APPResource resource, URI uri) throws ResourceAlreadyExistsException;
	APPResource createResource(APPResource resource, String id) throws ResourceAlreadyExistsException;
	
	APPResource updateResource(APPResource resource, URI uri) throws ResourceNotFoundException;
	APPResource updateResource(APPResource resource, String id) throws ResourceNotFoundException;
	
	boolean deleteResource(URI uri) throws ResourceNotFoundException;
	boolean deleteResource(String id) throws ResourceNotFoundException;
	
	//Service Document Methods
	APPServiceDocument loadServiceDocument(URI iri) throws ResourceNotFoundException, InvalidResourceTypeException;
	
	APPServiceDocument createServiceDocument(APPServiceDocument serviceDoc, URI uri) throws ResourceAlreadyExistsException;
	
	APPServiceDocument updateServiceDocument(APPServiceDocument serviceDoc, URI uri) throws ResourceNotFoundException, InvalidResourceTypeException;
	
	boolean deleteServiceDocument(URI uri) throws ResourceNotFoundException, InvalidResourceTypeException;
	
	//Category Document Methods
	APPCategories loadCategoryDocument(URI iri) throws ResourceNotFoundException, InvalidResourceTypeException;
	
	APPCategories createCategoryDocument(APPCategories categoryDoc, URI uri) throws ResourceAlreadyExistsException;
	
	APPCategories updateCategoryDocument(APPCategories categoryDoc, URI uri) throws ResourceNotFoundException, InvalidResourceTypeException;
	
	boolean deleteCategoryDocument(URI uri) throws ResourceNotFoundException, InvalidResourceTypeException;

	//Feed methods
	AtomFeed loadFeed(URI iri) throws ResourceNotFoundException, InvalidResourceTypeException;
	AtomFeed loadFeed(String id) throws ResourceNotFoundException, InvalidResourceTypeException;
	
	AtomFeed createFeed(AtomFeed feed, URI uri) throws ResourceAlreadyExistsException;
	AtomFeed createFeed(AtomFeed feed,String id) throws ResourceAlreadyExistsException;
	
	AtomFeed updateFeed(AtomFeed feed, URI uri) throws ResourceNotFoundException, InvalidResourceTypeException;
	AtomFeed updateFeed(AtomFeed feed, String id) throws ResourceNotFoundException, InvalidResourceTypeException;
	
	boolean deleteFeed(URI uri) throws ResourceNotFoundException, InvalidResourceTypeException;
	boolean deleteFeed(String id) throws ResourceNotFoundException, InvalidResourceTypeException;
	
	//Entry methods
	AtomEntry loadEntry(URI iri) throws ResourceNotFoundException, InvalidResourceTypeException;
	AtomEntry loadEntry(String id) throws ResourceNotFoundException, InvalidResourceTypeException;
	
	AtomEntry createEntry(AtomEntry entry, URI uri) throws ResourceAlreadyExistsException;
	AtomEntry createEntry(AtomEntry entry, String id) throws ResourceAlreadyExistsException;
	
	AtomEntry updateEntry(AtomEntry entry, URI uri) throws ResourceNotFoundException, InvalidResourceTypeException;
	AtomEntry updateEntry(AtomEntry entry, String id) throws ResourceNotFoundException, InvalidResourceTypeException;
	
	boolean deleteEntry(URI uri) throws ResourceNotFoundException, InvalidResourceTypeException;
	boolean deleteEntry(String id) throws ResourceNotFoundException, InvalidResourceTypeException;
	
	
}
