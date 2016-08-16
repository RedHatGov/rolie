package gov.nist.rolie.polie.core.visitors;

import java.util.Map;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import gov.nist.rolie.polie.core.event.Delete;
import gov.nist.rolie.polie.core.event.Get;
import gov.nist.rolie.polie.core.event.Post;
import gov.nist.rolie.polie.core.event.Put;

public interface RESTEventVisitor {
	public boolean visit(Get get, ResponseBuilder rb, Map<String,Object> data);
	public boolean visit(Post post, ResponseBuilder rb, Map<String,Object> data);
	public boolean visit(Put put, ResponseBuilder rb, Map<String,Object> data);
	public boolean visit(Delete delete, ResponseBuilder rb, Map<String,Object> data);
}
