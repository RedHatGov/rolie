package gov.nist.rolie.polie.core.models.elements;

import gov.nist.rolie.polie.core.models.constructs.AtomTextConstruct;

/*
 * 4.2.12.  The "atom:subtitle" Element

   The "atom:subtitle" element is a Text construct that conveys a human-
   readable description or subtitle for a feed.

   atomSubtitle = element atom:subtitle { atomTextConstruct }
 */
public class AtomSubTitle {

	private AtomTextConstruct subtitle;

	/**
	 * @param subtitle
	 */
	public AtomSubTitle(AtomTextConstruct subtitle) {
		super();
		this.subtitle = subtitle;
	}

	/**
	 * @return the subtitle
	 */
	public AtomTextConstruct getSubtitle() {
		return subtitle;
	}

	/**
	 * @param subtitle the subtitle to set
	 */
	public void setSubtitle(AtomTextConstruct subtitle) {
		this.subtitle = subtitle;
	}
	
	
	
}
