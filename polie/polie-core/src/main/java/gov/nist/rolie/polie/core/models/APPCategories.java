package gov.nist.rolie.polie.core.models;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import gov.nist.rolie.polie.core.XMLMangement.AtomURIAdapter;
import gov.nist.rolie.polie.core.models.constructs.AtomURI;
import gov.nist.rolie.polie.core.models.elements.APPElement;
import gov.nist.rolie.polie.core.models.elements.AtomCategory;

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
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name="categories" , namespace="http://www.w3.org/2007/app")
public class APPCategories extends APPResource implements APPElement {
	
	@XmlAttribute
	@XmlJavaTypeAdapter(AtomURIAdapter.class)
	private AtomURI href;
	
	@XmlAttribute
	private String fixed;
	
	@XmlAttribute
	@XmlJavaTypeAdapter(AtomURIAdapter.class)
	private AtomURI scheme;
	
	@XmlElement
	private ArrayList<AtomCategory> category;

	/**
	 * @param href
	 * @param fixed
	 * @param scheme
	 * @param category
	 */
	public APPCategories(AtomURI href, String fixed, AtomURI scheme, ArrayList<AtomCategory> category) {
		super();
		this.href = href;
		this.fixed = fixed;
		this.scheme = scheme;
		this.category = category;
	}
	
	public APPCategories(){}

	/**
	 * @return the href
	 */
	public AtomURI getHref() {
		return href;
	}

	/**
	 * @return the fixed
	 */
	public String getFixed() {
		return fixed;
	}

	/**
	 * @return the scheme
	 */
	public AtomURI getScheme() {
		return scheme;
	}

	/**
	 * @return the category
	 */
	public ArrayList<AtomCategory> getCategory() {
		return category;
	}

	/**
	 * @param href the href to set
	 */
	public void setHref(AtomURI href) {
		this.href = href;
	}

	/**
	 * @param fixed the fixed to set
	 */
	public void setFixed(String fixed) {
		this.fixed = fixed;
	}

	/**
	 * @param scheme the scheme to set
	 */
	public void setScheme(AtomURI scheme) {
		this.scheme = scheme;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(ArrayList<AtomCategory> category) {
		this.category = category;
	};
	
	
	
}
