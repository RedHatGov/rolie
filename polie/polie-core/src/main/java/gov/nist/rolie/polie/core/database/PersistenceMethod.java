package gov.nist.rolie.polie.core.database;

import gov.nist.rolie.polie.core.models.*;

public interface PersistenceMethod {
	Entry saveEntry(Entry entry);
	Entry loadEntry(String id);
	
	Collection saveCollection(Collection collection);
	Collection loadCollection(String id);
	
	Feed saveFeed(Feed feed);
	Feed loadFeed(String id);
	
	Workspace saveWorkspace(Workspace workspace);
	Workspace loadWorkspace(String id);
	
	ServiceDocument saveServiceDocument(ServiceDocument servicedocument);
	ServiceDocument loadServiceDocument(String id);
	
	CategoryDocument saveCategoryDocument(CategoryDocument categorydocument);
	CategoryDocument loadCategoryDocument(String id);
}
