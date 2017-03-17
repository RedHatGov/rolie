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

package gov.nist.rolie.polie.server.visitors;

import gov.nist.rolie.polie.model.models.APPResource;
import gov.nist.rolie.polie.server.event.Delete;
import gov.nist.rolie.polie.server.event.Get;
import gov.nist.rolie.polie.server.event.Post;
import gov.nist.rolie.polie.server.event.Put;

import org.springframework.stereotype.Component;

import java.util.Map;

import javax.ws.rs.core.Response.ResponseBuilder;

/**
 * This visitor provides the common final steps of response preparation before the request is sent out. Most of the work
 * is done automatically by the server, but any extra logic is done here. This should be a late visitor, or the last.
 * 
 * @author sab3
 *
 */
@Component
public class ResponseBuilderVisitor implements RESTEventVisitor {

  /**
   * When this visitor encounters a GET request, it gets the resource that has been found and places it in the body of
   * the request.
   * 
   * DATA MAP CONTRACT: BEFORE: "RetrivedResource" is an APPResource AFTER: NONE
   * 
   * @param get
   *          The event type.
   * @param rb
   *          The passed response builder
   * @param data
   *          The passed data map
   * @returns Boolean value indicating whether or not execution should continue.
   */
  @Override
  public boolean visit(Get get, ResponseBuilder rb, Map<String, Object> data) {
    APPResource resource = (APPResource) data.get(RESOURCE_KEY);
    rb.entity(resource.getXmlObject());
    return true;
  }

  /**
   * When this visitor encounters a post request, it gets the resource that has been cerated and places it in the body
   * of the request.
   * 
   * DATA MAP CONTRACT: BEFORE: "CreatedResource" is an APPResource AFTER: NONE
   * 
   * 
   * 
   * @param post
   *          The event type.
   * @param rb
   *          The passed response builder
   * @param data
   *          The passed data map
   * @returns Boolean value indicating whether or not execution should continue.
   */
  @Override
  public boolean visit(Post post, ResponseBuilder rb, Map<String, Object> data) {
    APPResource resource = (APPResource) data.get(RESOURCE_KEY);
    rb.entity(resource.getXmlObject());
    return true;
  }

  /**
   * When this visitor encounters a PUT request, it gets the resource that has been updated and places it in the body of
   * the request.
   * 
   * DATA MAP CONTRACT: BEFORE: "UpdatedResource" is an APPResource AFTER: NONE
   * 
   * @param put
   *          The event type.
   * @param rb
   *          The passed response builder
   * @param data
   *          The passed data map
   * @returns Boolean value indicating whether or not execution should continue.
   */
  @Override
  public boolean visit(Put put, ResponseBuilder rb, Map<String, Object> data) {
    APPResource resource = (APPResource) data.get(RESOURCE_KEY);
    rb.entity(resource.getXmlObject());
    return true;
  }

  /**
   * When this visitor encounters a DELETE request. It does nothing.
   * 
   * DATA MAP CONTRACT: BEFORE: NONE AFTER: NONE
   * 
   * @param delete
   *          The event type.
   * @param rb
   *          The passed response builder
   * @param data
   *          The passed data map
   * @returns Boolean value indicating whether or not execution should continue.
   */
  @Override
  public boolean visit(Delete delete, ResponseBuilder rb, Map<String, Object> data) {
    //rb.entity(data.get("ResponseBody"));
    return true;
  }

}
