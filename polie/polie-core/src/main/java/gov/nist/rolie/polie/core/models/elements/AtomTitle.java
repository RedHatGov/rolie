package gov.nist.rolie.polie.core.models.elements;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import gov.nist.rolie.polie.core.XMLMangement.AtomTextAdapter;
import gov.nist.rolie.polie.core.models.constructs.AtomTextConstruct;

/*
 * 4.2.14.  The "atom:title" Element

   The "atom:title" element is a Text construct that conveys a human-
   readable title for an entry or feed.

   atomTitle = element atom:title { atomTextConstruct }
 */
@XmlAccessorType(XmlAccessType.NONE)
public class AtomTitle implements AtomElement{

	@XmlValue
	@XmlJavaTypeAdapter(AtomTextAdapter.class)
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
	
	public AtomTitle(){}
	
	/**
	 * @return the title
	 */
	public AtomTextConstruct getTitleObject() {
		return title;
	}
	

	public String getTitle()
	{
		return title.toString();
	}
	

	public void setTitle(String string)
	{
		this.title.setContent(string);
	}
	/**
	 * @param title the title to set
	 */
	public void setTitleObject(AtomTextConstruct title) {
		this.title = title;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AtomTitle [title=" + title + "]";
	}
	
	
	
}
