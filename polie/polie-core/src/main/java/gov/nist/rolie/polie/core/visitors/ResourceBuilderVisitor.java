package gov.nist.rolie.polie.core.visitors;

import java.util.Map;

import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import gov.nist.rolie.polie.core.XMLMangement.JAXBXMLResourceInterface;
import gov.nist.rolie.polie.core.XMLMangement.XMLResourceInterface;
import gov.nist.rolie.polie.core.event.Delete;
import gov.nist.rolie.polie.core.event.Get;
import gov.nist.rolie.polie.core.event.Post;
import gov.nist.rolie.polie.core.event.Put;
import gov.nist.rolie.polie.core.exceptions.FailedToBuildResourceException;
import gov.nist.rolie.polie.core.utils.ROLIEResourceBuilder;

/**
 * When added to the visitor execution chain, this visitor consumes the body of the request
 * to generate am internal Atom model of the data.
 * 
 * @author sab3
 *
 */
public class ResourceBuilderVisitor implements RESTEventVisitor {
	
	private static XMLResourceInterface ri = new JAXBXMLResourceInterface();

	/** 
	 * When added to the visitor execution chain, this visitor consumes the body of the request
	 * to generate am internal Atom model of the data.
	 * 
	 * The model is placed in the data map at "resource"
	 * 
	 * DATA MAP CONTRACT: 
	 * BEFORE: 
	 * 		"body" holds the content of the request. It is also assumed that the body is ROLIE valid XML
	 * AFTER:
	 * 		"resource" holds the constructed APPResource.
	 * 
	 * @param POST The event type.
	 * @param rb The passed response builder
	 * @param data The passed data map
	 * @returns Boolean value indicating whether or not execution should continue.
	 */
	@Override
	public boolean visit(Post post, ResponseBuilder rb, Map<String, Object> data) {
		try {
			data.put("resource", ri.XMLToResource(data.get("body")));
			return true;
		} catch (FailedToBuildResourceException e) {
			rb.status(Status.SEE_OTHER);
			e.printStackTrace();
			return false;
		}
	}
	/** 
	 * When added to the visitor execution chain, this visitor consumes the body of the request
	 * to generate am internal Atom model of the data.
	 * 
	 * The model is placed in the data map at "resource"
	 * 
	 * DATA MAP CONTRACT: 
	 * BEFORE: 
	 * 		"body" holds the content of the request. It is also assumed that the body is ROLIE valid XML
	 * AFTER:
	 * 		"resource" holds the constructed APPResource.
	 * 
	 * @param PUT The event type.
	 * @param rb The passed response builder
	 * @param data The passed data map
	 * @returns Boolean value indicating whether or not execution should continue.
	 */
	@Override
	public boolean visit(Put put, ResponseBuilder rb, Map<String, Object> data) {
		try {
			data.put("resource", ROLIEResourceBuilder.buildFromText((String)data.get("body")));
		} catch (FailedToBuildResourceException e) {
			rb.status(Status.SEE_OTHER);
			e.printStackTrace();
			return false;
		}
		return true;
	}

	
	
	
	/**
	 * Get requests should never have a body, and the body should never be consumed.
	 * This visitor will cease execution if a get request is encountered.
	 */
	@Override
	public boolean visit(Get get, ResponseBuilder rb, Map<String, Object> data) {
		return false;
	}
	/**
	 * Get requests should never have a body, and the body should never be consumed.
	 * This visitor will cease execution if a get request is encountered.
	 */
	@Override
	public boolean visit(Delete delete, ResponseBuilder rb, Map<String, Object> data) {
		return false;
	}

	
}
