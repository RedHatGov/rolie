package gov.nist.rolie.polie.core.event;

import javax.ws.rs.core.Response;

public interface RESTEvent {

	public Response accept(RESTEventVisitor RESTEventVisitor);
	
}
