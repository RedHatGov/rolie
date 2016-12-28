package gov.nist.rolie.polie.core.models.elements;

import gov.nist.rolie.polie.core.models.constructs.AtomTextConstruct;

/*
 * 4.2.14.  The "atom:title" Element

   The "atom:title" element is a Text construct that conveys a human-
   readable title for an entry or feed.

   atomTitle = element atom:title { atomTextConstruct }
 */
public class AtomTitle implements AtomElement{

	private AtomTextConstruct title;

	/**
	 * @param title
	 */
	public AtomTitle(AtomTextConstruct title) {
		super();
		this.title = title;
	}

	public AtomTitle(String title) {
		super();
		this.title = new AtomTextConstruct(title);
	}
	
	/**
	 * @return the title
	 */
	public AtomTextConstruct getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(AtomTextConstruct title) {
		this.title = title;
	}
	
	
}
