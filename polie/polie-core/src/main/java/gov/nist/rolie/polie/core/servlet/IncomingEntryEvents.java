///*
// * 
// */
//package gov.nist.rolie.polie.core.servlet;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.ws.rs.Consumes;
//import javax.ws.rs.DELETE;
//import javax.ws.rs.GET;
//import javax.ws.rs.POST;
//import javax.ws.rs.PUT;
//import javax.ws.rs.Path;
//import javax.ws.rs.PathParam;
//import javax.ws.rs.Produces;
//import javax.ws.rs.core.Context;
//import javax.ws.rs.core.HttpHeaders;
//import javax.ws.rs.core.Response;
//import javax.ws.rs.core.UriInfo;
//
//import gov.nist.rolie.polie.core.event.Delete;
//import gov.nist.rolie.polie.core.event.Get;
//import gov.nist.rolie.polie.core.event.Post;
//import gov.nist.rolie.polie.core.event.Put;
//import gov.nist.rolie.polie.core.event.RESTEvent;
//import gov.nist.rolie.polie.core.visitors.CollectionCreationVisitor;
//import gov.nist.rolie.polie.core.visitors.CollectionDeletionVisitor;
//import gov.nist.rolie.polie.core.visitors.CollectionRetrivalVisitor;
//import gov.nist.rolie.polie.core.visitors.CollectionUpdateVisitor;
//import gov.nist.rolie.polie.core.visitors.CollectionValidationVisitor;
//import gov.nist.rolie.polie.core.visitors.DebugVisitor;
//import gov.nist.rolie.polie.core.visitors.EntryCreationVisitor;
//import gov.nist.rolie.polie.core.visitors.EntryDeletionVisitor;
//import gov.nist.rolie.polie.core.visitors.EntryRetrivalVisitor;
//import gov.nist.rolie.polie.core.visitors.EntryUpdateVisitor;
//import gov.nist.rolie.polie.core.visitors.EntryValidationVisitor;
//import gov.nist.rolie.polie.core.visitors.RESTEventVisitor;
//import gov.nist.rolie.polie.core.visitors.RequestValidatorVisitor;
//import gov.nist.rolie.polie.core.visitors.ResponseBuilderVisitor;
//import gov.nist.rolie.polie.core.visitors.UnimplementedVisitor;
//
//// TODO: Auto-generated Javadoc
////This Path escapes all slashes in order to get all text that follows the context path.
///**
// * The Class IncomingEntryEvents.
// */
////All requests should match this Path as long as they start with the context path.
//@Path("entries/{euri}")
//public class IncomingEntryEvents {
//	
//	//Visitors
//	private static final RESTEventVisitor DEBUG_VISITOR = new DebugVisitor();
//	
//	private static final RESTEventVisitor REQUEST_VALIDATOR_VISITOR = new RequestValidatorVisitor();
//	
//	private static final RESTEventVisitor ENTRY_RETRIVIAL_VISITOR = new EntryRetrivalVisitor();
//	private static final RESTEventVisitor ENTRY_CREATION_VISITOR = new EntryCreationVisitor();
//	private static final RESTEventVisitor ENTRY_UPDATE_VISITOR = new EntryUpdateVisitor();
//	private static final RESTEventVisitor ENTRY_DELETION_VISITOR = new EntryDeletionVisitor();
//	private static final RESTEventVisitor ENTRY_VALIDATION_VISITOR = new EntryValidationVisitor();
//
//	private static final RESTEventVisitor RESPONSE_BUILDER_VISITOR = new ResponseBuilderVisitor();
//	
//	//
//	private static final VisitorManager vm;
//	
//	static {
//		vm = new DefaultVisitorManager();
//	}
//	
//	@Context
//	private UriInfo uriInfo;
//	/**
//	 * Gets the.
//	 *
//	 * @param uri the uri
//	 * @param headers the headers
//	 * @return the response
//	 */
//	@Produces({"application/atom+xml"})
//	@GET
//	public static Response get(@Context HttpHeaders headers, @Context UriInfo uriInfo)
//	{
//		
//		RESTEvent get = new Get(headers,uriInfo.getAbsolutePath().toString());
//		
//		vm.addVisitor(REQUEST_VALIDATOR_VISITOR);
//		vm.addVisitor(ENTRY_RETRIVIAL_VISITOR);
//		vm.addVisitor(RESPONSE_BUILDER_VISITOR);
//		
//		Map<String,Object> data = new HashMap<>();
//		data.put("path", uriInfo.getPath());
//		data.put("IRI", uriInfo.getAbsolutePath());
//		data.put("headers", headers.getRequestHeaders());
//		
//		return vm.execute(get,data);
//		
//	}
//
//	/**
//	 * Post.
//	 *
//	 * @param uri the uri
//	 * @param x the x
//	 * @param headers the headers
//	 * @return the response
//	 */
//	@Consumes({"application/xml","application/atom+xml"})
//	@Produces({"application/atom+xml"})
//	@POST
//	public static Response post( @Context UriInfo uriInfo, String body, @Context HttpHeaders headers)
//	{
//		
//		RESTEvent post = new Post(headers,body,uriInfo.getAbsolutePath().toString());
//
//		vm.addVisitor(REQUEST_VALIDATOR_VISITOR);
//		vm.addVisitor(ENTRY_VALIDATION_VISITOR);
//		vm.addVisitor(ENTRY_CREATION_VISITOR);
//		vm.addVisitor(RESPONSE_BUILDER_VISITOR);
//		
//		Map<String,Object> data = new HashMap<>();
//		data.put("path", uriInfo.getPath());
//		data.put("IRI", uriInfo.getAbsolutePath());
//		data.put("headers", headers.getRequestHeaders());
//		
//		return vm.execute(post,data);
//	}
//	
//	/**
//	 * Put.
//	 *
//	 * @param uri the uri
//	 * @param x the x
//	 * @param headers the headers
//	 * @return the response
//	 */
//	@Consumes({"application/xml","application/atom+xml"})
//	@Produces({"application/atom+xml"})
//	@PUT
//	public static Response put( @Context UriInfo uriInfo, String body, @Context HttpHeaders headers)
//	{
//		
//		RESTEvent put = new Put(headers,body,uriInfo.getAbsolutePath().toString());
//
//		vm.addVisitor(REQUEST_VALIDATOR_VISITOR);
//		vm.addVisitor(ENTRY_VALIDATION_VISITOR);
//		vm.addVisitor(ENTRY_UPDATE_VISITOR);
//		vm.addVisitor(RESPONSE_BUILDER_VISITOR);
//		
//		Map<String,Object> data = new HashMap<>();
//		data.put("path", uriInfo.getPath());
//		data.put("IRI", uriInfo.getAbsolutePath());
//		data.put("headers", headers.getRequestHeaders());
//		
//		return vm.execute(put,data);
//	}
//	
//	/**
//	 * Delete.
//	 *
//	 * @param uri the uri
//	 * @param x the x
//	 * @param headers the headers
//	 * @return the response
//	 */
//	@Consumes("text/plain")
//	@Produces("text/plain")
//	@DELETE
//	public static Response delete(@PathParam("euri") String uri, String x, @Context HttpHeaders headers)
//	{
//		RESTEvent delete = new Delete(headers,x,uri);
//		DefaultVisitorManager vm = new DefaultVisitorManager();
//		vm.addVisitor(new UnimplementedVisitor());
//		
//		Map<String,Object> data = new HashMap<>();
//		
//		return vm.execute(delete,data);
//	}
//	
//}
