package gov.nist.rolie.polie.core.event;

import java.util.Map;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.UriInfo;

import gov.nist.rolie.polie.core.visitors.RESTEventVisitor;

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
