package gov.nist.rolie.polie.server.event;

import java.util.Map;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response.ResponseBuilder;

import gov.nist.rolie.polie.server.visitors.RESTEventVisitor;

import javax.ws.rs.core.UriInfo;

public class Delete extends AbstractRESTEvent implements RESTEvent {
	

	public Delete(HttpHeaders headers,UriInfo uriInfo)
	{
		super(headers,uriInfo);
	}
	
	@Override
	public boolean accept(RESTEventVisitor RESTEventVisitor, ResponseBuilder rb, Map<String, Object> data)
	{
		return RESTEventVisitor.visit(this, rb, data);
	}
}
