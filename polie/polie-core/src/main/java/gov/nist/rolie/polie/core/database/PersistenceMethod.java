package gov.nist.rolie.polie.core.database;

import gov.nist.rolie.polie.core.models.*;

public interface PersistenceMethod {
	AtomEntry saveEntry(AtomEntry entry);
	AtomEntry loadEntry(String id);
	
	AtomCollection saveCollection(AtomCollection collection);
	AtomCollection loadCollection(String id);
	
	AtomFeed saveFeed(AtomFeed feed);
	AtomFeed loadFeed(String id);
	
	AtomWorkspace saveWorkspace(AtomWorkspace workspace);
	AtomWorkspace loadWorkspace(String id);
	
	AtomServiceDocument saveServiceDocument(AtomServiceDocument servicedocument);
	AtomServiceDocument loadServiceDocument(String id);
	
	AtomCategoryDocument saveCategoryDocument(AtomCategoryDocument categorydocument);
	AtomCategoryDocument loadCategoryDocument(String id);
}
