
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
	//@Path("/{path: .*}")
	public class IncomingTestEvents {
		
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
		public static Response get(@PathParam("path") String uri, @Context HttpHeaders headers, String x)
		{
			String body=x;
			RESTEvent get = new Get(headers,uri);
			
			DefaultVisitorManager vm = new DefaultVisitorManager();
			vm.addVisitor(new DebugVisitor());
			
			Map<String,Object> data = new HashMap<>();
			data.put("uri", uri);
			data.put("headers", headers.getRequestHeaders());
			data.put("body", body);
			
			return vm.execute(get,data);
			
		}

}
