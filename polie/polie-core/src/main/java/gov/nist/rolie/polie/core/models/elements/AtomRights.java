package gov.nist.rolie.polie.core.models.elements;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import gov.nist.rolie.polie.core.XMLMangement.AtomTextAdapter;
import gov.nist.rolie.polie.core.models.constructs.AtomTextConstruct;

/*
 * 4.2.10.  The "atom:rights" Element

   The "atom:rights" element is a Text construct that conveys
   information about rights held in and over an entry or feed.

   atomRights = element atom:rights { atomTextConstruct }

   The atom:rights element SHOULD NOT be used to convey machine-readable
   licensing information.

   If an atom:entry element does not contain an atom:rights element,
   then the atom:rights element of the containing atom:feed element, if
   present, is considered to apply to the entry.
 */
@XmlAccessorType(XmlAccessType.NONE)
public class AtomRights implements AtomElement{
	
	@XmlValue
	@XmlJavaTypeAdapter(AtomTextAdapter.class)
	private AtomTextConstruct text;

	/**
	 * @param text
	 */
	public AtomRights(AtomTextConstruct text) {
		super();
		this.text = text;
	}

	public AtomRights(){}
	
	/**
	 * @return the text
	 */
	public AtomTextConstruct getText() {
		return text;
	}

	/**
	 * @param text the text to set
	 */
	public void setText(AtomTextConstruct text) {
		this.text = text;
	}
	
	
	
}
