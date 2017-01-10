package gov.nist.rolie.polie.model.models.elements;


import gov.nist.rolie.polie.model.models.constructs.AtomCommonAttributes;
import gov.nist.rolie.polie.model.models.constructs.AtomURI;

/**rolieProperty =
element rolie:property {
  atomCommonAttributes,
  attribute scheme { atomURI },
  attribute term { text },
  attribute label { text } ?
  empty
  */

public class RolieProperty implements RolieElement{
	
	private AtomCommonAttributes atomCommonAttributes;

	private AtomURI scheme;

	private String term;

	private String label;
	
	
	
	
	
	/**
	 * @param atomCommonAttributes
	 * @param scheme
	 * @param term
	 * @param label
	 */
	public RolieProperty(AtomCommonAttributes atomCommonAttributes, AtomURI scheme, String term, String label) {
		super();
		this.atomCommonAttributes = atomCommonAttributes;
		this.scheme = scheme;
		this.term = term;
		this.label = label;
	}
	
	public RolieProperty(){};
	/**
	 * @return the atomCommonAttributes
	 */
	public AtomCommonAttributes getAtomCommonAttributes() {
		return atomCommonAttributes;
	}
	/**
	 * @return the scheme
	 */
	public AtomURI getScheme() {
		return scheme;
	}
	/**
	 * @return the term
	 */
	public String getTerm() {
		return term;
	}
	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}
	/**
	 * @param atomCommonAttributes the atomCommonAttributes to set
	 */
	public void setAtomCommonAttributes(AtomCommonAttributes atomCommonAttributes) {
		this.atomCommonAttributes = atomCommonAttributes;
	}
	/**
	 * @param scheme the scheme to set
	 */
	public void setScheme(AtomURI scheme) {
		this.scheme = scheme;
	}
	/**
	 * @param term the term to set
	 */
	public void setTerm(String term) {
		this.term = term;
	}
	/**
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}
	
	

}
