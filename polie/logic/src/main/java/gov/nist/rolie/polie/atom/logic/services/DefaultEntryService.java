/**
 * Portions of this software was developed by employees of the National Institute
 * of Standards and Technology (NIST), an agency of the Federal Government.
 * Pursuant to title 17 United States Code Section 105, works of NIST employees are
 * not subject to copyright protection in the United States and are considered to
 * be in the public domain. Permission to freely use, copy, modify, and distribute
 * this software and its documentation without fee is hereby granted, provided that
 * this notice and disclaimer of warranty appears in all copies.
 *
 * THE SOFTWARE IS PROVIDED 'AS IS' WITHOUT ANY WARRANTY OF ANY KIND, EITHER
 * EXPRESSED, IMPLIED, OR STATUTORY, INCLUDING, BUT NOT LIMITED TO, ANY WARRANTY
 * THAT THE SOFTWARE WILL CONFORM TO SPECIFICATIONS, ANY IMPLIED WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, AND FREEDOM FROM
 * INFRINGEMENT, AND ANY WARRANTY THAT THE DOCUMENTATION WILL CONFORM TO THE
 * SOFTWARE, OR ANY WARRANTY THAT THE SOFTWARE WILL BE ERROR FREE. IN NO EVENT
 * SHALL NIST BE LIABLE FOR ANY DAMAGES, INCLUDING, BUT NOT LIMITED TO, DIRECT,
 * INDIRECT, SPECIAL OR CONSEQUENTIAL DAMAGES, ARISING OUT OF, RESULTING FROM, OR
 * IN ANY WAY CONNECTED WITH THIS SOFTWARE, WHETHER OR NOT BASED UPON WARRANTY,
 * CONTRACT, TORT, OR OTHERWISE, WHETHER OR NOT INJURY WAS SUSTAINED BY PERSONS OR
 * PROPERTY OR OTHERWISE, AND WHETHER OR NOT LOSS WAS SUSTAINED FROM, OR AROSE OUT
 * OF THE RESULTS OF, OR USE OF, THE SOFTWARE OR SERVICES PROVIDED HEREUNDER.
 */

package gov.nist.rolie.polie.atom.logic.services;

