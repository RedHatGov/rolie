/*
 * 
 */
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
import gov.nist.rolie.polie.core.visitors.CollectionRetrivalVisitor;
import gov.nist.rolie.polie.core.visitors.RequestValidatorVisitor;
import gov.nist.rolie.polie.core.visitors.ResponseBuilderVisitor;
import gov.nist.rolie.polie.core.visitors.ServiceDocumentRetrivalVisitor;
import gov.nist.rolie.polie.core.visitors.UnimplementedVisitor;

// TODO: Auto-generated Javadoc
/**
 * The Class IncomingSrvDocEvent.
 */
@Path("rolie/servicedocument")
public class IncomingSrvDocEvent 
{
	
	/**
	 * Gets the.
	 *
	 * @param curi the curi
	 * @param headers the headers
	 * @return the response
	 */
	@Produces({"text/plain","application/xml","application/atom+xml"})
	@GET
	public static Response get(@Context HttpHeaders headers)
	{
		
		RESTEvent get = new Get(headers,"");
		
		VisitorManager vm = new VisitorManager();
		vm.addVisitor(new RequestValidatorVisitor());
		vm.addVisitor(new ServiceDocumentRetrivalVisitor());
		vm.addVisitor(new ResponseBuilderVisitor());
		//vm.addVisitor(new DebugVisitor());
		
		Map<String,Object> data = new HashMap<>();
		data.put("uri", "");
		data.put("headers", headers.getRequestHeaders());
		
		return vm.execute(get,data);
		
	}
}
