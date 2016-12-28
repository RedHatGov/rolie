package gov.nist.rolie.polie.core.visitors;

import java.net.URI;
import java.util.Map;

import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import gov.nist.rolie.polie.core.database.PersistenceMethod;
import gov.nist.rolie.polie.core.database.TextPersist;
import gov.nist.rolie.polie.core.event.Delete;
import gov.nist.rolie.polie.core.event.Get;
import gov.nist.rolie.polie.core.event.Post;
import gov.nist.rolie.polie.core.event.Put;
import gov.nist.rolie.polie.core.models.APPResource;

/**
 * Primary driver of resource requests in POLIE. Consolidated in one visitor because the request type implies
 * the valid resource types.
 * 
 * @author sab3
 *
 */
public class ResourceEventVisitor implements RESTEventVisitor {

	static PersistenceMethod database = new TextPersist();
	
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
		APPResource resource = database.loadResource((URI)data.get("IRI"));
		data.put("RetrivedResource", resource);
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
		APPResource resource = (APPResource)data.get("resource");
		APPResource createdResource = database.createResource(resource,(URI)data.get("IRI"));
		rb=rb.status(Status.CREATED);
		data.put("CreatedResourceLocationIRI", (URI)data.get("IRI")); //TODO FIX THIS
		rb=rb.header("Location", (URI)data.get("CreatedResourceLocationIRI"));
		data.put("CreatedResource",createdResource);
		return true;
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
		APPResource resource = (APPResource)data.get("resource");
		APPResource updatedResource = database.updateResource(resource,(URI)data.get("IRI"));
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
		database.deleteResource((URI)data.get("IRI"));
		rb=rb.status(Status.OK);
		return true;
	}

}
