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
 * Handles incoming request validation. Should be placed first or very early in the execution list. Most of the request
 * validation is handled automatically by the server. This is for extra validation logic.
 */
public class RequestValidatorVisitor implements RESTEventVisitor {

	/** 
	 * Handles GET request validation.
	 * 
	 * DATA MAP CONTRACT: 
	 * BEFORE: 
	 * 		NONE
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
		return true;
	}

	/** 
	 * Handles POST request validation.
	 * 
	 * DATA MAP CONTRACT: 
	 * BEFORE: 
	 * 		NONE
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
		return true;
	}

	/** 
	 * Handles PUT request validation.
	 * 
	 * DATA MAP CONTRACT: 
	 * BEFORE: 
	 * 		NONE
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
		return true;
	}

	/** 
	 * Handles DELETE request validation.
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
