package gov.nist.rolie.polie.atomLogic.logic;

import java.net.URI;

import gov.nist.rolie.polie.model.models.APPCategories;
import gov.nist.rolie.polie.model.models.APPServiceDocument;
import gov.nist.rolie.polie.model.models.AtomEntry;
import gov.nist.rolie.polie.model.models.elements.APPCollection;
import gov.nist.rolie.polie.persistence.database.PersistenceMethod;
import gov.nist.rolie.polie.tools.exceptions.ResourceNotFoundInDatabaseException;

public interface AtomExecutor {
	
	
	public AtomEntry postEntryToCollection (AtomEntry entry,APPCollection collection);
	public AtomEntry getEntry(URI iri) throws ResourceNotFoundInDatabaseException;
	public APPServiceDocument getServiceDocument();
	public APPCategories getCategoryDocument();
	

}
