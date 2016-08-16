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
import gov.nist.rolie.polie.core.visitors.RequestValidatorVisitor;
import gov.nist.rolie.polie.core.visitors.UnimplementedVisitor;

//This Path escapes all slashes in order to get all text that follows the context path.
//All requests should match this Path as long as they start with the context path.
@Path("collections/{curi}")
public class IncomingCollectionEvents {
	/**
	 * 
	 * @param curi         
	 * @param headers
	 * @return
	 */
	@Produces({"text/plain","application/xml","application/atom+xml"})
	@GET
	public static Response get(@PathParam("curi") String curi, @Context HttpHeaders headers)
	{
		
		RESTEvent get = new Get(headers,curi);
		
		VisitorManager vm = new VisitorManager();
		vm.addVisitor(new DebugVisitor());
		
		Map<String,Object> data = new HashMap<>();
		
		return vm.execute(get,data);
		
	}

	@Consumes({"application/xml","application/atom+xml"})
	@Produces("application/xml")
	@POST
	public static Response post(@PathParam("curi") String uri, String x, @Context HttpHeaders headers)
	{
		
		RESTEvent post = new Post(headers,x,uri);
		VisitorManager vm = new VisitorManager();
		vm.addVisitor(new UnimplementedVisitor());
		
		Map<String,Object> data = new HashMap<>();
		
		return vm.execute(post,data);
	}
	
	@Consumes({"text/plain","application/xml"})
	@Produces("text/plain")
	@PUT
	public static Response put(@PathParam("curi") String uri, String x, @Context HttpHeaders headers)
	{
		RESTEvent put = new Put(headers,x,uri);
		VisitorManager vm = new VisitorManager();
		vm.addVisitor(new UnimplementedVisitor());
		
		Map<String,Object> data = new HashMap<>();
		
		return vm.execute(put,data);
	}
	
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
