/*
 * 
 */
package gov.nist.rolie.polie.core.utils;

import javax.ws.rs.core.HttpHeaders;

// TODO: Auto-generated Javadoc
/**
 * The Class ROLIEHttpUtils.
 */
public class ROLIEHttpUtils {
	
	/**
	 * Instantiates a new ROLIE http utils.
	 */
	//Instantiation Protector
	private ROLIEHttpUtils(){};
	
	/**
	 * Parses the headers.
	 *
	 * @param headers the headers
	 * @return the string
	 */
	public static String parseHeaders(HttpHeaders headers)
	{
		return headers.getRequestHeaders().toString();
	}
	
}
