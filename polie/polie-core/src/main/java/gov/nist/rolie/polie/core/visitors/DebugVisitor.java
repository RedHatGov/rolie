/*
 * 
 */
package gov.nist.rolie.polie.core.visitors;

import java.util.Map;

import javax.ws.rs.core.Response.ResponseBuilder;

import gov.nist.rolie.polie.core.event.Delete;
import gov.nist.rolie.polie.core.event.Get;
import gov.nist.rolie.polie.core.event.Post;
import gov.nist.rolie.polie.core.event.Put;

// TODO: Auto-generated Javadoc
/**
 * The Class DebugVisitor.
 */
public class DebugVisitor implements RESTEventVisitor {

	/* (non-Javadoc)
	 * @see gov.nist.rolie.polie.core.visitors.RESTEventVisitor#visit(gov.nist.rolie.polie.core.event.Get, javax.ws.rs.core.Response.ResponseBuilder, java.util.Map)
	 */
	@Override
	public boolean visit(Get get, ResponseBuilder rb, Map<String, Object> data) {
		rb=rb.status(200);
		rb=rb.entity("This is the debug responder."
				+ "\nThis is a GET request."
				+ "\nData Dump: \n " + data.toString());
		return false;
	}

	/* (non-Javadoc)
	 * @see gov.nist.rolie.polie.core.visitors.RESTEventVisitor#visit(gov.nist.rolie.polie.core.event.Post, javax.ws.rs.core.Response.ResponseBuilder, java.util.Map)
	 */
	@Override
	public boolean visit(Post post, ResponseBuilder rb, Map<String, Object> data) {
		rb.status(200);
		rb.entity("This is the debug responder."
				+ "\nThis is a POST request."
				+ "\nData Dump: \n\n " + data.toString());
		return false;
	}

	/* (non-Javadoc)
	 * @see gov.nist.rolie.polie.core.visitors.RESTEventVisitor#visit(gov.nist.rolie.polie.core.event.Put, javax.ws.rs.core.Response.ResponseBuilder, java.util.Map)
	 */
	@Override
	public boolean visit(Put put, ResponseBuilder rb, Map<String, Object> data) {
		rb.status(200);
		rb.entity("This is the debug responder."
				+ "\nThis is a PUT request."
				+ "\nData Dump: \n " + data.toString());
		return false;
	}

	/* (non-Javadoc)
	 * @see gov.nist.rolie.polie.core.visitors.RESTEventVisitor#visit(gov.nist.rolie.polie.core.event.Delete, javax.ws.rs.core.Response.ResponseBuilder, java.util.Map)
	 */
	@Override
	public boolean visit(Delete delete, ResponseBuilder rb, Map<String, Object> data) {
		rb.status(200);
		rb.entity("This is the debug responder."
				+ "\nThis is a DELETE request."
				+ "\nData Dump: \n " + data.toString());
		return false; 
	}

}
