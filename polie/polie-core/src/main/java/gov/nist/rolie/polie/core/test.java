package gov.nist.rolie.polie.core;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("test")
public class test {
  
	@GET
	@Consumes("text/plain")
	@Produces("text/plain")
	@Path("test")
	public String add() 
	{
		return "Hello World!";
	}
}