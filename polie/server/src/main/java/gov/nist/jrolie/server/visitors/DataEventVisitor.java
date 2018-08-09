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

package gov.nist.jrolie.server.visitors;

import gov.nist.jrolie.atom.logic.LinkAlreadyExistsException;
import gov.nist.jrolie.atom.logic.services.DataService;
import gov.nist.jrolie.persistence.api.exceptions.InvalidResourceTypeException;
import gov.nist.jrolie.persistence.api.exceptions.ResourceAlreadyExistsException;
import gov.nist.jrolie.persistence.api.exceptions.ResourceNotFoundException;
import gov.nist.jrolie.server.event.Delete;
import gov.nist.jrolie.server.event.Get;
import gov.nist.jrolie.server.event.Post;
import gov.nist.jrolie.server.event.Put;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

/**
 * Primary driver of resource requests in POLIE. Consolidated in one visitor because the request type implies the valid
 * resource types.
 * 
 * @author sab3
 *
 */
@Component
public class DataEventVisitor implements RESTEventVisitor { // TODO:
  private static final Logger log = LogManager.getLogger(DataEventVisitor.class);

  @Autowired
  DataService dataService;

  /**
   * When this visitor encounters a get request, the resource at the given IRI can be loaded. There is no needed
   * consideration at this point as to what the resource is.
   * 
   * It then places the Retrieved resource in the data map and returns.
   * 
   * DATA MAP CONTRACT: BEFORE: "IRI" is an absolute path to the resource. AFTER: "resource" holds the APPResource at
   * the IRI.
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
  public boolean visit(Get get, ResponseBuilder rb, Map<String, Object> dataMap) {
    log.debug("Processing GET request");
    URI iri = get.getURIInfo().getAbsolutePath();
    String data = null;
    try {
      data = dataService.loadData(iri);
    } catch (ResourceNotFoundException e) {
      rb.status(Status.NOT_FOUND);
      rb.entity("Resource not found at database location: " + e.getResourceLocation());
      return false;
    } catch (InvalidResourceTypeException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    dataMap.put(RESOURCE_KEY, data);
    rb.status(Status.OK);
    rb.entity(data);
    return true;
  }

  /**
   * When this visitor encounters a POST request, we can assume that the target is a collection. The body of the request
   * needs to be created and added to the Collection as a member resource.
   * 
   * Header Considerations: "Location" is set to the location the resource was created at.
   * 
   * DATA MAP CONTRACT: BEFORE: "IRI" is an absolute path to the collection that the resource will be under. "resource"
   * holds a valid APPResource to be posted to a collection. AFTER: "CreatedResourceLocationIRI" holds the actual
   * location the resource was created at. NOTE: This MUST be set by the createResource() method. "CreatedResource"
   * holds the actual created representation of the resource.
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
  public boolean visit(Post post, ResponseBuilder rb, Map<String, Object> dataMap) {

    URI targetURI = post.getURIInfo().getAbsolutePath();
    String data = null;

    data = post.getBody();
    
    try {
      dataService.createData(data, targetURI);
    } catch (ResourceAlreadyExistsException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (LinkAlreadyExistsException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (URISyntaxException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    // Report success, store resource, and return
    rb.status(Status.CREATED);
    rb = rb.header("Location", targetURI);

    dataMap.put(RESOURCE_KEY, data);
    rb.entity(data);
    return true;

  }

  /**
   * When this visitor encounters a PUT request, we can assume that the target is a resource. The body of the request
   * will be used to overwrite the target resource.
   * <p>
   * Header Considerations: "Location" is set to the location the resource was created at.
   * <p>
   * DATA MAP CONTRACT: BEFORE: "IRI" is an absolute path to the resource to be updated "resource" holds a valid
   * APPResource that will overwrite the target AFTER: "UpdatedResource" holds the actual updated representation of the
   * resource.
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
  public boolean visit(Put put, ResponseBuilder rb, Map<String, Object> dataMap) {
    URI targetURI = put.getURIInfo().getAbsolutePath();
    String data = null;

    data = put.getBody();

    try {
      dataService.createData(data, targetURI);
    } catch (ResourceAlreadyExistsException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (LinkAlreadyExistsException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (URISyntaxException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    // Report success, store resource, and return
    rb.status(Status.CREATED);
    rb = rb.header("Location", targetURI);
    rb.entity(data);
    dataMap.put(RESOURCE_KEY, data);
    return true;

  }

  /**
   * When this visitor encounters a DELETE request, the resource at the given IRI will be deleted. There is no needed
   * consideration at this point as to what the resource is.
   * 
   * 
   * DATA MAP CONTRACT: BEFORE: "IRI" is an absolute path to the resource. AFTER: NONE
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
  public boolean visit(Delete delete, ResponseBuilder rb, Map<String, Object> dataMap) {
    log.debug("Processing GET request");
    URI iri = delete.getURIInfo().getAbsolutePath();
    String data = null;
    try {
      dataService.deleteData(iri);
    } catch (ResourceNotFoundException e) {
      rb.status(Status.NOT_FOUND);
      rb.entity("Resource not found at database location: " + e.getResourceLocation());
      return false;
    } catch (InvalidResourceTypeException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    rb.entity(data);
    dataMap.put(RESOURCE_KEY, data);
    rb.status(Status.OK);
    return true;
  }

}
