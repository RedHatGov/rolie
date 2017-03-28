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

import gov.nist.rolie.polie.atom.logic.EntryNotFoundException;
import gov.nist.rolie.polie.atom.logic.MismatchedCategoriesException;
import gov.nist.rolie.polie.model.models.APPServiceDocument;
import gov.nist.rolie.polie.model.models.AtomEntry;
import gov.nist.rolie.polie.model.models.AtomFeed;
import gov.nist.rolie.polie.persistence.InvalidResourceTypeException;
import gov.nist.rolie.polie.persistence.ResourceAlreadyExistsException;
import gov.nist.rolie.polie.persistence.ResourceNotFoundException;
import gov.nist.rolie.polie.persistence.database.PersistenceMethod;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3.x2005.atom.AtomDateConstruct;
import org.w3.x2005.atom.CategoryDocument.Category;
import org.w3.x2005.atom.EntryDocument;
import org.w3.x2005.atom.LinkDocument.Link;
import org.w3.x2007.app.CategoriesType;
import org.w3.x2007.app.CollectionType;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Calendar;
import java.util.List;

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

  /**
   * 
   * @param entry
   * @param feed
   * @return
   * @throws MismatchedCategoriesException
   * @throws ResourceNotFoundException
   * @throws InvalidResourceTypeException
   * @throws URISyntaxException
   * @throws EntryNotFoundException
   */
  @Override
  public AtomFeed updateEntryInFeed(AtomEntry entry, AtomFeed feed) throws MismatchedCategoriesException,
      ResourceNotFoundException, InvalidResourceTypeException, URISyntaxException, EntryNotFoundException {
    matchingCategories(entry, feed);
    feed = removeEntryFromFeed(entry, feed);
    feed = addEntryToFeed(entry, feed);
    feed = updateFeedDates(feed);
    return feed;
  }

  private AtomFeed removeEntryFromFeed(AtomEntry entry, AtomFeed feed) throws EntryNotFoundException {
    return removeEntryFromFeedById(entry.getXmlObject().getEntry().getIdList().get(0).getStringValue(), feed);
  }

  private AtomFeed removeEntryFromFeedById(String id, AtomFeed feed) throws EntryNotFoundException {
    AtomFeed localFeed = feed;
    boolean success = false;
    List<EntryDocument.Entry> entries = localFeed.getXmlObject().getFeed().getEntryList();
    for (int i = 0; i < entries.size(); i++) {
      EntryDocument.Entry innerEntry = entries.get(i);
      if (innerEntry.getIdList().get(0).getStringValue().equals(id)) {
        localFeed.getXmlObject().getFeed().getEntryList().remove(i);
        success = true;
        break;
      }
    }
    if (success) {
      return localFeed;
    } else {
      throw new EntryNotFoundException();
    }
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
    //feed.getXmlObject().getFeed().getEntryList()
    //XmlCursor cursor = fir
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
        found = false;
        if (!ecat.getScheme().equals("urn:ietf:params:rolie:category:information-type")) {
          continue;
        }
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
