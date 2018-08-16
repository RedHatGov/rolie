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
import javax.xml.bind.annotation.XmlValue;

import gov.nist.jrolie.model.JAttribute;
import gov.nist.jrolie.model.JGenerator;
import gov.nist.jrolie.model.JPerson;

@XmlAccessorType(XmlAccessType.FIELD)
public class JGeneratorImpl implements JGenerator {
  @XmlAttribute
  URI uri;
  @XmlAttribute
  String version;
  @XmlAttribute
  String lang;
  @XmlAttribute
  URI base;
  @XmlValue
  String text;

  /**
   * @return the uri
   */
  @Override
  public URI getUri() {
    return uri;
  }

  /**
   * @return the version
   */
  @Override
  public String getVersion() {
    return version;
  }

  /**
   * @return the lang
   */
  @Override
  public String getLang() {
    return lang;
  }

  /**
   * @return the base
   */
  @Override
  public URI getBase() {
    return base;
  }

  /**
   * @return the text
   */
  @Override
  public String getText() {
    return text;
  }

  /**
   * @param uri
   *          the uri to set
   */
  @Override
  public void setUri(URI uri) {
    this.uri = uri;
  }

  /**
   * @param version
   *          the version to set
   */
  @Override
  public void setVersion(String version) {
    this.version = version;
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
   * @param base
   *          the base to set
   */
  @Override
  public void setBase(URI base) {
    this.base = base;
  }

  /**
   * @param text
   *          the text to set
   */
  @Override
  public void setText(String text) {
    this.text = text;
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
  
  public JGenerator clone()
  {
		JGenerator retval = new JGeneratorImpl();
		retval.setBase(base);
		retval.setLang(lang);
		retval.setText(text);
		retval.setVersion(version);
		try {
			retval.setUri(new URI(uri.toString()));
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retval;
  }

}
