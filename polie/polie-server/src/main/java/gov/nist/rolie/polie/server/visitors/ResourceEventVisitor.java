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
 * SHALL NASA BE LIABLE FOR ANY DAMAGES, INCLUDING, BUT NOT LIMITED TO, DIRECT,
 * INDIRECT, SPECIAL OR CONSEQUENTIAL DAMAGES, ARISING OUT OF, RESULTING FROM, OR
 * IN ANY WAY CONNECTED WITH THIS SOFTWARE, WHETHER OR NOT BASED UPON WARRANTY,
 * CONTRACT, TORT, OR OTHERWISE, WHETHER OR NOT INJURY WAS SUSTAINED BY PERSONS OR
 * PROPERTY OR OTHERWISE, AND WHETHER OR NOT LOSS WAS SUSTAINED FROM, OR AROSE OUT
 * OF THE RESULTS OF, OR USE OF, THE SOFTWARE OR SERVICES PROVIDED HEREUNDER.
 */

package gov.nist.rolie.polie.server.visitors;

import gov.nist.rolie.polie.atom.logic.EntryNotFoundException;
import gov.nist.rolie.polie.atom.logic.LinkAlreadyExistsException;
import gov.nist.rolie.polie.atom.logic.MismatchedCategoriesException;
import gov.nist.rolie.polie.atom.logic.services.EntryService;
import gov.nist.rolie.polie.atom.logic.services.FeedService;
import gov.nist.rolie.polie.atom.logic.services.ResourceService;
import gov.nist.rolie.polie.model.models.APPResource;
import gov.nist.rolie.polie.model.models.AtomEntry;
import gov.nist.rolie.polie.model.models.AtomFeed;
import gov.nist.rolie.polie.persistence.InvalidResourceTypeException;
import gov.nist.rolie.polie.persistence.ResourceAlreadyExistsException;
import gov.nist.rolie.polie.persistence.ResourceNotFoundException;
import gov.nist.rolie.polie.server.event.Delete;
import gov.nist.rolie.polie.server.event.Get;
import gov.nist.rolie.polie.server.event.Post;
import gov.nist.rolie.polie.server.event.Put;
import gov.nist.rolie.polie.server.servlet.AtomResourceEvent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.xmlbeans.XmlException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3.x2005.atom.EntryDocument;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

/**
 * Primary driver of resource requests in POLIE. Consolidated in one visitor because the request
 * type implies the valid resource types.
 * 
 * @author sab3
 *
 */
@Component
public class ResourceEventVisitor implements RESTEventVisitor { // TODO:
  private static final Logger log = LogManager.getLogger(ResourceEventVisitor.class);

  @Autowired
  ResourceService resourceService;

  @Autowired
  FeedService feedService;

  @Autowired
  EntryService entryService;

  /**
   * When this visitor encounters a get request, the resource at the given IRI can be loaded. There
   * is no needed consideration at this point as to what the resource is.
   * 
   * It then places the Retrieved resource in the data map and returns.
   * 
   * DATA MAP CONTRACT: BEFORE: "IRI" is an absolute path to the resource. AFTER: "resource" holds
   * the APPResource at the IRI.
   * 
   * @param get
   *          The event type.
   * @param rb
   *          The passed response builder
   * @param data
   *          The passed data map
   * @returns Boolean value indicating whether or not execution should continue.
   */
  @Override
  public boolean visit(Get get, ResponseBuilder rb, Map<String, Object> data) {
    log.debug("Processing GET request");
    URI iri = get.getURIInfo().getAbsolutePath();
    APPResource resource;
    try {
      resource = resourceService.loadResource(iri);
    } catch (ResourceNotFoundException e) {
      rb.status(Status.NOT_FOUND);
      rb.entity("Resource not found at database location: " + e.getResourceLocation());
      return false;
    }

    data.put(RESOURCE_KEY, resource);
    rb.status(Status.OK);
    return true;
  }

