/*
 * 
 */
package gov.nist.rolie.polie.core.visitors;

import java.util.Map;

import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import gov.nist.rolie.polie.core.event.Delete;
import gov.nist.rolie.polie.core.event.Get;
import gov.nist.rolie.polie.core.event.Post;
import gov.nist.rolie.polie.core.event.Put;

// TODO: Auto-generated Javadoc
/**
 * The Class UnimplementedVisitor.
 */
public class UnimplementedVisitor implements RESTEventVisitor {

	/* (non-Javadoc)
	 * @see gov.nist.rolie.polie.core.visitors.RESTEventVisitor#visit(gov.nist.rolie.polie.core.event.Get, javax.ws.rs.core.Response.ResponseBuilder, java.util.Map)
	 */
	@Override
	public boolean visit(Get get, ResponseBuilder rb, Map<String, Object> data) {
		rb=rb.status(Status.NOT_IMPLEMENTED);
		rb=rb.entity("This request is Unimplemented.");
		return false;
	}

	/* (non-Javadoc)
	 * @see gov.nist.rolie.polie.core.visitors.RESTEventVisitor#visit(gov.nist.rolie.polie.core.event.Post, javax.ws.rs.core.Response.ResponseBuilder, java.util.Map)
	 */
	@Override
	public boolean visit(Post post, ResponseBuilder rb, Map<String, Object> data) {
		rb=rb.status(Status.NOT_IMPLEMENTED);
		rb=rb.entity("This request is Unimplemented.");
		return false;
	}

	/* (non-Javadoc)
	 * @see gov.nist.rolie.polie.core.visitors.RESTEventVisitor#visit(gov.nist.rolie.polie.core.event.Put, javax.ws.rs.core.Response.ResponseBuilder, java.util.Map)
	 */
	@Override
	public boolean visit(Put put, ResponseBuilder rb, Map<String, Object> data) {
		rb=rb.status(Status.NOT_IMPLEMENTED);
		rb=rb.entity("This request is Unimplemented.");
		return false;
	}

	/* (non-Javadoc)
	 * @see gov.nist.rolie.polie.core.visitors.RESTEventVisitor#visit(gov.nist.rolie.polie.core.event.Delete, javax.ws.rs.core.Response.ResponseBuilder, java.util.Map)
	 */
	@Override
	public boolean visit(Delete delete, ResponseBuilder rb, Map<String, Object> data) {
		rb=rb.status(Status.NOT_IMPLEMENTED);
		rb=rb.entity("This request is Unimplemented.");
		return false;
	}

}
