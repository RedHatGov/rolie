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
 * SHALL NASA BE LIABLE FOR ANY DAMAGES, INCLUDING, BUT NOT LIMITED TO, DIRECT,
 * INDIRECT, SPECIAL OR CONSEQUENTIAL DAMAGES, ARISING OUT OF, RESULTING FROM, OR
 * IN ANY WAY CONNECTED WITH THIS SOFTWARE, WHETHER OR NOT BASED UPON WARRANTY,
 * CONTRACT, TORT, OR OTHERWISE, WHETHER OR NOT INJURY WAS SUSTAINED BY PERSONS OR
 * PROPERTY OR OTHERWISE, AND WHETHER OR NOT LOSS WAS SUSTAINED FROM, OR AROSE OUT
 * OF THE RESULTS OF, OR USE OF, THE SOFTWARE OR SERVICES PROVIDED HEREUNDER.
 */
package gov.nist.rolie.polie.server.servlet;

import gov.nist.rolie.polie.server.visitors.AuthorizationVisitor;
import gov.nist.rolie.polie.server.visitors.ROLIEValidationVisitor;
import gov.nist.rolie.polie.server.visitors.RequestValidatorVisitor;
import gov.nist.rolie.polie.server.visitors.ResourceEventVisitor;
import gov.nist.rolie.polie.server.visitors.ResponseBuilderVisitor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
   * Validates the request itself. Most of this is handled by the server, but extra logic may be included as needed.
   */
  @Autowired
  private RequestValidatorVisitor requestValidator;

  /**
   * Validates ROLIE XML content in the body of the request.
   */
  @Autowired
  private ROLIEValidationVisitor rolieContentValidator;

  /**
   * Handles the final steps of response construction, including header fields.
   */
  @Autowired
  private ResponseBuilderVisitor responseBuilder;

  // private static final RESTEventVisitor DEBUGPROCESSOR = new

  @Autowired
  private AuthorizationVisitor authorizationManager;
  /*
   * Primary visitor for resource requests. Drives required Atom transformations, and starts persistence procedures.
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
      getVisitorManagerInstance.addVisitor(requestValidator);
      getVisitorManagerInstance.addVisitor(authorizationManager);
      getVisitorManagerInstance.addVisitor(requestProcessor);
      getVisitorManagerInstance.addVisitor(responseBuilder);
    }
    return getVisitorManagerInstance;
  }

  @Override
  public synchronized VisitorManager GetPostVisitorManager() {
    if (postVisitorManagerInstance == null) {
      // Adds visitors in order to the execution list. FIFO order.
      postVisitorManagerInstance = new DefaultVisitorManager();

      postVisitorManagerInstance.addVisitor(requestValidator);
      postVisitorManagerInstance.addVisitor(authorizationManager);
      postVisitorManagerInstance.addVisitor(rolieContentValidator);
      postVisitorManagerInstance.addVisitor(requestProcessor);
      postVisitorManagerInstance.addVisitor(responseBuilder);
    }
    return postVisitorManagerInstance;
  }

  @Override
  public synchronized VisitorManager GetPutVisitorManager() {
    if (putVisitorManagerInstance == null) {
      // Adds visitors in order to the execution list. FIFO order.
      putVisitorManagerInstance = new DefaultVisitorManager();
      putVisitorManagerInstance.addVisitor(requestValidator);
      putVisitorManagerInstance.addVisitor(authorizationManager);
      putVisitorManagerInstance.addVisitor(rolieContentValidator);
      putVisitorManagerInstance.addVisitor(requestProcessor);
      putVisitorManagerInstance.addVisitor(responseBuilder);
    }
    return putVisitorManagerInstance;
  }

  @Override
  public synchronized VisitorManager GetDeleteVisitorManager() {
    if (deleteVisitorManagerInstance == null) {
      // Adds visitors in order to the execution list. FIFO order.
      deleteVisitorManagerInstance = new DefaultVisitorManager();
      deleteVisitorManagerInstance.addVisitor(requestValidator);
      deleteVisitorManagerInstance.addVisitor(authorizationManager);
      deleteVisitorManagerInstance.addVisitor(requestProcessor);
      deleteVisitorManagerInstance.addVisitor(responseBuilder);

    }
    return deleteVisitorManagerInstance;
  }

  public void cleanup() {
  }

}
