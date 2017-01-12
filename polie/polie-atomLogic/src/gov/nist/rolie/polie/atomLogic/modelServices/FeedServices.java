package gov.nist.rolie.polie.atomLogic.modelServices;

import gov.nist.rolie.polie.model.models.AtomEntry;
import gov.nist.rolie.polie.model.models.AtomFeed;

public interface FeedServices {

	void addEntryToFeed(AtomEntry entry, AtomFeed feed);

}
