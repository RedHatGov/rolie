package gov.nist.rolie.polie.server.visitors;

import java.io.IOException;
import java.util.Map;

import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import gov.nist.rolie.polie.server.event.Delete;
import gov.nist.rolie.polie.server.event.Get;
import gov.nist.rolie.polie.server.event.Post;
import gov.nist.rolie.polie.server.event.Put;


public class ROLIEValidationVisitor implements RESTEventVisitor {

	//static ROLIEValidator validator = new DefaultROLIEValidator();

	@Override
	public boolean visit(Post post, ResponseBuilder rb, Map<String, Object> data) {
//		String content = (String)data.get("body");
//		
//		try {
//			validator.validate(content);
//			return true;
//		} catch (ParserConfigurationException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return false;
//		} catch (SAXException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			rb.entity("XML NOT VALID");
//			rb.status(Status.SEE_OTHER);
//			return false;
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return false;
//		}
		return true;
	}

	@Override
	public boolean visit(Put put, ResponseBuilder rb, Map<String, Object> data) {
//		String content = (String)data.get("body");
//		rb=rb.status(Status.BAD_REQUEST);
//		boolean success = false;
//		try {
//			success = validator.validate(content);
//		} catch (ParserConfigurationException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			success = false;
//		} catch (SAXException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			success = false;
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			success = false;
//		}
//		return success;
		return true;
	}
	

	@Override
	public boolean visit(Delete delete, ResponseBuilder rb, Map<String, Object> data) {
//		String content = (String)data.get("body");
//		rb=rb.status(Status.BAD_REQUEST);
//		boolean success = false;
//		try {
//			success = validator.validate(content);
//		} catch (ParserConfigurationException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			success = false;
//		} catch (SAXException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			success = false;
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			success = false;
//		}
//		return success;
		return true;
	}
	

	
	
	
	
	@Override
	public boolean visit(Get get, ResponseBuilder rb, Map<String, Object> data) {
		// TODO Auto-generated method stub
		return false;
	}
}
