package gov.nist.rolie.polie.core.models.elements;

import gov.nist.rolie.polie.core.models.constructs.AtomTextConstruct;

/*
 * 4.2.13.  The "atom:summary" Element

   The "atom:summary" element is a Text construct that conveys a short
   summary, abstract, or excerpt of an entry.

   atomSummary = element atom:summary { atomTextConstruct }

   It is not advisable for the atom:summary element to duplicate
   atom:title or atom:content because Atom Processors might assume there
   is a useful summary when there is none.
 */
public class AtomSummary implements AtomElement{

	private AtomTextConstruct summary;

	/**
	 * @param summary
	 */
	public AtomSummary(AtomTextConstruct summary) {
		super();
		this.summary = summary;
	}

	/**
	 * @return the summary
	 */
	public AtomTextConstruct getSummary() {
		return summary;
	}

	/**
	 * @param summary the summary to set
	 */
	public void setSummary(AtomTextConstruct summary) {
		this.summary = summary;
	}
	
	
}
