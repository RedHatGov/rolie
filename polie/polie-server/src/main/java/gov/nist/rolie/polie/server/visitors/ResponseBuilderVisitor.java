package gov.nist.rolie.polie.server.visitors;

import java.net.URI;
import java.util.Map;

import javax.ws.rs.core.Response.ResponseBuilder;

import gov.nist.rolie.polie.atomLogic.modelServices.DefaultResourceService;
import gov.nist.rolie.polie.atomLogic.modelServices.ResourceService;
import gov.nist.rolie.polie.model.models.APPResource;
import gov.nist.rolie.polie.persistence.ResourceNotFoundException;
import gov.nist.rolie.polie.server.event.Delete;
import gov.nist.rolie.polie.server.event.Get;
import gov.nist.rolie.polie.server.event.Post;
import gov.nist.rolie.polie.server.event.Put;

/**
 * This visitor provides the common final steps of response preparation before the request is sent out.
 * Most of the work is done automatically by the server, but any extra logic is done here. This should
 * be a late visitor, or the last.
 * 
 * @author sab3
 *
 */

public class ResponseBuilderVisitor implements RESTEventVisitor {

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/** 
	 * When this visitor encounters a GET request, it gets the resource that has been found and places it
	 * in the body of the request.
	 * 
	 * DATA MAP CONTRACT: 
	 * BEFORE: 
	 * 		"RetrivedResource" is an APPResource
	 * AFTER:
	 * 		NONE
	 * 
	 * @param get The event type.
	 * @param rb The passed response builder
	 * @param data The passed data map
	 * @returns Boolean value indicating whether or not execution should continue.
	 */
	@Override
	public boolean visit(Get get, ResponseBuilder rb, Map<String, Object> data) {
//		APPResource resource = (APPResource)data.get(RESOURCE_DATA_KEY);
		URI iri = get.getURIInfo().getAbsolutePath();
		APPResource resource = null;
		ResourceService resourceService = new DefaultResourceService();
		try {
			resource = resourceService.retrieveResource(iri);
		} catch (ResourceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		rb.entity(resource.getXmlObject());
		return true;
	}
	/** 
	 * When this visitor encounters a post request, it gets the resource that has been cerated and places it
	 * in the body of the request.
	 * 
	 * DATA MAP CONTRACT: 
	 * BEFORE: 
	 * 		"CreatedResource" is an APPResource
	 * AFTER:
	 * 		NONE
	 * 
	 * 
	 * 
	 * @param post The event type.
	 * @param rb The passed response builder
	 * @param data The passed data map
	 * @returns Boolean value indicating whether or not execution should continue.
	 */
	@Override
	public boolean visit(Post post, ResponseBuilder rb, Map<String, Object> data) {
		APPResource resource = (APPResource)data.get("CreatedResource");
		rb.entity(resource.getXmlObject());
		return true;
	}
	
	/** 
	 * When this visitor encounters a PUT request, it gets the resource that has been updated and places it
	 * in the body of the request.
	 * 
	 * DATA MAP CONTRACT: 
	 * BEFORE: 
	 * 		"UpdatedResource" is an APPResource
	 * AFTER:
	 * 		NONE
	 * 
	 * @param put The event type.
	 * @param rb The passed response builder
	 * @param data The passed data map
	 * @returns Boolean value indicating whether or not execution should continue.
	 */
	@Override
	public boolean visit(Put put, ResponseBuilder rb, Map<String, Object> data) {
		rb.entity(data.get("ResponseBody"));
		return true;
	}

	/** 
	 * When this visitor encounters a DELETE request. It does nothing.
	 * 
	 * DATA MAP CONTRACT: 
	 * BEFORE: 
	 * 		NONE
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
		rb.entity(data.get("ResponseBody"));
		return true;
	}

}
