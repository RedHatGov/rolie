package gov.nist.rolie.polie.core.servlet;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Response;
import gov.nist.rolie.polie.core.event.RESTEvent;
import gov.nist.rolie.polie.core.visitors.RESTEventVisitor;

public interface VisitorManager {

	
		public List<RESTEventVisitor> getVisitors();
		
		/**
		 * Adds a visitor to the end of the visitor list
		 *
		 * @param visitor Visitor object to add to the list
		 */
		public void addVisitor(RESTEventVisitor visitor);
		
		/**
		 * Adds a visitor to the visitor list at the specified index
		 *
		 * @param visitor Visitor object to add to the list
		 * @param index integer index at which the visitor is added.
		 */
		public void addVisitor(int index, RESTEventVisitor visitor);
		
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
		public Response execute(RESTEvent event, Map<String, Object> data);

	}
