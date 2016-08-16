/*
 * 
 */
package gov.nist.rolie.polie.core.visitors;

import java.util.Map;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import gov.nist.rolie.polie.core.event.Delete;
import gov.nist.rolie.polie.core.event.Get;
import gov.nist.rolie.polie.core.event.Post;
import gov.nist.rolie.polie.core.event.Put;

// TODO: Auto-generated Javadoc
/**
 * The Interface RESTEventVisitor.
 */
public interface RESTEventVisitor {
	
	/**
	 * Visit.
	 *
	 * @param get the get
	 * @param rb the rb
	 * @param data the data
	 * @return true, if successful
	 */
	public boolean visit(Get get, ResponseBuilder rb, Map<String,Object> data);
	
	/**
	 * Visit.
	 *
	 * @param post the post
	 * @param rb the rb
	 * @param data the data
	 * @return true, if successful
	 */
	public boolean visit(Post post, ResponseBuilder rb, Map<String,Object> data);
	
	/**
	 * Visit.
	 *
	 * @param put the put
	 * @param rb the rb
	 * @param data the data
	 * @return true, if successful
	 */
	public boolean visit(Put put, ResponseBuilder rb, Map<String,Object> data);
	
	/**
	 * Visit.
	 *
	 * @param delete the delete
	 * @param rb the rb
	 * @param data the data
	 * @return true, if successful
	 */
	public boolean visit(Delete delete, ResponseBuilder rb, Map<String,Object> data);
}
