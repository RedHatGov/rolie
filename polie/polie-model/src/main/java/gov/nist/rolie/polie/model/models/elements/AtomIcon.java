package gov.nist.rolie.polie.model.models.elements;
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

import gov.nist.rolie.polie.model.models.constructs.AtomCommonAttributes;


public class AtomIcon implements AtomElement{
	
	
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
	public URI getIri() {
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
	public void setIri(URI iri) {
		this.iri = iri;
	}
	
	
}
