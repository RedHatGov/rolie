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
package gov.nist.rolie.polie.model.models.elements;

import gov.nist.rolie.polie.model.models.constructs.AtomCommonAttributes;

import java.net.URI;

/*
 * 4.1.3.  The "atom:content" Element

   The "atom:content" element either contains or links to the content of
   the entry.  The content of atom:content is Language-Sensitive.

   atomInlineTextContent =
      element atom:content {
         atomCommonAttributes,
         attribute type { "text" | "html" }?,
         (text)*
      }

   atomInlineXHTMLContent =
      element atom:content {
         atomCommonAttributes,
         attribute type { "xhtml" },
         xhtmlDiv
      }

   atomInlineOtherContent =
      element atom:content {
         atomCommonAttributes,
         attribute type { atomMediaType }?,
         (text|anyElement)*
      }

   atomOutOfLineContent =
      element atom:content {
         atomCommonAttributes,
         attribute type { atomMediaType }?,
         attribute src { atomUri },
         empty
      }

   atomContent = atomInlineTextContent
    | atomInlineXHTMLContent
    | atomInlineOtherContent
    | atomOutOfLineContent

 */

public class AtomContent implements AtomElement {

  private AtomCommonAttributes commonAttributes;

  private String type;

  private URI src;

  /**
   * @param commonAttributes
   * @param type
   * @param uri
   */
  public AtomContent(AtomCommonAttributes commonAttributes, String type, URI uri) {
    super();
    this.commonAttributes = commonAttributes;
    this.type = type;
    this.src = uri;
  }

  public AtomContent() {
  }

  /**
   * @return the commonAttributes
   */
  public AtomCommonAttributes getCommonAttributes() {
    return commonAttributes;
  }

  /**
   * @return the type
   */
  public String getType() {
    return type;
  }

  /**
   * @return the uri
   */
  public URI getUri() {
    return src;
  }

  /**
   * @param commonAttributes
   *          the commonAttributes to set
   */
  public void setCommonAttributes(AtomCommonAttributes commonAttributes) {
    this.commonAttributes = commonAttributes;
  }

  /**
   * @param type
   *          the type to set
   */
  public void setType(String type) {
    this.type = type;
  }

  /**
   * @param uri
   *          the uri to set
   */
  public void setUri(URI uri) {
    this.src = uri;
  }

}
