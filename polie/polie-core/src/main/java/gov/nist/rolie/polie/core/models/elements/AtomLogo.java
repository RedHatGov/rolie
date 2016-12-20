package gov.nist.rolie.polie.core.models.elements;

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
public class AtomLogo  implements AtomElement{

	private AtomCommonAttributes commonattributes;
	private AtomURI iri;
	
}
