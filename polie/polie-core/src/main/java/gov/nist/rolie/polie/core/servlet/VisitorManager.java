package gov.nist.rolie.polie.core.servlet;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import gov.nist.rolie.polie.core.event.RESTEvent;
import gov.nist.rolie.polie.core.visitors.RESTEventVisitor;

public class VisitorManager {

	private List<RESTEventVisitor> visitors = new LinkedList<>();
	
	public void addVisitor(RESTEventVisitor visitor)
	{
		visitors.add(visitor);
	}
	
	public void addVisitor(int index, RESTEventVisitor visitor)
	{
		visitors.add(index, visitor);
	}
	
	public Response execute(RESTEvent event, Map<String, Object> data)
	{
		ResponseBuilder rb = Response.status(Status.SERVICE_UNAVAILABLE);
		for (RESTEventVisitor visitor : this.visitors) 
		{
			boolean processNext = event.accept(visitor, rb, data);
			if (!processNext) 
			{
				break;
			}
		}
		return rb.build();
	}

}
