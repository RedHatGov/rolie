package gov.nist.rolie.polie.server.servlet;

import gov.nist.rolie.polie.server.visitors.RESTEventVisitor;
import gov.nist.rolie.polie.server.visitors.ROLIEValidationVisitor;
import gov.nist.rolie.polie.server.visitors.RequestValidatorVisitor;
import gov.nist.rolie.polie.server.visitors.ResourceEventVisitor;
import gov.nist.rolie.polie.server.visitors.ResponseBuilderVisitor;

public class DefaultVisitorManagerFactory implements VisitorManagerFactory {
	
	//Visitors are declared here by their intended purpose. If a new visitor is written it can be swapped here
	//to apply to all requests.
	
	/**
	 * Provides debug functions. DISABLED.
	 */
	//private static final RESTEventVisitor DEBUG_VISITOR = new DebugVisitor();
	
	/**
	*Validates the request itself. Most of this is handled by the server,
	*but extra logic may be included as needed.
	*/
	private static final RESTEventVisitor HTTP_REQUEST_VALIDATOR_VISITOR = new RequestValidatorVisitor();
	
	/**
	 * Primary visitor for resource requests. Drives required Atom transformations, and starts 
	 * persistence procedures.
	 */
	private static final RESTEventVisitor REQUEST_PROCESSOR_VISITOR = new ResourceEventVisitor();
	
	/**
	 * Validates ROLIE XML content in the body of the request.
	 */
	private static final RESTEventVisitor ROLIE_CONTENT_VALIDATION_VISITOR = new ROLIEValidationVisitor();
	
	/**
	 * Handles the final steps of response construction, including header fields
	 */
	private static final RESTEventVisitor RESPONSE_BUILDER_VISITOR = new ResponseBuilderVisitor();

	private static final VisitorManager getVisitorManagerInstance;
	private static final VisitorManager postVisitorManagerInstance;
	private static final VisitorManager putVisitorManagerInstance;
	private static final VisitorManager deleteVisitorManagerInstance;

	static {
		//get appcontext get bean for visitor instad of creating a new one
		getVisitorManagerInstance = new DefaultVisitorManager();
		
		//Adds visitors in order to the execution list. FIFO order.
		getVisitorManagerInstance.addVisitor(HTTP_REQUEST_VALIDATOR_VISITOR); 
		getVisitorManagerInstance.addVisitor(REQUEST_PROCESSOR_VISITOR);
		getVisitorManagerInstance.addVisitor(RESPONSE_BUILDER_VISITOR);

		postVisitorManagerInstance = new DefaultVisitorManager();

		postVisitorManagerInstance.addVisitor(HTTP_REQUEST_VALIDATOR_VISITOR);
		postVisitorManagerInstance.addVisitor(ROLIE_CONTENT_VALIDATION_VISITOR);
		postVisitorManagerInstance.addVisitor(REQUEST_PROCESSOR_VISITOR);
		postVisitorManagerInstance.addVisitor(RESPONSE_BUILDER_VISITOR);

		putVisitorManagerInstance = new DefaultVisitorManager();
		putVisitorManagerInstance.addVisitor(HTTP_REQUEST_VALIDATOR_VISITOR);
		putVisitorManagerInstance.addVisitor(ROLIE_CONTENT_VALIDATION_VISITOR);
		putVisitorManagerInstance.addVisitor(REQUEST_PROCESSOR_VISITOR);
		putVisitorManagerInstance.addVisitor(RESPONSE_BUILDER_VISITOR);

		deleteVisitorManagerInstance = new DefaultVisitorManager();
		deleteVisitorManagerInstance.addVisitor(HTTP_REQUEST_VALIDATOR_VISITOR);
		deleteVisitorManagerInstance.addVisitor(REQUEST_PROCESSOR_VISITOR);
		deleteVisitorManagerInstance.addVisitor(RESPONSE_BUILDER_VISITOR);
	}

	private static final VisitorManagerFactory instance = new DefaultVisitorManagerFactory();

	public static VisitorManagerFactory instance() {
		return instance;
	}

	public DefaultVisitorManagerFactory() {
	}

	@Override
	public VisitorManager GetGetVisitorManager() {
		return getVisitorManagerInstance;
	}

	@Override
	public VisitorManager GetPostVisitorManager() {
		return postVisitorManagerInstance;
	}

	@Override
	public VisitorManager GetPutVisitorManager() {
		return putVisitorManagerInstance;
	}

	@Override
	public VisitorManager GetDeleteVisitorManager() {
		return deleteVisitorManagerInstance;
	}

}
