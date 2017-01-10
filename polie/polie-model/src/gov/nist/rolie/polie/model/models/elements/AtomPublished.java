package gov.nist.rolie.polie.model.models.elements;


import gov.nist.rolie.polie.model.models.constructs.AtomDate;

/*
 * 4.2.9.  The "atom:published" Element

   The "atom:published" element is a Date construct indicating an
   instant in time associated with an event early in the life cycle of
   the entry.

   atomPublished = element atom:published { atomDateConstruct }

   Typically, atom:published will be associated with the initial
   creation or first availability of the resource.
 */

public class AtomPublished implements AtomElement{


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
