package gov.nist.rolie.polie.atomLogic.modelServices;

import gov.nist.rolie.polie.model.models.AtomEntry;
import gov.nist.rolie.polie.model.models.elements.APPCollection;

public interface CollectionServices {
	public void addEntryToCollection(AtomEntry entry,APPCollection collection);
}
