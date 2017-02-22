package gov.nist.rolie.polie.server.servlet;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.jboss.resteasy.specimpl.ResponseBuilderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import gov.nist.rolie.polie.atomLogic.modelServices.ModuleStringMakerInterface;

@Component

public class resteasyServlet {

	@Autowired
	StringMaker sm;
	
	@Autowired
	ModuleStringMakerInterface msm;
	
	@GET
	public Response testGet()
	{
		ResponseBuilder rb = new ResponseBuilderImpl();
		rb.entity(msm.makeModuleString());
		return rb.build();
	}
	
	
}
