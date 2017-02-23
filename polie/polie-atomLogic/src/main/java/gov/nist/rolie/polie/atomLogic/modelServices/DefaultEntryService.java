package gov.nist.rolie.polie.atomLogic.modelServices;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import gov.nist.rolie.polie.model.models.AtomEntry;
import gov.nist.rolie.polie.persistence.InvalidResourceTypeException;
import gov.nist.rolie.polie.persistence.ResourceAlreadyExistsException;
import gov.nist.rolie.polie.persistence.ResourceNotFoundException;
import gov.nist.rolie.polie.persistence.database.PersistenceMethod;

@Component
public class DefaultEntryService implements EntryService {

	@Autowired
	PersistenceMethod persistenceMethod;
	
	public DefaultEntryService() {
	}

	@Override
	public void publishEntry(AtomEntry entry) {
		
	}

	@Override
	public AtomEntry loadEntry(URI uri) throws ResourceNotFoundException, InvalidResourceTypeException {
		return persistenceMethod.loadEntry(uri);
	}

	@Override
	public AtomEntry createEntry(AtomEntry entry, URI iri) throws ResourceAlreadyExistsException {
		return persistenceMethod.createEntry(entry, iri);
	}

	@Override
	public AtomEntry updateEntry(AtomEntry entry, URI iri) throws ResourceNotFoundException, InvalidResourceTypeException {
		return persistenceMethod.updateEntry(entry, iri);
	}

	@Override
	public boolean deleteEntry(URI iri) throws ResourceNotFoundException, InvalidResourceTypeException {
		return persistenceMethod.deleteEntry(iri);
	}

		

}
