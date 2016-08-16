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

// TODO: Auto-generated Javadoc
/**
 * The Class VisitorManager.
 */
public class VisitorManager {

	/** The visitors. */
	private List<RESTEventVisitor> visitors = new LinkedList<>();
	
	/**
	 * Adds the visitor.
	 *
	 * @param visitor the visitor
	 */
	public void addVisitor(RESTEventVisitor visitor)
	{
		visitors.add(visitor);
	}
	
	/**
	 * Adds the visitor.
	 *
	 * @param index the index
	 * @param visitor the visitor
	 */
	public void addVisitor(int index, RESTEventVisitor visitor)
	{
		visitors.add(index, visitor);
	}
	
	/**
	 * Execute.
	 *
	 * @param event the event
	 * @param data the data
	 * @return the response
	 */
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
