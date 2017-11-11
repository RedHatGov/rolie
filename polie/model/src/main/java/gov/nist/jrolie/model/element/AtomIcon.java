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

package gov.nist.jrolie.model.element;
/*
 * 4.2.5.  The "atom:icon" Element

   The "atom:icon" element's content is an IRI reference [RFC3987] that
   identifies an image that provides iconic visual identification for a
   feed.

   atomIcon = element atom:icon {
      atomCommonAttributes,
      (atomUri)
   }

   The image SHOULD have an aspect ratio of one (horizontal) to one
   (vertical) and SHOULD be suitable for presentation at a small size.
 */

import java.net.URI;

import gov.nist.jrolie.model.construct.AtomCommonAttributes;

public class AtomIcon implements AtomElement {

  private AtomCommonAttributes commonAttributes;

  private URI iri;

  /**
   * @param commonAttributes
   * @param iri
   */
  public AtomIcon(AtomCommonAttributes commonAttributes, URI iri) {
    super();
    this.commonAttributes = commonAttributes;
    this.iri = iri;
  }

  public AtomIcon() {
  }

  /**
   * @return the commonAttributes
   */
  public AtomCommonAttributes getCommonAttributes() {
    return commonAttributes;
  }

  /**
   * @return the iri
   */
  public URI getIri() {
    return iri;
  }

  /**
   * @param commonAttributes
   *          the commonAttributes to set
   */
  public void setCommonAttributes(AtomCommonAttributes commonAttributes) {
    this.commonAttributes = commonAttributes;
  }

  /**
   * @param iri
   *          the iri to set
   */
  public void setIri(URI iri) {
    this.iri = iri;
  }

}
