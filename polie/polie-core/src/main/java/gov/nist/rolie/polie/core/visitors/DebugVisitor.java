package gov.nist.rolie.polie.core.visitors;

import java.util.Map;

import javax.ws.rs.core.Response.ResponseBuilder;

import gov.nist.rolie.polie.core.event.Delete;
import gov.nist.rolie.polie.core.event.Get;
import gov.nist.rolie.polie.core.event.Post;
import gov.nist.rolie.polie.core.event.Put;

public class DebugVisitor implements RESTEventVisitor {

	@Override
	public boolean visit(Get get, ResponseBuilder rb, Map<String, Object> data) {
		rb=rb.status(200);
		rb=rb.entity("This is the debug responder."
				+ "\nThis is a GET request."
				+ "\nData Dump: \n " + data.toString());
		return false;
	}

	@Override
	public boolean visit(Post post, ResponseBuilder rb, Map<String, Object> data) {
		rb.status(200);
		rb.entity("This is the debug responder."
				+ "\nThis is a POST request."
				+ "\nData Dump: \n\n " + data.toString());
		return false;
	}

	@Override
	public boolean visit(Put put, ResponseBuilder rb, Map<String, Object> data) {
		rb.status(200);
		rb.entity("This is the debug responder."
				+ "\nThis is a PUT request."
				+ "\nData Dump: \n " + data.toString());
		return false;
	}

	@Override
	public boolean visit(Delete delete, ResponseBuilder rb, Map<String, Object> data) {
		rb.status(200);
		rb.entity("This is the debug responder."
				+ "\nThis is a DELETE request."
				+ "\nData Dump: \n " + data.toString());
		return false; 
	}

}
