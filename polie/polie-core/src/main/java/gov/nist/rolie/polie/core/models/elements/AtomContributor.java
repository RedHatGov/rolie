package gov.nist.rolie.polie.core.models.elements;

import gov.nist.rolie.polie.core.models.constructs.AtomCommonAttributes;
import gov.nist.rolie.polie.core.models.constructs.AtomPerson;

/*
 * 
 *  The "atom:contributor" element is a Person construct that indicates a
 *  person or other entity who contributed to the entry or feed.
 *
 * atomContributor = element atom:contributor { atomPersonConstruct }
 */
public class AtomContributor implements AtomElement{
	
	private AtomCommonAttributes commonattributes;
	private AtomPerson person;
	
}
