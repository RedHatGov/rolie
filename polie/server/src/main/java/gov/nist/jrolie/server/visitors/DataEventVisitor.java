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

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import gov.nist.jrolie.atom.logic.InternalServerError;
import gov.nist.jrolie.atom.logic.LinkAlreadyExistsException;
import gov.nist.jrolie.atom.logic.services.ResourceService;
import gov.nist.jrolie.model.JData;
import gov.nist.jrolie.model.JResource;
import gov.nist.jrolie.persistence.api.exceptions.InvalidResourceTypeException;
import gov.nist.jrolie.persistence.api.exceptions.ResourceAlreadyExistsException;
import gov.nist.jrolie.persistence.api.exceptions.ResourceNotFoundException;
import gov.nist.jrolie.server.event.Delete;
import gov.nist.jrolie.server.event.Get;
import gov.nist.jrolie.server.event.Post;
import gov.nist.jrolie.server.event.Put;

/**
 * Primary driver of data actions. Fired from the DataEvent.
 *
 * @author sab3
 *
 */
@Component
public class DataEventVisitor implements RESTEventVisitor { // TODO:

	@Autowired
	ResourceService rs;

	/**
	 * When this visitor encounters a get request, the resource at the given IRI can
	 * be loaded. There is no needed consideration at this point as to what the
	 * resource is.
	 * 
	 * It then places the Retrieved resource in the data map and returns.
	 * 
	 * 
	 * @param get  The event type.
	 * @param rb   The passed response builder
	 * @param data The passed data map
	 * @returns Boolean value indicating whether or not execution should continue.
	 */
	@Override
	public boolean visit(Get get, ResponseBuilder rb, Map<String, Object> data) {
		
		final String path = get.getURIInfo().getPath();
		JResource resource;

		// A GET request only triggers one operation: load the resource at the given
		// path
		try {
			resource = this.rs.load(this.rs.pathToId(path)); //TODO: Pull media type from "content" element OR store original media type
		//TODO: Add mismatched content type warnings
		} catch (final ResourceNotFoundException e) {
			rb.status(Status.NOT_FOUND);
			rb.entity("Resource not found at location: " + path);
			return false;
		} catch (final InvalidResourceTypeException e) {
			rb.status(Status.METHOD_NOT_ALLOWED);
			rb.entity("Wrong resource tpye");
			return false;
		} catch (final InternalServerError e) {
			rb.status(Status.INTERNAL_SERVER_ERROR);
			rb.entity("Unexpected Error:\n" + e.getStackTrace());
			return false;
		}
		data.put(RESOURCE_KEY, resource); // Store the retrieved resource for processing by the next visitor
		rb.status(Status.OK);
		return true;
	}

	/**
	 * Store the body of the POST request at the given target URL
	 * 
	 * @param post The event type.
	 * @param rb   The passed response builder
	 * @param data The passed data map
	 * @returns Boolean value indicating whether or not execution should continue.
	 */
	@Override
	public boolean visit(Post post, ResponseBuilder rb, Map<String, Object> dataMap) {

		JResource jr = null;

		final String path = post.getURIInfo().getPath();

		JData data = new JData();
		data.setData(post.getBody());
		data.setId(path);
		data.setPath(path);
		
		try {
			rs.create(data);
		} catch (ResourceAlreadyExistsException | InternalServerError e) {
			rb.status(Status.INTERNAL_SERVER_ERROR);
			rb.entity("Unexpected Error:\n" + e.getStackTrace());
			return false;
		}
		rb.status(Status.CREATED);
		dataMap.put(RESOURCE_KEY, data);
		return true;

	}

	@Override
	public boolean visit(Put put, ResponseBuilder rb, Map<String, Object> data) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean visit(Delete delete, ResponseBuilder rb, Map<String, Object> data) {
		// TODO Auto-generated method stub
		return false;
	}

}
