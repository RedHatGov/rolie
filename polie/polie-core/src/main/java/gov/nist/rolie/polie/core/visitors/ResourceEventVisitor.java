package gov.nist.rolie.polie.core.visitors;

import java.net.URI;
import java.util.Map;

import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import gov.nist.rolie.polie.core.database.PersistenceMethod;
import gov.nist.rolie.polie.core.database.TextPersist;
import gov.nist.rolie.polie.core.event.Delete;
import gov.nist.rolie.polie.core.event.Get;
import gov.nist.rolie.polie.core.event.Post;
import gov.nist.rolie.polie.core.event.Put;
import gov.nist.rolie.polie.core.models.AtomResource;
import gov.nist.rolie.polie.core.models.elements.AtomCollection;
import gov.nist.rolie.polie.core.utils.ResourceBuilder;

public class ResourceEventVisitor implements RESTEventVisitor {

	static PersistenceMethod database = new TextPersist();
	
	@Override
	public boolean visit(Get get, ResponseBuilder rb, Map<String, Object> data) {
		AtomResource resource = database.loadResource((URI)data.get("IRI"));
		data.put("RetrivedResource", resource);
		return true;
	}

	@Override
	public boolean visit(Post post, ResponseBuilder rb, Map<String, Object> data) {
		AtomResource resource = (AtomResource)data.get("resource");
		AtomResource createdResource = database.createResource(resource,(URI)data.get("IRI"));
		rb=rb.status(Status.CREATED);
		data.put("CreatedResourceLocationIRI", (URI)data.get("IRI")); //TODO FIX THIS
		rb=rb.header("Location", (URI)data.get("CreatedResourceLocationIRI"));
		data.put("CreatedResource",createdResource);
		return true;
	}

	@Override
	public boolean visit(Put put, ResponseBuilder rb, Map<String, Object> data) {
		AtomResource resource = (AtomResource)data.get("resource");
		AtomResource updatedResource = database.updateResource(resource,(URI)data.get("IRI"));
		rb=rb.status(Status.OK);
		rb.header("Location", data.get("LocationIRI"));
		data.put("updatedResource",updatedResource);
		return true;
	}

	@Override
	public boolean visit(Delete delete, ResponseBuilder rb, Map<String, Object> data) {
		database.deleteResource((URI)data.get("IRI"));
		rb=rb.status(Status.OK);
		return true;
	}

}
