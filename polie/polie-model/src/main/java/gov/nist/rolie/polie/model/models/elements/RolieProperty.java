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
 * SHALL NASA BE LIABLE FOR ANY DAMAGES, INCLUDING, BUT NOT LIMITED TO, DIRECT,
 * INDIRECT, SPECIAL OR CONSEQUENTIAL DAMAGES, ARISING OUT OF, RESULTING FROM, OR
 * IN ANY WAY CONNECTED WITH THIS SOFTWARE, WHETHER OR NOT BASED UPON WARRANTY,
 * CONTRACT, TORT, OR OTHERWISE, WHETHER OR NOT INJURY WAS SUSTAINED BY PERSONS OR
 * PROPERTY OR OTHERWISE, AND WHETHER OR NOT LOSS WAS SUSTAINED FROM, OR AROSE OUT
 * OF THE RESULTS OF, OR USE OF, THE SOFTWARE OR SERVICES PROVIDED HEREUNDER.
 */

package gov.nist.rolie.polie.model.models.elements;

import gov.nist.rolie.polie.model.models.constructs.AtomCommonAttributes;

import java.net.URI;

/**
 * rolieProperty = element rolie:property { atomCommonAttributes, attribute scheme { atomURI },
 * attribute term { text }, attribute label { text } ? empty
 */

public class RolieProperty implements RolieElement {

  private AtomCommonAttributes atomCommonAttributes;

  private URI scheme;

  private String term;

  private String label;

  /**
   * @param atomCommonAttributes
   * @param scheme
   * @param term
   * @param label
   */
  public RolieProperty(AtomCommonAttributes atomCommonAttributes, URI scheme, String term, String label) {
    super();
    this.atomCommonAttributes = atomCommonAttributes;
    this.scheme = scheme;
    this.term = term;
    this.label = label;
  }

  public RolieProperty() {
  }

  /**
   * @return the atomCommonAttributes
   */
  public AtomCommonAttributes getAtomCommonAttributes() {
    return atomCommonAttributes;
  }

  /**
   * @return the scheme
   */
  public URI getScheme() {
    return scheme;
  }

  /**
   * @return the term
   */
  public String getTerm() {
    return term;
  }

  /**
   * @return the label
   */
  public String getLabel() {
    return label;
  }

  /**
   * @param atomCommonAttributes
   *          the atomCommonAttributes to set
   */
  public void setAtomCommonAttributes(AtomCommonAttributes atomCommonAttributes) {
    this.atomCommonAttributes = atomCommonAttributes;
  }

  /**
   * @param scheme
   *          the scheme to set
   */
  public void setScheme(URI scheme) {
    this.scheme = scheme;
  }

  /**
   * @param term
   *          the term to set
   */
  public void setTerm(String term) {
    this.term = term;
  }

  /**
   * @param label
   *          the label to set
   */
  public void setLabel(String label) {
    this.label = label;
  }

}
