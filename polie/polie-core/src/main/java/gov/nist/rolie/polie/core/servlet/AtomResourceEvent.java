/*
 * 
 */
package gov.nist.rolie.polie.core.servlet;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import gov.nist.rolie.polie.core.event.Delete;
import gov.nist.rolie.polie.core.event.Get;
import gov.nist.rolie.polie.core.event.Post;
import gov.nist.rolie.polie.core.event.Put;
import gov.nist.rolie.polie.core.event.RESTEvent;

import gov.nist.rolie.polie.core.visitors.RESTEventVisitor;
import gov.nist.rolie.polie.core.visitors.ROLIEValidationVisitor;
import gov.nist.rolie.polie.core.visitors.RequestValidatorVisitor;
import gov.nist.rolie.polie.core.visitors.ResourceBuilderVisitor;
import gov.nist.rolie.polie.core.visitors.ResourceEventVisitor;
import gov.nist.rolie.polie.core.visitors.ResponseBuilderVisitor;

//import gov.nist.rolie.polie.core.visitors.DebugVisitor;
/**
 * One of three entry point Classes for the webapp. This class handles ALL resource requests 
 * that are not category or service documents. The Path annotation is a regex expression that
 * should match all valid paths. 
 * 
 * In this case the path is everything after the server root "{server}/polie-core/{path}"
 * 
 * 
 * @author sab3
 *
 */
@Path("{path: .*}")
public class AtomResourceEvent {
	
	//Visitors are declared here by their intended purpose. If a new visitor is written it can be swapped here
	//to apply to all requests.
	
	/**
	 * Provides debug functions. DISABLED.
	 */
	//private static final RESTEventVisitor DEBUG_VISITOR = new DebugVisitor();
	
	/**
	*Validates the request itself. Most of this is handled by the server,
	*but extra logic may be included as needed.
	*/
	private static final RESTEventVisitor REQUEST_VALIDATOR_VISITOR = new RequestValidatorVisitor();
	
	/**
	 * Primary visitor for resource requests. Drives required Atom transformations, and starts 
	 * persistence procedures.
	 */
	private static final RESTEventVisitor RESOURCE_EVENT_VISITOR = new ResourceEventVisitor();
	
	/**
	 * Validates ROLIE XML content in the body of the request.
	 */
	private static final RESTEventVisitor ROLIE_VALIDATION_VISITOR = new ROLIEValidationVisitor();
	
	/**
	 * Transforms incoming ROLIE XML content into the internal model format. This intermediate format is
	 * consumed by the persistence code.
	 */
	private static final RESTEventVisitor RESOURCE_BUILDER_VISITOR = new ResourceBuilderVisitor();

	/**
	 * Handles the final steps of response construction, including header fields
	 */
	private static final RESTEventVisitor RESPONSE_BUILDER_VISITOR = new ResponseBuilderVisitor();


	/**The visitor manager is declared here. If a new visitor manager is written is can be swapped out here
	*To apply to all requests.
	*/
	private static final VisitorManager vm;	
	static {
		vm = new DefaultVisitorManager();
	}
	
	/**
	 * Provides a map for storing data to be transfered between visitors. There are no requirements
	 * or static definitions for keys and values. Each visitor should document what values it uses and which
	 * it places in the map.
	 * 
	 * As of this version, here are some used keys:
	 * 
	 * "IRI" - a string of the absolute IRI.
	 * "path" - a string of the relative IRI.
	 * "headers" - the map of headers from the request.
	 * "body" - the body content from the request.
	 * "RetrievedResource" - a resource loaded from persistence
	 * "CreatedResource" - representation of a resource in persistence after creation
	 * "UpdatedResource" - representation of a resource in persistence after update
	 * 
	 * These are examples and pose no requirements or limitations, but investigate before
	 * overwritting them.
	 * 
	 */
	private static Map<String,Object> data;
	

