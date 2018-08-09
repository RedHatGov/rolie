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
package gov.nist.jrolie.persistence.api;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import gov.nist.jrolie.model.JCollection;
import gov.nist.jrolie.model.JEntry;
import gov.nist.jrolie.model.JFeed;
import gov.nist.jrolie.model.JServiceDocument;
import gov.nist.jrolie.model.JWorkspace;
import gov.nist.jrolie.model.impl.JCollectionImpl;
import gov.nist.jrolie.model.impl.JEntryImpl;
import gov.nist.jrolie.model.impl.JFeedImpl;
import gov.nist.jrolie.model.impl.JServiceDocumentImpl;
import gov.nist.jrolie.model.impl.JTextConstructImpl;
import gov.nist.jrolie.model.impl.JWorkspaceImpl;
import gov.nist.jrolie.persistence.api.exceptions.ResourceAlreadyExistsException;

public class DemoBootstrap {
	private static final Logger log = LogManager.getLogger(DemoBootstrap.class);

	public DemoBootstrap(PersistenceContext pc) throws ResourceAlreadyExistsException {
		log.debug("Starting bootstrap");

		JEntry e = new JEntryImpl();
		e.setPath("/e/demoentry");
		e.setId("demoEntry");
		e.setTitle(new JTextConstructImpl("I'm a demo entry!"));
		pc.create(e);

		JFeed f = new JFeedImpl();
		f.setPath("/f/demofeed");
		f.setId("demoFeed");
		f.setTitle(new JTextConstructImpl("I'm a demo feed!"));

		ArrayList<String> entries = new ArrayList<String>();
		entries.add(e.getId());
		f.setEntries(entries);
		pc.create(f);

		JCollection c = new JCollectionImpl();
		c.setTitle(new JTextConstructImpl("I'm a demo collection!"));
		try {
			c.setHref(new URI(f.getPath()));
		} catch (URISyntaxException e1) {
			//  Auto-generated catch block
			e1.printStackTrace();
		}

		JWorkspace w = new JWorkspaceImpl();
		w.setCollections(new ArrayList<JCollection>());
		w.setTitle(new JTextConstructImpl("demoworkspace"));
		w.getCollections().add(c);

		JServiceDocument s = new JServiceDocumentImpl();
		s.setId("demoservice");
		s.setPath("/s/demoservice");
		s.setWorkspaces(new ArrayList<JWorkspace>());
		s.getWorkspaces().add(w);
		pc.create(s);

	}

}
