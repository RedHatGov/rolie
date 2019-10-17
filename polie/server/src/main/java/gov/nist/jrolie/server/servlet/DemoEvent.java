package gov.nist.jrolie.server.servlet;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import gov.nist.jrolie.atom.logic.InternalServerError;
import gov.nist.jrolie.atom.logic.MismatchedCategoriesException;
import gov.nist.jrolie.atom.logic.services.EntryService;
import gov.nist.jrolie.atom.logic.services.FeedService;
import gov.nist.jrolie.atom.logic.services.ResourceService;
import gov.nist.jrolie.atom.logic.services.ServiceDocumentService;
import gov.nist.jrolie.model.JCollection;
import gov.nist.jrolie.model.JContent;
import gov.nist.jrolie.model.JData;
import gov.nist.jrolie.model.JEntry;
import gov.nist.jrolie.model.JFeed;
import gov.nist.jrolie.model.JServiceDocument;
import gov.nist.jrolie.model.JWorkspace;
import gov.nist.jrolie.model.impl.JCollectionImpl;
import gov.nist.jrolie.model.impl.JContentImpl;
import gov.nist.jrolie.model.impl.JServiceDocumentImpl;
import gov.nist.jrolie.model.impl.JWorkspaceImpl;
import gov.nist.jrolie.persistence.api.FileLoader;
import gov.nist.jrolie.persistence.api.exceptions.ResourceAlreadyExistsException;

/**
 * Provides an endpoint for triggering the demo code.
 * @author sab3
 *
 */
@Component
@Path("/demo")
public class DemoEvent {


	@Autowired
	private ServiceDocumentService ss;

	@Autowired
	private FeedService fs;

	@Autowired
	private EntryService es;

	@Autowired
	private ResourceService rs;
	
	@Produces({ "text/plain" })
	@POST
	@Consumes
	public Response get(@Context HttpHeaders headers, @Context UriInfo uriInfo) {
		ResponseBuilder rb = Response.status(Status.INTERNAL_SERVER_ERROR);
		try {
			rb = demo(rb);
		} catch (ResourceAlreadyExistsException | InternalServerError | MismatchedCategoriesException e) {
			rb.entity(e.getClass().getName());
		}
		
		return rb.build();
	
	}
	
	//TODO: Generate NVD CVE Feed from REST API for demo
	public ResponseBuilder demo(ResponseBuilder rb) throws ResourceAlreadyExistsException, InternalServerError, MismatchedCategoriesException {
		String response = "Starting Demo Buildout----------------------";
		
		
		JFeed swdFeed = FileLoader.loadFeed(
				"C:\\Users\\sab3\\Desktop\\TBFOMP\\ROLIEImpl\\IETF-ROLIE-POLIE\\polie\\server\\src\\main\\resources\\Demo\\Feeds\\SWDFeed.xml");
		fs.create(swdFeed);
		response += "\n[" + new Date(System.currentTimeMillis()) + "] Created Object " + swdFeed.getId()
		+ " at location " + swdFeed.getPath() + " with type " + swdFeed.getClass().toString();
		
		JEntry swdEntryNSRL = FileLoader.loadEntry(
				"C:\\Users\\sab3\\Desktop\\TBFOMP\\ROLIEImpl\\IETF-ROLIE-POLIE\\polie\\server\\src\\main\\resources\\Demo\\Entries\\NSRLSwidEntry.xml");
		swdEntryNSRL.setFeedID(swdFeed.getId());
		es.create(swdEntryNSRL);
		response += "\n[" + new Date(System.currentTimeMillis()) + "] Created Object " + swdEntryNSRL.getId()
		+ " at location " + swdEntryNSRL.getPath() + " with type " + swdEntryNSRL.getClass().toString();
		
		JData NSRLSwid = new JData();
		NSRLSwid.setId("NSRLSWID");
		NSRLSwid = (JData) rs.create(NSRLSwid);
		NSRLSwid.setData(FileLoader.loadData("C:\\Users\\sab3\\Desktop\\TBFOMP\\ROLIEImpl\\IETF-ROLIE-POLIE\\polie\\server\\src\\main\\resources\\Demo\\Content\\gov.nist.nsrl.steam.linux.windows.291550.1678990.-2.demo20170424.swidtag"));
		response += "\n[" + new Date(System.currentTimeMillis()) + "] Created Object " + NSRLSwid.getId()
		+ " at location " + NSRLSwid.getPath() + " with type " + NSRLSwid.getClass().toString();
		
		JContent content = new JContentImpl();
		try {
			content.setSrc(new URI("http://localhost:8080/server/rolie/data/NSRLSWID"));
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		content.setMediaType("application/xml+swid");
		
		
		swdEntryNSRL.setContent(content);
		
		fs.addEntry(swdFeed, swdEntryNSRL);
		response += "\n[" + new Date(System.currentTimeMillis()) + "] Added Entry " + swdEntryNSRL.getId()
		+ " to Feed " + swdFeed.getId();
		
		
		final JCollection c = new JCollectionImpl();
		c.setTitle(swdFeed.getTitle().getText());
		try {
			c.setHref(new URI(System.getProperties().getProperty("SERVER_ROOT") + swdFeed.getPath()));
		} catch (final URISyntaxException e1) {
			e1.printStackTrace();
		}

		final JWorkspace w = new JWorkspaceImpl();
		w.setCollections(new ArrayList<JCollection>());
		w.setTitle(("demoWorkspace"));
		w.getCollections().add(c);

		final JServiceDocument s = new JServiceDocumentImpl();
		s.setId("demoService");
		s.setPath("/s/demoService");
		s.setWorkspaces(new ArrayList<JWorkspace>());
		s.getWorkspaces().add(w);
		ss.create(s);
		response += "\n[" + new Date(System.currentTimeMillis()) + "] Created Object " + s.getId()
		+ " at location " + s.getPath() + " with type " + s.getClass().toString();
		

		
		response += "\nDemo Buildout Complete-------------------";
		rb.entity(response);
		rb.status(Status.CREATED);
		return rb;
		
	}
}
