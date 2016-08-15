package gov.nist.rolie.polie.core.servlet;

import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.GET;
import javax.ws.rs.Path;


@Path("debug")
public class TestDebugger {
  
	@GET
	@Produces("text/plain")
	@Path("hello")
	public String hello() 
	{
		return "Hello World!";
	}
	
	@GET
	@Produces("text/plain")
	@Path("response")
	public Response response() 
	{
		Response.ResponseBuilder rb = Response.status(null);
		rb.status(0);
		rb.entity("Test Response!");
		return rb.build();
	}
}