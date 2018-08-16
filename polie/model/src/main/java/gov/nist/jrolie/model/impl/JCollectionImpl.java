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

import gov.nist.jrolie.model.JAccept;
import gov.nist.jrolie.model.JAttribute;
import gov.nist.jrolie.model.JCategories;
import gov.nist.jrolie.model.JCollection;
import gov.nist.jrolie.model.JTextConstruct;

@XmlAccessorType(XmlAccessType.FIELD)
public class JCollectionImpl implements JCollection {
  @XmlAttribute
  URI base;
  @XmlAttribute
  String lang;
  ArrayList<JAttribute> extensions;
  JTextConstruct title;
  URI href;
  String feedPath;
  ArrayList<JAccept> accepts;
  ArrayList<JCategories> categories;

  public JTextConstruct getTitle() {
    return title;
  }

  public void setTitle(JTextConstruct title) {
    this.title = title;
  }

  public URI getHref() {
    return href;
  }

  public void setHref(URI href) {
    this.href = href;
  }

  public String getFeedPath() {
    return feedPath;
  }

  public void setFeedPath(String feedPath) {
    this.feedPath = feedPath;
  }

  public ArrayList<JAccept> getAccepts() {
    return accepts;
  }

  public void setAccepts(ArrayList<JAccept> accepts) {
    this.accepts = accepts;
  }

  public ArrayList<JCategories> getCategories() {
    return categories;
  }

  public void setCategories(ArrayList<JCategories> categories) {
    this.categories = categories;
  }

  public URI getBase() {
    return base;
  }

  public void setBase(URI base) {
    this.base = base;
  }

  public String getLang() {
    return lang;
  }

  public void setLang(String lang) {
    this.lang = lang;
  }

  public ArrayList<JAttribute> getExtensions() {
    return extensions;
  }

  public void setExtensions(ArrayList<JAttribute> extensions) {
    this.extensions = extensions;
  }

}
