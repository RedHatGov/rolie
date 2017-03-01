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
	public AtomFeed addEntryToFeed(AtomEntry entry, AtomFeed feed) {
		// make local copies
		AtomEntry localEntry = entry;
		AtomFeed localFeed = feed;

		// Make server modifications to the entry
		localEntry = entryService.updateDates(localEntry);

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
		feed = updateFeedCategories(entry, feed);
		feed.getXmlObject().getFeed().addNewEntry().set(entry.getXmlObject().getEntry());
		return feed;
	}

	private AtomFeed updateFeedCategories(AtomEntry entry, AtomFeed feed) {
		// if entry categories has more than feed categories, add to feed categories
		List<Category> feedCats = feed.getXmlObject().getFeed().getCategoryList();
		List<Category> entryCats = entry.getXmlObject().getEntry().getCategoryList();
		for (Category cat : entryCats) {
			boolean found = false;
			for (int i = 0; i < feedCats.size(); i++) {
				if (feedCats.get(i).equals(cat)) {
					found = true;
					break;
				}
			}
			if (found == false) {
				
				serviceDocumentService.updateCollectionCategories(cat,feed);
				
				feed.getXmlObject().getFeed().addNewCategory().set(cat);

			}
		}
		return feed;
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
	
	public String searchFeedLinksForRel(AtomFeed feed,String rel)
	{
		for (Link link : feed.getXmlObject().getFeed().getLinkList())
		{
			if (link.getRel().equals(rel))
			{
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
