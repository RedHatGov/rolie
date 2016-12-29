package gov.nist.rolie.polie.core.models.elements;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import gov.nist.rolie.polie.core.XMLMangement.AtomTextAdapter;
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
@XmlAccessorType(XmlAccessType.NONE)
public class AtomSummary implements AtomElement{

	@XmlValue
	@XmlJavaTypeAdapter(AtomTextAdapter.class)
	private AtomTextConstruct summary;

	/**
	 * @param summary
	 */
	public AtomSummary(AtomTextConstruct summary) {
		super();
		this.summary = summary;
	}

	public AtomSummary(String string) {
		super();
		this.summary=new AtomTextConstruct(string);
	}

	public AtomSummary(){}
	/**
	 * @return the summary
	 */
	public AtomTextConstruct getSummary() {
		return summary;
	}
	

	public String getSummaryText() {
		return summary.toString();
	}
	

	public void setSummaryText(String string)
	{
		summary.setContent(string);
	}

	/**
	 * @param summary the summary to set
	 */
	public void setSummary(AtomTextConstruct summary) {
		this.summary = summary;
	}
	
	
}
