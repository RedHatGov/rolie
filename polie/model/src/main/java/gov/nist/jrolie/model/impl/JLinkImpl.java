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
import java.net.URISyntaxException;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

import gov.nist.jrolie.model.JAttribute;
import gov.nist.jrolie.model.JLink;
import gov.nist.jrolie.model.JPerson;

@XmlAccessorType(XmlAccessType.FIELD)
public class JLinkImpl implements JLink {
  @XmlAttribute
  URI base;

  @XmlAttribute
  String lang;

  @XmlAttribute
  URI href;
  @XmlAttribute
  String rel;

  @XmlAttribute
  String type;

  @XmlAttribute
  String hreflang;

  @XmlAttribute
  String title;

  @XmlAttribute
  String length;

  /**
   * @return the base
   */
  @Override
  public URI getBase() {
    return base;
  }

  /**
   * @return the lang
   */
  @Override
  public String getLang() {
    return lang;
  }

  /**
   * @return the href
   */
  @Override
  public URI getHref() {
    return href;
  }

  /**
   * @return the rel
   */
  @Override
  public String getRel() {
    return rel;
  }

  /**
   * @return the type
   */
  @Override
  public String getType() {
    return type;
  }

  /**
   * @return the hreflang
   */
  @Override
  public String getHreflang() {
    return hreflang;
  }

  /**
   * @return the title
   */
  @Override
  public String getTitle() {
    return title;
  }

  /**
   * @return the length
   */
  @Override
  public String getLength() {
    return length;
  }

  /**
   * @param base
   *          the base to set
   */
  @Override
  public void setBase(URI base) {
    this.base = base;
  }

  /**
   * @param lang
   *          the lang to set
   */
  @Override
  public void setLang(String lang) {
    this.lang = lang;
  }

  /**
   * @param href
   *          the href to set
   */
  @Override
  public void setHref(URI href) {
    this.href = href;
  }

  /**
   * @param rel
   *          the rel to set
   */
  @Override
  public void setRel(String rel) {
    this.rel = rel;
  }

  /**
   * @param type
   *          the type to set
   */
  @Override
  public void setType(String type) {
    this.type = type;
  }

  /**
   * @param hreflang
   *          the hreflang to set
   */
  @Override
  public void setHreflang(String hreflang) {
    this.hreflang = hreflang;
  }

  /**
   * @param title
   *          the title to set
   */
  @Override
  public void setTitle(String title) {
    this.title = title;
  }

  /**
   * @param length
   *          the length to set
   */
  @Override
  public void setLength(String length) {
    this.length = length;
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
  
  public JLink clone()
  {
		JLink retval = new JLinkImpl();
		retval.setBase(base);
		retval.setHreflang(hreflang);
		retval.setLength(length);
		retval.setRel(rel);
		retval.setTitle(title);
		retval.setType(type);
		//retval.setExtensions(extensions);
		retval.setLang(lang);

		try {
			retval.setHref(new URI(href.toString()));
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retval;
  }

}
