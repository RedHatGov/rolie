package gov.nist.rolie.polie.core.models.elements;

import gov.nist.rolie.polie.core.models.constructs.AtomPerson;

/*    The "atom:author" element is a Person construct that indicates the
   author of the entry or feed.

   atomAuthor = element atom:author { atomPersonConstruct }

   If an atom:entry element does not contain atom:author elements, then
   the atom:author elements of the contained atom:source element are
   considered to apply.  In an Atom Feed Document, the atom:author
   elements of the containing atom:feed element are considered to apply
   to the entry if there are no atom:author elements in the locations
   described above. */
public class AtomAuthor implements AtomElement {

	private AtomPerson person;
	
	public AtomAuthor()
	{
		this.person = new AtomPerson();
	}
	
}
