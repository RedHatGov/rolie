package gov.nist.rolie.polie.core.servlet;

import static org.junit.Assert.fail;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.Map;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;

import gov.nist.rolie.polie.server.event.Get;
import gov.nist.rolie.polie.server.servlet.AtomResourceEvent;
import gov.nist.rolie.polie.server.servlet.VisitorManager;
import gov.nist.rolie.polie.server.servlet.VisitorManagerFactory;


public class AtomResourceEventTest {
	@Rule
	public JUnitRuleMockery context = new JUnitRuleMockery();
	

	@Test
	@SuppressWarnings("unchecked")
	public void testGet() throws URISyntaxException {
//		 event = new AtomResourceEvent();
		HttpHeaders headers = context.mock(HttpHeaders.class);
		UriInfo uriInfo = context.mock(UriInfo.class);
		VisitorManagerFactory vmFactory = context.mock(VisitorManagerFactory.class);
		VisitorManager vm = context.mock(VisitorManager.class);
		MultivaluedMap<String, String> requestHeaders = context.mock(MultivaluedMap.class);
		AtomResourceEvent event = new AtomResourceEvent(vmFactory);

		context.checking(new Expectations() {{
			allowing(uriInfo).getPath(); will(returnValue("path"));
			allowing(uriInfo).getAbsolutePath(); will(returnValue(new URI("http://blah.com/")));
			allowing(headers).getRequestHeaders(); will(returnValue(requestHeaders));

			oneOf(vmFactory).GetGetVisitorManager(); will(returnValue(vm));
			oneOf(vm).execute(with(any(Get.class)), with(any(Map.class)));
//			oneOf (headers).getRequestHeaders();
//		    returnValue(requestHeaders);
//		    oneOf (requestHeaders).get(with("Text"));
		}});

		event.get(headers, uriInfo);
	}

	@Test
	@Ignore
	public void testPost() {
		fail("Not yet implemented");
	}

	@Test
	@Ignore
	public void testPut() {
		fail("Not yet implemented");
	}

	@Test
	@Ignore
	public void testDelete() {
		fail("Not yet implemented");
	}

}
