package gov.nist.rolie.polie.core.servlet;

import java.io.IOException;

import javax.ws.rs.core.Response;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import gov.nist.rolie.polie.core.event.Delete;
import gov.nist.rolie.polie.core.event.Get;
import gov.nist.rolie.polie.core.event.Post;
import gov.nist.rolie.polie.core.event.Put;
import gov.nist.rolie.polie.core.event.RESTEventVisitor;
import gov.nist.rolie.polie.core.utils.*;

public class RESTEventValidationVisitor implements RESTEventVisitor {

	@Override
	public Response visit(Get get) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response visit(Post post) {
		String body = post.getBody();
		Response.ResponseBuilder rb = Response.status(200);
		try {
			RolieModelValidator.validate(ROLIEDocumentUtils.buildDocFromText(body));
		} catch (ParserConfigurationException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		} 
		catch (SAXException e) {
			rb=Response.status(400);
			rb.entity("XML Invalid");
		}
		
		return rb.build();
	}

	@Override
	public Response visit(Put put) {
		String body = put.getBody();
		Response.ResponseBuilder rb = Response.status(200);
		try {
			RolieModelValidator.validate(ROLIEDocumentUtils.buildDocFromText(body));
		} catch (ParserConfigurationException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		} 
		catch (SAXException e) {
			rb=Response.status(400);
			rb.entity("XML Invalid");
		}
		
		return rb.build();
	}

	@Override
	public Response visit(Delete delete) {
		// TODO Auto-generated method stub
		return null;
	}

}
