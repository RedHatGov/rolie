package gov.nist.rolie.polie.atomLogic.modelServices;

import java.net.URI;
import gov.nist.rolie.polie.model.models.AtomEntry;
import gov.nist.rolie.polie.model.models.AtomFeed;
import gov.nist.rolie.polie.persistence.InvalidResourceTypeException;
import gov.nist.rolie.polie.persistence.ResourceAlreadyExistsException;
import gov.nist.rolie.polie.persistence.ResourceNotFoundException;

public interface FeedService extends Service{

	AtomFeed addEntryToFeed(AtomEntry entry, AtomFeed feed);

	AtomFeed loadFeed(URI uri) throws ResourceNotFoundException, InvalidResourceTypeException;
	AtomFeed createFeed(AtomFeed feed, URI iri) throws ResourceAlreadyExistsException;
	AtomFeed updateFeed(AtomFeed feed, URI iri) throws ResourceNotFoundException, InvalidResourceTypeException;
	boolean deleteFeed(URI iri) throws ResourceNotFoundException, InvalidResourceTypeException;
	URI getServiceDocumentIRI(AtomFeed feed);
}
