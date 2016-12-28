package gov.nist.rolie.polie.core.models.elements;

import gov.nist.rolie.polie.core.models.constructs.AtomDate;

/*
 * 4.2.15.  The "atom:updated" Element

   The "atom:updated" element is a Date construct indicating the most
   recent instant in time when an entry or feed was modified in a way
   the publisher considers significant.  Therefore, not all
   modifications necessarily result in a changed atom:updated value.

   atomUpdated = element atom:updated { atomDateConstruct }

   Publishers MAY change the value of this element over time.

 */
public class AtomUpdated implements AtomElement{

	private AtomDate date;

	/**
	 * @param date
	 */
	public AtomUpdated(AtomDate date) {
		super();
		this.date = date;
	}

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
