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

package gov.nist.rolie.polie.persistence.database;

import gov.nist.rolie.polie.model.ResourceType;
import gov.nist.rolie.polie.model.models.APPCategories;
import gov.nist.rolie.polie.model.models.APPResource;
import gov.nist.rolie.polie.model.models.APPServiceDocument;
import gov.nist.rolie.polie.model.models.AtomEntry;
import gov.nist.rolie.polie.model.models.AtomFeed;
import gov.nist.rolie.polie.persistence.InvalidResourceTypeException;
import gov.nist.rolie.polie.persistence.ResourceAlreadyExistsException;
import gov.nist.rolie.polie.persistence.ResourceNotFoundException;

import org.apache.xmlbeans.XmlException;
import org.springframework.stereotype.Component;
import org.w3.x2005.atom.EntryDocument;
import org.w3.x2005.atom.FeedDocument;
import org.w3.x2007.app.ServiceDocument;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

@Component
public class MapPersist implements PersistenceMethod {

  private static final boolean BOOTSTRAP = true;
  HashMap<String, MappedResource> map = new HashMap<>();

  /**
   * 
   */
  public MapPersist() {
    if (BOOTSTRAP) {
      // System.out.println("Bootstrapping...");
      Path pathFeed1 = Paths
          .get("C:\\Users\\sab3\\git\\IETF-ROLIE\\polie\\polie-server\\src\\main\\resources\\testFeed1.xml");
      File fileFeed1 = pathFeed1.toFile();

      Path pathFeed2 = Paths
          .get("C:\\Users\\sab3\\git\\IETF-ROLIE\\polie\\polie-server\\src\\main\\resources\\testFeed2.xml");
      File fileFeed2 = pathFeed2.toFile();

      Path pathEntry1 = Paths
          .get("C:\\Users\\sab3\\git\\IETF-ROLIE\\polie\\polie-server\\src\\main\\resources\\testEntry1.xml");
      File fileEntry1 = pathEntry1.toFile();

      Path pathEntry2 = Paths
          .get("C:\\Users\\sab3\\git\\IETF-ROLIE\\polie\\polie-server\\src\\main\\resources\\testEntry2.xml");
      File fileEntry2 = pathEntry2.toFile();

      Path pathService = Paths
          .get("C:\\Users\\sab3\\git\\IETF-ROLIE\\polie\\polie-server\\src\\main\\resources\\testService.xml");
      File fileService = pathService.toFile();

      AtomFeed feed1 = null;
      AtomFeed feed2 = null;
      AtomEntry entry1 = null;
      AtomEntry entry2 = null;
      APPServiceDocument service = null;
      try {
        feed1 = new AtomFeed(FeedDocument.Factory.parse(fileFeed1));
        feed2 = new AtomFeed(FeedDocument.Factory.parse(fileFeed2));
        entry1 = new AtomEntry(EntryDocument.Factory.parse(fileEntry1));
        entry2 = new AtomEntry(EntryDocument.Factory.parse(fileEntry2));
        service = new APPServiceDocument(ServiceDocument.Factory.parse(fileService));
      } catch (XmlException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      try {
        createFeed(feed1, "http://localhost:8080/polie-server/rest/feed/1");
        createFeed(feed2, "http://localhost:8080/polie-server/rest/feed/2");
        createEntry(entry1, "http://localhost:8080/polie-server/rest/entry/1");
        createEntry(entry2, "http://localhost:8080/polie-server/rest/entry/2");
        URI serviceuri = new URI("http://localhost:8080/polie-server/rest/service");
        createServiceDocument(service, serviceuri);
        // System.out.println("Just created a service document at:" +
        // serviceuri.toString());
      } catch (ResourceAlreadyExistsException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } catch (URISyntaxException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
  }

  @Override
  public ResourceType identifyResouceType(URI iri) throws ResourceNotFoundException {
    return identifyResouceType(iri.toString());
  }

  @Override
  public ResourceType identifyResouceType(String id) throws ResourceNotFoundException {
    MappedResource resource = map.get(id);
    if (resource == null) {
      throw new ResourceNotFoundException(id);
    } else {
      return resource.getType();
    }
  }

  @Override
  public APPResource loadResource(URI iri) throws ResourceNotFoundException {

    return loadResource(iri.toString());
  }

  @Override
  public APPResource loadResource(String id) throws ResourceNotFoundException {
    MappedResource resource = map.get(id);
    if (resource == null) {
      throw new ResourceNotFoundException(id);
    } else {
      return resource.getResource();
    }
  }

  @Override
  public APPResource createResource(APPResource resource, URI uri) throws ResourceAlreadyExistsException {
    return createResource(resource, uri.toString());
  }

  @Override
  public APPResource createResource(APPResource resource, String id) throws ResourceAlreadyExistsException {
    if (map.containsKey(id)) {
      throw new ResourceAlreadyExistsException();
    } else {
      map.put(id, new MappedResource(resource, ResourceType.RESOURCE));
      return resource;
    }
  }

  @Override
  public APPResource updateResource(APPResource resource, URI uri) throws ResourceNotFoundException {
    return updateResource(resource, uri.toString());
  }

  @Override
  public APPResource updateResource(APPResource resource, String id) throws ResourceNotFoundException {
    MappedResource mappedResource = map.get(id);
    if (mappedResource == null) {
      throw new ResourceNotFoundException(id);
    } else {
      map.put(id, new MappedResource(resource, ResourceType.RESOURCE));
      return resource;
    }
  }

  @Override
  public boolean deleteResource(URI uri) throws ResourceNotFoundException {
    return deleteResource(uri.toString());
  }

  @Override
  public boolean deleteResource(String id) throws ResourceNotFoundException {
    if (map.remove(id) == null) {
      throw new ResourceNotFoundException(id);
    } else {
      return true;
    }
  }

  @Override
  public APPServiceDocument loadServiceDocument(URI iri)
      throws ResourceNotFoundException, InvalidResourceTypeException {
    MappedResource mappedResource = map.get(iri.toString());
    if (mappedResource == null) {
      throw new ResourceNotFoundException(iri.toString());
    } else if (mappedResource.getType() != ResourceType.SERVICE) {
      throw new InvalidResourceTypeException();
    } else {
      return (APPServiceDocument) mappedResource.getResource();
    }
  }

  @Override
  public APPServiceDocument createServiceDocument(APPServiceDocument serviceDoc, URI uri)
      throws ResourceAlreadyExistsException {
    if (map.containsKey(uri)) {
      throw new ResourceAlreadyExistsException();
    } else {
      map.put(uri.toString(), new MappedResource(serviceDoc, ResourceType.SERVICE));
      return serviceDoc;
    }
  }

  @Override
  public APPServiceDocument updateServiceDocument(APPServiceDocument serviceDoc, URI uri)
      throws ResourceNotFoundException, InvalidResourceTypeException {
    MappedResource mappedResource = map.get(uri.toString());
    if (mappedResource == null) {
      throw new ResourceNotFoundException(uri.toString());
    } else if (mappedResource.getType() != ResourceType.SERVICE) {
      throw new InvalidResourceTypeException();
    } else {
      map.put(uri.toString(), new MappedResource(serviceDoc, ResourceType.SERVICE));
      return serviceDoc;
    }
  }

  @Override
  public boolean deleteServiceDocument(URI uri) throws ResourceNotFoundException, InvalidResourceTypeException {
    MappedResource mappedResource = map.get(uri.toString());
    if (mappedResource == null) {
      throw new ResourceNotFoundException(uri.toString());
    } else if (mappedResource.getType() != ResourceType.SERVICE) {
      throw new InvalidResourceTypeException();
    } else {
      map.remove(uri.toString());
      return true;
    }
  }

  @Override
  public APPCategories loadCategoryDocument(URI iri) throws ResourceNotFoundException, InvalidResourceTypeException {
    MappedResource mappedResource = map.get(iri.toString());
    if (mappedResource == null) {
      throw new ResourceNotFoundException(iri.toString());
    } else if (mappedResource.getType() != ResourceType.CATEGORY) {
      throw new InvalidResourceTypeException();
    } else {
      return (APPCategories) mappedResource.getResource();
    }
  }

  @Override
  public APPCategories createCategoryDocument(APPCategories categoryDoc, URI uri)
      throws ResourceAlreadyExistsException {
    if (map.containsKey(uri)) {
      throw new ResourceAlreadyExistsException();
    } else {
      map.put(uri.toString(), new MappedResource(categoryDoc, ResourceType.CATEGORY));
      return categoryDoc;
    }
  }

  @Override
  public APPCategories updateCategoryDocument(APPCategories categoryDoc, URI uri)
      throws ResourceNotFoundException, InvalidResourceTypeException {
    MappedResource mappedResource = map.get(uri.toString());
    if (mappedResource == null) {
      throw new ResourceNotFoundException(uri.toString());
    } else if (mappedResource.getType() != ResourceType.SERVICE) {
      throw new InvalidResourceTypeException();
    } else {
      map.put(uri.toString(), new MappedResource(categoryDoc, ResourceType.CATEGORY));
      return categoryDoc;
    }
  }

  @Override
  public boolean deleteCategoryDocument(URI uri) throws ResourceNotFoundException, InvalidResourceTypeException {
    MappedResource mappedResource = map.get(uri.toString());
    if (mappedResource == null) {
      throw new ResourceNotFoundException(uri.toString());
    } else if (mappedResource.getType() != ResourceType.CATEGORY) {
      throw new InvalidResourceTypeException();
    } else {
      map.remove(uri.toString());
      return true;
    }
  }

  @Override
  public AtomFeed loadFeed(URI iri) throws ResourceNotFoundException, InvalidResourceTypeException {
    return loadFeed(iri.toString());
  }

  @Override
  public AtomFeed loadFeed(String id) throws ResourceNotFoundException, InvalidResourceTypeException {
    MappedResource mappedResource = map.get(id);
    if (mappedResource == null) {
      throw new ResourceNotFoundException(id);
    } else if (mappedResource.getType() != ResourceType.FEED) {
      throw new InvalidResourceTypeException();
    } else {
      return (AtomFeed) mappedResource.getResource();
    }
  }

  @Override
  public AtomFeed createFeed(AtomFeed feed, URI uri) throws ResourceAlreadyExistsException {
    return createFeed(feed, uri.toString());
  }

  @Override
  public AtomFeed createFeed(AtomFeed feed, String id) throws ResourceAlreadyExistsException {
    if (map.containsKey(id)) {
      throw new ResourceAlreadyExistsException();
    } else {
      map.put(id, new MappedResource(feed, ResourceType.FEED));
      return feed;
    }
  }

  @Override
  public AtomFeed updateFeed(AtomFeed feed, URI uri) throws ResourceNotFoundException, InvalidResourceTypeException {
    return updateFeed(feed, uri.toString());
  }

  @Override
  public AtomFeed updateFeed(AtomFeed feed, String id) throws ResourceNotFoundException, InvalidResourceTypeException {
    MappedResource mappedResource = map.get(id);
    if (mappedResource == null) {
      throw new ResourceNotFoundException(id);
    } else if (mappedResource.getType() != ResourceType.FEED) {
      throw new InvalidResourceTypeException();
    } else {
      map.put(id, new MappedResource(feed, ResourceType.FEED));
      return feed;
    }
  }

  @Override
  public boolean deleteFeed(URI uri) throws ResourceNotFoundException, InvalidResourceTypeException {
    return deleteFeed(uri.toString());
  }

  @Override
  public boolean deleteFeed(String id) throws ResourceNotFoundException, InvalidResourceTypeException {
    MappedResource mappedResource = map.get(id);
    if (mappedResource == null) {
      throw new ResourceNotFoundException(id);
    } else if (mappedResource.getType() != ResourceType.CATEGORY) {
      throw new InvalidResourceTypeException();
    } else {
      map.remove(id);
      return true;
    }
  }

  @Override
  public AtomEntry loadEntry(URI iri) throws ResourceNotFoundException, InvalidResourceTypeException {
    return loadEntry(iri.toString());
  }

  @Override
  public AtomEntry loadEntry(String id) throws ResourceNotFoundException, InvalidResourceTypeException {
    MappedResource mappedResource = map.get(id);
    if (mappedResource == null) {
      throw new ResourceNotFoundException(id);
    } else if (mappedResource.getType() != ResourceType.ENTRY) {
      throw new InvalidResourceTypeException();
    } else {
      return (AtomEntry) mappedResource.getResource();
    }
  }

  @Override
  public AtomEntry createEntry(AtomEntry entry, URI uri) throws ResourceAlreadyExistsException {
    return createEntry(entry, uri.toString());
  }

  @Override
  public AtomEntry createEntry(AtomEntry entry, String id) throws ResourceAlreadyExistsException {
    if (map.containsKey(id)) {
      throw new ResourceAlreadyExistsException();
    } else {
      map.put(id, new MappedResource(entry, ResourceType.ENTRY));
      return entry;
    }
  }

  @Override
  public AtomEntry updateEntry(AtomEntry entry, URI uri)
      throws ResourceNotFoundException, InvalidResourceTypeException {
    return updateEntry(entry, uri.toString());
  }

  @Override
  public AtomEntry updateEntry(AtomEntry entry, String id)
      throws ResourceNotFoundException, InvalidResourceTypeException {
    MappedResource mappedResource = map.get(id);
    if (mappedResource == null) {
      throw new ResourceNotFoundException(id);
    } else if (mappedResource.getType() != ResourceType.ENTRY) {
      throw new InvalidResourceTypeException();
    } else {
      map.put(id, new MappedResource(entry, ResourceType.ENTRY));
      return entry;
    }
  }

  @Override
  public boolean deleteEntry(URI uri) throws ResourceNotFoundException, InvalidResourceTypeException {
    return deleteEntry(uri.toString());
  }

  @Override
  public boolean deleteEntry(String id) throws ResourceNotFoundException, InvalidResourceTypeException {
    MappedResource mappedResource = map.get(id);
    if (mappedResource == null) {
      throw new ResourceNotFoundException(id);
    } else if (mappedResource.getType() != ResourceType.CATEGORY) {
      throw new InvalidResourceTypeException();
    } else {
      map.remove(id);
      return true;
    }
  }

  @Override
  public boolean resourceExists(URI iri) {
    return map.containsKey(iri.toString());

  }

  @Override
  public boolean resourceExists(String id) {
    return map.containsKey(id);
  }

  @Override
  public boolean resourceExists(APPResource resource, ResourceType type) {
    return map.containsValue(new MappedResource(resource, type));
  }

  @Override
  public String generateNewEntryID(AtomEntry entry) {
    return java.util.UUID.randomUUID().toString();
  }
}
