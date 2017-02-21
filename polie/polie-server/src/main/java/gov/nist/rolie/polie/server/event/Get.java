package gov.nist.rolie.polie.server.event;

import java.util.Map;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.ResponseBuilder;

import gov.nist.rolie.polie.server.visitors.RESTEventVisitor;

/**
 * @author sab3
 *
 */
public class Get extends AbstractRESTEvent implements RESTEvent {
	
	public Get(HttpHeaders headers,UriInfo uriInfo)
	{
		super(headers,uriInfo);
	}
	
	@Override
	public boolean accept(RESTEventVisitor restEventVisitor, ResponseBuilder rb, Map<String, Object> data)
	{
		return restEventVisitor.visit(this, rb, data);
	}
	
}
