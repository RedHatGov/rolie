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

import java.util.Map;

import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import gov.nist.jrolie.atom.logic.InternalServerError;
import gov.nist.jrolie.atom.logic.MismatchedCategoriesException;
import gov.nist.jrolie.atom.logic.services.EntryService;
import gov.nist.jrolie.atom.logic.services.FeedService;
import gov.nist.jrolie.atom.logic.services.ResourceService;
import gov.nist.jrolie.atom.logic.services.ServiceDocumentService;
import gov.nist.jrolie.model.JCategoryDocument;
import gov.nist.jrolie.model.JEntry;
import gov.nist.jrolie.model.JFeed;
import gov.nist.jrolie.model.JResource;
import gov.nist.jrolie.model.JServiceDocument;
import gov.nist.jrolie.model.impl.JCategoryDocumentImpl;
import gov.nist.jrolie.model.impl.JCategoryImpl;
import gov.nist.jrolie.model.impl.JEntryImpl;
import gov.nist.jrolie.model.impl.JFeedImpl;
import gov.nist.jrolie.model.impl.JServiceDocumentImpl;
import gov.nist.jrolie.persistence.api.exceptions.InvalidResourceTypeException;
import gov.nist.jrolie.persistence.api.exceptions.ResourceAlreadyExistsException;
import gov.nist.jrolie.persistence.api.exceptions.ResourceNotFoundException;
import gov.nist.jrolie.server.event.Delete;
import gov.nist.jrolie.server.event.Get;
import gov.nist.jrolie.server.event.Post;
import gov.nist.jrolie.server.event.Put;
import gov.nist.jrolie.server.servlet.config;

/**
 * Primary driver of resource requests in JROLIE, aka "Where the magic happens".
 * Consolidated in one visitor because the request type implies the valid
 * resource types.
 *
 * @author sab3
 *
 */
@Component
public class ResourceEventVisitor implements RESTEventVisitor { //TODO: Transactional Consistency (annotations, etc)
	private static final Logger log = LogManager.getLogger(ResourceEventVisitor.class);
	private final String context = System.getProperty("SERVER_ROOT");

	@Autowired
	private ResourceService resourceService;

	@Autowired
	private FeedService fs;

	@Autowired
	private EntryService es;

	@Autowired
	private ServiceDocumentService ss;
	
	@Autowired
	private ResourceService rs;

	protected void setResourceService(ResourceService rs) {
		this.resourceService = rs;
	}
	
	protected void setServiceService(ServiceDocumentService ss) {
		this.ss=ss;
	}

	protected void setFs(FeedService fs) {
		this.fs = fs;
	}

	protected void setEs(EntryService es) {
		this.es = es;
	}

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
			resource = this.resourceService.load(this.resourceService.pathToId(path));
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
	 * When this visitor encounters a POST request, we can assume that the target is
	 * a feed. The body of the request needs to be created and added to the Feed as
	 * a member resource.
	 *
	 * Header Considerations: "Location" is set to the location the resource was
	 * created at.
	 *
	 *
	 * @param post The event type.
	 * @param rb   The passed response builder
	 * @param data The passed data map
	 * @returns Boolean value indicating whether or not execution should continue.
	 */
	@Override
	public boolean visit(Post post, ResponseBuilder rb, Map<String, Object> data) {

		JEntry e = new JEntryImpl();
		JFeed f = new JFeedImpl();

		final String path = post.getURIInfo().getPath();

		// We first need to load the Feed we are targeting.
		try {
			f = this.fs.load(this.resourceService.pathToId(path));
		} catch (final ResourceNotFoundException e1) {
			rb.status(Status.NOT_FOUND);
			rb.entity("Resource not found at database location: " + e1.getResourceLocation());
			return false;
		} catch (final InvalidResourceTypeException e1) {
			rb.status(Status.METHOD_NOT_ALLOWED);
			rb.entity("The requested resource is not a valid feed to POST to.");
			return false;
		} catch (final InternalServerError e1) {
			rb.status(Status.INTERNAL_SERVER_ERROR);
			rb.entity("Unexpected Error:\n" + e1.getStackTrace());
			return false;
		}

		e = (JEntryImpl) data.get(RESOURCE_KEY); // The resource that was generated by the validation visitor

		// Modify the submitted entry according to server requirements
		try {
			this.es.setLink(e, "feed", this.context + f.getPath());
			e.setFeedID(f.getId());
			this.es.create(e);
			rb.header("Location", this.context + e.getPath());
		} catch (final ResourceAlreadyExistsException e1) {
			rb.status(Status.METHOD_NOT_ALLOWED);
			rb.entity("This entry already exists");
			return false;
		} catch (final InternalServerError e1) {
			rb.status(Status.INTERNAL_SERVER_ERROR);
			rb.entity("Unexpected Error:\n" + e1.getStackTrace());
			return false;
		}

		// If enabled, archive the feed, then add the entry to the feed.
		try {
			if (System.getProperty("FEED_ARCHIVE_MODE").equals("FULL")) {
				this.fs.archive(f); //TODO: Check order of operations for request consistency
				//TODO:Change Archive to return updated feed, not save it to DB itself
			}
			this.fs.addEntry(f, e);
		} catch (InternalServerError | ResourceNotFoundException | InvalidResourceTypeException
				| ResourceAlreadyExistsException e2) {
			rb.status(Status.INTERNAL_SERVER_ERROR);
			rb.entity("Unexpected Error:\n" + e2.getStackTrace());
			return false;
		} catch (final MismatchedCategoriesException e1) {
			rb.status(Status.INTERNAL_SERVER_ERROR);
			rb.entity("This Entry does not have matching cateogries"); //TODO: Add the valid categories in the error message.
			return false;
		}

		// Complete the process by updating the feed with new date/other info
		try {
			this.fs.update(f);
		} catch (final ResourceNotFoundException e1) {
			rb.status(Status.NOT_FOUND);
			rb.entity("This Feed doesnt exist");
			return false;
		} catch (final InternalServerError e1) {
			rb.status(Status.INTERNAL_SERVER_ERROR);
			rb.entity("Unexpected Error:\n" + e1.getStackTrace());
			return false;
		}

		// The entry, post modification, is passed onwards (to the response builder)
		rb.status(Status.CREATED);
		data.put(RESOURCE_KEY, e);
		return true;
	}

