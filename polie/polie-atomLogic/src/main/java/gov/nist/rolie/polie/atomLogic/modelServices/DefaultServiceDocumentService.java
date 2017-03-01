package gov.nist.rolie.polie.atomLogic.modelServices;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.xmlbeans.XmlCursor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3.x2005.atom.CategoryDocument.Category;
import org.w3.x2007.app.CollectionType;
import org.w3.x2007.app.WorkspaceType;

import gov.nist.rolie.polie.model.models.APPServiceDocument;
import gov.nist.rolie.polie.model.models.AtomFeed;
import gov.nist.rolie.polie.model.models.elements.APPCollection;
import gov.nist.rolie.polie.persistence.InvalidResourceTypeException;
import gov.nist.rolie.polie.persistence.ResourceAlreadyExistsException;
import gov.nist.rolie.polie.persistence.ResourceNotFoundException;
import gov.nist.rolie.polie.persistence.database.PersistenceMethod;

@Component
public class DefaultServiceDocumentService implements ServiceDocumentService {

	@Autowired
	FeedService feedService;

	@Autowired
	PersistenceMethod persistenceMethod;

	public APPCollection buildCollectionFromFeed(AtomFeed feed) {
		APPCollection collection = new APPCollection();
		APPServiceDocument serv = new APPServiceDocument();
		serv.getXmlObject().getService().getWorkspaceList().get(0).getCollectionList().get(0);

		return null;
	}

	public APPCollection addTitleToCollection(APPCollection collection, String title) {
		XmlCursor c = collection.getXmlObject().newCursor();
		c.toChild("collection");
		c.insertElementWithText("atom:title", title);
		c.dispose();
		return collection;
	}

	public String readTitleFromCollection(APPCollection collection) {
		XmlCursor c = collection.getXmlObject().newCursor();
		c.selectPath("atom:title");
		String titleText = c.getTextValue();
		c.dispose();
		return titleText;
	}

	@Override
	public APPServiceDocument loadServiceDocument(URI uri)
			throws ResourceNotFoundException, InvalidResourceTypeException {
		return persistenceMethod.loadServiceDocument(uri);
	}

	@Override
	public APPServiceDocument createServiceDocument(APPServiceDocument service, URI iri)
			throws ResourceAlreadyExistsException {
		return persistenceMethod.createServiceDocument(service, iri);
	}

	@Override
	public APPServiceDocument updateServiceDocument(APPServiceDocument service, URI iri)
			throws ResourceNotFoundException, InvalidResourceTypeException {
		return persistenceMethod.updateServiceDocument(service, iri);
	}

	@Override
	public boolean deleteServiceDocument(URI iri) throws ResourceNotFoundException, InvalidResourceTypeException {
		return persistenceMethod.deleteServiceDocument(iri);
	}

	public CollectionType getCollectionFromFeed(AtomFeed feed,APPServiceDocument serviceDoc) {

		String feedURI = feedService.searchFeedLinksForRel(feed, "self");

		for (CollectionType collection : getAllCollections(serviceDoc)) {
			if (collection.getHref().equals(feedURI)) {
				return collection;
			}
		}
		return null;
	}

	@Override
	public void updateCollectionCategories(Category cat, AtomFeed feed) {
		APPServiceDocument serviceDoc = null;
		try {
			serviceDoc = loadServiceDocument(feedService.getServiceDocumentIRI(feed));
		} catch (ResourceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidResourceTypeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		getCollectionFromFeed(feed,serviceDoc).getCategoriesList().get(0).addNewCategory().set(cat);
		
		try {
			updateServiceDocument(serviceDoc,feedService.getServiceDocumentIRI(feed));
		} catch (ResourceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidResourceTypeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<CollectionType> getAllCollections(APPServiceDocument service) {
		ArrayList<CollectionType> collections = new ArrayList<CollectionType>();
		for (WorkspaceType workspace : service.getXmlObject().getService().getWorkspaceList()) {
			for (CollectionType collection : workspace.getCollectionList()) {
				collections.add(collection);
			}
		}
		return collections;
	}

}
