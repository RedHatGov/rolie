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

import gov.nist.rolie.polie.model.models.APPServiceDocument;
import gov.nist.rolie.polie.model.models.AtomFeed;
import gov.nist.rolie.polie.model.models.elements.APPCollection;
import gov.nist.rolie.polie.persistence.InvalidResourceTypeException;
import gov.nist.rolie.polie.persistence.ResourceAlreadyExistsException;
import gov.nist.rolie.polie.persistence.ResourceNotFoundException;
import gov.nist.rolie.polie.persistence.database.PersistenceMethod;

import org.apache.xmlbeans.XmlCursor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3.x2005.atom.CategoryDocument.Category;
import org.w3.x2007.app.CollectionType;
import org.w3.x2007.app.WorkspaceType;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Component
public class DefaultServiceDocumentService implements ServiceDocumentService {

  @Autowired
  FeedService feedService;

  @Autowired
  PersistenceMethod persistenceMethod;

  public APPCollection buildCollectionFromFeed(AtomFeed feed) {
    // Unused
    return null;
  }

  public APPCollection addTitleToCollection(APPCollection collection, String title) {
    XmlCursor cursor = collection.getXmlObject().newCursor();
    cursor.toChild("collection");
    cursor.insertElementWithText("atom:title", title);
    cursor.dispose();
    return collection;
  }

  public String readTitleFromCollection(APPCollection collection) {
    XmlCursor cur = collection.getXmlObject().newCursor();
    cur.selectPath("atom:title");
    String titleText = cur.getTextValue();
    cur.dispose();
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

  public CollectionType getCollectionFromFeed(AtomFeed feed, APPServiceDocument serviceDoc) {

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

    getCollectionFromFeed(feed, serviceDoc).getCategoriesList().get(0).addNewCategory().set(cat);

    try {
      updateServiceDocument(serviceDoc, feedService.getServiceDocumentIRI(feed));
    } catch (ResourceNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (InvalidResourceTypeException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  /**
   * @param service
   * @return
   */
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
