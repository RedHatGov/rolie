package gov.nist.rolie.polie.core.event;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

public class Get extends AbstractRESTEvent implements RESTEvent {
	
	private String body;
	
	public Get(HttpHeaders headers,String body,String uri)
	{
		super(uri,headers);

	}
	
	@Override
	public Response accept(RESTEventVisitor RESTEventVisitor) {
		return RESTEventVisitor.visit(this);
	}

//	/rolie/servicedocument
//	/rolie/{feed-name}
//	/rolie/{feed-name}/{entry}
}
