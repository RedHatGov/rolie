package gov.nist.rolie.polie.atomLogic.modelServices;

import java.net.URI;

import org.w3.x2005.atom.CategoryDocument.Category;

import gov.nist.rolie.polie.model.models.APPServiceDocument;
import gov.nist.rolie.polie.model.models.AtomFeed;
import gov.nist.rolie.polie.model.models.elements.APPCollection;
import gov.nist.rolie.polie.persistence.InvalidResourceTypeException;
import gov.nist.rolie.polie.persistence.ResourceAlreadyExistsException;
import gov.nist.rolie.polie.persistence.ResourceNotFoundException;

public interface ServiceDocumentService {
	
	APPCollection buildCollectionFromFeed(AtomFeed feed);
	APPCollection addTitleToCollection(APPCollection collection, String title);
	String readTitleFromCollection(APPCollection collection);
	APPServiceDocument loadServiceDocument(URI uri) throws ResourceNotFoundException, InvalidResourceTypeException;
	APPServiceDocument createServiceDocument(APPServiceDocument service, URI iri) throws ResourceAlreadyExistsException;
	APPServiceDocument updateServiceDocument(APPServiceDocument service, URI iri) throws ResourceNotFoundException, InvalidResourceTypeException;
	boolean deleteServiceDocument(URI iri) throws ResourceNotFoundException, InvalidResourceTypeException;
	void updateCollectionCategories(Category cat,AtomFeed feed);
}
