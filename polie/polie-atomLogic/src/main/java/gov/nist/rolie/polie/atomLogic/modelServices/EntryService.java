package gov.nist.rolie.polie.atomLogic.modelServices;

import java.net.URI;
import java.net.URISyntaxException;

import gov.nist.rolie.polie.atomLogic.LinkAlreadyExistsException;
import gov.nist.rolie.polie.model.models.AtomEntry;
import gov.nist.rolie.polie.persistence.InvalidResourceTypeException;
import gov.nist.rolie.polie.persistence.ResourceAlreadyExistsException;
import gov.nist.rolie.polie.persistence.ResourceNotFoundException;

public interface EntryService {

	void publishEntry(AtomEntry entry);
	
	AtomEntry loadEntry(URI uri) throws ResourceNotFoundException, InvalidResourceTypeException;
	AtomEntry createEntry(AtomEntry entry, URI iri) throws ResourceAlreadyExistsException, LinkAlreadyExistsException, URISyntaxException;
	AtomEntry updateEntry(AtomEntry entry, URI iri) throws ResourceNotFoundException, InvalidResourceTypeException;
	boolean deleteEntry(URI iri) throws ResourceNotFoundException, InvalidResourceTypeException;
	AtomEntry addEntryLink(AtomEntry entry,String rel,String href) throws LinkAlreadyExistsException;
	AtomEntry updateDates(AtomEntry localEntry);

	AtomEntry stripEntry(AtomEntry localEntry);
}
