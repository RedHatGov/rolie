package gov.nist.rolie.polie.server.visitors;

import java.util.Map;

import javax.ws.rs.core.Response.ResponseBuilder;

import gov.nist.rolie.polie.model.models.APPResource;
import gov.nist.rolie.polie.server.event.Delete;
import gov.nist.rolie.polie.server.event.Get;
import gov.nist.rolie.polie.server.event.Post;
import gov.nist.rolie.polie.server.event.Put;
import gov.nist.rolie.polie.tools.utils.ROLIEResourceSerializer;

public class ResourceSerializerVisitor implements RESTEventVisitor {

	@Override
	public boolean visit(Get get, ResponseBuilder rb, Map<String, Object> data) {
		//data.put("ResponseBody", ((APPResource)data.get("RetrivedResource")).toXML());
		data.put("ResponseBody","Crippled but working!");
		return true;
		
	}

	@Override
	public boolean visit(Post post, ResponseBuilder rb, Map<String, Object> data) {
//		data.put("ResponseBody", ((APPResource)data.get("CreatedResource")).toXML());
		data.put("ResponseBody","Crippled but working!");
		return true;
	}

	@Override
	public boolean visit(Put put, ResponseBuilder rb, Map<String, Object> data) {
//		data.put("ResponseBody", ROLIEResourceSerializer.serializeResource((APPResource)data.get("UpdatedResource")));
		data.put("ResponseBody","Crippled but working!");
		return true;
	}

	@Override
	public boolean visit(Delete delete, ResponseBuilder rb, Map<String, Object> data) {
		// TODO Auto-generated method stub
		return false;
	}

}
