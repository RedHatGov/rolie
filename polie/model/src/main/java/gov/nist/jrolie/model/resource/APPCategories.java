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

package gov.nist.jrolie.model.resource;

import gov.nist.jrolie.model.ResourceType;
import gov.nist.jrolie.model.element.APPElement;

import org.w3.x2007.app.CategoriesDocument;

/**
 * 
 * The root of a Category Document is the "app:categories" element. An app:categories element can
 * contain zero or more atom:category elements from the Atom Syndication Format [RFC4287] namespace
 * ("http://www.w3.org/2005/Atom").
 * <p>
 * An atom:category child element that has no "scheme" attribute inherits the attribute from its
 * app:categories parent. An atom: category child element with an existing "scheme" attribute does
 * not inherit the "scheme" value of its app:categories parent element.
 * <p>
 * atomCategory = element atom:category { atomCommonAttributes, attribute term { text }, attribute
 * scheme { atomURI }?, attribute label { text }?, undefinedContent }
 * <p>
 * appInlineCategories = element app:categories { attribute fixed { "yes" | "no" }?, attribute
 * scheme { atomURI }?, (atomCategory*, undefinedContent) }
 * <p>
 * appOutOfLineCategories = element app:categories { attribute href { atomURI }, undefinedContent }
 * <p>
 * appCategories = appInlineCategories | appOutOfLineCategories
 * 
 * 
 * The app:categories element can contain a "fixed" attribute, with a value of either "yes" or "no",
 * indicating whether the list of categories is a fixed or an open set. The absence of the "fixed"
 * attribute is equivalent to the presence of a "fixed" attribute with a value of "no".
 * 
 * Alternatively, the app:categories element MAY contain an "href" attribute, whose value MUST be an
 * IRI reference identifying a Category Document. If the "href" attribute is provided, the
 * app:categories element MUST be empty and MUST NOT have the "fixed" or "scheme" attributes.
 * 
 * @author sab3
 * 
 */
public class APPCategories extends AbstractAPPResource<CategoriesDocument> implements APPElement {

  public APPCategories() {
    this(CategoriesDocument.Factory.newInstance());
  }

  public APPCategories(CategoriesDocument doc) {
    super(doc);
  }

  public ResourceType getResourceType() {
    return ResourceType.CATEGORY;
  }

}
