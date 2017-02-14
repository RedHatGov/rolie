package gov.nist.rolie.polie.server.event;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.UriInfo;

public abstract class AbstractRESTEvent implements RESTEvent {
	

	private UriInfo uriInfo;
	private HttpHeaders headers;
	
	public AbstractRESTEvent(HttpHeaders headers, UriInfo uriInfo) 
	{
		this.uriInfo = uriInfo;
		this.headers = headers;
	}

	public UriInfo getURIInfo()
	{
		return this.uriInfo;
	}
	
	public HttpHeaders getHeaders() {
		return this.headers;
	}
	


}
