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
import javax.xml.bind.annotation.XmlTransient;

import gov.nist.jrolie.model.Constants;
import gov.nist.jrolie.model.JAccept;
import gov.nist.jrolie.model.JAttribute;
import gov.nist.jrolie.model.JCategories;
import gov.nist.jrolie.model.JCollection;
import gov.nist.jrolie.model.JLink;

@XmlAccessorType(XmlAccessType.FIELD)
public class JCollectionImpl implements JCollection {
	@XmlAttribute
	URI base;
	@XmlAttribute
	String lang;
	@XmlTransient
	ArrayList<JAttribute> extensions;
	@XmlElement
	String title;
	@XmlAttribute
	URI href;
	@XmlTransient
	String feedPath;
	@XmlTransient
	ArrayList<JAccept> accepts;
	@XmlElement(type = JCategoryImpl.class, namespace = Constants.APP_NS, name = "categories")
	ArrayList<JCategories> categories;
	@XmlElement(type = JLinkImpl.class, namespace = Constants.ATOM_NS, name = "link")
	ArrayList<JLink> links;

	@Override
	public String getTitle() {
		return this.title;
	}

	@Override
	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public URI getHref() {
		return this.href;
	}

	@Override
	public void setHref(URI href) {
		this.href = href;
	}

	@Override
	public String getFeedPath() {
		return this.feedPath;
	}

	@Override
	public void setFeedPath(String feedPath) {
		this.feedPath = feedPath;
	}

	@Override
	public ArrayList<JAccept> getAccepts() {
		return this.accepts;
	}

	@Override
	public void setAccepts(ArrayList<JAccept> accepts) {
		this.accepts = accepts;
	}

	@Override
	public ArrayList<JCategories> getCategories() {
		return this.categories;
	}

	@Override
	public void setCategories(ArrayList<JCategories> categories) {
		this.categories = categories;
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
	public ArrayList<JAttribute> getExtensions() {
		return this.extensions;
	}

	@Override
	public void setExtensions(ArrayList<JAttribute> extensions) {
		this.extensions = extensions;
	}

}
