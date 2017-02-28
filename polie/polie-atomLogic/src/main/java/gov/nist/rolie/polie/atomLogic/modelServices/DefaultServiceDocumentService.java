package gov.nist.rolie.polie.atomLogic.modelServices;

import java.net.URI;

import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.impl.soap.Node;
import org.springframework.beans.factory.annotation.Autowired;

import gov.nist.rolie.polie.model.models.APPServiceDocument;
import gov.nist.rolie.polie.model.models.AtomFeed;
import gov.nist.rolie.polie.model.models.elements.APPCollection;
import gov.nist.rolie.polie.model.models.elements.APPWorkspace;
import gov.nist.rolie.polie.persistence.InvalidResourceTypeException;
import gov.nist.rolie.polie.persistence.ResourceAlreadyExistsException;
import gov.nist.rolie.polie.persistence.ResourceNotFoundException;
import gov.nist.rolie.polie.persistence.database.PersistenceMethod;

public class DefaultServiceDocumentService implements ServiceDocumentService{

	@Autowired
	PersistenceMethod persistenceMethod;
	
	
	public APPCollection buildCollectionFromFeed(AtomFeed feed)
	{
		APPCollection collection = new APPCollection();
		APPServiceDocument serv = new APPServiceDocument();
		serv.getXmlObject().getService().getWorkspaceList().get(0).getCollectionList().get(0);
		

		return null;
	}
	
	public APPCollection addTitleToCollection(APPCollection collection, String title)
	{
		XmlCursor c = collection.getXmlObject().newCursor();
		c.toChild("collection");
		c.insertElementWithText("atom:title",title);
		c.dispose();
		return collection;
	}
	public String readTitleFromCollection(APPCollection collection)
	{
		XmlCursor c = collection.getXmlObject().newCursor();
		c.selectPath("atom:title");
		return c.getTextValue();
	}

	@Override
	public APPServiceDocument loadServiceDocument(URI uri) throws ResourceNotFoundException, InvalidResourceTypeException {
		return persistenceMethod.loadServiceDocument(uri);
	}

	@Override
	public APPServiceDocument createServiceDocument(APPServiceDocument service, URI iri) throws ResourceAlreadyExistsException {
		return persistenceMethod.createServiceDocument(service, iri);
	}

	@Override
	public APPServiceDocument updateServiceDocument(APPServiceDocument service, URI iri) throws ResourceNotFoundException, InvalidResourceTypeException {
		return persistenceMethod.updateServiceDocument(service, iri);
	}

	@Override
	public boolean deleteServiceDocument(URI iri) throws ResourceNotFoundException, InvalidResourceTypeException {
		return persistenceMethod.deleteServiceDocument(iri);
	}
	
}
