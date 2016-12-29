package gov.nist.rolie.polie.core.models.elements;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import gov.nist.rolie.polie.core.models.constructs.AtomCommonAttributes;
import gov.nist.rolie.polie.core.models.constructs.AtomPerson;

/*
 * 
 *  The "atom:contributor" element is a Person construct that indicates a
 *  person or other entity who contributed to the entry or feed.
 *
 * atomContributor = element atom:contributor { atomPersonConstruct }
 */
@XmlAccessorType(XmlAccessType.NONE)
public class AtomContributor implements AtomElement{
	
	private AtomCommonAttributes commonattributes;
	@XmlElement
	private AtomPerson person;
	/**
	 * @param commonattributes
	 * @param person
	 */
	public AtomContributor(AtomCommonAttributes commonattributes, AtomPerson person) {
		super();
		this.commonattributes = commonattributes;
		this.person = person;
	}
	
	public AtomContributor(){};
	/**
	 * @return the commonattributes
	 */
	public AtomCommonAttributes getCommonattributes() {
		return commonattributes;
	}
	/**
	 * @return the person
	 */
	public AtomPerson getPerson() {
		return person;
	}
	/**
	 * @param commonattributes the commonattributes to set
	 */
	public void setCommonattributes(AtomCommonAttributes commonattributes) {
		this.commonattributes = commonattributes;
	}
	/**
	 * @param person the person to set
	 */
	public void setPerson(AtomPerson person) {
		this.person = person;
	}
	
	
	
	
}
