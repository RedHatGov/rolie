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
import javax.xml.bind.annotation.XmlTransient;

import gov.nist.jrolie.model.JAttribute;
import gov.nist.jrolie.model.JContent;

@XmlAccessorType(XmlAccessType.FIELD)
public class JContentImpl implements JContent {
	@XmlAttribute
	String lang;
	@XmlAttribute
	URI base;
	@XmlAttribute
	String mediaType;
	@XmlAttribute
	URI src;

	@XmlTransient // TODO use enity adapter
	ArrayList<JAttribute> extensions;

	/**
	 * @return the lang
	 */
	@Override
	public String getLang() {
		return this.lang;
	}

	/**
	 * @return the base
	 */
	@Override
	public URI getBase() {
		return this.base;
	}

	/**
	 * @return the mediaType
	 */
	@Override
	public String getMediaType() {
		return this.mediaType;
	}

	/**
	 * @return the src
	 */
	@Override
	public URI getSrc() {
		return this.src;
	}

	/**
	 * @param lang the lang to set
	 */
	@Override
	public void setLang(String lang) {
		this.lang = lang;
	}

	/**
	 * @param base the base to set
	 */
	@Override
	public void setBase(URI base) {
		this.base = base;
	}

	/**
	 * @param mediaType the mediaType to set
	 */
	@Override
	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}

	/**
	 * @param src the src to set
	 */
	@Override
	public void setSrc(URI src) {
		this.src = src;
	}

	@Override
	public ArrayList<JAttribute> getExtensions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setExtensions(ArrayList<JAttribute> extensions) {
		// TODO Auto-generated method stub

	}

}
