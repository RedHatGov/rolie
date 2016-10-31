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
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import gov.nist.rolie.polie.core.event.Delete;
import gov.nist.rolie.polie.core.event.Get;
import gov.nist.rolie.polie.core.event.Post;
import gov.nist.rolie.polie.core.event.Put;
import gov.nist.rolie.polie.core.event.RESTEvent;
import gov.nist.rolie.polie.core.visitors.CollectionRetrivalVisitor;
import gov.nist.rolie.polie.core.visitors.DebugVisitor;
import gov.nist.rolie.polie.core.visitors.RESTEventVisitor;
import gov.nist.rolie.polie.core.visitors.RequestValidatorVisitor;
import gov.nist.rolie.polie.core.visitors.ResponseBuilderVisitor;
import gov.nist.rolie.polie.core.visitors.UnimplementedVisitor;

/**
 * The Class IncomingCollectionEvents.
 * 
 * Handles all incoming HTTP requests on collections. In this implementation the URIs for collections all start
 * with collections/. The PathParam curi is the resource URI of the collection. 
 */
@Path("collections/{curi}")
public class IncomingCollectionEvents {
	
	//Visitors
	private static final RESTEventVisitor REQUEST_VALIDATOR_VISITOR = new RequestValidatorVisitor();
	

	// visitor managers
	private static final VisitorManager GET_COLLECTION_VM;
	
	static {
		DefaultVisitorManager obj = new VisitorManagerImpl();
		obj.addVisitor(visitor);
		GET_COLLECTION_VM = obj;
	}
	
	
	/**
	 * GET request handler on the collection URIs
	 *
	 * @param curi The URI of the collection, generated from the PathParam from the request
	 * @param headers The headers of the request
	 * @return The built HTTP response. Note that the returned response is automatically sent to the requesting agent
	 * 			right after completion of the method.
	 */
	@Produces({"text/plain","application/xml","application/atom+xml"})
	@GET
	public static Response get(@PathParam("curi") String uri, @Context HttpHeaders headers)
	{
		
		RESTEvent get = new Get(headers,uri);
		
		VisitorManager vm = new VisitorManager();
//		vm.addVisitor(REQUEST_VALIDATOR_VISITOR);
		vm.addVisitor(new CollectionRetrivalVisitor());
		//vm.addVisitor(new DebugVisitor());
		
		Map<String,Object> data = new HashMap<>();
		data.put("uri", uri);
		data.put("headers", headers.getRequestHeaders());
		
		return GET_COLLECTION_VM.execute(get,data);
		
	}

	/**
	 * POST request handler on the collection URIs
	 *
	 * @param curi The URI of the collection, generated from the PathParam from the request
	 * @param x JAVARS automatically generates this parameter with the body of the request
	 * @param headers The headers of the request
	 * @return The built HTTP response. Note that the returned response is automatically sent to the requesting agent
	 * 			right after completion of the method.
	 */
	@Consumes({"application/xml","application/atom+xml"})
	@Produces("application/xml")
	@POST
	public static Response post(@PathParam("curi") String uri, String body, @Context HttpHeaders headers)
	{
		
		RESTEvent post = new Post(headers,body,uri);
		VisitorManager vm = new VisitorManager();
		vm.addVisitor(new DebugVisitor());
		
		Map<String,Object> data = new HashMap<>();
		
		return vm.execute(post,data);
	}
	
	/**
	 * PUT request handler on the collection URIs
	 *
	 * @param curi The URI of the collection, generated from the PathParam from the request
	 * @param x JAVARS automatically generates this parameter with the body of the request
	 * @param headers The headers of the request
	 * @return The built HTTP response. Note that the returned response is automatically sent to the requesting agent
	 * 			right after completion of the method.
	 */
	@Consumes({"text/plain","application/xml"})
	@Produces("text/plain")
	@PUT
	public static Response put(@PathParam("curi") String uri, String x, @Context HttpHeaders headers)
	{
		RESTEvent put = new Put(headers,x,uri);
		VisitorManager vm = new VisitorManager();
//		vm.addVisitor(new RequestValidatorVisitor());
//		vm.addVisitor(new CollectionUpdateVisitor());
//		vm.addVisitor(new ResponseBuilderVisitor());
		vm.addVisitor(new UnimplementedVisitor());
		
		
		
		Map<String,Object> data = new HashMap<>();
		
		return vm.execute(put,data);
	}
	
	/**
	 * DELETE request handler on the collection URIs
	 *
	 * @param curi The URI of the collection, generated from the PathParam from the request
	 * @param x JAVARS automatically generates this parameter with the body of the request
	 * @param headers The headers of the request
	 * @return The built HTTP response. Note that the returned response is automatically sent to the requesting agent
	 * 			right after completion of the method.
	 */
	@Consumes("text/plain")
	@Produces("text/plain")
	@DELETE
	public static Response delete(@PathParam("curi") String uri, String x, @Context HttpHeaders headers)
	{
		RESTEvent delete = new Delete(headers,x,uri);
		VisitorManager vm = new VisitorManager();
		vm.addVisitor(new UnimplementedVisitor());
		
		Map<String,Object> data = new HashMap<>();
		
		return vm.execute(delete,data);
	}
	
}
