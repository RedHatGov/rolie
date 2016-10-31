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

	/** The list of visitors for this manager. Note that the order matters.*/
	private List<RESTEventVisitor> visitors = new LinkedList<>();
	
	/**
	 * Adds a visitor to the end of the visitor list
	 *
	 * @param visitor Visitor object to add to the list
	 */
	public void addVisitor(RESTEventVisitor visitor)
	{
		visitors.add(visitor);
	}
	
	/**
	 * Adds a visitor to the visitor list at the specified index
	 *
	 * @param visitor Visitor object to add to the list
	 * @param index integer index at which the visitor is added.
	 */
	public void addVisitor(int index, RESTEventVisitor visitor)
	{
		visitors.add(index, visitor);
	}
	
	/**
	 * Visitor Execution loop. All visitors that are currently in the visitor list are executed in order.
	 *  data and rb are passed by reference and used to pass information from visitor to visitor and to 
	 *  incrementally build the response, respectively. A visitor may cease the execution chain at any time
	 *  if an error occurs. Later visitors may overwrite response characteristics.
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
