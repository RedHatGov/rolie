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

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import gov.nist.jrolie.server.event.Delete;
import gov.nist.jrolie.server.event.Get;
import gov.nist.jrolie.server.event.Post;
import gov.nist.jrolie.server.event.Put;
import gov.nist.jrolie.server.event.RESTEvent;

//import gov.nist.rolie.polie.server.visitors.DebugVisitor;
/**
 * One of three entry point Classes for the webapp. This class handles ALL
 * resource requests that are not category or service documents. The Path
 * annotation is a regex expression that should match all valid paths.
 *
 * In this case the path is everything after the server root
 * "{server}/polie-core/{path}"
 *
 *
 * @author sab3
 *
 */
@Component
@Path("/{exp: (data).+}")

public class DataEvent {
	private static final Logger log = LogManager.getLogger(DataEvent.class);

	/**
	 * The visitor manager is declared here. If a new visitor manager is written is
	 * can be swapped out here To apply to all requests.
	 */
	@Autowired
	private DataVisitorManagerFactory vmFactory;


	public DataEvent() {

	}


	/**
	 * Code that executes when the server receives a GET request on the /data path.
	 * 
	 * @param headers Automatically populated with a map of all headers.
	 * @param uriInfo Automatically populated with a variety of URI data fields
	 * @return Returns the completed Response that is passed off to the server to be
	 *         sent back to the requester. At this point, the response is completed
	 *         and is handled all by the webapp.
	 */
	@GET
	public Response get(@Context HttpHeaders headers, @Context UriInfo uriInfo) {

		log.debug("Processing GET request, access through data resource.");

		final VisitorManager vm = this.vmFactory.getGetVisitorManager();

		// Generates a new Get event.
		final RESTEvent get = new Get(headers, uriInfo);

		// Starts the execution chain, returns a built response
		log.debug("Visitor chain init, executing data request.");

		return vm.execute(get);
	}

	/**
	 * Code that executes when the server receives a POST request on the /data path.
	 * 
	 * @param headers Automatically populated with a map of all headers.
	 * @param uriInfo Automatically populated with a variety of URI data fields
	 * @param body    Text from the content of the request. This is automatically
	 *                populated.
	 * @return Returns the completed Response that is passed off to the server to be
	 *         sent back to the requester. At this point, the response is completed
	 *         and is handled all by the webapp.
	 */
	@POST // TODO: Add atomsvc type support
	public Response post(@Context HttpHeaders headers, @Context UriInfo uriInfo, String body) {
		log.debug("Processing POST request");
		final RESTEvent post = new Post(headers, uriInfo, body);

		final VisitorManager vm = this.vmFactory.getPostVisitorManager();

		return vm.execute(post);
	}

	/**
	 * Code that executes when the server receives a PUT request on the /data path.
	 * 
	 * @param headers Automatically populated with a map of all headers.
	 * @param uriInfo Automatically populated with a variety of URI data fields
	 * @param body    Text from the content of the request. This is automatically
	 *                populated.
	 * @return Returns the completed Response that is passed off to the server to be
	 *         sent back to the requester. At this point, the response is completed
	 *         and is handled all by the webapp.
	 */
	@PUT
	public Response put(@Context HttpHeaders headers, @Context UriInfo uriInfo, String body) {
		log.debug("Processing PUT request");
		final RESTEvent put = new Put(headers, uriInfo, body);

		final VisitorManager vm = this.vmFactory.getPutVisitorManager();

		return vm.execute(put);
	}

	/**
	 * Code that executes when the server receives a DELETE request on the /data path.
	 * 
	 * @param headers Automatically populated with a map of all headers.
	 * @param uriInfo Automatically populated with a variety of URI data fields
	 * @return Returns the completed Response that is passed off to the server to be
	 *         sent back to the requester. At this point, the response is completed
	 *         and is handled all by the webapp.
	 */
	@DELETE
	public Response delete(@Context HttpHeaders headers, @Context UriInfo uriInfo) {
		log.debug("Processing DELETE request");
		final RESTEvent delete = new Delete(headers, uriInfo);

		final VisitorManager vm = this.vmFactory.getDeleteVisitorManager();

		return vm.execute(delete);
	}

}
