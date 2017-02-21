/*
 * 
 */
package gov.nist.rolie.polie.model.models;
import org.w3.x2005.atom.EntryDocument;

import gov.nist.rolie.polie.model.ResourceType;
import gov.nist.rolie.polie.model.models.elements.AtomElement;


/**
4.1.2.  The "atom:entry" Element

   The "atom:entry" element represents an individual entry, acting as a
   container for metadata and data associated with the entry.  This
   element can appear as a child of the atom:feed element, or it can
   appear as the document (i.e., top-level) element of a stand-alone
   Atom Entry Document.

   atomEntry =
      element atom:entry {
         atomCommonAttributes,
         (atomAuthor*
          & atomCategory*
          & atomContent?
          & atomContributor*
          & atomId
          & atomLink*
          & atomPublished?
          & atomRights?
          & atomSource?
          & atomSummary?
          & atomTitle
          & atomUpdated
          & extensionElement*)
      }

   This specification assigns no significance to the order of appearance
   of the child elements of atom:entry.

   The following child elements are defined by this specification (note
   that it requires the presence of some of these elements):

   o  atom:entry elements MUST contain one or more atom:author elements,
      unless the atom:entry contains an atom:source element that
      contains an atom:author element or, in an Atom Feed Document, the
      atom:feed element contains an atom:author element itself.
   o  atom:entry elements MAY contain any number of atom:category
      elements.
   o  atom:entry elements MUST NOT contain more than one atom:content
      element.
   o  atom:entry elements MAY contain any number of atom:contributor
      elements.
   o  atom:entry elements MUST contain exactly one atom:id element.
   o  atom:entry elements that contain no child atom:content element
      MUST contain at least one atom:link element with a rel attribute
      value of "alternate".
   o  atom:entry elements MUST NOT contain more than one atom:link
      element with a rel attribute value of "alternate" that has the
      same combination of type and hreflang attribute values.
   o  atom:entry elements MAY contain additional atom:link elements
      beyond those described above.
   o  atom:entry elements MUST NOT contain more than one atom:publishedDate
      element.
   o  atom:entry elements MUST NOT contain more than one atom:rights
      element.
   o  atom:entry elements MUST NOT contain more than one atom:source
      element.
   o  atom:entry elements MUST contain an atom:summary element in either
      of the following cases:
      *  the atom:entry contains an atom:content that has a "src"
         attribute (and is thus empty).
      *  the atom:entry contains content that is encoded in Base64;
         i.e., the "type" attribute of atom:content is a MIME media type
         [MIMEREG], but is not an XML media type [RFC3023], does not
         begin with "text/", and does not end with "/xml" or "+xml".
   o  atom:entry elements MUST NOT contain more than one atom:summary
      element.
   o  atom:entry elements MUST contain exactly one atom:title element.
   o  atom:entry elements MUST contain exactly one atom:updated element.
 */

public class AtomEntry extends AbstractAPPResource<EntryDocument> implements AtomElement {

	public AtomEntry() {
		this(EntryDocument.Factory.newInstance());
	}

	public AtomEntry(EntryDocument doc) {
		super(doc);
	}


	public ResourceType getResourceType() {
		return ResourceType.ENTRY;
	}

}
