package gov.nist.rolie.polie.core.event;

import java.util.Map;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.ResponseBuilder;

import gov.nist.rolie.polie.core.visitors.RESTEventVisitor;

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
	public boolean accept(RESTEventVisitor RESTEventVisitor, ResponseBuilder rb, Map<String, Object> data)
	{
		return RESTEventVisitor.visit(this, rb, data);
	}
	
}
