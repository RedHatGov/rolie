package gov.nist.rolie.polie.core.models.elements;
/**
 * 
 * The root of a Category Document is the "app:categories" element.  An
   app:categories element can contain zero or more atom:category
   elements from the Atom Syndication Format [RFC4287] namespace
   ("http://www.w3.org/2005/Atom").
	<p>
   An atom:category child element that has no "scheme" attribute
   inherits the attribute from its app:categories parent.  An atom:
   category child element with an existing "scheme" attribute does not
   inherit the "scheme" value of its app:categories parent element.
 	<p>
   atomCategory =
       element atom:category {
          atomCommonAttributes,
          attribute term { text },
          attribute scheme { atomURI }?,
          attribute label { text }?,
          undefinedContent
       }
	<p>
   appInlineCategories =
       element app:categories {
           attribute fixed { "yes" | "no" }?,
           attribute scheme { atomURI }?,
           (atomCategory*,
           undefinedContent)
       }
	<p>
   appOutOfLineCategories =
       element app:categories {
           attribute href { atomURI },
           undefinedContent
       }
	<p>
   appCategories = appInlineCategories | appOutOfLineCategories
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * @author sab3
 * 
 */
public class AtomPPCategories implements AtomElement{

}
