package gov.nist.rolie.polie.model.models.elements;


import gov.nist.rolie.polie.model.models.constructs.AtomCommonAttributes;
import gov.nist.rolie.polie.model.models.constructs.AtomURI;

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

public class AtomLogo  implements AtomElement{

	private AtomCommonAttributes commonattributes;

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
