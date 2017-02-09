package gov.nist.rolie.polie.atomLogic.modelServices;

import java.net.URI;

import gov.nist.rolie.polie.model.models.AtomEntry;
import gov.nist.rolie.polie.model.models.AtomFeed;
import gov.nist.rolie.polie.persistence.database.DummyPersist;
import gov.nist.rolie.polie.persistence.database.PersistenceMethod;

public class DefaultFeedServices implements FeedServices{

	PersistenceMethod db = new DummyPersist();
	EntryServices es;
	
	public DefaultFeedServices() {
		es = new DefaultEntryServices();
	}

	@Override
	public void addEntryToFeed(AtomEntry entry, AtomFeed feed) {
		es.publishEntry(entry);
		addEntry(feed,entry);
		updateFeedCategories(entry,feed);
		feed.setUpdatedDate("Right now!"); //TODO datatime
	}

	public AtomFeed loadFeed(URI uri)
	{
		return db.loadFeed(uri);
	}

	private void addEntry(AtomFeed feed, AtomEntry entry) {
		//TODO If not exists
		feed.getEntries().add(entry);
	}

	private void updateFeedCategories(AtomEntry entry, AtomFeed feed) {
		// TODO if entry categories has more than feed categories, add to feed categories
		
	}

	@Override
	public AtomFeed saveFeed(AtomFeed feed) {
		return db.saveFeed(feed);
		
	}


}
