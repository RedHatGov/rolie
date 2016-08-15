package gov.nist.rolie.polie.core.servlet;

import javax.ws.rs.core.Response;

import gov.nist.rolie.polie.core.event.Delete;
import gov.nist.rolie.polie.core.event.Get;
import gov.nist.rolie.polie.core.event.Post;
import gov.nist.rolie.polie.core.event.Put;
import gov.nist.rolie.polie.core.event.RESTEventVisitor;
import gov.nist.rolie.polie.core.utils.ROLIEHttpUtils;;
public class RESTEventTestVisitor implements RESTEventVisitor {

	@Override
	public Response visit(Delete delete) {
		Response.ResponseBuilder rb = Response.status(200);
		String uri = delete.getURI();
		String body = delete.getBody();
		String headers = ROLIEHttpUtils.parseHeaders(delete.getHeaders());
		rb.entity("This is the test visitor."
				+ "\nThis was a Get request."
				+ "\nThe URI passed is " + uri 
				+ "\nThe body of the request is " + body
				+ "\nThe headers of the request are " + headers);
		return rb.build();
	}

	@Override
	public Response visit(Get get) 
	{
		Response.ResponseBuilder rb = Response.status(200);
		String uri = get.getURI();
		String body = get.getBody();
		String headers = ROLIEHttpUtils.parseHeaders(get.getHeaders());
		rb.entity("This is the test visitor."
				+ "\nThis was a Get request."
				+ "\nThe URI passed is " + uri 
				+ "\nThe body of the request is " + body
				+ "\nThe headers of the request are " + headers);
		return rb.build();
	}

	@Override
	public Response visit(Post post) 
	{
		Response.ResponseBuilder rb = Response.status(200);
		String uri = post.getURI();
		String body = post.getBody();
		String headers = ROLIEHttpUtils.parseHeaders(post.getHeaders());
		rb.entity("This is the test visitor."
				+ "\nThis was a Get request."
				+ "\nThe URI passed is " + uri 
				+ "\nThe body of the request is " + body
				+ "\nThe headers of the request are " + headers);
		return rb.build();
	}

	@Override
	public Response visit(Put put) 
	{
		Response.ResponseBuilder rb = Response.status(200);
		String uri = put.getURI();
		String body =put.getBody();
		String headers = ROLIEHttpUtils.parseHeaders(put.getHeaders());
		rb.entity("This is the test visitor."
				+ "\nThis was a Get request."
				+ "\nThe URI passed is " + uri 
				+ "\nThe body of the request is " + body
				+ "\nThe headers of the request are " + headers);
		return rb.build();
	}

}
