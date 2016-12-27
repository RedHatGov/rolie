/*
 * 
 */
package gov.nist.rolie.polie.core.utils;

import java.util.Map;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import gov.nist.rolie.polie.core.event.RESTEvent;

// TODO: Auto-generated Javadoc
/**
 * The Class ROLIEHttpUtils.
 */
public class ROLIEErrorGenerator {
	

	//Instantiation Protector
	private ROLIEErrorGenerator(){};
	
	public static ResponseBuilder InvalidMethod(ResponseBuilder rb, RESTEvent method, Map<String, Object> data)
	{
		String message = "";
		message = "The method: " + method.getClass().getSimpleName() + " is not valid here";
		rb=rb.status(Status.BAD_REQUEST);
		rb=rb.entity(message);
		return rb;
	}
	


	
}
