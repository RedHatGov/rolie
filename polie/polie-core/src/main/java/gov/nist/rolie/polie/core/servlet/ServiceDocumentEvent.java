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

import gov.nist.rolie.polie.core.visitors.RESTEventVisitor;
import gov.nist.rolie.polie.core.visitors.RequestValidatorVisitor;
import gov.nist.rolie.polie.core.visitors.ResourceSerializerVisitor;
import gov.nist.rolie.polie.core.visitors.ResponseBuilderVisitor;
import gov.nist.rolie.polie.core.visitors.ServiceDocumentRetrivalVisitor;

//import gov.nist.rolie.polie.core.visitors.DebugVisitor;

@Path("rolie/servicedocument")
public class ServiceDocumentEvent {

	// Visitors are declared here by their intended purpose. If a new visitor is
	// written it can be swapped here
	// to apply to all requests.

	/**
	 * Provides debug functions. DISABLED.
	 */
	// private static final RESTEventVisitor DEBUG_VISITOR = new DebugVisitor();

	/**
	 * Validates the request itself. Most of this is handled by the server, but
	 * extra logic may be included as needed.
	 */
	private static final RESTEventVisitor REQUEST_VALIDATOR_VISITOR = new RequestValidatorVisitor();

	/**
	 * Handles the retrival of the category document. Primarily fires off
	 * persistence method requests for the data.
	 */
	private static final RESTEventVisitor SERVICE_DOCUMENT_RETRIVIAL_VISITOR = new ServiceDocumentRetrivalVisitor();

	/**
	 * Handles the final steps of response construction, including header fields
	 */
	private static final RESTEventVisitor RESPONSE_BUILDER_VISITOR = new ResponseBuilderVisitor();

	private static final RESTEventVisitor RESOURCE_SERIALIZER_VISITOR = new ResourceSerializerVisitor();
	/**
	 * TODO
	 * Provides a single location that points to the actual service document location
	 * Use this is the server wants to use a different location for the document
	 * but doesn't want to redirect. DISABLED
	 */
	//private static final String REAL_SERVICE_DOCUMENT_LOCATION = "rolie/servicedocument";

	/**The visitor manager is declared here. If a new visitor manager is written is can be swapped out here
	*To apply to all requests.
	*/
	private static final VisitorManager vm;
	static {
		vm = new DefaultVisitorManager();
	}

	
	
	/**
	 * Handles get requests sent to rolie/categorydocument.
	 * 
	 * @param headers Automatically populated with a map of all headers.
	 * @param uriInfo Automatically populated with a variety of URI data fields
	 * @return Returns the completed Response that is passed off to the server to be sent back to the requester.
	 * 			At this point, the response is completed and is handled all by the webapp.
	 */
	@Produces({ "application/atom+xml" })
	@GET
	public static Response get(@Context HttpHeaders headers, @Context UriInfo uriInfo) {

		RESTEvent get = new Get(headers, uriInfo);

		vm.addVisitor(REQUEST_VALIDATOR_VISITOR);
		vm.addVisitor(SERVICE_DOCUMENT_RETRIVIAL_VISITOR);
		vm.addVisitor(RESOURCE_SERIALIZER_VISITOR);
		vm.addVisitor(RESPONSE_BUILDER_VISITOR);

		Map<String, Object> data = new HashMap<>();
		data.put("path", uriInfo.getPath());
		data.put("IRI", uriInfo.getAbsolutePath());
		data.put("headers", headers.getRequestHeaders());

		return vm.execute(get, data);

	}
}
