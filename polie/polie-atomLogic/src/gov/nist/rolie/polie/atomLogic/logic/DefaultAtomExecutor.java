package gov.nist.rolie.polie.atomLogic.logic;

import java.net.URI;

import gov.nist.rolie.polie.atomLogic.modelServices.CollectionServices;
import gov.nist.rolie.polie.atomLogic.modelServices.DefaultCollectionServices;
import gov.nist.rolie.polie.atomLogic.modelServices.DefaultEntryServices;
import gov.nist.rolie.polie.atomLogic.modelServices.DefaultServiceDocumentServices;
import gov.nist.rolie.polie.atomLogic.modelServices.EntryServices;
import gov.nist.rolie.polie.atomLogic.modelServices.FeedServices;
import gov.nist.rolie.polie.atomLogic.modelServices.ServiceDocumentServices;
import gov.nist.rolie.polie.model.models.APPCategories;
import gov.nist.rolie.polie.model.models.APPResource;
import gov.nist.rolie.polie.model.models.APPServiceDocument;
import gov.nist.rolie.polie.model.models.AtomEntry;
import gov.nist.rolie.polie.model.models.elements.APPCollection;
import gov.nist.rolie.polie.persistence.database.DummyPersist;
import gov.nist.rolie.polie.persistence.database.PersistenceMethod;
import gov.nist.rolie.polie.tools.exceptions.ResourceNotFoundInDatabaseException;

public class DefaultAtomExecutor implements AtomExecutor {

	PersistenceMethod pm;
	EntryServices es;
	FeedServices fs;
	ServiceDocumentServices sds;
	
	public DefaultAtomExecutor() 
	{
		pm = new DummyPersist();
		es = new DefaultEntryServices();
		sds = new DefaultServiceDocumentServices();
	}
	
	public DefaultAtomExecutor(PersistenceMethod pm)
	{
		this.pm=pm;
	}

	@Override
	public AtomEntry postEntryToCollection(AtomEntry entry, APPCollection collection) 
	{
		sds.createEntryAtCollection(entry,collection);
	}

	@Override
	public AtomEntry getEntry(URI iri) throws ResourceNotFoundInDatabaseException {
		AtomEntry entry = pm.loadEntry(iri);
		return entry;
	}

	@Override
	public APPServiceDocument getServiceDocument() {
		APPServiceDocument srv = pm.loadServiceDocument();
		return srv;
	}

	@Override
	public APPCategories getCategoryDocument() {
		APPCategories cats = pm.loadCategoryDocument();
		return cats;
	}

}
