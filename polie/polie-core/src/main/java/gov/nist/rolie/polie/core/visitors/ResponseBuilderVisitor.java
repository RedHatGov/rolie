package gov.nist.rolie.polie.core.visitors;

import java.util.Map;

import javax.ws.rs.core.Response.ResponseBuilder;

import gov.nist.rolie.polie.core.event.Delete;
import gov.nist.rolie.polie.core.event.Get;
import gov.nist.rolie.polie.core.event.Post;
import gov.nist.rolie.polie.core.event.Put;

public class ResponseBuilderVisitor implements RESTEventVisitor {

	@Override
	public boolean visit(Get get, ResponseBuilder rb, Map<String, Object> data) {
		rb.entity(data.get("RetrivedResource"));
		return true;
	}

	@Override
	public boolean visit(Post post, ResponseBuilder rb, Map<String, Object> data) {
		rb.entity(data.get("CreatedResource").toString());
		return true;
	}

	@Override
	public boolean visit(Put put, ResponseBuilder rb, Map<String, Object> data) {
		rb.entity(data.get("UpdatedResource"));
		return true;
	}

	@Override
	public boolean visit(Delete delete, ResponseBuilder rb, Map<String, Object> data) {
		return true;
	}

}
