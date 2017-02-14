/*
 * 
 */
package gov.nist.rolie.polie.server.servlet;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import gov.nist.rolie.polie.server.event.RESTEvent;
import gov.nist.rolie.polie.server.visitors.RESTEventVisitor;


/**
 * The default implementation of the visitor manager. Provides basic capabilities.
 */
public class DefaultVisitorManager implements VisitorManager {
	private static final Logger log = LogManager.getLogger(DefaultVisitorManager.class);

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
	
	@Override
	public Response execute(RESTEvent event) {
		return execute(event, new HashMap<String, Object>());
	}

	//Is there is threading trouble, you'll probably fix it in this method.
	public Response execute(RESTEvent event, Map<String, Object> data)
	{
		//If something goes wrong, set the default Response to a server error response.
		// TODO: set to a resonable default status that doesn't need to be changed
		// TODO: use a call to the event to get the default status by request method
		ResponseBuilder rb = Response.status(Status.METHOD_NOT_ALLOWED);
		
		//Basic for loop to execute all visitors. If a visitor returns false, the loop is terminated
		//right away and the response is built as-is.
		for (RESTEventVisitor visitor : this.visitors) 
		{
//			log.debug("Processing visitor: {}", visitor.getClass().getName());
			boolean processNext = event.accept(visitor, rb, data);
			if (!processNext) 
			{
				break;
			}
		}
		
		//The response builder has incrementally gathered information, this constructs a single response
		//with that information and returns it.
		return rb.build();
	}

}
