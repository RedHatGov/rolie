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
 * 4.2.7.  The "atom:link" Element

   The "atom:link" element defines a reference from an entry or feed to
   a Web resource.  This specification assigns no meaning to the content
   (if any) of this element.

   atomLink =
      element atom:link {
         atomCommonAttributes,
         attribute href { atomUri },
         attribute rel { atomNCName | atomUri }?,
         attribute type { atomMediaType }?,
         attribute hreflang { atomLanguageTag }?,
         attribute title { text }?,
         attribute length { text }?,
         undefinedContent
      }

 */

public class AtomLink implements AtomElement {

  private AtomCommonAttributes commonAttributes;

  /*
   * The "href" attribute contains the link's IRI. atom:link elements MUST have an href attribute,
   * whose value MUST be a IRI reference [RFC3987].
   */

  private URI href;

  /*
   * 4.2.7.2. The "rel" Attribute atom:link elements MAY have a "rel" attribute that indicates the
   * link relation type. If the "rel" attribute is not present, the link element MUST be interpreted
   * as if the link relation type is "alternate".
   * 
   * The value of "rel" MUST be a string that is non-empty and matches either the "isegment-nz-nc"
   * or the "IRI" production in [RFC3987]. Note that use of a relative reference other than a simple
   * name is not allowed. If a name is given, implementations MUST consider the link relation type
   * equivalent to the same name registered within the IANA
   * 
   * Registry of Link Relations (Section 7), and thus to the IRI that would be obtained by appending
   * the value of the rel attribute to the string "http://www.iana.org/assignments/relation/". The
   * value of "rel" describes the meaning of the link, but does not impose any behavioral
   * requirements on Atom Processors.
   * 
   * This document defines five initial values for the Registry of Link Relations:
   * 
   * 1. The value "alternate" signifies that the IRI in the value of the href attribute identifies
   * an alternate version of the resource described by the containing element.
   * 
   * 2. The value "related" signifies that the IRI in the value of the href attribute identifies a
   * resource related to the resource described by the containing element. For example, the feed for
   * a site that discusses the performance of the search engine at "http://search.example.com" might
   * contain, as a child of atom:feed:
   * 
   * <link rel="related" href="http://search.example.com/"/>
   * 
   * An identical link might appear as a child of any atom:entry whose content contains a discussion
   * of that same search engine.
   * 
   * 3. The value "self" signifies that the IRI in the value of the href attribute identifies a
   * resource equivalent to the containing element.
   * 
   * 4. The value "enclosure" signifies that the IRI in the value of the href attribute identifies a
   * related resource that is potentially large in size and might require special handling. For
   * atom:link elements with rel="enclosure", the length attribute SHOULD be provided.
   * 
   * 5. The value "via" signifies that the IRI in the value of the href attribute identifies a
   * resource that is the source of the information provided in the containing element.
   */

  private URI rel;

  /*
   * 4.2.7.3. The "type" Attribute
   * 
   * On the link element, the "type" attribute's value is an advisory media type: it is a hint about
   * the type of the representation that is expected to be returned when the value of the href
   * attribute is dereferenced. Note that the type attribute does not override the actual media type
   * returned with the representation. Link elements MAY have a type attribute, whose value MUST
   * conform to the syntax of a MIME media type [MIMEREG].
   */
  private String type;

  /*
   * The "hreflang" attribute's content describes the language of the resource pointed to by the
   * href attribute. When used together with the rel="alternate", it implies a translated version of
   * the entry. Link elements MAY have an hreflang attribute, whose value MUST be a language tag
   * [RFC3066].
   */
  private String hrefLang;

  /*
   * The "title" attribute conveys human-readable information about the link. The content of the
   * "title" attribute is Language-Sensitive. Entities such as "&amp;" and "&lt;" represent their
   * corresponding characters ("&" and "<", respectively), not markup. Link elements MAY have a
   * title attribute.
   */

  private String title;

  /*
   * 4.2.7.6. The "length" Attribute The "length" attribute indicates an advisory length of the
   * linked content in octets; it is a hint about the content length of the representation returned
   * when the IRI in the href attribute is mapped to a URI and dereferenced. Note that the length
   * attribute does not override the actual content length of the representation as reported by the
   * underlying protocol. Link elements MAY have a length attribute.
   */

  private String length;

  /**
   * @param commonAttributes
   * @param href
   * @param rel
   * @param type
   * @param hrefLang
   * @param title
   * @param length
   */
  public AtomLink(AtomCommonAttributes commonAttributes, URI href, URI rel, String type, String hrefLang, String title,
      String length) {
    super();
    this.commonAttributes = commonAttributes;
    this.href = href;
    this.rel = rel;
    this.type = type;
    this.hrefLang = hrefLang;
    this.title = title;
    this.length = length;
  }

  /**
   * @return the commonAttributes
   */
  public AtomCommonAttributes getCommonAttributes() {
    return commonAttributes;
  }

  /**
   * @return the href
   */
  public URI getHref() {
    return href;
  }

  /**
   * @return the rel
   */
  public URI getRel() {
    return rel;
  }

  /**
   * @return the type
   */
  public String getType() {
    return type;
  }

  /**
   * @return the hrefLang
   */
  public String getHrefLang() {
    return hrefLang;
  }

  /**
   * @return the title
   */
  public String getTitle() {
    return title;
  }

  /**
   * @return the length
   */
  public String getLength() {
    return length;
  }

  /**
   * @param commonAttributes
   *          the commonAttributes to set
   */
  public void setCommonAttributes(AtomCommonAttributes commonAttributes) {
    this.commonAttributes = commonAttributes;
  }

  /**
   * @param href
   *          the href to set
   */
  public void setHref(URI href) {
    this.href = href;
  }

  /**
   * @param rel
   *          the rel to set
   */
  public void setRel(URI rel) {
    this.rel = rel;
  }

  /**
   * @param type
   *          the type to set
   */
  public void setType(String type) {
    this.type = type;
  }

  /**
   * @param hrefLang
   *          the hrefLang to set
   */
  public void setHrefLang(String hrefLang) {
    this.hrefLang = hrefLang;
  }

  /**
   * @param title
   *          the title to set
   */
  public void setTitle(String title) {
    this.title = title;
  }

  /**
   * @param length
   *          the length to set
   */
  public void setLength(String length) {
    this.length = length;
  }

}
