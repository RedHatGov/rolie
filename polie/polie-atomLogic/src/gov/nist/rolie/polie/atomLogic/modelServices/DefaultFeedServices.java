package gov.nist.rolie.polie.atomLogic.modelServices;

import gov.nist.rolie.polie.model.models.AtomEntry;
import gov.nist.rolie.polie.model.models.AtomFeed;
import gov.nist.rolie.polie.model.models.constructs.AtomDate;
import gov.nist.rolie.polie.model.models.elements.AtomUpdated;

public class DefaultFeedServices implements FeedServices{

	EntryServices es;
	
	public DefaultFeedServices() {
		es = new DefaultEntryServices();
	}

	@Override
	public void addEntryToFeed(AtomEntry entry, AtomFeed feed) {
		es.publishEntry(entry);
		addEntry(feed,entry);
		updateFeedCategories(entry,feed);
		publishFeed(feed);
	}

	private void publishFeed(AtomFeed feed) {
		feed.setUpdated(new AtomUpdated(new AtomDate(null,"RIGHT NOW!")));
	}

	private void addEntry(AtomFeed feed, AtomEntry entry) {
		//TODO If not exists
		feed.getEntries().add(entry);
	}

	private void updateFeedCategories(AtomEntry entry, AtomFeed feed) {
		// TODO Auto-generated method stub
		
	}

}
