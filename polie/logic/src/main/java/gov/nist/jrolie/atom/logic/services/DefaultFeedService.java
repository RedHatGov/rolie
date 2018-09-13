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

package gov.nist.jrolie.atom.logic.services;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import gov.nist.jrolie.atom.logic.InternalServerError;
import gov.nist.jrolie.atom.logic.MismatchedCategoriesException;
import gov.nist.jrolie.model.JCategory;
import gov.nist.jrolie.model.JEntry;
import gov.nist.jrolie.model.JFeed;
import gov.nist.jrolie.model.JLink;
import gov.nist.jrolie.model.impl.JDateImpl;
import gov.nist.jrolie.model.impl.JEntryWrapper;
import gov.nist.jrolie.model.impl.JFeedImpl;
import gov.nist.jrolie.model.impl.JLinkImpl;
import gov.nist.jrolie.persistence.api.PersistenceContext;
import gov.nist.jrolie.persistence.api.exceptions.InvalidResourceTypeException;
import gov.nist.jrolie.persistence.api.exceptions.ResourceAlreadyExistsException;
import gov.nist.jrolie.persistence.api.exceptions.ResourceNotFoundException;

@Component
public class DefaultFeedService implements FeedService {
	String root = System.getProperty("SERVER_ROOT");
	String feedRoot = System.getProperty("FEED_PREFIX");
	String prefix = root + feedRoot;
	@Autowired
	ResourceService rs;

	@Autowired
	PersistenceContext pc;

	@Autowired
	EntryService es;

	@Override
	public JFeed load(String id) throws ResourceNotFoundException, InvalidResourceTypeException {
		return pc.load(id, JFeed.class);
	}

	@Override
	public JFeed create(JFeed f) throws ResourceAlreadyExistsException {
		f.setUpdated(new JDateImpl());
		return pc.create(f);
	}

	@Override
	public JFeed delete(String id) throws ResourceNotFoundException {
		return pc.delete(id);
	}

	@Override
	public void addEntry(JFeed f, JEntry e) throws InternalServerError, MismatchedCategoriesException {
		if (categoriesCheck(f, e)) {
			f.getEntries().add(0, new JEntryWrapper(e.getId()));
			es.setLink(e, "feed", root + f.getPath());
			f.setUpdated(new JDateImpl());
		} else {
			throw new MismatchedCategoriesException();
		}
	}

	private boolean categoriesCheck(JFeed f, JEntry e) {
		for (JCategory c : f.getCategorys()) { //TODO Fix the null pointers that is going ort happen if you do this,
			for (JCategory ec : e.getCategorys()) {
				if (c.equals(ec))
				{
					continue;
				}
				return false;
			}
		}
		return true;
	}

	@Override
	public JFeed archive(JFeed f) throws InternalServerError, ResourceNotFoundException, InvalidResourceTypeException,
			ResourceAlreadyExistsException {

		JFeed archive = new JFeedImpl(f);
		if (hasValidLink(f, "next-archive") == -1) { // No next archive, this is the most current ver

			archive.setId(archive.getId() + rs.generateArchiveSuffix());
			archive.setPath(feedRoot + archive.getId());
			setLink(archive, "current", root + f.getPath());
			setLink(archive, "next-archive", root + f.getPath());
			if (hasValidLink(f, "prev-archive") == -1) { // No prev archive, this is most current, but has never been
															// archived before
				setLink(archive, "prev-archive", "");
				setLink(f, "prev-archive", root + archive.getPath());
			} else {// Does have a prev-archive, most current ver but has been archived before
				JFeed lastArchive = load(rs.pathToId(getLinkRel(f, "prev-archive").getHref().toString()));
				setLink(lastArchive, "next-archive", root + archive.getPath());
				setLink(archive, "prev-archive", root + lastArchive.getPath());
			}

		}
		create(archive);
		return null;
	}

	@Override
	public JFeed update(JFeed f) throws ResourceNotFoundException, InternalServerError {
		f.setUpdated(new JDateImpl());
		return pc.update(f);
	}

	/**
	 * Changes a link if it exists, creates it and sets it if it doesnt exist.
	 * 
	 * @param f
	 * @param rel
	 * @param href
	 * @throws InternalServerError
	 */
	private void setLink(JFeed f, String rel, String href) throws InternalServerError {
		int linkIndex = hasLink(f, rel);
		if (linkIndex != -1) {
			try {
				f.getLinks().get(linkIndex).setHref(new URI(href));
			} catch (URISyntaxException e1) {
				throw new InternalServerError(e1);
			}
		} else {
			JLink l = new JLinkImpl();
			try {
				l.setHref(new URI(href));
				l.setRel(rel);
				f.getLinks().add(l);
			} catch (URISyntaxException e1) {
				throw new InternalServerError(e1);
			}
		}
	}

	/**
	 * 
	 * @param f
	 * @param rel
	 *            The relation to search
	 * @return index of link if present, -1 if not present
	 */
	private int hasLink(JFeed f, String rel) {
		ArrayList<JLink> ls = f.getLinks();
		for (int i = 0; i < ls.size(); i++) {
			if (ls.get(i).getRel().equals(rel)) {
				return i;
			}
		}
		return -1;
	}

	private int hasValidLink(JFeed f, String rel) {
		ArrayList<JLink> ls = f.getLinks();
		for (int i = 0; i < ls.size(); i++) {
			if (ls.get(i).getRel().equals(rel) && !ls.get(i).getHref().equals("")) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Similar to above but returns the Link object directly instead of an index
	 * 
	 * @param f
	 * @param rel
	 * @return
	 */
	private JLink getLinkRel(JFeed f, String rel) {
		ArrayList<JLink> ls = f.getLinks();
		for (JLink l : ls) {
			if (l.getRel().equals(rel)) {
				return l;
			}
		}
		return null;

	}

}
