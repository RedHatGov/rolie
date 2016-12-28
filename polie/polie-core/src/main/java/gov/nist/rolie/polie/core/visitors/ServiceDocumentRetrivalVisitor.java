package gov.nist.rolie.polie.core.visitors;

import java.net.URI;
import java.util.Map;

import javax.ws.rs.core.Response.ResponseBuilder;

import gov.nist.rolie.polie.core.database.DummyPersist;
import gov.nist.rolie.polie.core.database.PersistenceMethod;
import gov.nist.rolie.polie.core.database.TextPersist;
import gov.nist.rolie.polie.core.event.Delete;
import gov.nist.rolie.polie.core.event.Get;
import gov.nist.rolie.polie.core.event.Post;
import gov.nist.rolie.polie.core.event.Put;
import gov.nist.rolie.polie.core.models.APPServiceDocument;

/**
 * 
 * The visitor that handles service document retrieval.
 * 
 * @author sab3
 *
 */
public class ServiceDocumentRetrivalVisitor implements RESTEventVisitor {
	
	/**The persistence method is declared here. If a new persistence method is written it can be swapped out here
	*To apply to all requests.
	*/
	static PersistenceMethod database = new DummyPersist();
	
	/** 
	 * When executed by the visitor manager, this visitor loads the service document from the given
	 * IRI (in this case {server}/polie-core/rolie/serviceydocument.
	 * 
	 * It then places the Retrieved resource in the data map, notes a OK response status (200), and returns.
	 * 
	 * DATA MAP CONTRACT: 
	 * BEFORE: 
	 * 		"IRI" is an absolute path to the service document.
	 * AFTER:
	 * 		"RetrievedResource" holds the APPServiceDocument for the repo.
	 * 
	 * @param get The event type.
	 * @param rb The passed response builder
	 * @param data The passed data map
	 * @returns Boolean value indicating whether or not execution should continue.
	 */
	@Override
	public boolean visit(Get get, ResponseBuilder rb, Map<String, Object> data) {
		APPServiceDocument serviceDocument = (database.loadServiceDocument((URI)data.get("IRI")));
		data.put("RetrivedResource", serviceDocument);
		rb.status(200);
		return true;
	}

	//--------------------------------------------------------------------------------------------------
	/**Unreachable block. These requests are invalid for this resource.*/
	@Override
	public boolean visit(Post post, ResponseBuilder rb, Map<String, Object> data) {return false;}
	/**Unreachable block. These requests are invalid for this resource.*/
	@Override
	public boolean visit(Put put, ResponseBuilder rb, Map<String, Object> data) {return false;}
	/**Unreachable block. These requests are invalid for this resource.*/
	@Override
	public boolean visit(Delete delete, ResponseBuilder rb, Map<String, Object> data) {return false;}
	//-----------------------------------------------------------------------------------------------------
}
