package gov.nist.rolie.polie.core.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * The root of a Category Document is the "app:categories" element. An
 * app:categories element can contain zero or more atom:category elements from
 * the Atom Syndication Format [RFC4287] namespace
 * ("http://www.w3.org/2005/Atom").
 * <p>
 * An atom:category child element that has no "scheme" attribute inherits the
 * attribute from its app:categories parent. An atom: category child element
 * with an existing "scheme" attribute does not inherit the "scheme" value of
 * its app:categories parent element.
 * <p>
 * atomCategory = element atom:category { atomCommonAttributes, attribute term {
 * text }, attribute scheme { atomURI }?, attribute label { text }?,
 * undefinedContent }
 * <p>
 * appInlineCategories = element app:categories { attribute fixed { "yes" | "no"
 * }?, attribute scheme { atomURI }?, (atomCategory*, undefinedContent) }
 * <p>
 * appOutOfLineCategories = element app:categories { attribute href { atomURI },
 * undefinedContent }
 * <p>
 * appCategories = appInlineCategories | appOutOfLineCategories
 * 
 * 
 * The app:categories element can contain a "fixed" attribute, with a value of
 * either "yes" or "no", indicating whether the list of categories is a fixed or
 * an open set. The absence of the "fixed" attribute is equivalent to the
 * presence of a "fixed" attribute with a value of "no".
 * 
 * Alternatively, the app:categories element MAY contain an "href" attribute,
 * whose value MUST be an IRI reference identifying a Category Document. If the
 * "href" attribute is provided, the app:categories element MUST be empty and
 * MUST NOT have the "fixed" or "scheme" attributes.
 * 
 * @author sab3
 * 
 */
@XmlRootElement(name="categories")
@XmlAccessorType(XmlAccessType.NONE)
public class APPCategoryDocument extends APPResource {

}
