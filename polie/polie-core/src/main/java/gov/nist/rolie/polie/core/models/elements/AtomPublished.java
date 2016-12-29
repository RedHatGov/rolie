package gov.nist.rolie.polie.core.models.elements;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import gov.nist.rolie.polie.core.XMLMangement.AtomDateAdapter;
import gov.nist.rolie.polie.core.models.constructs.AtomDate;

/*
 * 4.2.9.  The "atom:published" Element

   The "atom:published" element is a Date construct indicating an
   instant in time associated with an event early in the life cycle of
   the entry.

   atomPublished = element atom:published { atomDateConstruct }

   Typically, atom:published will be associated with the initial
   creation or first availability of the resource.
 */
@XmlAccessorType(XmlAccessType.NONE)
public class AtomPublished implements AtomElement{

	@XmlValue
	@XmlJavaTypeAdapter(AtomDateAdapter.class)
	private AtomDate date;

	/**
	 * @param date
	 */
	public AtomPublished(AtomDate date) {
		super();
		this.date = date;
	}
	
	public AtomPublished(){}

	/**
	 * @return the date
	 */
	public AtomDate getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(AtomDate date) {
		this.date = date;
	}
	
	
}