	/**
	 * Code that executes when the server receives a GET request on anything other than rolie/servicedocument and
	 * rolie/categorydocument.
	 * 
	 * @param headers Automatically populated with a map of all headers.
	 * @param uriInfo Automatically populated with a variety of URI data fields
	 * @return Returns the completed Response that is passed off to the server to be sent back to the requester.
	 * 			At this point, the response is completed and is handled all by the webapp.
	 */
	@Produces({"application/atom+xml"})
	@GET
	public static Response get(@Context HttpHeaders headers, @Context UriInfo uriInfo)
	{
		//Generates a new Get event. This only servers to identify the event,
		//provides no real functionality. 
		RESTEvent get = new Get(headers,uriInfo);
		
		//Adds visitors in order to the execution list. FIFO order.
		vm.addVisitor(REQUEST_VALIDATOR_VISITOR); 
		vm.addVisitor(RESOURCE_EVENT_VISITOR);
		vm.addVisitor(RESPONSE_BUILDER_VISITOR);
		
		//Creates and populates the data package for this request. This data map will
		//be passed from visitor to visitor and relies on contracts (i.e. there are no base requirements
		// imposed on this map) to define keys and values. 
		data = new HashMap<>();
		data.put("path", uriInfo.getPath());
		data.put("IRI", uriInfo.getAbsolutePath());
		data.put("headers", headers.getRequestHeaders());
		
		//Starts the execution chain, returns a built response
		return vm.execute(get,data);
	}


	/**
	 * Code that executes when the server receives a POST request on anything other than rolie/servicedocument and
	 * rolie/categorydocument.
	 * 
	 * @param headers Automatically populated with a map of all headers.
	 * @param uriInfo Automatically populated with a variety of URI data fields
	 * @param body Text from the content of the request. This is automatically populated.
	 * @return Returns the completed Response that is passed off to the server to be sent back to the requester.
	 * 			At this point, the response is completed and is handled all by the webapp.
	 */
	@Consumes({"application/xml","application/atom+xml"})
	@Produces({"application/atom+xml"})
	@POST
	public static Response post(@Context HttpHeaders headers, @Context UriInfo uriInfo, String body)
	{
		
		RESTEvent post = new Post(headers,uriInfo,body);

		vm.addVisitor(REQUEST_VALIDATOR_VISITOR);
		vm.addVisitor(ROLIE_VALIDATION_VISITOR);
		vm.addVisitor(RESOURCE_BUILDER_VISITOR);
		vm.addVisitor(RESOURCE_EVENT_VISITOR);
		vm.addVisitor(RESPONSE_BUILDER_VISITOR);
		
		data = new HashMap<>();
		data.put("path", uriInfo.getPath());
		data.put("IRI", uriInfo.getAbsolutePath());
		data.put("headers", headers.getRequestHeaders());
		data.put("body", body);
		
		return vm.execute(post,data);
	}
	
	/**
	 * Code that executes when the server receives a PUT request on anything other than rolie/servicedocument and
	 * rolie/categorydocument.
	 * 
	 * @param headers Automatically populated with a map of all headers.
	 * @param uriInfo Automatically populated with a variety of URI data fields
	 * @param body Text from the content of the request. This is automatically populated.
	 * @return Returns the completed Response that is passed off to the server to be sent back to the requester.
	 * 			At this point, the response is completed and is handled all by the webapp.
	 */
	@Consumes({"application/xml","application/atom+xml"})
	@Produces({"application/atom+xml"})
	@PUT
	public static Response put( @Context HttpHeaders headers, @Context UriInfo uriInfo, String body)
	{
		
		RESTEvent put = new Put(headers,uriInfo,body);

		vm.addVisitor(REQUEST_VALIDATOR_VISITOR);
		vm.addVisitor(RESOURCE_EVENT_VISITOR);
		vm.addVisitor(RESPONSE_BUILDER_VISITOR);
		
		data = new HashMap<>();
		data.put("path", uriInfo.getPath());
		data.put("IRI", uriInfo.getAbsolutePath());
		data.put("headers", headers.getRequestHeaders());
		
		return vm.execute(put,data);
	}

	/**
	 * Code that executes when the server receives a DELETE request on anything other than rolie/servicedocument and
	 * rolie/categorydocument.
	 * 
	 * @param headers Automatically populated with a map of all headers.
	 * @param uriInfo Automatically populated with a variety of URI data fields
	 * @return Returns the completed Response that is passed off to the server to be sent back to the requester.
	 * 			At this point, the response is completed and is handled all by the webapp.
	 */
	@Produces("text/plain")
	@DELETE
	public static Response delete(@Context HttpHeaders headers, @Context UriInfo uriInfo)
	{
		RESTEvent delete = new Delete(headers,uriInfo);

		vm.addVisitor(REQUEST_VALIDATOR_VISITOR);
		vm.addVisitor(RESOURCE_EVENT_VISITOR);
		vm.addVisitor(RESPONSE_BUILDER_VISITOR);
		
		data = new HashMap<>();
		data.put("path", uriInfo.getPath());
		data.put("IRI", uriInfo.getAbsolutePath());
		data.put("headers", headers.getRequestHeaders());
		
		return vm.execute(delete,data);
	}
	
}
