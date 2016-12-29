package gov.nist.rolie.polie.core.models.elements;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

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
@XmlAccessorType(XmlAccessType.NONE)
public class AtomAuthor implements AtomElement {
	@XmlElement
	private AtomPerson person;
	
	public AtomAuthor(AtomPerson person)
	{
		this.setPerson(person);
	}
	
	public AtomAuthor(){}

	/**
	 * @return the person
	 */
	public AtomPerson getPerson() {
		return person;
	}

	/**
	 * @param person the person to set
	 */
	public void setPerson(AtomPerson person) {
		this.person = person;
	}
	
}
