package gov.nist.rolie.polie.atomLogic.modelServices;

import java.net.URI;

import gov.nist.rolie.polie.model.models.AtomEntry;
import gov.nist.rolie.polie.model.models.AtomFeed;
import gov.nist.rolie.polie.model.models.elements.APPCollection;

public interface FeedService extends Service{

	void addEntryToFeed(AtomEntry entry, AtomFeed feed);

	AtomFeed loadFeed(URI uri);

	AtomFeed saveFeed(AtomFeed feed);

}
