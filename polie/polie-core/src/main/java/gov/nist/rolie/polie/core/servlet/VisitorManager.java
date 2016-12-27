package gov.nist.rolie.polie.core.servlet;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Response;
import gov.nist.rolie.polie.core.event.RESTEvent;
import gov.nist.rolie.polie.core.visitors.RESTEventVisitor;

/**
 * Interface for the visitor manager. Implementations should take note that this object will
 * be created once per request, and execute will be run once per request. Visitor manager provides core
 * functionality and the webapp will not function if there is a bug here.
 * 
 * @author sab3
 *
 */
public interface VisitorManager {

	/** 
	 * A list of visitors to be executed. The order of this list
	 * is maintained and is FIFO
	 */
		public List<RESTEventVisitor> getVisitors();
		
		/**
		 * Adds a visitor to the end of the visitor list
		 *
		 * @param visitor Visitor object to add to the list
		 */
		public void addVisitor(RESTEventVisitor visitor);
		
		/**
		 * Adds a visitor to the visitor list at the specified index memes
		 *
		 * @param visitor Visitor object to add to the list
		 * @param index integer index at which the visitor is added.
		 */
		public void addVisitor(int index, RESTEventVisitor visitor);
		
		/**
		 * Visitor Execution loop. All visitors that are currently in the visitor list are executed in order.
		 *  The data map and the response builder are passed by reference and used to pass information from visitor to visitor and to 
		 *  incrementally build the response, respectively. A visitor may cease the execution chain at any time
		 *  if an error occurs. Later visitors may overwrite response characteristics.
		 *  
		 *  This means that if a visitor encounters a non-recoverable error, it will most likely overwrite the
		 *  work of all previous visitors, and prevent any further visitors from executing.
		 *
		 * @param event The event that the execution chain is tied to (i.e. GET,PUT,POST, or DELETE)
		 * @param data Data map for passing data between visitors
		 * @return The built response from the response builder.
		 */
		public Response execute(RESTEvent event, Map<String, Object> data);

	}
