package gov.nist.rolie.polie.server.visitors;

import java.net.URI;
import java.util.Map;

import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import org.glassfish.jersey.message.internal.Statuses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import gov.nist.rolie.polie.atomLogic.modelServices.FeedService;
import gov.nist.rolie.polie.atomLogic.modelServices.ResourceService;
import gov.nist.rolie.polie.model.ResourceType;
import gov.nist.rolie.polie.model.models.APPResource;
import gov.nist.rolie.polie.model.models.AtomEntry;
import gov.nist.rolie.polie.model.models.AtomFeed;
import gov.nist.rolie.polie.persistence.ResourceNotFoundException;
import gov.nist.rolie.polie.server.event.Delete;
import gov.nist.rolie.polie.server.event.Get;
import gov.nist.rolie.polie.server.event.Post;
import gov.nist.rolie.polie.server.event.Put;

/**
 * Primary driver of resource requests in POLIE. Consolidated in one visitor because the request type implies
 * the valid resource types.
 * 
 * @author sab3
 *
 */
@Component
public class ResourceEventVisitor implements RESTEventVisitor { //TODO: 

	@Autowired
	ResourceService resourceService;

	@Autowired
	FeedService feedService;
	
	/** 
	 * When this visitor encounters a get request, the resource at the given IRI can be loaded.
	 * There is no needed consideration at this point as to what the resource is.
	 * 
	 * It then places the Retrieved resource in the data map and returns.
	 * 
	 * DATA MAP CONTRACT: 
	 * BEFORE: 
	 * 		"IRI" is an absolute path to the resource.
	 * AFTER:
	 * 		"resource" holds the APPResource at the IRI.
	 * 
	 * @param get The event type.
	 * @param rb The passed response builder
	 * @param data The passed data map
	 * @returns Boolean value indicating whether or not execution should continue.
	 */
	@Override
	public boolean visit(Get get, ResponseBuilder rb, Map<String, Object> data) {

		URI iri = get.getURIInfo().getAbsolutePath();
		APPResource resource;
		try {
			resource = resourceService.retrieveResource(iri);
		} catch (ResourceNotFoundException e) {
			rb.status(Status.NOT_FOUND);
			return false;
		}
		
		data.put(RESOURCE_DATA_KEY, resource);
		rb.status(Status.OK);
		return true;
	}
	
	/** 
	 * When this visitor encounters a POST request, we can assume that the target is a collection.
	 * The body of the request needs to be created and added to the Collection as a member resource.
	 * 
	 * Header Considerations:
	 * 	"Location" is set to the location the resource was created at.
	 * 
	 * DATA MAP CONTRACT: 
	 * BEFORE: 
	 * 		"IRI" is an absolute path to the collection that the resource will be under.
	 * 		"resource" holds a valid APPResource to be posted to a collection.
	 * AFTER:
	 * 		"CreatedResourceLocationIRI" holds the actual location the resource was created at. 
	 * 				NOTE: This MUST be set by the createResource() method.
	 * 		"CreatedResource" holds the actual created representation of the resource.
	 * 
	 * @param post The event type.
	 * @param rb The passed response builder
	 * @param data The passed data map
	 * @returns Boolean value indicating whether or not execution should continue.
	 */
	@Override
	public boolean visit(Post post, ResponseBuilder rb, Map<String, Object> data) {
		
		AtomEntry entry=(AtomEntry)data.get("resource");
		
		URI iri = post.getURIInfo().getAbsolutePath();
		
		// Current assumptions are: 1) iri is the collection?,  
		APPResource resource;
		try {
			resource = resourceService.retrieveResource(iri);
		} catch (ResourceNotFoundException e) {
			rb.status(Status.NOT_FOUND);
			return false;
		}

		if (!ResourceType.FEED.equals(resource.getResourceType())) {
			// the IRI is not a feed
			rb.status(Statuses.from(Status.NOT_ACCEPTABLE, "IRI is not a valid feed"));
			return false;
		}

		AtomFeed feed = (AtomFeed)resource;
		
		feedService.addEntryToFeed(entry, feed);
		AtomFeed created = feedService.saveFeed(feed);
		
		rb.status(Status.CREATED);
		rb=rb.header("Location", created.getIRI()); //TODO FIX THIS
		
		data.put("CreatedResource",entry);
		return true;
//		APPResource resource = (APPResource)data.get("resource");
//		APPCollection collection = ae.getCollection((URI)data.get("IRI"));
//		
//		APPResource createdResource = ae.postEntryToCollection((AtomEntry)resource, collection);
//		
//		rb=rb.status(Status.CREATED);
//		data.put("CreatedResourceLocationIRI", (URI)data.get("IRI")); //TODO FIX THIS
//		rb=rb.header("Location", (URI)data.get("CreatedResourceLocationIRI"));
//		data.put("CreatedResource",createdResource);
//		return true;
	}
	
	
	
	/** 
	 * When this visitor encounters a PUT request, we can assume that the target is a resource.
	 * The body of the request will be used to overwrite the target resource.
	 * 
	 * Header Considerations:
	 * 	"Location" is set to the location the resource was created at.
	 * 
	 * DATA MAP CONTRACT: 
	 * BEFORE: 
	 * 		"IRI" is an absolute path to the resource to be updated
	 * 		"resource" holds a valid APPResource that will overwrite the target
	 * AFTER:
	 * 		"UpdatedResource" holds the actual updated representation of the resource.
	 * 
	 * @param put The event type.
	 * @param rb The passed response builder
	 * @param data The passed data map
	 * @returns Boolean value indicating whether or not execution should continue.
	 */
	@Override
	public boolean visit(Put put, ResponseBuilder rb, Map<String, Object> data) {
		URI iri = put.getURIInfo().getAbsolutePath();
		APPResource resource = (APPResource)data.get("resource");
		APPResource updatedResource = null;
//		database.updateResource(resource,iri);
		rb=rb.status(Status.OK);
		data.put("updatedResource",updatedResource);
		return true;
	}

	/** 
	 * When this visitor encounters a DELETE request, the resource at the given IRI will be deleted.
	 * There is no needed consideration at this point as to what the resource is.
	 * 
	 * 
	 * DATA MAP CONTRACT: 
	 * BEFORE: 
	 * 		"IRI" is an absolute path to the resource.
	 * AFTER:
	 * 		NONE
	 * 
	 * @param delete The event type.
	 * @param rb The passed response builder
	 * @param data The passed data map
	 * @returns Boolean value indicating whether or not execution should continue.
	 */
	@Override
	public boolean visit(Delete delete, ResponseBuilder rb, Map<String, Object> data) {
		//database.deleteResource((URI)data.get("IRI"));
		rb=rb.status(Status.OK);
		return true;
	}

}
