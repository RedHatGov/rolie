/*
 * 
 */
package gov.nist.rolie.polie.core.servlet;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import gov.nist.rolie.polie.core.event.RESTEvent;
import gov.nist.rolie.polie.core.visitors.RESTEventVisitor;


/**
 * The default implementation of the visitor manager. Provides basic capabilities.
 */
public class DefaultVisitorManager implements VisitorManager{

	private List<RESTEventVisitor> visitors = new LinkedList<>();

	public void addVisitor(RESTEventVisitor visitor)
	{
		visitors.add(visitor);
	}
	
	public void addVisitor(int index, RESTEventVisitor visitor)
	{
		visitors.add(index, visitor);
	}
	
	public List<RESTEventVisitor> getVisitors() {
		return visitors;
	}
	
	public void clearVisitors()
	{
		visitors.clear();
	}
	
	public Response execute(RESTEvent event, Map<String, Object> data)
	{
		//If something goes wrong, set the default Response to a service unavailable response.
		ResponseBuilder rb = Response.status(Status.SERVICE_UNAVAILABLE);
		
		//Basic for loop to execute all visitors. If a visitor returns false, the loop is terminated
		//right away and the response is built as-is.
		for (RESTEventVisitor visitor : this.visitors) 
		{
			boolean processNext = event.accept(visitor, rb, data);
			if (!processNext) 
			{
				break;
			}
		}
		
		//The response builder has incrementally gathered information, this constructs a single response
		//with that information and returns it.
		clearVisitors();
		data.clear();
		data=null;
		return rb.build();
	}

}
