/**
 * Portions of this software was developed by employees of the National Institute
 * of Standards and Technology (NIST), an agency of the Federal Government.
 * Pursuant to title 17 United States Code Section 105, works of NIST employees are
 * not subject to copyright protection in the United States and are considered to
 * be in the public domain. Permission to freely use, copy, modify, and distribute
 * this software and its documentation without fee is hereby granted, provided that
 * this notice and disclaimer of warranty appears in all copies.
 *
 * THE SOFTWARE IS PROVIDED 'AS IS' WITHOUT ANY WARRANTY OF ANY KIND, EITHER
 * EXPRESSED, IMPLIED, OR STATUTORY, INCLUDING, BUT NOT LIMITED TO, ANY WARRANTY
 * THAT THE SOFTWARE WILL CONFORM TO SPECIFICATIONS, ANY IMPLIED WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, AND FREEDOM FROM
 * INFRINGEMENT, AND ANY WARRANTY THAT THE DOCUMENTATION WILL CONFORM TO THE
 * SOFTWARE, OR ANY WARRANTY THAT THE SOFTWARE WILL BE ERROR FREE. IN NO EVENT
 * SHALL NIST BE LIABLE FOR ANY DAMAGES, INCLUDING, BUT NOT LIMITED TO, DIRECT,
 * INDIRECT, SPECIAL OR CONSEQUENTIAL DAMAGES, ARISING OUT OF, RESULTING FROM, OR
 * IN ANY WAY CONNECTED WITH THIS SOFTWARE, WHETHER OR NOT BASED UPON WARRANTY,
 * CONTRACT, TORT, OR OTHERWISE, WHETHER OR NOT INJURY WAS SUSTAINED BY PERSONS OR
 * PROPERTY OR OTHERWISE, AND WHETHER OR NOT LOSS WAS SUSTAINED FROM, OR AROSE OUT
 * OF THE RESULTS OF, OR USE OF, THE SOFTWARE OR SERVICES PROVIDED HEREUNDER.
 */

package gov.nist.jrolie.server.servlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import gov.nist.jrolie.server.visitors.AuthorizationVisitor;
import gov.nist.jrolie.server.visitors.DataEventVisitor;
import gov.nist.jrolie.server.visitors.RequestValidatorVisitor;
import gov.nist.jrolie.server.visitors.ResponseBuilderVisitor;
import gov.nist.jrolie.server.visitors.ValidationVisitor;

@Component
public class DefaultDataVisitorManagerFactory implements DataVisitorManagerFactory {

	// Visitors are declared here by their intended purpose. If a new visitor is
	// written it can be swapped here
	// to apply to all requests.

	/**
	 * Handles the final steps of response construction, including header fields.
	 */
	@Autowired
	private ResponseBuilderVisitor responseBuilder;

	// private static final RESTEventVisitor DEBUGPROCESSOR = new

	@Autowired
	private AuthorizationVisitor authorizationManager;
	/*
	 * Primary visitor for resource requests. Drives required Atom transformations,
	 * and starts persistence procedures.
	 */
	@Autowired
	private DataEventVisitor requestProcessor;

	private VisitorManager getVisitorManagerInstance;
	private VisitorManager postVisitorManagerInstance;
	private VisitorManager putVisitorManagerInstance;
	private VisitorManager deleteVisitorManagerInstance;

	public DefaultDataVisitorManagerFactory() {
	}

	@Override
	public synchronized VisitorManager getGetVisitorManager() {
		if (this.getVisitorManagerInstance == null) {
			this.getVisitorManagerInstance = new DefaultVisitorManager();

			// Adds visitors in order to the execution list. FIFO order.
			this.getVisitorManagerInstance.addVisitor(this.authorizationManager);
			this.getVisitorManagerInstance.addVisitor(this.requestProcessor);
			this.getVisitorManagerInstance.addVisitor(responseBuilder);
		}
		return this.getVisitorManagerInstance;
	}

	@Override
	public synchronized VisitorManager getPostVisitorManager() {
		if (this.postVisitorManagerInstance == null) {
			// Adds visitors in order to the execution list. FIFO order.
			this.postVisitorManagerInstance = new DefaultVisitorManager();

			this.postVisitorManagerInstance.addVisitor(this.authorizationManager);
			this.postVisitorManagerInstance.addVisitor(this.requestProcessor);
			this.postVisitorManagerInstance.addVisitor(responseBuilder);
		}
		return this.postVisitorManagerInstance;
	}

	@Override
	public synchronized VisitorManager getPutVisitorManager() {
		if (this.putVisitorManagerInstance == null) {
			// Adds visitors in order to the execution list. FIFO order.
			this.putVisitorManagerInstance = new DefaultVisitorManager();


			this.putVisitorManagerInstance.addVisitor(this.requestProcessor);
			this.putVisitorManagerInstance.addVisitor(responseBuilder);
		}
		return this.putVisitorManagerInstance;
	}

	@Override
	public synchronized VisitorManager getDeleteVisitorManager() {
		if (this.deleteVisitorManagerInstance == null) {
			// Adds visitors in order to the execution list. FIFO order.
			this.deleteVisitorManagerInstance = new DefaultVisitorManager();

			this.deleteVisitorManagerInstance.addVisitor(this.authorizationManager);
			this.deleteVisitorManagerInstance.addVisitor(this.requestProcessor);
			this.deleteVisitorManagerInstance.addVisitor(responseBuilder);

		}
		return this.deleteVisitorManagerInstance;
	}

	public void cleanup() {
	}

}
