package gov.nist.rolie.polie.core.event;

import java.util.Map;

import javax.ws.rs.core.Response.ResponseBuilder;

import gov.nist.rolie.polie.core.visitors.RESTEventVisitor;

public interface RESTEvent {

	public boolean accept(RESTEventVisitor RESTEventVisitor, ResponseBuilder rb, Map<String, Object> data);
	
}
