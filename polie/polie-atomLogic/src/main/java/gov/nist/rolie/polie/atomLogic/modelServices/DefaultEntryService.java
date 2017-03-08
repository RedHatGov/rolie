package gov.nist.rolie.polie.atomLogic.modelServices;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3.x2005.atom.AtomDateConstruct;
import org.w3.x2005.atom.EntryDocument;
import org.w3.x2005.atom.LinkDocument.Link;

import gov.nist.rolie.polie.atomLogic.LinkAlreadyExistsException;
import gov.nist.rolie.polie.model.models.AtomEntry;
import gov.nist.rolie.polie.persistence.InvalidResourceTypeException;
import gov.nist.rolie.polie.persistence.ResourceAlreadyExistsException;
import gov.nist.rolie.polie.persistence.ResourceNotFoundException;
import gov.nist.rolie.polie.persistence.database.PersistenceMethod;

@Component
public class DefaultEntryService implements EntryService {

	@Autowired
	PersistenceMethod persistenceMethod;

	public DefaultEntryService() {
	}

	@Override
	public void publishEntry(AtomEntry entry) {

	}

	@Override
	public AtomEntry loadEntry(URI uri) throws ResourceNotFoundException, InvalidResourceTypeException {
		return persistenceMethod.loadEntry(uri);
	}

	@Override
	public AtomEntry createEntry(AtomEntry entry, URI iri) throws ResourceAlreadyExistsException, LinkAlreadyExistsException, URISyntaxException {
		String ID = null;
		if (!hasID(entry))
		{
			ID = generateEntryID(entry);
			entry.getXmlObject().getEntry().addNewId().setStringValue(ID);
		}
		else
		{
			ID = entry.getXmlObject().getEntry().getIdList().get(0).getStringValue();
		}
		;

		String entryLocation = iri.toString() + "entry/" + ID;
		
		entry = addEntryLink(entry,"self",entryLocation); //TODO: Generate hrefs
		entry = addEntryLink(entry,"edit",entryLocation);
		entry = updateDates(entry);
		
		entry.setIRI(new URI(entryLocation));
		return persistenceMethod.createEntry(entry, entryLocation);
	}
	
	private String generateEntryID(AtomEntry entry) {
		return persistenceMethod.generateNewEntryID(entry);
	}

	private boolean hasID(AtomEntry entry)
	{
		return (entry.getXmlObject().getEntry().getIdList()!=null && entry.getXmlObject().getEntry().sizeOfIdArray()>0);
	}
	
	public AtomEntry addEntryLink(AtomEntry entry,String rel,String href) throws LinkAlreadyExistsException
	{
		if (hasLink(entry,rel,href)==null)
		{
			Link link = Link.Factory.newInstance();
			link.setHref(href);
			link.setRel(rel);
			entry.getXmlObject().getEntry().addNewLink().set(link);
		}
		else
		{
			throw new LinkAlreadyExistsException();
		}
		return entry;
	}

	private Link hasLink(AtomEntry entry, String rel, String href) { //TODO: Add regex matching for rel and href
		List<Link> links = entry.getXmlObject().getEntry().getLinkList();
		for (Link link : links) {
			if ((link.getRel().equals(rel) || rel.equals("any"))
					&& (link.getHref().equals(href) || href.equals("any"))) {
				return link;
			}
		}
		return null;
	}

	@Override
	public AtomEntry updateEntry(AtomEntry entry, URI iri)
			throws ResourceNotFoundException, InvalidResourceTypeException {
		return persistenceMethod.updateEntry(entry, iri);
	}

	@Override
	public boolean deleteEntry(URI iri) throws ResourceNotFoundException, InvalidResourceTypeException {
		return persistenceMethod.deleteEntry(iri);
	}

	@Override
	public AtomEntry updateDates(AtomEntry entry) {
		AtomDateConstruct date = entry.getXmlObject().getEntry().getPublishedArray(0);
		date.setDateValue(Calendar.getInstance().getTime());

		entry.getXmlObject().getEntry().getPublishedList().set(0, date);
		entry.getXmlObject().getEntry().getUpdatedList().set(0, date);

		return entry;
	}

	@Override
	public AtomEntry stripEntry(AtomEntry entry) {
		EntryDocument entryDoc = EntryDocument.Factory.newInstance();
		entryDoc.set(entry.getXmlObject().copy());
		AtomEntry localEntry = new AtomEntry(entryDoc);
		
		Link link = hasLink(localEntry,"feed","any");
		localEntry.getXmlObject().getEntry().getLinkList().remove(link);
		
		return localEntry; //TODO: remove unneeded elements from entry view in feed
	}

}
