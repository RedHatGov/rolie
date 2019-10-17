/**
 * Portions of this software was developed by employees of the National Institute
 * of Standards and Technology (NIST), an agency of the Federal Government.
 * Pursuant to title 17 United States Code Section 105, works of NIST employees are
 * not subject to copyright protection in the United States and are considered to
 * be in the public domain. Permission to freely use, copy, modify, and distribute
 * this software and its documentation without fee is hereby granted, provided that
 * this notice and disclaimer of warranty appears in all copies.
 *
 * THE SOFTWARE IS PROVIDED 'AS IS' WITHOUT ANY WARRANTY OF ANY KIND, EITHER
 * EXPRESSED, IMPLIED, OR STATUTORY, INCLUDING, BUT NOT LIMITED TO, ANY WARRANTY
 * THAT THE SOFTWARE WILL CONFORM TO SPECIFICATIONS, ANY IMPLIED WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, AND FREEDOM FROM
 * INFRINGEMENT, AND ANY WARRANTY THAT THE DOCUMENTATION WILL CONFORM TO THE
 * SOFTWARE, OR ANY WARRANTY THAT THE SOFTWARE WILL BE ERROR FREE. IN NO EVENT
 * SHALL NIST BE LIABLE FOR ANY DAMAGES, INCLUDING, BUT NOT LIMITED TO, DIRECT,
 * INDIRECT, SPECIAL OR CONSEQUENTIAL DAMAGES, ARISING OUT OF, RESULTING FROM, OR
 * IN ANY WAY CONNECTED WITH THIS SOFTWARE, WHETHER OR NOT BASED UPON WARRANTY,
 * CONTRACT, TORT, OR OTHERWISE, WHETHER OR NOT INJURY WAS SUSTAINED BY PERSONS OR
 * PROPERTY OR OTHERWISE, AND WHETHER OR NOT LOSS WAS SUSTAINED FROM, OR AROSE OUT
 * OF THE RESULTS OF, OR USE OF, THE SOFTWARE OR SERVICES PROVIDED HEREUNDER.
 */
package gov.nist.jrolie.server.writers;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import gov.nist.jrolie.atom.logic.InternalServerError;
import gov.nist.jrolie.atom.logic.MismatchedCategoriesException;
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


//@RunWith(SpringJUnit4ClassRunner.class) ADD SPRING4JUNIT OR SOMETHING
public class TestDriver {

	  @Rule public JUnitRuleMockery context = new JUnitRuleMockery();
	
	  public static final String sr = System.getProperty("SERVER_ROOT");
	  public static final String ep = System.getProperty("ENTRY_PREFIX");
	  public static final String fp = System.getProperty("FEED_PREFIX");
	  public static final String sp = System.getProperty("SERVICE_PREFIX");
	  public static final String cp = System.getProperty("CAT_PREFIX");
	
	  @Mock private EntryService es;
	  @Mock private FeedService fs;
	  @Mock private PersistenceContext pc;
	  
	
	public void test() throws URISyntaxException, InternalServerError, ResourceAlreadyExistsException, ResourceNotFoundException, InvalidResourceTypeException, MismatchedCategoriesException {
	
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
