package gov.nist.rolie.polie.model.models.elements;

import java.net.URI;

/*    The "atom:category" element conveys information about a category
   associated with an entry or feed.  This specification assigns no
   meaning to the content (if any) of this element.

   atomCategory =
      element atom:category {
         atomCommonAttributes,
         attribute term { text },
         attribute scheme { atomUri }?,
         attribute label { text }?,
         undefinedContent
      }
*/

public class AtomCategory implements AtomElement{
	
	/*   The "term" attribute is a string that identifies the category to
   which the entry or feed belongs.  Category elements MUST have a
   "term" attribute.*/

	private String term;
	
	/*
   The "label" attribute provides a human-readable label for display in
   end-user applications.  The content of the "label" attribute is
   Language-Sensitive.  Entities such as "&amp;" and "&lt;" represent
   their corresponding characters ("&" and "<", respectively), not
   markup.  Category elements MAY have a "label" attribute.
	 */
	private String label;
	
	/*
	 *    The "scheme" attribute is an IRI that identifies a categorization
   scheme.  Category elements MAY have a "scheme" attribute.
	 */
	private URI scheme;

	/**
	 * @param term
	 * @param label
	 * @param scheme
	 */
	public AtomCategory(String term, String label, URI scheme) {
		super();
		this.term = term;
		this.label = label;
		this.scheme = scheme;
	}
	
	public AtomCategory(){}

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
	 * @return the scheme
	 */
	public URI getScheme() {
		return scheme;
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

	/**
	 * @param scheme the scheme to set
	 */
	public void setScheme(URI scheme) {
		this.scheme = scheme;
	}

	
	
	
}
