package gov.nist.rolie.polie.core.servlet;

import java.util.HashMap;
import java.util.Map;


import javax.ws.rs.GET;

import javax.ws.rs.Path;

import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;


import gov.nist.rolie.polie.core.event.Get;

import gov.nist.rolie.polie.core.event.RESTEvent;

import gov.nist.rolie.polie.core.visitors.DebugVisitor;
import gov.nist.rolie.polie.core.visitors.RESTEventVisitor;

import gov.nist.rolie.polie.core.visitors.ResponseBuilderVisitor;



/**
 * A debug tester class that intercepts any requests coming to {server-root}/polie-core/test
 */
@Path("test")
public class IncomingTestEvents {

	private static final RESTEventVisitor RESPONSE_BUILDER_VISITOR = new ResponseBuilderVisitor();
	private static final RESTEventVisitor DEBUG_VISITOR = new DebugVisitor();
	
	private static final VisitorManager vm;
	static {
		vm = new DefaultVisitorManager();
	}
	
	@Produces({ "text/plain", "application/xml", "application/atom+xml" })
	@GET
	public static Response get(@Context HttpHeaders headers, @Context UriInfo uriInfo) {

		RESTEvent get = new Get(headers, uriInfo);

		vm.addVisitor(DEBUG_VISITOR);
		vm.addVisitor(RESPONSE_BUILDER_VISITOR);

		Map<String, Object> data = new HashMap<>();
		data.put("uri", uriInfo.getPath());
		data.put("headers", headers.getRequestHeaders());
		data.put("body", "this is a test probably");

		return vm.execute(get, data);

	}

}
