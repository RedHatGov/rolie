package gov.nist.rolie.polie.server.event;

import java.util.Map;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response.ResponseBuilder;

import gov.nist.rolie.polie.server.visitors.RESTEventVisitor;

import javax.ws.rs.core.UriInfo;

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
