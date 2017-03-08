/*
 * 
 */
package gov.nist.rolie.polie.server.visitors;

import java.util.Map;

import javax.ws.rs.core.Response.ResponseBuilder;

import gov.nist.rolie.polie.server.event.Delete;
import gov.nist.rolie.polie.server.event.Get;
import gov.nist.rolie.polie.server.event.Post;
import gov.nist.rolie.polie.server.event.Put;

/**
 * The Interface RESTEventVisitor.
 * 
 * All visitors MUST implement this interface and MUST adhere to this contract:
 * 
 * 1.The visitor must only return false if there is an unrecoverable error during execution. In this
 * 		case the visitor must set the response builder with a reasonable error status and description.
 */
public interface RESTEventVisitor {
	public static final String RESOURCE_DATA_KEY = "resource";

	public boolean visit(Get get, ResponseBuilder rb, Map<String,Object> data);

	public boolean visit(Post post, ResponseBuilder rb, Map<String,Object> data);

	public boolean visit(Put put, ResponseBuilder rb, Map<String,Object> data);
	
	public boolean visit(Delete delete, ResponseBuilder rb, Map<String,Object> data);
}
