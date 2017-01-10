package gov.nist.rolie.polie.model.models.constructs;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlValue;

import gov.nist.rolie.polie.model.models.elements.AtomElement;

/*The "atom:email" element's content conveys an e-mail address
associated with the person.  Person constructs MAY contain an
atom:email element, but MUST NOT contain more than one.  Its content
MUST conform to the "addr-spec" production in [RFC2822].*/
@XmlAccessorType(XmlAccessType.NONE)
public class AtomEmailAddress implements AtomElement{
	
	
	//atomEmailAddress = xsd:string { pattern = ".+@.+" }
	@XmlValue
	private String address;

	public AtomEmailAddress(String address)
	{
		this.address = address;
	}

	public AtomEmailAddress(){}
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
