package gov.nist.rolie.polie.core.servlet;

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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import gov.nist.rolie.polie.core.event.Delete;
import gov.nist.rolie.polie.core.event.Get;
import gov.nist.rolie.polie.core.event.Post;
import gov.nist.rolie.polie.core.event.Put;
import gov.nist.rolie.polie.core.event.RESTEvent;
import gov.nist.rolie.polie.core.event.RESTEventVisitor;

//This Path escapes all slashes in order to get all text that follows the context path.
//All requests should match this Path as long as they start with the context path.
@Path("{uri : .+}")
public class IncomingEvents {
	
	@Produces({"text/plain","application/xml","application/atom+xml"})
	@GET
	public static Response get(@PathParam("uri") String uri, String x, @Context HttpHeaders headers)
	{
		VisitorManager vm = new VisitorManager();
		Response response = null;
		RESTEvent event = new Get(headers,x,uri);
		vm.addVisitor(new RESTEventTestVisitor());
		for (RESTEventVisitor visitor : vm.build()) 
		{
			response = event.accept(visitor);
		}
		return response;
	}

	@Consumes({"application/xml","application/atom+xml"})
	@Produces("application/xml")
	@POST
	public static Response post(@PathParam("uri") String uri, String x, @Context HttpHeaders headers)
	{
		VisitorManager vm = new VisitorManager();
		Response response = null;
		RESTEvent event = new Post(headers,x,uri);
		vm.addVisitor(new RESTEventValidationVisitor());
		for (RESTEventVisitor visitor : vm.build()) 
		{
			response = event.accept(visitor);
		}
		return response;
	}
	
	@Consumes({"text/plain","application/xml"})
	@Produces("text/plain")
	@PUT
	public static Response put(@PathParam("uri") String uri, String x, @Context HttpHeaders headers)
	{
		VisitorManager vm = new VisitorManager();
		Response response = null;
		RESTEvent event = new Put(headers,x,uri);
		vm.addVisitor(new RESTEventTestVisitor());
		for (RESTEventVisitor visitor : vm.build()) 
		{
			response = event.accept(visitor);
		}
		return response;
	}
	
	@Consumes("text/plain")
	@Produces("text/plain")
	@DELETE
	public static Response delete(@PathParam("uri") String uri, String x, @Context HttpHeaders headers)
	{
		VisitorManager vm = new VisitorManager();
		Response response = null;
		RESTEvent event = new Delete(headers,x,uri);
		vm.addVisitor(new RESTEventTestVisitor());
		for (RESTEventVisitor visitor : vm.build()) 
		{
			response = event.accept(visitor);
		}
		return response;
	}
	
}
