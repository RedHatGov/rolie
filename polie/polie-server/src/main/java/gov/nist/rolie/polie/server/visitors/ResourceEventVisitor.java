package gov.nist.rolie.polie.server.visitors;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import org.apache.xmlbeans.XmlException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3.x2005.atom.EntryDocument;

import gov.nist.rolie.polie.atomLogic.modelServices.EntryService;
import gov.nist.rolie.polie.atomLogic.modelServices.FeedService;
import gov.nist.rolie.polie.atomLogic.LinkAlreadyExistsException;
import gov.nist.rolie.polie.atomLogic.MismatchedCategoriesException;
import gov.nist.rolie.polie.atomLogic.modelServices.ResourceService;
import gov.nist.rolie.polie.model.ResourceType;
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

/**
 * Primary driver of resource requests in POLIE. Consolidated in one visitor
 * because the request type implies the valid resource types.
 * 
 * @author sab3
 *
 */
@Component
public class ResourceEventVisitor implements RESTEventVisitor { // TODO:

	@Autowired
	ResourceService resourceService;

	@Autowired
	FeedService feedService;

	@Autowired
	EntryService entryService;

	/**
	 * When this visitor encounters a get request, the resource at the given IRI
	 * can be loaded. There is no needed consideration at this point as to what
	 * the resource is.
	 * 
	 * It then places the Retrieved resource in the data map and returns.
	 * 
	 * DATA MAP CONTRACT: BEFORE: "IRI" is an absolute path to the resource.
	 * AFTER: "resource" holds the APPResource at the IRI.
	 * 
	 * @param get
	 *            The event type.
	 * @param rb
	 *            The passed response builder
	 * @param data
	 *            The passed data map
	 * @returns Boolean value indicating whether or not execution should
	 *          continue.
	 */
	@Override
	public boolean visit(Get get, ResponseBuilder rb, Map<String, Object> data) {

		URI iri = get.getURIInfo().getAbsolutePath();
		APPResource resource;
		try {

			resource = resourceService.loadResource(iri);
		} catch (ResourceNotFoundException e) {
			rb.status(Status.NOT_FOUND);
			return false;
		}

		data.put(RESOURCE_DATA_KEY, resource);
		rb.status(Status.OK);
		return true;
	}

	/**
	 * When this visitor encounters a POST request, we can assume that the
	 * target is a collection. The body of the request needs to be created and
	 * added to the Collection as a member resource.
	 * 
	 * Header Considerations: "Location" is set to the location the resource was
	 * created at.
	 * 
	 * DATA MAP CONTRACT: BEFORE: "IRI" is an absolute path to the collection
	 * that the resource will be under. "resource" holds a valid APPResource to
	 * be posted to a collection. AFTER: "CreatedResourceLocationIRI" holds the
	 * actual location the resource was created at. NOTE: This MUST be set by
	 * the createResource() method. "CreatedResource" holds the actual created
	 * representation of the resource.
	 * 
	 * @param post
	 *            The event type.
	 * @param rb
	 *            The passed response builder
	 * @param data
	 *            The passed data map
	 * @returns Boolean value indicating whether or not execution should
	 *          continue.
	 */
	@Override
	public boolean visit(Post post, ResponseBuilder rb, Map<String, Object> data) {

		AtomEntry entry;
		AtomFeed feed;
		URI feedURI = post.getURIInfo().getAbsolutePath();
		URI entryURI = post.getURIInfo().getBaseUri();
		
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
			entry = new AtomEntry(EntryDocument.Factory.parse(post.getBody()));
		} catch (XmlException e) {
			rb.status(Status.NOT_ACCEPTABLE);
			rb.entity("The resource you are posting is invalid.");
			return false;
		}
		
		try{
		entryService.addEntryLink(entry,"feed",feedURI.toString());
		}
		catch (LinkAlreadyExistsException e)
		{
			rb.status(Status.CONFLICT);
			rb.entity("Link conflict in entry. This shouldn't ever happen.");
			return false;
		}
		try {
			entry = entryService.createEntry(entry, entryURI);
		} catch (ResourceAlreadyExistsException e) {
			rb.status(Status.CONFLICT);
			rb.entity("The resource you are posting already exists.");
			return false;
		} catch (LinkAlreadyExistsException e) {
			rb.status(Status.CONFLICT);
			rb.entity("Link conflict in entry. This shouldn't ever happen.");
			return false;
		} catch (URISyntaxException e) {
			rb.status(Status.CONFLICT);
			rb.entity("ID generates an invalid URI. This shouldn't ever happen.");
			return false;
		}
		try {
			feed = feedService.addEntryToFeed(entry, feed);
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

		rb.status(Status.CREATED);
		rb = rb.header("Location", entry.getIRI().toString());

		data.put("CreatedResource", entry);
		return true;

	}

	/**
	 * When this visitor encounters a PUT request, we can assume that the target
	 * is a resource. The body of the request will be used to overwrite the
	 * target resource.
	 * 
	 * Header Considerations: "Location" is set to the location the resource was
	 * created at.
	 * 
	 * DATA MAP CONTRACT: BEFORE: "IRI" is an absolute path to the resource to
	 * be updated "resource" holds a valid APPResource that will overwrite the
	 * target AFTER: "UpdatedResource" holds the actual updated representation
	 * of the resource.
	 * 
	 * @param put
	 *            The event type.
	 * @param rb
	 *            The passed response builder
	 * @param data
	 *            The passed data map
	 * @returns Boolean value indicating whether or not execution should
	 *          continue.
	 */
	@Override
	public boolean visit(Put put, ResponseBuilder rb, Map<String, Object> data) {
		AtomEntry entry = null;
		try {
			entry = new AtomEntry(EntryDocument.Factory.parse(put.getBody()));
		} catch (XmlException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		URI iri = put.getURIInfo().getAbsolutePath();

		// Current assumptions are: 1) iri is the collection?,
		APPResource feedResource;
		try {
			feedResource = resourceService.loadResource(iri);
		} catch (ResourceNotFoundException e) {
			rb.status(Status.NOT_FOUND);
			return false;
		}

		if (!ResourceType.FEED.equals(feedResource.getResourceType())) {
			// the IRI is not a feed
			rb.status(Status.NOT_FOUND);
			return false;
		}

		AtomFeed feed = (AtomFeed) feedResource;

		if (resourceService.resourceExists(iri)) {

		}
		try {
			feedService.addEntryToFeed(entry, feed);
		} catch (MismatchedCategoriesException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ResourceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidResourceTypeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		AtomFeed created = null;
		try {
			created = feedService.createFeed(feed, iri);
		} catch (ResourceAlreadyExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		rb.status(Status.CREATED);
		rb = rb.header("Location", created.getIRI()); // TODO FIX THIS

		data.put("CreatedResource", entry);
		return true;

	}

	/**
	 * When this visitor encounters a DELETE request, the resource at the given
	 * IRI will be deleted. There is no needed consideration at this point as to
	 * what the resource is.
	 * 
	 * 
	 * DATA MAP CONTRACT: BEFORE: "IRI" is an absolute path to the resource.
	 * AFTER: NONE
	 * 
	 * @param delete
	 *            The event type.
	 * @param rb
	 *            The passed response builder
	 * @param data
	 *            The passed data map
	 * @returns Boolean value indicating whether or not execution should
	 *          continue.
	 */
	@Override
	public boolean visit(Delete delete, ResponseBuilder rb, Map<String, Object> data) {
		try {
			resourceService.deleteResource(delete.getURIInfo().getAbsolutePath());
		} catch (ResourceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		rb = rb.status(Status.OK);
		return true;
	}

}