import gov.nist.jrolie.model.resource.AtomEntry;
import gov.nist.rolie.polie.atom.logic.LinkAlreadyExistsException;
import gov.nist.rolie.polie.persistence.InvalidResourceTypeException;
import gov.nist.rolie.polie.persistence.ResourceAlreadyExistsException;
import gov.nist.rolie.polie.persistence.ResourceNotFoundException;
import gov.nist.rolie.polie.persistence.database.PersistenceMethod;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3.x2005.atom.AtomDateConstruct;
import org.w3.x2005.atom.EntryDocument;
import org.w3.x2005.atom.LinkDocument.Link;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Calendar;
import java.util.List;

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
  public AtomEntry createEntry(AtomEntry entry, URI iri)
      throws ResourceAlreadyExistsException, LinkAlreadyExistsException, URISyntaxException {
    String id = null;

    if (!hasID(entry)) {
      id = generateEntryID(entry);
      entry.getXmlObject().getEntry().addNewId().setStringValue(id);
    } else {
      id = entry.getXmlObject().getEntry().getIdList().get(0).getStringValue();
    }

    String entryLocation = iri.toString() + "entry/" + id;

    entry = addNewEntryLink(entry, "self", entryLocation);
    entry = addNewEntryLink(entry, "edit", entryLocation);
    entry = updateDates(entry);

    entry.setIRI(new URI(entryLocation));
    return persistenceMethod.createEntry(entry, entryLocation);
  }

  private String generateEntryID(AtomEntry entry) {
    return persistenceMethod.generateNewEntryID(entry);
  }

  private boolean hasID(AtomEntry entry) {
    return (entry.getXmlObject().getEntry().getIdList() != null && entry.getXmlObject().getEntry().sizeOfIdArray() > 0);
  }

  @Override
  public AtomEntry addNewEntryLink(AtomEntry entry, String rel, String href) throws LinkAlreadyExistsException {
    Link link = hasLink(entry, rel, href);
    if (link == null) {
      link = Link.Factory.newInstance();
      link.setHref(href);
      link.setRel(rel);
      entry.getXmlObject().getEntry().addNewLink().set(link);
    } else {
      throw new LinkAlreadyExistsException();
    }
    return entry;
  }

  /**
   * 
   * @param entry
   * @param link
   * @return
   */
  public Link deleteLink(AtomEntry entry, Link link) {
    List<Link> links = entry.getXmlObject().getEntry().getLinkList();
    for (int i = 0; i < links.size(); i++) {
      Link innerLink = links.get(i);
      if (linkEquals(innerLink, link)) {
        links.remove(i);
        return innerLink;
      }
    }
    return null;
  }

  private boolean linkEquals(Link link1, Link link2) {
    return link1.getRel().equals(link2.getRel()) && link2.getHref().equals(link2.getHref());
  }

  @Override
  public Link hasLink(AtomEntry entry, String rel, String href) { // TODO:
    // Add
    // regex
    // matching
    // for
    // rel
    // and
    // href
    List<Link> links = entry.getXmlObject().getEntry().getLinkList();
    for (Link link : links) {
      if ((link.getRel().equals(rel) || rel.equals("any")) && (link.getHref().equals(href) || href.equals("any"))) {
        System.out.println("dink" + rel + href);
        return link;
      }
    }
    return null;
  }

  @Override
  public AtomEntry updateEntry(AtomEntry entry, URI iri)
      throws ResourceNotFoundException, InvalidResourceTypeException {
    entry = updateUpdatedDate(entry);
    return persistenceMethod.updateEntry(entry, iri);
  }

  @Override
  public boolean deleteEntry(URI iri) throws ResourceNotFoundException, InvalidResourceTypeException {
    return persistenceMethod.deleteEntry(iri);
  }

  private AtomEntry updateUpdatedDate(AtomEntry entry) {
    if (entry.getXmlObject().getEntry().getUpdatedList().isEmpty()) {
      entry.getXmlObject().getEntry().addNewUpdated();
    }

    AtomDateConstruct date = entry.getXmlObject().getEntry().getPublishedList().get(0);
    date.setDateValue(Calendar.getInstance().getTime());

    entry.getXmlObject().getEntry().getUpdatedList().set(0, date);
    return entry;
  }

  @Override
  public AtomEntry updateDates(AtomEntry entry) {
    if (entry.getXmlObject().getEntry().getPublishedList().isEmpty()) {
      entry.getXmlObject().getEntry().addNewPublished();
    }
    if (entry.getXmlObject().getEntry().getUpdatedList().isEmpty()) {
      entry.getXmlObject().getEntry().addNewUpdated();
    }

    AtomDateConstruct date = entry.getXmlObject().getEntry().getPublishedList().get(0);
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

    Link link = hasLink(localEntry, "feed", "any");
    localEntry.getXmlObject().getEntry().getLinkList().remove(link);

    return localEntry; // TODO: remove unneeded elements from entry view in
    // feed
  }

  private AtomEntry cleanEntryLinks(AtomEntry entry) {
    if (entry.getXmlObject().getEntry().getLinkList().isEmpty()) {
      return entry;
    }
    List<Link> links = entry.getXmlObject().getEntry().getLinkList();
    for (int i = 0; i < links.size(); i++) {
      Link link = links.get(i);
      if (link.getRel().equals("self") || link.getRel().equals("feed") || link.getRel().equals("edit")) {
        links.remove(link);
        i--;
      }
    }
    return entry;
  }

  @Override
  public AtomEntry cleanEntry(AtomEntry entry) {
    entry.getXmlObject().getEntry().getPublishedList().clear();
    entry.getXmlObject().getEntry().getUpdatedList().clear();

    if (!entry.getXmlObject().getEntry().getLinkList().isEmpty()) {
      entry = cleanEntryLinks(entry);
    }
    return entry;
  }

}
