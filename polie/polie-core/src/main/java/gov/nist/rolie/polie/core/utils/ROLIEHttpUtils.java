package gov.nist.rolie.polie.core.utils;

import javax.ws.rs.core.HttpHeaders;

public class ROLIEHttpUtils {
	
	//Instantiation Protector
	private ROLIEHttpUtils(){};
	
	public static String parseHeaders(HttpHeaders headers)
	{
		return headers.getRequestHeaders().toString();
	}
	
}