	/**
	 * When this visitor encounters a PUT request, we can assume that the target is
	 * a resource. The body of the request will be used to overwrite the target
	 * resource.
	 * <p>
	 * Header Considerations: "Location" is set to the location the resource was
	 * created at.
	 * <p>
	 *
	 * @param put  The event type.
	 * @param rb   The passed response builder
	 * @param data The passed data map
	 * @returns Boolean value indicating whether or not execution should continue.
	 */
	@Override
	public boolean visit(Put put, ResponseBuilder rb, Map<String, Object> data) {

		final String path = put.getURIInfo().getPath();

		Object obj = data.get(RESOURCE_KEY); // The resource that was generated by the validation visitor
		
		if (obj instanceof JEntryImpl)
		{
			JEntry e = (JEntryImpl) obj;
			e.setPath(config.ENTRY_PREFIX + this.rs.sanitize(e.getId()));
			try {
				es.update(e);
			} catch (ResourceNotFoundException e1) {
				rb.status(Status.NOT_FOUND);
				rb.entity("This Entry doesn't exist");
				return false;
			} catch (InternalServerError e1) {
				rb.status(Status.INTERNAL_SERVER_ERROR);
				rb.entity("Unexpected Error:\n" + e1.getStackTrace());
				return false;
			}
			rb.status(Status.CREATED);
			data.put(RESOURCE_KEY, e);
			return true;
		}
		/**
		 * Should this process change the Entries in the feed? Does the PUT body need to have the entries in it?
		 * There should probably be a way to just change the headers.
		 */
		if (obj instanceof JFeedImpl)
		{
			JFeed f = (JFeedImpl) obj;
			f.setPath(config.FEED_PREFIX + this.rs.sanitize(f.getId()));
			try {
				fs.update(f);
			} catch (ResourceNotFoundException e) {
				rb.status(Status.NOT_FOUND);
				rb.entity("This Feed doesn't exist");
				return false;
			} catch (InternalServerError e1) {
				rb.status(Status.INTERNAL_SERVER_ERROR);
				rb.entity("Unexpected Error:\n" + e1.getStackTrace());
				return false;
			}
			rb.status(Status.CREATED);
			data.put(RESOURCE_KEY, f);
			return true;
		}
		if (obj instanceof JServiceDocumentImpl)
		{
			JServiceDocument s = (JServiceDocumentImpl) obj;
			s.setPath(config.SERVICE_PREFIX + this.rs.sanitize(s.getId()));
			try {
				ss.update(s);
			} catch (ResourceNotFoundException e) {
				rb.status(Status.NOT_FOUND);
				rb.entity("This Service Document doesn't exist");
				return false;
			} catch (InternalServerError e) {
				rb.status(Status.INTERNAL_SERVER_ERROR);
				rb.entity("Unexpected Error:\n" + e.getStackTrace());
				return false;
			}
			rb.status(Status.CREATED);
			data.put(RESOURCE_KEY, s);
			return true;
		}
		if (obj instanceof JCategoryImpl)
		{
			JCategoryDocument e = (JCategoryDocumentImpl) obj;
			rb.status(Status.NOT_IMPLEMENTED);
			return false;
		}
		
		// The entry, post modification, is passed onwards (to the response builder)
		rb.status(Status.CREATED);
		data.put(RESOURCE_KEY, obj);
		return true;
	}

	/**
	 * When this visitor encounters a DELETE request, the resource at the given IRI
	 * will be deleted. There is no needed consideration at this point as to what
	 * the resource is.
	 *
	 *
	 * @param delete The event type.
	 * @param rb     The passed response builder
	 * @param data   The passed data map
	 * @returns Boolean value indicating whether or not execution should continue.
	 */
	@Override
	public boolean visit(Delete delete, ResponseBuilder rb, Map<String, Object> data) {
		final String path = delete.getURIInfo().getPath();
		JResource resource;


		// A DELETE request only triggers one operation: delete the resource at the given
		// path
		try {
			//TODO: Fix Entry Removal - chain to feed
			resource = this.resourceService.delete(this.resourceService.pathToId(path));
		} catch (final ResourceNotFoundException e2) {
			rb.status(Status.NOT_FOUND);
			rb.entity("Resource not found at location: " + path);
			return false;
		} catch (final InternalServerError e1) {
			rb.status(Status.INTERNAL_SERVER_ERROR);
			rb.entity("Unexpected Error:\n" + e1.getStackTrace());
			return false;
		}
		data.put(RESOURCE_KEY, resource); // Store the deleted resource for processing by the next visitor
		rb.status(Status.OK);
		return true;
	}

}
