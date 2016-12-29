package gov.nist.rolie.polie.core.models.elements;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import gov.nist.rolie.polie.core.XMLMangement.AtomTextAdapter;
import gov.nist.rolie.polie.core.models.constructs.AtomTextConstruct;

/*
 * 4.2.12.  The "atom:subtitle" Element

   The "atom:subtitle" element is a Text construct that conveys a human-
   readable description or subtitle for a feed.

   atomSubtitle = element atom:subtitle { atomTextConstruct }
 */
@XmlAccessorType(XmlAccessType.NONE)
public class AtomSubTitle {
	@XmlValue
	@XmlJavaTypeAdapter(AtomTextAdapter.class)
	private AtomTextConstruct subtitle;

	/**
	 * @param subtitle
	 */
	public AtomSubTitle(AtomTextConstruct subtitle) {
		super();
		this.subtitle = subtitle;
	}

	public AtomSubTitle(){}
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
