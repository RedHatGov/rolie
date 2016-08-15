package gov.nist.rolie.polie.core.event;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

public class Get implements RESTEvent {
	
	private String body;
	private String uri;
	private HttpHeaders headers;
	
	public Get(HttpHeaders headers,String body,String uri)
	{
		this.body=body;
		this.uri=uri;
		this.headers=headers;
	}

	public String getBody()
	{
		return this.body;
	}
	
	public String getURI()
	{
		return this.uri;
	}
	
	public Response accept(RESTEventVisitor RESTEventVisitor) {
		return RESTEventVisitor.visit(this);
	}

	public HttpHeaders getHeaders() {
		return this.headers;
	}

}
