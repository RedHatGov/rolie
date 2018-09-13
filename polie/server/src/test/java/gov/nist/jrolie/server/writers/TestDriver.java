package gov.nist.jrolie.server.writers;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import gov.nist.jrolie.atom.logic.InternalServerError;
import gov.nist.jrolie.atom.logic.services.EntryService;
import gov.nist.jrolie.atom.logic.services.FeedService;
import gov.nist.jrolie.model.JContent;
import gov.nist.jrolie.model.JEntry;
import gov.nist.jrolie.model.JFeed;
import gov.nist.jrolie.model.JLink;
import gov.nist.jrolie.model.impl.JContentImpl;
import gov.nist.jrolie.model.impl.JEntryImpl;
import gov.nist.jrolie.model.impl.JEntryWrapper;
import gov.nist.jrolie.model.impl.JFeedImpl;
import gov.nist.jrolie.persistence.api.PersistenceContext;
import gov.nist.jrolie.persistence.api.exceptions.InvalidResourceTypeException;
import gov.nist.jrolie.persistence.api.exceptions.ResourceAlreadyExistsException;
import gov.nist.jrolie.persistence.api.exceptions.ResourceNotFoundException;

@Component
//@RunWith(SpringJUnit4ClassRunner.class) ADD SPRING4JUNIT OR SOMETHING
public class TestDriver {

	  public static final String sr = System.getProperty("SERVER_ROOT");
	  public static final String ep = System.getProperty("ENTRY_PREFIX");
	  public static final String fp = System.getProperty("FEED_PREFIX");
	  public static final String sp = System.getProperty("SERVICE_PREFIX");
	  public static final String cp = System.getProperty("CAT_PREFIX");
	
	  @Autowired
	  EntryService es;
	  
	  @Autowired
	  FeedService fs;
	  
	  @Autowired
	  PersistenceContext pc;
	  
	@Test
	public void test() throws URISyntaxException, InternalServerError, ResourceAlreadyExistsException, ResourceNotFoundException, InvalidResourceTypeException {
		JEntry e = es.create(createSimpleEntry());
		JFeed f = fs.create(createSimpleFeed());
		fs.archive(f);
		fs.addEntry(f, e);
	}

	private JEntry createSimpleEntry() throws URISyntaxException
	{
		JEntry e = new JEntryImpl();
		e.setId("TestEntry1");
		e.setPath(ep+e.getId());
		JContent content = new JContentImpl();
		content.setSrc(new URI("www.NVD.gov/swidtag"));
		e.setContent(content);
		return e;
	}
	
	private JFeed createSimpleFeed()
	{
		JFeed f = new JFeedImpl();
		f.setId("TestFeed1");
		f.setPath(fp+f.getId());
		f.setEntries(new ArrayList<JEntryWrapper>());
		f.setLinks(new ArrayList<JLink>());
		return f;
	}
	
}
