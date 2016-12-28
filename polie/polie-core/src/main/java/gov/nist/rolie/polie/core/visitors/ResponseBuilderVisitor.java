package gov.nist.rolie.polie.core.visitors;

import java.util.Map;

import javax.ws.rs.core.Response.ResponseBuilder;

import gov.nist.rolie.polie.core.event.Delete;
import gov.nist.rolie.polie.core.event.Get;
import gov.nist.rolie.polie.core.event.Post;
import gov.nist.rolie.polie.core.event.Put;

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
		rb.entity(data.get("RetrivedResource"));
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
	 * @param post The event type.
	 * @param rb The passed response builder
	 * @param data The passed data map
	 * @returns Boolean value indicating whether or not execution should continue.
	 */
	@Override
	public boolean visit(Post post, ResponseBuilder rb, Map<String, Object> data) {
		rb.entity(data.get("CreatedResource").toString());
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
		rb.entity(data.get("UpdatedResource"));
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
		return true;
	}

}
