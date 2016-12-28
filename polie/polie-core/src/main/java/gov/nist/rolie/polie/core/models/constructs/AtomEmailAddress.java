package gov.nist.rolie.polie.core.models.constructs;

import gov.nist.rolie.polie.core.models.elements.AtomElement;

/*The "atom:email" element's content conveys an e-mail address
associated with the person.  Person constructs MAY contain an
atom:email element, but MUST NOT contain more than one.  Its content
MUST conform to the "addr-spec" production in [RFC2822].*/
public class AtomEmailAddress implements AtomElement{
	
	
	//atomEmailAddress = xsd:string { pattern = ".+@.+" }
	private String address;

	public AtomEmailAddress(String address)
	{
		this.address = address;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	
	
}
