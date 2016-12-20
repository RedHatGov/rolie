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

import gov.nist.rolie.polie.core.models.constructs.AtomCommonAttributes;
import gov.nist.rolie.polie.core.models.constructs.AtomURI;

public class AtomIcon implements AtomElement{
	private AtomCommonAttributes commonAttributes;
	private AtomURI iri;
}
