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
/*
 * 
 */

package gov.nist.jrolie.server.servlet;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import gov.nist.jrolie.server.event.RESTEvent;
import gov.nist.jrolie.server.visitors.RESTEventVisitor;

/**
 * The default implementation of the visitor manager. Provides basic capabilities.
 */
public class DefaultVisitorManager implements VisitorManager {
  private static final Logger log = LogManager.getLogger(DefaultVisitorManager.class);

  private List<RESTEventVisitor> visitors = new LinkedList<>();

  public void addVisitor(RESTEventVisitor visitor) {
    visitors.add(visitor);
  }

  public void addVisitor(int index, RESTEventVisitor visitor) {
    visitors.add(index, visitor);
  }

  public List<RESTEventVisitor> getVisitors() {
    return visitors;
  }

  public void clearVisitors() {
    visitors.clear();
  }

  @Override
  public Response execute(RESTEvent event) {
    return execute(event, new HashMap<String, Object>());
  }

  public Response execute(RESTEvent event, Map<String, Object> data) {
    // If something goes wrong, set the default Response to a server error
    // response.
    // TODO: set to a resonable default status that doesn't need to be
    // changed
    // TODO: use a call to the event to get the default status by request
    // method
    ResponseBuilder rb = Response.status(Status.INTERNAL_SERVER_ERROR);

    // Basic for loop to execute all visitors. If a visitor returns false,
    // the loop is terminated
    // right away and the response is built as-is.
    for (RESTEventVisitor visitor : this.visitors) {
      // log.debug("Processing visitor: {}",
      // visitor.getClass().getName());
      boolean processNext = event.accept(visitor, rb, data);
      if (!processNext) {
        break;
      }
    }

    // The response builder has incrementally gathered information, this
    // constructs a single response
    // with that information and returns it.
    return rb.build();
  }

}
