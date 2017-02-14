package gov.nist.rolie.polie.atomLogic.modelServices;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import gov.nist.rolie.polie.model.models.AtomEntry;
import gov.nist.rolie.polie.model.models.AtomFeed;
import gov.nist.rolie.polie.persistence.database.DummyPersist;
import gov.nist.rolie.polie.persistence.database.PersistenceMethod;
@Component
public class DefaultFeedService implements FeedService{
	@Autowired
	PersistenceMethod db;
	EntryServices es;
	
	public DefaultFeedService() {
		es = new DefaultEntryServices();
	}

	@Override
	public void addEntryToFeed(AtomEntry entry, AtomFeed feed) {
		es.publishEntry(entry);
		addEntry(feed,entry);
		updateFeedCategories(entry,feed);
//		feed.setUpdatedDate("Right now!"); //TODO datatime
	}

	public AtomFeed loadFeed(URI uri)
	{
		return db.loadFeed(uri);
	}

	private void addEntry(AtomFeed feed, AtomEntry entry) {
		//TODO If not exists
//		feed.getEntries().add(entry);
	}

	private void updateFeedCategories(AtomEntry entry, AtomFeed feed) {
		// TODO if entry categories has more than feed categories, add to feed categories
		
	}

	@Override
	public AtomFeed saveFeed(AtomFeed feed) {
		return db.saveFeed(feed);
		
	}

	@Override
	public void cleanup() {
		// TODO Auto-generated method stub
		
	}


}
