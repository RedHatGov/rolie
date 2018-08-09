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
package gov.nist.jrolie.model.impl;

import java.net.URI;
import java.util.ArrayList;

import gov.nist.jrolie.model.JCategory;
import gov.nist.jrolie.model.JCattr;
import gov.nist.jrolie.model.JDate;
import gov.nist.jrolie.model.JElement;
import gov.nist.jrolie.model.JFeed;
import gov.nist.jrolie.model.JGenerator;
import gov.nist.jrolie.model.JLink;
import gov.nist.jrolie.model.JPersonConstruct;
import gov.nist.jrolie.model.JTextConstruct;

public class JFeedImpl implements JFeed {

	ArrayList<JCattr> cattr;
	boolean changed;
	String id;
	String path;

	ArrayList<JPersonConstruct> authors;
	ArrayList<JCategory> categorys;
	ArrayList<JPersonConstruct> contributors;

	JGenerator generator;
	URI icon;
	ArrayList<JLink> link;
	URI logo;
	JTextConstruct rights;
	JTextConstruct subtitle;
	JTextConstruct title;
	JDate updated;

	ArrayList<JElement> extensions;
	ArrayList<String> entries;

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String getPath() {
		return path;
	}

	@Override
	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public ArrayList<JPersonConstruct> getAuthors() {
		return authors;
	}

	@Override
	public void setAuthors(ArrayList<JPersonConstruct> authors) {
		this.authors = authors;
	}

	@Override
	public ArrayList<JCategory> getCategorys() {
		return categorys;
	}

	@Override
	public void setCategorys(ArrayList<JCategory> categorys) {
		this.categorys = categorys;
	}

	@Override
	public ArrayList<JPersonConstruct> getContributors() {
		return contributors;
	}

	@Override
	public void setContributors(ArrayList<JPersonConstruct> contributors) {
		this.contributors = contributors;
	}

	@Override
	public JGenerator getGenerator() {
		return generator;
	}

	@Override
	public void setGenerator(JGenerator generator) {
		this.generator = generator;
	}

	@Override
	public URI getIcon() {
		return icon;
	}

	@Override
	public void setIcon(URI icon) {
		this.icon = icon;
	}

	@Override
	public ArrayList<JLink> getLinks() {
		return link;
	}

	@Override
	public void setLinks(ArrayList<JLink> link) {
		this.link = link;
	}

	@Override
	public URI getLogo() {
		return logo;
	}

	@Override
	public void setLogo(URI logo) {
		this.logo = logo;
	}

	@Override
	public JTextConstruct getRights() {
		return rights;
	}

	@Override
	public void setRights(JTextConstruct rights) {
		this.rights = rights;
	}

	@Override
	public JTextConstruct getSubtitle() {
		return subtitle;
	}

	@Override
	public void setSubtitle(JTextConstruct subtitle) {
		this.subtitle = subtitle;
	}

	@Override
	public JTextConstruct getTitle() {
		return title;
	}

	@Override
	public void setTitle(JTextConstruct title) {
		this.title = title;
	}

	@Override
	public JDate getUpdated() {
		return updated;
	}

	@Override
	public void setUpdated(JDate updated) {
		this.updated = updated;
	}

	@Override
	public ArrayList<JElement> getExtensions() {
		return extensions;
	}

	@Override
	public void setExtensions(ArrayList<JElement> extensions) {
		this.extensions = extensions;
	}

	@Override
	public ArrayList<String> getEntries() {
		return entries;
	}

	@Override
	public void setEntries(ArrayList<String> entries) {
		this.entries = entries;
	}

	@Override
	public boolean isChanged() {
		return changed;
	}

	@Override
	public void markChanged() {
		changed = true;
	}

	@Override
	public ArrayList<JCattr> getCattr() {
		return cattr;
	}

	@Override
	public void setCattr(ArrayList<JCattr> cattr) {
		this.cattr = cattr;
	}

}
