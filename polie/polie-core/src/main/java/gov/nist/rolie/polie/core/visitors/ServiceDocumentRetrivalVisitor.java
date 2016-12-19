package gov.nist.rolie.polie.core.visitors;

import java.util.Map;

import javax.ws.rs.core.Response.ResponseBuilder;

import gov.nist.rolie.polie.core.database.PersistenceMethod;
import gov.nist.rolie.polie.core.database.TextPersist;
import gov.nist.rolie.polie.core.event.Delete;
import gov.nist.rolie.polie.core.event.Get;
import gov.nist.rolie.polie.core.event.Post;
import gov.nist.rolie.polie.core.event.Put;
import gov.nist.rolie.polie.core.models.ServiceDocument;

public class ServiceDocumentRetrivalVisitor implements RESTEventVisitor {

	static PersistenceMethod database = new TextPersist();
	
	@Override
	public boolean visit(Get get, ResponseBuilder rb, Map<String, Object> data) {
		String path = (String)data.get("path");
		//Collection collection = (database.loadCollection(path));
		data.put("body", database.loadServiceDocument(path));
		rb.status(200);
		return true;
	}

	@Override
	public boolean visit(Post post, ResponseBuilder rb, Map<String, Object> data) {
		// TODO Auto-generated method stub
		return false;
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
