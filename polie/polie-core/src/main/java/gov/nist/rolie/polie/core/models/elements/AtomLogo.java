package gov.nist.rolie.polie.core.models.elements;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import gov.nist.rolie.polie.core.XMLMangement.AtomTextAdapter;
import gov.nist.rolie.polie.core.XMLMangement.AtomURIAdapter;
import gov.nist.rolie.polie.core.models.constructs.AtomCommonAttributes;
import gov.nist.rolie.polie.core.models.constructs.AtomURI;

/*
 * 4.2.8.  The "atom:logo" Element

   The "atom:logo" element's content is an IRI reference [RFC3987] that
   identifies an image that provides visual identification for a feed.

   atomLogo = element atom:logo {
      atomCommonAttributes,
      (atomUri)
   }

   The image SHOULD have an aspect ratio of 2 (horizontal) to 1
   (vertical).
 */
@XmlAccessorType(XmlAccessType.NONE)
public class AtomLogo  implements AtomElement{

	private AtomCommonAttributes commonattributes;
	@XmlValue
	@XmlJavaTypeAdapter(AtomURIAdapter.class)
	private AtomURI iri;
	/**
	 * @param commonattributes
	 * @param iri
	 */
	public AtomLogo(AtomCommonAttributes commonattributes, AtomURI iri) {
		super();
		this.commonattributes = commonattributes;
		this.iri = iri;
	}
	
	public AtomLogo(){};
	/**
	 * @return the commonattributes
	 */
	public AtomCommonAttributes getCommonattributes() {
		return commonattributes;
	}
	/**
	 * @return the iri
	 */
	public AtomURI getIri() {
		return iri;
	}
	/**
	 * @param commonattributes the commonattributes to set
	 */
	public void setCommonattributes(AtomCommonAttributes commonattributes) {
		this.commonattributes = commonattributes;
	}
	/**
	 * @param iri the iri to set
	 */
	public void setIri(AtomURI iri) {
		this.iri = iri;
	}
	
	
	
}
