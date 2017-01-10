/*
 * 
 */
package gov.nist.rolie.polie.server.visitors;

import java.util.Map;

import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import gov.nist.rolie.polie.server.event.Delete;
import gov.nist.rolie.polie.server.event.Get;
import gov.nist.rolie.polie.server.event.Post;
import gov.nist.rolie.polie.server.event.Put;

// TODO: Auto-generated Javadoc
/**
 * The Class UnimplementedVisitor.
 */
public class UnimplementedVisitor implements RESTEventVisitor {

	/* (non-Javadoc)
	 * @see gov.nist.rolie.polie.server.visitors.RESTEventVisitor#visit(gov.nist.rolie.polie.server.event.Get, javax.ws.rs.core.Response.ResponseBuilder, java.util.Map)
	 */
	@Override
	public boolean visit(Get get, ResponseBuilder rb, Map<String, Object> data) {
		rb=rb.status(Status.NOT_IMPLEMENTED);
		rb=rb.entity("This request is Unimplemented.");
		return false;
	}

	/* (non-Javadoc)
	 * @see gov.nist.rolie.polie.server.visitors.RESTEventVisitor#visit(gov.nist.rolie.polie.server.event.Post, javax.ws.rs.core.Response.ResponseBuilder, java.util.Map)
	 */
	@Override
	public boolean visit(Post post, ResponseBuilder rb, Map<String, Object> data) {
		rb=rb.status(Status.NOT_IMPLEMENTED);
		rb=rb.entity("This request is Unimplemented.");
		return false;
	}

	/* (non-Javadoc)
	 * @see gov.nist.rolie.polie.server.visitors.RESTEventVisitor#visit(gov.nist.rolie.polie.server.event.Put, javax.ws.rs.core.Response.ResponseBuilder, java.util.Map)
	 */
	@Override
	public boolean visit(Put put, ResponseBuilder rb, Map<String, Object> data) {
		rb=rb.status(Status.NOT_IMPLEMENTED);
		rb=rb.entity("This request is Unimplemented.");
		return false;
	}

	/* (non-Javadoc)
	 * @see gov.nist.rolie.polie.server.visitors.RESTEventVisitor#visit(gov.nist.rolie.polie.server.event.Delete, javax.ws.rs.core.Response.ResponseBuilder, java.util.Map)
	 */
	@Override
	public boolean visit(Delete delete, ResponseBuilder rb, Map<String, Object> data) {
		rb=rb.status(Status.NOT_IMPLEMENTED);
		rb=rb.entity("This request is Unimplemented.");
		return false;
	}

}
