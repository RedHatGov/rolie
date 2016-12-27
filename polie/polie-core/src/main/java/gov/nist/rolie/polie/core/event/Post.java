package gov.nist.rolie.polie.core.event;

import java.util.Map;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.UriInfo;

import gov.nist.rolie.polie.core.visitors.RESTEventVisitor;

public class Post extends AbstractRESTEvent implements RESTEvent {
	
	private String body;
	
	public Post(HttpHeaders headers,UriInfo uriInfo,String body)
	{
		super(headers,uriInfo);
		this.body=body;
	}
	
	public String getBody()
	{
		return this.body;
	}
	
	@Override
	public boolean accept(RESTEventVisitor RESTEventVisitor, ResponseBuilder rb, Map<String, Object> data)
	{
		return RESTEventVisitor.visit(this, rb, data);
	}
}
