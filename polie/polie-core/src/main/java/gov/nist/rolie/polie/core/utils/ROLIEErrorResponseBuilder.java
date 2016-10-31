/*
 * 
 */
package gov.nist.rolie.polie.core.utils;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

// TODO: Auto-generated Javadoc
/**
 * The Class ROLIEHttpUtils.
 */
public class ROLIEErrorResponseBuilder {
	

	//Instantiation Protector
	private ROLIEErrorResponseBuilder(){};
	
	public static ResponseBuilder status(int status)
	{
		ResponseBuilder rb = Response.status(status);
		rb.entity("ERROR BODY");
		//rb.header("", ""); Set headers to match defaults.
		return rb;
	}
	


	
}