  /**
   * When this visitor encounters a POST request, we can assume that the target is a collection. The
   * body of the request needs to be created and added to the Collection as a member resource.
   * 
   * Header Considerations: "Location" is set to the location the resource was created at.
   * 
   * DATA MAP CONTRACT: BEFORE: "IRI" is an absolute path to the collection that the resource will
   * be under. "resource" holds a valid APPResource to be posted to a collection. AFTER:
   * "CreatedResourceLocationIRI" holds the actual location the resource was created at. NOTE: This
   * MUST be set by the createResource() method. "CreatedResource" holds the actual created
   * representation of the resource.
   * 
   * @param post
   *          The event type.
   * @param rb
   *          The passed response builder
   * @param data
   *          The passed data map
   * @returns Boolean value indicating whether or not execution should continue.
   */
  @Override
  public boolean visit(Post post, ResponseBuilder rb, Map<String, Object> data) {

    AtomEntry entry;
    AtomFeed feed;
    URI feedURI = post.getURIInfo().getAbsolutePath();
    URI entryURI = post.getURIInfo().getBaseUri();

    // Load feed from database
    try {
      feed = feedService.loadFeed(feedURI);
    } catch (ResourceNotFoundException e) {
      rb.status(Status.NOT_FOUND);
      rb.entity("Resource not found at database location: " + e.getResourceLocation());
      return false;
    } catch (InvalidResourceTypeException e) {
      rb.status(Status.METHOD_NOT_ALLOWED);
      rb.entity("The requested resource is not a valid feed to POST to.");
      return false;
    }

    try {
      entry = (AtomEntry) data.get(RESOURCE_KEY);
      entry = entryService.cleanEntry(entry);
      entryService.addNewEntryLink(entry, "feed", feedURI.toString());
      entry = entryService.createEntry(entry, entryURI);
    } catch (ResourceAlreadyExistsException e) {
      rb.status(Status.CONFLICT);
      rb.entity("The resource you are posting already exists.");
      return false;
    } catch (LinkAlreadyExistsException e) {
      rb.status(Status.CONFLICT);
      rb.entity("Link conflict in entry.");
      return false;
    } catch (URISyntaxException e) {
      rb.status(Status.CONFLICT);
      rb.entity("ID generates an invalid URI.");
      return false;
    }

    // Add the new entry to the feed
    try {
      feed = feedService.addEntryToFeed(entry, feed);
      feedService.updateFeed(feed, feedURI);
    } catch (MismatchedCategoriesException e) {
      rb.status(Status.EXPECTATION_FAILED);
      rb.entity("The entry you are trying to post has invalid categories for"
          + " this fixed cateory feed. Entry category:" + e.getChildCategory().toString());
      return false;
    } catch (ResourceNotFoundException e) {
      rb.status(Status.NOT_FOUND);
      rb.entity("Resource not found at database location: " + e.getResourceLocation());
      return false;
    } catch (InvalidResourceTypeException e) {
      rb.status(Status.METHOD_NOT_ALLOWED);
      rb.entity("Invalid resource while adding entry to feed.");
      return false;
    }

    // Report success, store resource, and return
    rb.status(Status.CREATED);
    rb = rb.header("Location", entry.getIRI().toString());

    data.put(RESOURCE_KEY, entry);
    return true;

  }

