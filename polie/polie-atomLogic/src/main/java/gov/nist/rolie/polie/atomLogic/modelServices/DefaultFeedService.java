package gov.nist.rolie.polie.atomLogic.modelServices;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import gov.nist.rolie.polie.model.models.AtomEntry;
import gov.nist.rolie.polie.model.models.AtomFeed;
import gov.nist.rolie.polie.persistence.InvalidResourceTypeException;
import gov.nist.rolie.polie.persistence.ResourceAlreadyExistsException;
import gov.nist.rolie.polie.persistence.ResourceNotFoundException;
import gov.nist.rolie.polie.persistence.database.PersistenceMethod;

@Component
public class DefaultFeedService implements FeedService{
	
	@Autowired
	PersistenceMethod persistenceMethod;

	@Autowired
	EntryService es;
	
	public DefaultFeedService() {
	}

	@Override
	public AtomFeed addEntryToFeed(AtomEntry entry, AtomFeed feed) {
		feed.getXmlObject().getFeed().addNewEntry().set(entry.getXmlObject());
		return feed;
	}



	private void addEntry(AtomFeed feed, AtomEntry entry) {
		//TODO If not exists
//		feed.getEntries().add(entry);
	}

	private void updateFeedCategories(AtomEntry entry, AtomFeed feed) {
		// TODO if entry categories has more than feed categories, add to feed categories
		
	}

	@Override
	public AtomFeed loadFeed(URI uri) throws ResourceNotFoundException, InvalidResourceTypeException {
		return persistenceMethod.loadFeed(uri);
	}

	@Override
	public AtomFeed createFeed(AtomFeed feed, URI iri) throws ResourceAlreadyExistsException {
		return persistenceMethod.createFeed(feed, iri);
	}

	@Override
	public AtomFeed updateFeed(AtomFeed feed, URI iri) throws ResourceNotFoundException, InvalidResourceTypeException {
		return persistenceMethod.updateFeed(feed, iri);
	}

	@Override
	public boolean deleteFeed(URI iri) throws ResourceNotFoundException, InvalidResourceTypeException {
		return persistenceMethod.deleteFeed(iri);
	}




}
