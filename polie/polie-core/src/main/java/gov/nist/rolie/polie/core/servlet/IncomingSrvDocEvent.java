package gov.nist.rolie.polie.core.servlet;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import gov.nist.rolie.polie.core.event.Get;
import gov.nist.rolie.polie.core.event.RESTEvent;
import gov.nist.rolie.polie.core.visitors.UnimplementedVisitor;

@Path("rolie/servicedocument")
public class IncomingSrvDocEvent 
{
	
	@Produces({"text/plain","application/xml","application/atom+xml"})
	@GET
	public static Response get(@PathParam("euri") String curi, @Context HttpHeaders headers)
	{
		
		RESTEvent get = new Get(headers,curi);
		
		VisitorManager vm = new VisitorManager();
		vm.addVisitor(new UnimplementedVisitor());
		
		Map<String,Object> data = new HashMap<>();
		
		return vm.execute(get,data);
		
	}
}