  /**
   * When this visitor encounters a PUT request, we can assume that the target is a resource. The
   * body of the request will be used to overwrite the target resource.
   * <p>
   * Header Considerations: "Location" is set to the location the resource was created at.
   * <p>
   * DATA MAP CONTRACT: BEFORE: "IRI" is an absolute path to the resource to be updated "resource"
   * holds a valid APPResource that will overwrite the target AFTER: "UpdatedResource" holds the
   * actual updated representation of the resource.
   * 
   * @param put
   *          The event type.
   * @param rb
   *          The passed response builder
   * @param data
   *          The passed data map
   * @returns Boolean value indicating whether or not execution should continue.
   */
  @Override
  public boolean visit(Put put, ResponseBuilder rb, Map<String, Object> data) {
    AtomEntry newEntry;
    AtomEntry oldEntry;
    AtomFeed feed = null;
    URI feedURI = put.getURIInfo().getAbsolutePath();
    URI entryURI = put.getURIInfo().getAbsolutePath();

    try {
      oldEntry = entryService.loadEntry(entryURI);
    } catch (ResourceNotFoundException e) {
      rb.status(Status.NOT_FOUND);
      rb.entity("Entry not found at database location: " + e.getResourceLocation());
      return false;
    } catch (InvalidResourceTypeException e) {
      rb.status(Status.METHOD_NOT_ALLOWED);
      rb.entity("The requested resource is not a valid entry to PUT to.");
      return false;
    }

    try {
      if (entryService.hasLink(oldEntry, "feed", "any") == null) {
        rb.status(Status.NOT_IMPLEMENTED);
        rb.entity("Target entry is either standalone or has been decoupled from its feed. "
            + "Standalone entry support is not implemented.");
        return false;
      }
      feedURI = new URI(entryService.hasLink(oldEntry, "feed", "any").getHref());
      feed = feedService.loadFeed(feedURI);
    } catch (ResourceNotFoundException e) {
      rb.status(Status.NOT_FOUND);
      rb.entity("Feed not found at database location: " + e.getResourceLocation());
      return false;
    } catch (InvalidResourceTypeException e) {
      rb.status(Status.METHOD_NOT_ALLOWED);
      rb.entity("The requested resource is not a valid feed to PUT to.");
      return false;
    } catch (URISyntaxException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    newEntry = (AtomEntry) data.get(RESOURCE_KEY);

    try {
      newEntry = entryService.updateEntry(newEntry, entryURI);
    } catch (ResourceNotFoundException e) {
      rb.status(Status.NOT_FOUND);
      rb.entity("Entry not found at database location: " + e.getResourceLocation());
      return false;
    } catch (InvalidResourceTypeException e) {
      rb.status(Status.CONFLICT);
      rb.entity("The resource you are attempting to PUT to does not match the type of the body.");
      return false;
    }
    try {
      feed = feedService.updateEntryInFeed(newEntry, feed);
    } catch (MismatchedCategoriesException e) {
      rb.status(Status.EXPECTATION_FAILED);
      rb.entity("The entry you are trying to post has invalid categories for"
          + " this fixed cateory feed. Entry category:" + e.getChildCategory().toString());
      return false;
    } catch (ResourceNotFoundException e) {
      rb.status(Status.NOT_FOUND);
      rb.entity("Resource not found at database location: " + e.getResourceLocation());
      return false;
    } catch (InvalidResourceTypeException e) {
      rb.status(Status.METHOD_NOT_ALLOWED);
      rb.entity("Invalid resource while adding entry to feed.");
      return false;
    } catch (URISyntaxException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (EntryNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    try {
      feedService.updateFeed(feed, feedURI);
    } catch (ResourceNotFoundException e) {
      rb.status(Status.NOT_FOUND);
      rb.entity("Resource not found at database location: " + e.getResourceLocation());
      return false;
    } catch (InvalidResourceTypeException e) {
      rb.status(Status.METHOD_NOT_ALLOWED);
      rb.entity("The feed is not a valid feed to update");
      return false;
    }
    // TODO:switch to app:edited from updated
    rb.status(Status.CREATED);
    rb = rb.header("Location", entryURI);
    rb = rb.header("Content-Location", entryURI);
    data.put(RESOURCE_KEY, newEntry);
    return true;

  }

  /**
   * When this visitor encounters a DELETE request, the resource at the given IRI will be deleted.
   * There is no needed consideration at this point as to what the resource is.
   * 
   * 
   * DATA MAP CONTRACT: BEFORE: "IRI" is an absolute path to the resource. AFTER: NONE
   * 
   * @param delete
   *          The event type.
   * @param rb
   *          The passed response builder
   * @param data
   *          The passed data map
   * @returns Boolean value indicating whether or not execution should continue.
   */
  @Override
  public boolean visit(Delete delete, ResponseBuilder rb, Map<String, Object> data) {
    try {
      resourceService.deleteResource(delete.getURIInfo().getAbsolutePath());
    } catch (ResourceNotFoundException e) {
      rb.status(Status.NOT_FOUND);
      rb.entity("Resource not found at database location: " + e.getResourceLocation());
      return false;
    }
    rb = rb.status(Status.OK);
    return true;
  }

}
