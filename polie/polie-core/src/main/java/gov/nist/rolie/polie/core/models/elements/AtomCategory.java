package gov.nist.rolie.polie.core.models.elements;

import gov.nist.rolie.polie.core.models.constructs.AtomURI;

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
	private AtomURI scheme;

}
