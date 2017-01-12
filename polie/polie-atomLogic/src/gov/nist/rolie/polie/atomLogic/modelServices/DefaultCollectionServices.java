package gov.nist.rolie.polie.atomLogic.modelServices;


import gov.nist.rolie.polie.model.models.AtomEntry;
import gov.nist.rolie.polie.model.models.AtomFeed;
import gov.nist.rolie.polie.model.models.elements.APPCollection;
import gov.nist.rolie.polie.model.models.elements.APPWorkspace;
import gov.nist.rolie.polie.persistence.database.DummyPersist;
import gov.nist.rolie.polie.persistence.database.PersistenceMethod;

public class DefaultCollectionServices implements CollectionServices {
	
	PersistenceMethod pm;
	FeedServices fs;
	EntryServices es;
	WorkspaceServices wss;
	
	public DefaultCollectionServices() 
	{
		pm = new DummyPersist();
		fs = new DefaultFeedServices();
		es = new DefaultEntryServices();
		wss =new DefaultWorkspaceServices();
	}

	public void addEntryToCollection(AtomEntry entry,APPCollection collection)
	{
		AtomFeed feed = pm.loadFeed(collection.getHref().getURI());
		fs.addEntryToFeed(entry,feed);
		pm.saveFeed(feed);
		updateCollectionCategories(entry,collection);
		updateParentWorkspace(collection);
	}
	
	private void updateParentWorkspace(APPCollection collection) {
		APPWorkspace ws = getCollectionWorkspace(collection);
		wss.updateWorkspace(collection);
		
	}

	private APPWorkspace getCollectionWorkspace(APPCollection collection) {
		// TODO Auto-generated method stub
		return null;
	}

	private void updateCollectionCategories(AtomEntry entry, APPCollection collection) {
		
	}

	public void updateCollection(APPCollection collection,AtomFeed feed)
	{
		
	}
	
	public void rebuildCollection(APPCollection collection,AtomFeed feed)
	{
		
	}
	
}
