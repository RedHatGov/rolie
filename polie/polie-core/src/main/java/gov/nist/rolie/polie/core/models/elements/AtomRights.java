package gov.nist.rolie.polie.core.models.elements;

import gov.nist.rolie.polie.core.models.constructs.AtomTextConstruct;

/*
 * 4.2.10.  The "atom:rights" Element

   The "atom:rights" element is a Text construct that conveys
   information about rights held in and over an entry or feed.

   atomRights = element atom:rights { atomTextConstruct }

   The atom:rights element SHOULD NOT be used to convey machine-readable
   licensing information.

   If an atom:entry element does not contain an atom:rights element,
   then the atom:rights element of the containing atom:feed element, if
   present, is considered to apply to the entry.
 */
public class AtomRights implements AtomElement{
	
	private AtomTextConstruct text;
	
}
