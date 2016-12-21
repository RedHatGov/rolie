package gov.nist.rolie.polie.core.models;

import java.util.ArrayList;

import gov.nist.rolie.polie.core.models.constructs.AtomCommonAttributes;
import gov.nist.rolie.polie.core.models.constructs.AtomURI;
import gov.nist.rolie.polie.core.models.elements.AtomCategory;
import gov.nist.rolie.polie.core.models.elements.AtomPPCategories;
import gov.nist.rolie.polie.core.models.elements.AtomTitle;

/**
8.3.3.  The "app:collection" Element

   The "app:collection" element describes a Collection.  The app:
   collection element MUST contain one atom:title element.

   The app:collection element MAY contain any number of app:accept
   elements, indicating the types of representations accepted by the
   Collection.  The order of such elements is not significant.

   The app:collection element MAY contain any number of app:categories
   elements.

   appCollection =
      element app:collection {
         appCommonAttributes,
         attribute href { atomURI  },
         ( atomTitle
           & appAccept*
           & appCategories*
           & extensionSansTitleElement* )
      }
 */
public class AtomCollection implements AtomDocument {

	private AtomCommonAttributes commonAttributes;
	
	/*
	 * 8.3.3.1.  The "href" Attribute

   The app:collection element MUST contain an "href" attribute, whose
   value gives the IRI of the Collection.
	 */
	private AtomURI href;
	
	//TODO
	private AtomTitle title;
	private AtomPPCategories categories;
	

	//private AtomPPAccept accept;
}
