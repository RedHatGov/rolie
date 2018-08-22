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
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import gov.nist.jrolie.model.Constants;
import gov.nist.jrolie.model.JAttribute;
import gov.nist.jrolie.model.JCategory;
import gov.nist.jrolie.model.JDate;
import gov.nist.jrolie.model.JFeed;
import gov.nist.jrolie.model.JGenerator;
import gov.nist.jrolie.model.JLink;
import gov.nist.jrolie.model.JPerson;
import gov.nist.jrolie.model.JTextConstruct;

@XmlRootElement(name = "feed", namespace = Constants.ATOM_NS)
@XmlAccessorType(XmlAccessType.FIELD)
public class JFeedImpl implements JFeed {
	@XmlAttribute
	String lang;
	@XmlAttribute
	URI base;

	@XmlTransient
	boolean changed;

	@XmlElement(namespace = Constants.ATOM_NS)
	String id;

	@XmlTransient
	String path;

	@XmlElement(type = JPersonImpl.class, namespace = Constants.ATOM_NS)
	ArrayList<JPerson> authors;

	@XmlElement(type = JCategoryImpl.class, namespace = Constants.ATOM_NS)
	ArrayList<JCategory> categorys;

	@XmlElement(type = JPersonImpl.class, namespace = Constants.ATOM_NS)
	ArrayList<JPerson> contributors;

	@XmlElement(type = JGeneratorImpl.class, namespace = Constants.ATOM_NS)
	JGenerator generator;

	@XmlElement(namespace = Constants.ATOM_NS)
	URI icon;

	@XmlElement(type = JLinkImpl.class, namespace = Constants.ATOM_NS)
	ArrayList<JLink> link;

	@XmlElement(namespace = Constants.ATOM_NS)
	URI logo;

	@XmlElement(type = JTextConstructImpl.class, namespace = Constants.ATOM_NS)
	JTextConstruct rights;

	@XmlElement(type = JTextConstructImpl.class, namespace = Constants.ATOM_NS)
	JTextConstruct subtitle;

	@XmlElement(type = JTextConstructImpl.class, namespace = Constants.ATOM_NS)
	JTextConstruct title;

	@XmlElement(type = JDateImpl.class, namespace = Constants.ATOM_NS)
	JDate updated;

	@XmlTransient
	ArrayList<JAttribute> extensions;

	@XmlElement(type = JEntryImpl.class, namespace = Constants.ATOM_NS)
	ArrayList<String> entries;


	public JFeedImpl(JFeed f) { // DEEP COPY
		this.lang = f.getLang();
		if (f.getBase() != null) {
			this.base = URI.create(f.getBase().toString());
		}
		this.changed = f.isChanged();
		this.id = f.getId();
		this.path = f.getPath();
		this.authors = new ArrayList<JPerson>();
		for (JPerson p : f.getAuthors()) {
			authors.add(p.clone());
		}
		this.categorys = new ArrayList<JCategory>();
		for (JCategory c : f.getCategorys()) {
			categorys.add(c.clone());
		}
		this.contributors = new ArrayList<JPerson>();
		for (JPerson p : f.getContributors()) {
			contributors.add(p.clone());
		}
		if (f.getGenerator()!=null) {
		this.generator = f.getGenerator().clone();}
		if (f.getIcon()!=null) {
		this.icon = URI.create(f.getIcon().toString());}
		this.link = new ArrayList<JLink>();
		for (JLink l : f.getLinks()) {
			link.add(l.clone());
		}
		if(f.getLogo()!=null) {
		this.logo = URI.create(f.getLogo().toString());}
		this.rights = f.getRights().clone();
		this.subtitle = f.getSubtitle().clone();
		this.title = f.getTitle().clone();
		this.updated = f.getUpdated().clone();
		// this.extensions = f.getExtensions();
		this.entries = new ArrayList<String>();
		for (String e : f.getEntries()) {
			entries.add(e);
		}

	}

	public JFeedImpl() {
		this.authors = new ArrayList<JPerson>();
		this.categorys = new ArrayList<JCategory>();
		this.contributors = new ArrayList<JPerson>();
		this.link = new ArrayList<JLink>();
		this.entries = new ArrayList<String>();
		//this.generator=new JGeneratorImpl();
		this.rights=new JTextConstructImpl();
		this.subtitle=new JTextConstructImpl();
		this.title = new JTextConstructImpl();
		this.updated=new JDateImpl();
	}

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
	public ArrayList<JPerson> getAuthors() {
		return authors;
	}

	@Override
	public void setAuthors(ArrayList<JPerson> authors) {
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
	public ArrayList<JPerson> getContributors() {
		return contributors;
	}

	@Override
	public void setContributors(ArrayList<JPerson> contributors) {
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
	public ArrayList<JAttribute> getExtensions() {
		return extensions;
	}

	@Override
	public void setExtensions(ArrayList<JAttribute> extensions) {
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
	public URI getBase() {
		return base;
	}

	@Override
	public void setBase(URI base) {
		this.base = base;
	}

	@Override
	public String getLang() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLang(String lang) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setChanged(boolean changed) {
		// TODO Auto-generated method stub

	}

}
