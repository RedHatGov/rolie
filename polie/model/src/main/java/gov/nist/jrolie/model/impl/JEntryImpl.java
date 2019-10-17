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

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import gov.nist.jrolie.model.Constants;
import gov.nist.jrolie.model.JAttribute;
import gov.nist.jrolie.model.JCategory;
import gov.nist.jrolie.model.JContent;
import gov.nist.jrolie.model.JDate;
import gov.nist.jrolie.model.JEntry;
import gov.nist.jrolie.model.JFeed;
import gov.nist.jrolie.model.JFormat;
import gov.nist.jrolie.model.JLink;
import gov.nist.jrolie.model.JPerson;
import gov.nist.jrolie.model.JProperty;
import gov.nist.jrolie.model.JTextConstruct;

@XmlRootElement(name = "entry", namespace = Constants.ATOM_NS)
@XmlAccessorType(XmlAccessType.FIELD)
public class JEntryImpl implements JEntry {

	@XmlTransient
	String path;

	@XmlElement(type = JPersonImpl.class, namespace = Constants.ATOM_NS, name = "author")
	ArrayList<JPerson> authors;

	@XmlElement(type = JCategoryImpl.class, namespace = Constants.ATOM_NS, name = "category")
	ArrayList<JCategory> categorys;

	@XmlElement(type = JContentImpl.class, namespace = Constants.ATOM_NS)
	JContent content;

	@XmlElement(type = JPersonImpl.class, namespace = Constants.ATOM_NS)
	ArrayList<JPerson> contributors;

	@XmlElement(namespace = Constants.ATOM_NS, required = true)
	String id;

	@XmlElement(type = JLinkImpl.class, namespace = Constants.ATOM_NS, name = "link")
	ArrayList<JLink> links;

	@XmlElement(type = JDateImpl.class, namespace = Constants.ATOM_NS)
	JDate published;

	@XmlElement(type = JTextConstructImpl.class, namespace = Constants.ATOM_NS)
	JTextConstruct rights;

	@XmlElement(type = JFeedImpl.class, namespace = Constants.ATOM_NS)
	JFeed source;

	@XmlElement(type = JTextConstructImpl.class, namespace = Constants.ATOM_NS)
	JTextConstruct summary;

	@XmlElement(type = JTextConstructImpl.class, namespace = Constants.ATOM_NS)
	JTextConstruct title;

	@XmlElement(type = JDateImpl.class, namespace = Constants.ATOM_NS)
	JDate updated;

	@XmlElement(type = JFormatImpl.class, namespace = Constants.ROLIE_NS, name = "format")
	JFormat format;

	@XmlElement(type = JPropertyImpl.class, namespace = Constants.ROLIE_NS, name = "property")
	ArrayList<JProperty> properties;

	@XmlTransient
	ArrayList<JAttribute> extensions;

	@XmlTransient
	boolean changed;

	@XmlAttribute
	String lang;

	@XmlAttribute
	URI base;
	
	@XmlAnyElement(lax=true)
	ArrayList<Object> elements;
	
	@XmlTransient
	private
	String feedID;

	public JEntryImpl() {
		this.links = new ArrayList<JLink>();
	}

	@Override
	public String getPath() {
		return this.path;
	}

	@Override
	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public ArrayList<JPerson> getAuthors() {
		return this.authors;
	}

	@Override
	public void setAuthors(ArrayList<JPerson> authors) {
		this.authors = authors;
	}

	@Override
	public ArrayList<JCategory> getCategorys() {
		return this.categorys;
	}

	@Override
	public void setCategorys(ArrayList<JCategory> categorys) {
		this.categorys = categorys;
	}

	@Override
	public JContent getContent() {
		return this.content;
	}

	@Override
	public void setContent(JContent content) {
		this.content = content;
	}

	@Override
	public ArrayList<JPerson> getContributors() {
		return this.contributors;
	}

	@Override
	public void setContributors(ArrayList<JPerson> contributors) {
		this.contributors = contributors;
	}

	@Override
	public String getId() {
		return this.id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public ArrayList<JLink> getLinks() {
		return this.links;
	}

	@Override
	public void setLinks(ArrayList<JLink> links) {
		this.links = links;
	}

	@Override
	public JDate getPublished() {
		return this.published;
	}

	@Override
	public void setPublished(JDate published) {
		this.published = published;
	}

	@Override
	public JTextConstruct getRights() {
		return this.rights;
	}

	@Override
	public void setRights(JTextConstruct rights) {
		this.rights = rights;
	}

	@Override
	public JFeed getSource() {
		return this.source;
	}

	@Override
	public void setSource(JFeed source) {
		this.source = source;
	}

	@Override
	public JTextConstruct getSummary() {
		return this.summary;
	}

	@Override
	public void setSummary(JTextConstruct summary) {
		this.summary = summary;
	}

	@Override
	public JTextConstruct getTitle() {
		return this.title;
	}

	@Override
	public void setTitle(JTextConstruct title) {
		this.title = title;
	}

	@Override
	public JDate getUpdated() {
		return this.updated;
	}

	@Override
	public void setUpdated(JDate updated) {
		this.updated = updated;
	}

	@Override
	public ArrayList<JAttribute> getExtensions() {
		return this.extensions;
	}

	@Override
	public void setExtensions(ArrayList<JAttribute> extensions) {
		this.extensions = extensions;
	}

	@Override
	public boolean isChanged() {
		return this.changed;
	}

	@Override
	public URI getBase() {
		return this.base;
	}

	@Override
	public void setBase(URI base) {
		this.base = base;

	}

	@Override
	public String getLang() {
		return this.lang;
	}

	@Override
	public void setLang(String lang) {
		this.lang = lang;

	}

	@Override
	public void setChanged(boolean changed) {
		this.changed = changed;
	}

	@Override
	public ArrayList<JProperty> getProperties() {
		return this.properties;
	}

	@Override
	public void setProperties(ArrayList<JProperty> properties) {
		this.properties = properties;

	}

	@Override
	public JFormat getFormat() {
		// TODO Auto-generated method stub
		return this.format;
	}

	@Override
	public void setFormat(JFormat format) {
		this.format = format;

	}

	public String getFeedID() {
		return feedID;
	}

	public void setFeedID(String feedID) {
		this.feedID = feedID;
	}

}
