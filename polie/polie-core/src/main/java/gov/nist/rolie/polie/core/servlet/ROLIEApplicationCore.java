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
import javax.ws.rs.core.UriInfo;

import gov.nist.rolie.polie.core.event.Delete;
import gov.nist.rolie.polie.core.event.Get;
import gov.nist.rolie.polie.core.event.Post;
import gov.nist.rolie.polie.core.event.Put;
import gov.nist.rolie.polie.core.event.RESTEvent;
import gov.nist.rolie.polie.core.visitors.CollectionCreationVisitor;
import gov.nist.rolie.polie.core.visitors.CollectionDeletionVisitor;
import gov.nist.rolie.polie.core.visitors.CollectionRetrivalVisitor;
import gov.nist.rolie.polie.core.visitors.CollectionUpdateVisitor;
import gov.nist.rolie.polie.core.visitors.CollectionValidationVisitor;
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
@Path("{path: .*}")
public class ROLIEApplicationCore {
	
	//Visitors
	private static final RESTEventVisitor DEBUG_VISITOR = new DebugVisitor();
	
	private static final RESTEventVisitor REQUEST_VALIDATOR_VISITOR = new RequestValidatorVisitor();
	
	private static final RESTEventVisitor COLLECTION_RETRIVIAL_VISITOR = new CollectionRetrivalVisitor();
	private static final RESTEventVisitor COLLECTION_CREATION_VISITOR = new CollectionCreationVisitor();
	private static final RESTEventVisitor COLLECTION_UPDATE_VISITOR = new CollectionUpdateVisitor();
	private static final RESTEventVisitor COLLECTION_DELETION_VISITOR = new CollectionDeletionVisitor();
	private static final RESTEventVisitor COLLECTION_VALIDATION_VISITOR = new CollectionValidationVisitor();

	private static final RESTEventVisitor RESPONSE_BUILDER_VISITOR = new ResponseBuilderVisitor();


	// visitor managers
	private static final VisitorManager vm;
	
	static {
		vm = new DefaultVisitorManager();
	}
	
	
	
	/**
	 * GET request handler on the collection URIs
	 *
	 * @param curi The URI of the collection, generated from the PathParam from the request
	 * @param headers The headers of the request
	 * @return The built HTTP response. Note that the returned response is automatically sent to the requesting agent
	 * 			right after completion of the method.
	 */
	@Produces({"application/atom+xml"})

	@GET
	public static Response get(@Context HttpHeaders headers, @Context UriInfo uriInfo)
	{
		
		RESTEvent get = new Get(headers,uriInfo.getAbsolutePath().toString());
		
		vm.addVisitor(REQUEST_VALIDATOR_VISITOR);
		vm.addVisitor(DEBUG_VISITOR);
		vm.addVisitor(RESPONSE_BUILDER_VISITOR);
		
		Map<String,Object> data = new HashMap<>();
		data.put("path", uriInfo.getPath());
		data.put("IRI", uriInfo.getAbsolutePath());
		data.put("headers", headers.getRequestHeaders());
		
		return vm.execute(get,data);
		
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
	@Produces({"application/atom+xml"})
	@POST
	public static Response post( @Context UriInfo uriInfo, String body, @Context HttpHeaders headers)
	{
		
		RESTEvent post = new Post(headers,body,uriInfo.getAbsolutePath().toString());

		vm.addVisitor(REQUEST_VALIDATOR_VISITOR);
		vm.addVisitor(COLLECTION_VALIDATION_VISITOR);
		vm.addVisitor(COLLECTION_CREATION_VISITOR);
		vm.addVisitor(RESPONSE_BUILDER_VISITOR);
		
		Map<String,Object> data = new HashMap<>();
		data.put("path", uriInfo.getPath());
		data.put("IRI", uriInfo.getAbsolutePath());
		data.put("headers", headers.getRequestHeaders());
		
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
	@Consumes({"application/xml","application/atom+xml"})
	@Produces({"application/atom+xml"})
	@PUT
	public static Response put( @Context UriInfo uriInfo, String body, @Context HttpHeaders headers)
	{
		
		RESTEvent put = new Put(headers,body,uriInfo.getAbsolutePath().toString());

		vm.addVisitor(REQUEST_VALIDATOR_VISITOR);
		vm.addVisitor(COLLECTION_VALIDATION_VISITOR);
		vm.addVisitor(COLLECTION_UPDATE_VISITOR);
		vm.addVisitor(RESPONSE_BUILDER_VISITOR);
		
		Map<String,Object> data = new HashMap<>();
		data.put("path", uriInfo.getPath());
		data.put("IRI", uriInfo.getAbsolutePath());
		data.put("headers", headers.getRequestHeaders());
		
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
	
	@Produces("text/plain")
	@DELETE
	public static Response delete(@Context UriInfo uriInfo, String body, @Context HttpHeaders headers)
	{
		RESTEvent delete = new Delete(headers,body,uriInfo.getAbsolutePath().toString());

		vm.addVisitor(REQUEST_VALIDATOR_VISITOR);
		vm.addVisitor(COLLECTION_VALIDATION_VISITOR);
		vm.addVisitor(COLLECTION_DELETION_VISITOR);
		vm.addVisitor(RESPONSE_BUILDER_VISITOR);
		
		Map<String,Object> data = new HashMap<>();
		data.put("path", uriInfo.getPath());
		data.put("IRI", uriInfo.getAbsolutePath());
		data.put("headers", headers.getRequestHeaders());
		
		return vm.execute(delete,data);
	}
	
}
