package gov.nist.rolie.polie.core.models.elements;
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

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import gov.nist.rolie.polie.core.XMLMangement.AtomURIAdapter;
import gov.nist.rolie.polie.core.models.constructs.AtomCommonAttributes;
import gov.nist.rolie.polie.core.models.constructs.AtomURI;
@XmlAccessorType(XmlAccessType.NONE)
public class AtomIcon implements AtomElement{
	
	
	private AtomCommonAttributes commonAttributes;
	
	@XmlValue
	@XmlJavaTypeAdapter(AtomURIAdapter.class)
	private AtomURI iri;
	/**
	 * @param commonAttributes
	 * @param iri
	 */
	public AtomIcon(AtomCommonAttributes commonAttributes, AtomURI iri) {
		super();
		this.commonAttributes = commonAttributes;
		this.iri = iri;
	}
	
	public AtomIcon(){};
	
	/**
	 * @return the commonAttributes
	 */
	public AtomCommonAttributes getCommonAttributes() {
		return commonAttributes;
	}
	/**
	 * @return the iri
	 */
	public AtomURI getIri() {
		return iri;
	}
	/**
	 * @param commonAttributes the commonAttributes to set
	 */
	public void setCommonAttributes(AtomCommonAttributes commonAttributes) {
		this.commonAttributes = commonAttributes;
	}
	/**
	 * @param iri the iri to set
	 */
	public void setIri(AtomURI iri) {
		this.iri = iri;
	}
	
	
}
