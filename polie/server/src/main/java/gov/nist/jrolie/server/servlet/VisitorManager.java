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

import gov.nist.jrolie.server.event.RESTEvent;
import gov.nist.jrolie.server.visitors.RESTEventVisitor;

import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Response;

/**
 * Interface for the visitor manager. Implementations should take note that this object will be
 * created once per request, and execute will be run once per request. Visitor manager provides core
 * functionality and the webapp will not function if there is a bug here.
 * 
 * @author sab3
 *
 */
public interface VisitorManager {

  /**
   * A list of visitors to be executed. The order of this list is maintained and is FIFO
   */
  public List<RESTEventVisitor> getVisitors();

  /**
   * Adds a visitor to the end of the visitor list
   *
   * @param visitor
   *          Visitor object to add to the list
   */
  public void addVisitor(RESTEventVisitor visitor);

  /**
   * Adds a visitor to the visitor list at the specified index memes
   *
   * @param visitor
   *          Visitor object to add to the list
   * @param index
   *          integer index at which the visitor is added.
   */
  public void addVisitor(int index, RESTEventVisitor visitor);

  /**
   * A simplification of {@link #execute(RESTEvent, Map)} that initializes an empty map. Same as
   * calling <code>execute(RESETEvent, new Map<>())</code>.
   * 
   * @param event
   *          The event that the execution chain is tied to (i.e. GET,PUT,POST, or DELETE)
   * @return The built response from the response builder.
   * @see VisitorManager#execute(RESTEvent, Map)
   */
  public Response execute(RESTEvent event);

  /**
   * Visitor Execution loop. All visitors that are currently in the visitor list are executed in
   * order. The data map and the response builder are passed by reference and used to pass information
   * from visitor to visitor and to incrementally build the response, respectively. A visitor may
   * cease the execution chain at any time if an error occurs. Later visitors may overwrite response
   * characteristics.
   * 
   * This means that if a visitor encounters a non-recoverable error, it will most likely overwrite
   * the work of all previous visitors, and prevent any further visitors from executing.
   *
   * @param event
   *          The event that the execution chain is tied to (i.e. GET,PUT,POST, or DELETE)
   * @param data
   *          Data map for passing data between visitors
   * @return The built response from the response builder.
   */
  public Response execute(RESTEvent event, Map<String, Object> data);

}
