package gov.nist.rolie.polie.atomLogic.modelServices;

import gov.nist.rolie.polie.model.models.AtomEntry;

public class DefaultEntryServices implements EntryServices {

	public DefaultEntryServices() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void publishEntry(AtomEntry entry) {
		entry.setPublishedDate("Right now!"); //TODO datetime
		entry.setUpdated("Right now!"); //TODO datetime
	}

}
