package gov.nist.rolie.polie.core.event;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

public abstract class AbstractRESTEvent implements RESTEvent {
	private String uri;
	private HttpHeaders headers;
	

	
	public AbstractRESTEvent(String uri, HttpHeaders headers) {
		this.uri = uri;
		this.headers = headers;
	}

	public String getURI()
	{
		return this.uri;
	}
	
	public HttpHeaders getHeaders() {
		return this.headers;
	}
	


}
