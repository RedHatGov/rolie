package gov.nist.rolie.polie.core.visitors;

import java.util.Map;

import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import gov.nist.rolie.polie.core.event.Delete;
import gov.nist.rolie.polie.core.event.Get;
import gov.nist.rolie.polie.core.event.Post;
import gov.nist.rolie.polie.core.event.Put;
import gov.nist.rolie.polie.core.exceptions.FailedToSerializeResourceException;
import gov.nist.rolie.polie.core.models.APPResource;
import gov.nist.rolie.polie.core.utils.ROLIEResourceSerializer;

public class ResourceSerializerVisitor implements RESTEventVisitor {

	@Override
	public boolean visit(Get get, ResponseBuilder rb, Map<String, Object> data) {
		try {
			data.put("ResponseBody", ((APPResource)data.get("RetrivedResource")).toXML());
			return true;
		} catch (FailedToSerializeResourceException e) {
			rb.status(Status.SEE_OTHER);
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public boolean visit(Post post, ResponseBuilder rb, Map<String, Object> data) {
		try {
			data.put("ResponseBody", ((APPResource)data.get("CreatedResource")).toXML());
			return true;
		} catch (FailedToSerializeResourceException e) {
			rb.status(Status.SEE_OTHER);
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean visit(Put put, ResponseBuilder rb, Map<String, Object> data) {
		data.put("ResponseBody", ROLIEResourceSerializer.serializeResource((APPResource)data.get("UpdatedResource")));
		return true;
	}

	@Override
	public boolean visit(Delete delete, ResponseBuilder rb, Map<String, Object> data) {
		// TODO Auto-generated method stub
		return false;
	}

}
