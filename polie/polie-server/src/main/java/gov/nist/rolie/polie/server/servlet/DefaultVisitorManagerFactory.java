package gov.nist.rolie.polie.server.servlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import gov.nist.rolie.polie.server.visitors.RESTEventVisitor;
import gov.nist.rolie.polie.server.visitors.ROLIEValidationVisitor;
import gov.nist.rolie.polie.server.visitors.RequestValidatorVisitor;
import gov.nist.rolie.polie.server.visitors.ResourceEventVisitor;
import gov.nist.rolie.polie.server.visitors.ResponseBuilderVisitor;

@Component
public class DefaultVisitorManagerFactory implements VisitorManagerFactory {

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
	private static final RESTEventVisitor HTTP_REQUEST_VALIDATOR_VISITOR = new RequestValidatorVisitor();

	/**
	 * Validates ROLIE XML content in the body of the request.
	 */
	private static final RESTEventVisitor ROLIE_CONTENT_VALIDATION_VISITOR = new ROLIEValidationVisitor();

	/**
	 * Handles the final steps of response construction, including header fields
	 */
	private static final RESTEventVisitor RESPONSE_BUILDER_VISITOR = new ResponseBuilderVisitor();

//	private static final RESTEventVisitor DEBUGPROCESSOR = new ResourceEventVisitor();
	/*
	 * Primary visitor for resource requests. Drives required Atom
	 * transformations, and starts persistence procedures.
	 */
	@Autowired
	private ResourceEventVisitor requestProcessor;

	private VisitorManager getVisitorManagerInstance;
	private VisitorManager postVisitorManagerInstance;
	private VisitorManager putVisitorManagerInstance;
	private VisitorManager deleteVisitorManagerInstance;

	public DefaultVisitorManagerFactory() {
	}

	@Override
	public synchronized VisitorManager GetGetVisitorManager() {
		if (getVisitorManagerInstance == null) {
			getVisitorManagerInstance = new DefaultVisitorManager();

			// Adds visitors in order to the execution list. FIFO order.
			getVisitorManagerInstance.addVisitor(HTTP_REQUEST_VALIDATOR_VISITOR);
			getVisitorManagerInstance.addVisitor(requestProcessor);
			getVisitorManagerInstance.addVisitor(RESPONSE_BUILDER_VISITOR);
		}
		return getVisitorManagerInstance;
	}

	@Override
	public synchronized VisitorManager GetPostVisitorManager() {
		if (postVisitorManagerInstance == null) {
			// Adds visitors in order to the execution list. FIFO order.
			postVisitorManagerInstance = new DefaultVisitorManager();

			postVisitorManagerInstance.addVisitor(HTTP_REQUEST_VALIDATOR_VISITOR);
			postVisitorManagerInstance.addVisitor(ROLIE_CONTENT_VALIDATION_VISITOR);
			postVisitorManagerInstance.addVisitor(requestProcessor);
			postVisitorManagerInstance.addVisitor(RESPONSE_BUILDER_VISITOR);
		}
		return postVisitorManagerInstance;
	}

	@Override
	public synchronized VisitorManager GetPutVisitorManager() {
		if (putVisitorManagerInstance == null) {
			// Adds visitors in order to the execution list. FIFO order.
			putVisitorManagerInstance = new DefaultVisitorManager();
			putVisitorManagerInstance.addVisitor(HTTP_REQUEST_VALIDATOR_VISITOR);
			putVisitorManagerInstance.addVisitor(ROLIE_CONTENT_VALIDATION_VISITOR);
			putVisitorManagerInstance.addVisitor(requestProcessor);
			putVisitorManagerInstance.addVisitor(RESPONSE_BUILDER_VISITOR);
		}
		return putVisitorManagerInstance;
	}

	@Override
	public synchronized VisitorManager GetDeleteVisitorManager() {
		if (deleteVisitorManagerInstance == null) {
			// Adds visitors in order to the execution list. FIFO order.
			deleteVisitorManagerInstance = new DefaultVisitorManager();
			deleteVisitorManagerInstance.addVisitor(HTTP_REQUEST_VALIDATOR_VISITOR);
			deleteVisitorManagerInstance.addVisitor(requestProcessor);
			deleteVisitorManagerInstance.addVisitor(RESPONSE_BUILDER_VISITOR);

		}
		return deleteVisitorManagerInstance;
	}

	public void cleanup() {
	}

}
