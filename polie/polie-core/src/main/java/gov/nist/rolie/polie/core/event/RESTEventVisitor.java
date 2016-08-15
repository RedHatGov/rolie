package gov.nist.rolie.polie.core.event;

import javax.ws.rs.core.Response;

public interface RESTEventVisitor {
	public Response visit(Get get);
	public Response visit(Post post);
	public Response visit(Put put);
	public Response visit(Delete delete);
}
