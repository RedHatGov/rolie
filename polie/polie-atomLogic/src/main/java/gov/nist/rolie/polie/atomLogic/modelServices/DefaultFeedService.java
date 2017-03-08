package gov.nist.rolie.polie.atomLogic.modelServices;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3.x2005.atom.AtomDateConstruct;
import org.w3.x2005.atom.CategoryDocument.Category;
import org.w3.x2005.atom.LinkDocument.Link;
import org.w3.x2007.app.CategoriesType;
import org.w3.x2007.app.CollectionType;

import gov.nist.rolie.polie.atomLogic.MismatchedCategoriesException;
import gov.nist.rolie.polie.model.models.APPServiceDocument;
import gov.nist.rolie.polie.model.models.AtomEntry;
import gov.nist.rolie.polie.model.models.AtomFeed;
import gov.nist.rolie.polie.persistence.InvalidResourceTypeException;
import gov.nist.rolie.polie.persistence.ResourceAlreadyExistsException;
import gov.nist.rolie.polie.persistence.ResourceNotFoundException;
import gov.nist.rolie.polie.persistence.database.PersistenceMethod;

@Component
public class DefaultFeedService implements FeedService {

	@Autowired
	PersistenceMethod persistenceMethod;

	@Autowired
	EntryService entryService;

	@Autowired
	ServiceDocumentService serviceDocumentService;

	public DefaultFeedService() {
	}

	@Override
	public AtomFeed addEntryToFeed(AtomEntry entry, AtomFeed feed)
			throws MismatchedCategoriesException, ResourceNotFoundException, InvalidResourceTypeException {
		// make local copies
		AtomEntry localEntry = entry;
		AtomFeed localFeed = feed;

		matchingCategories(entry, feed);

		localEntry = entryService.stripEntry(localEntry);

		// Add the entry to the feed
		localFeed = addEntry(localFeed, localEntry);

		// Make server mods to the feed
		localFeed = updateFeedDates(localFeed);

		return localFeed;
	}

	private AtomFeed updateFeedDates(AtomFeed localFeed) {

		AtomDateConstruct date = localFeed.getXmlObject().getFeed().getUpdatedArray(0);

		date.setDateValue(Calendar.getInstance().getTime());

		localFeed.getXmlObject().getFeed().setUpdatedArray(0, date);

		return localFeed;
	}

	private AtomFeed addEntry(AtomFeed feed, AtomEntry entry) {
		feed.getXmlObject().getFeed().addNewEntry().set(entry.getXmlObject().getEntry());
		return feed;
	}

	private boolean categoryEquals(Category cat1, Category cat2) {
		return cat1.getScheme().equals(cat2.getScheme()) && cat1.getTerm().equals(cat2.getTerm());
	}

	private boolean matchingCategories(AtomEntry entry, AtomFeed feed)
			throws ResourceNotFoundException, InvalidResourceTypeException, MismatchedCategoriesException {

		APPServiceDocument service = null;
		service = serviceDocumentService.loadServiceDocument(getServiceDocumentIRI(feed));
		CollectionType collection = serviceDocumentService.getCollectionFromFeed(feed, service);
		List<CategoriesType> categories = collection.getCategoriesList();
		CategoriesType singleCategories = categories.get(0);
		List<Category> feedCats = singleCategories.getCategoryList();
		List<Category> entryCats = entry.getXmlObject().getEntry().getCategoryList();

		String fixedString = collection.getCategoriesList().get(0).getFixed();
		boolean found = true;
		if (fixedString.equals("yes")) {
			for (Category ecat : entryCats) {
				if (!ecat.getScheme().equals("urn:ietf:params:rolie:category:information-type")) {
					continue;
				}
				found = false;
				for (Category fcat : feedCats) {
					if (categoryEquals(ecat, fcat)) {
						found = true;
						break;
					}
				}
				if (!found) {
					throw new MismatchedCategoriesException(ecat);
				}
			}
		}
		return found;
	}

	public URI getServiceDocumentIRI(AtomFeed feed) {
		List<Link> links = feed.getXmlObject().getFeed().getLinkList();
		for (Link link : links) {
			if (link.getRel().equals("service")) {
				try {
					return new URI(link.getHref());
				} catch (URISyntaxException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	public String searchFeedLinksForRel(AtomFeed feed, String rel) {
		for (Link link : feed.getXmlObject().getFeed().getLinkList()) {
			if (link.getRel().equals(rel)) {
				return link.getHref();
			}
		}
		return null;
	}

	@Override
	public AtomFeed loadFeed(URI uri) throws ResourceNotFoundException, InvalidResourceTypeException {
		return persistenceMethod.loadFeed(uri);
	}

	@Override
	public AtomFeed createFeed(AtomFeed feed, URI iri) throws ResourceAlreadyExistsException {
		return persistenceMethod.createFeed(feed, iri);
	}

	@Override
	public AtomFeed updateFeed(AtomFeed feed, URI iri) throws ResourceNotFoundException, InvalidResourceTypeException {
		return persistenceMethod.updateFeed(feed, iri);
	}

	@Override
	public boolean deleteFeed(URI iri) throws ResourceNotFoundException, InvalidResourceTypeException {
		return persistenceMethod.deleteFeed(iri);
	}

}
