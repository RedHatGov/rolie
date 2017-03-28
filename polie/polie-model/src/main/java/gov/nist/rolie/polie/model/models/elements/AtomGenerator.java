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

/*
 * 4.2.4.  The "atom:generator" Element

   The "atom:generator" element's content identifies the agent used to
   generate a feed, for debugging and other purposes.

   atomGenerator = element atom:generator {
      atomCommonAttributes,
      attribute uri { atomUri }?,
      attribute version { text }?,
      text
   }

   The content of this element, when present, MUST be a string that is a
   human-readable name for the generating agent.  Entities such as
   "&amp;" and "&lt;" represent their corresponding characters ("&" and
   "<" respectively), not markup.

   The atom:generator element MAY have a "uri" attribute whose value
   MUST be an IRI reference [RFC3987].  When dereferenced, the resulting
   URI (mapped from an IRI, if necessary) SHOULD produce a
   representation that is relevant to that agent.

   The atom:generator element MAY have a "version" attribute that
   indicates the version of the generating agent.
 */

public class AtomGenerator {

  private AtomCommonAttributes commonAttributes;

  private URI uri;

  private String version;

  private String name;

  /**
   * @param commonAttributes
   * @param uri
   * @param version
   */
  public AtomGenerator(AtomCommonAttributes commonAttributes, URI uri, String version, String name) {
    super();
    this.commonAttributes = commonAttributes;
    this.uri = uri;
    this.version = version;
    this.name = name;
  }

  public AtomGenerator() {
  }

  /**
   * @return the commonAttributes
   */
  public AtomCommonAttributes getCommonAttributes() {
    return commonAttributes;
  }

  /**
   * @return the uri
   */
  public URI getUri() {
    return uri;
  }

  /**
   * @return the version
   */
  public String getVersion() {
    return version;
  }

  /**
   * @param commonAttributes
   *          the commonAttributes to set
   */
  public void setCommonAttributes(AtomCommonAttributes commonAttributes) {
    this.commonAttributes = commonAttributes;
  }

  /**
   * @param uri
   *          the uri to set
   */
  public void setUri(URI uri) {
    this.uri = uri;
  }

  /**
   * @param version
   *          the version to set
   */
  public void setVersion(String version) {
    this.version = version;
  }

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @param name
   *          the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

}
