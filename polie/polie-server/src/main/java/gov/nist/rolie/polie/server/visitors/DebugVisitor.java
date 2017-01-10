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

// TODO: Auto-generated Javadoc
/**
 * The Class DebugVisitor.
 */
public class DebugVisitor implements RESTEventVisitor {

	/* (non-Javadoc)
	 * @see gov.nist.rolie.polie.server.visitors.RESTEventVisitor#visit(gov.nist.rolie.polie.server.event.Get, javax.ws.rs.core.Response.ResponseBuilder, java.util.Map)
	 */
	@Override
	public boolean visit(Get get, ResponseBuilder rb, Map<String, Object> data) {
		rb=rb.status(200);
		String debugString = "This is the debug responder."
				+ "\nThis is a GET request."
				+ "\nData dump follows:\n";
		if (data.isEmpty())
		{
			debugString+="PICNIC";
			rb=rb.entity(debugString);
			return false;
		}
		for (Map.Entry<String,Object> entry : data.entrySet())
		{
			debugString+="\n" + entry.getKey() + " : " + entry.getValue().toString();
		}
		rb=rb.entity(debugString);
		return false;
	}

	/* (non-Javadoc)
	 * @see gov.nist.rolie.polie.server.visitors.RESTEventVisitor#visit(gov.nist.rolie.polie.server.event.Post, javax.ws.rs.core.Response.ResponseBuilder, java.util.Map)
	 */
	@Override
	public boolean visit(Post post, ResponseBuilder rb, Map<String, Object> data) {
		rb=rb.status(200);
		String debugString = "This is the debug responder."
				+ "\nThis is a POST request."
				+ "\nData dump follows:\n";
		
		for (Map.Entry<String,Object> entry : data.entrySet())
		{
			debugString+="\n";
			debugString+=entry.getKey() + " : " + entry.getValue().toString();
		}
		rb=rb.entity(debugString);
		return false;
	}

	/* (non-Javadoc)
	 * @see gov.nist.rolie.polie.server.visitors.RESTEventVisitor#visit(gov.nist.rolie.polie.server.event.Put, javax.ws.rs.core.Response.ResponseBuilder, java.util.Map)
	 */
	@Override
	public boolean visit(Put put, ResponseBuilder rb, Map<String, Object> data) {
		rb=rb.status(200);
		String debugString = "This is the debug responder."
				+ "\nThis is a PUT request."
				+ "\nData dump follows:\n";
		
		for (Map.Entry<String,Object> entry : data.entrySet())
		{
			debugString+="\n" + entry.getKey() + " : " + entry.getValue().toString();
		}
		rb=rb.entity(debugString);
		return false;
	}

	/* (non-Javadoc)
	 * @see gov.nist.rolie.polie.server.visitors.RESTEventVisitor#visit(gov.nist.rolie.polie.server.event.Delete, javax.ws.rs.core.Response.ResponseBuilder, java.util.Map)
	 */
	@Override
	public boolean visit(Delete delete, ResponseBuilder rb, Map<String, Object> data) {
		rb=rb.status(200);
		String debugString = "This is the debug responder."
				+ "\nThis is a DELETE request."
				+ "\nData dump follows:\n";
		
		for (Map.Entry<String,Object> entry : data.entrySet())
		{
			debugString+="\n" + entry.getKey() + " : " + entry.getValue().toString();
		}
		rb=rb.entity(debugString);
		return false;
	}

}
