/*
 * 
 */
package gov.nist.rolie.polie.model.models;

import org.w3.x2005.atom.FeedDocument;

import gov.nist.rolie.polie.model.ResourceType;
import gov.nist.rolie.polie.model.models.elements.AtomElement;


/**
4.1.1.  The "atom:feed" Element

   The "atom:feed" element is the document (i.e., top-level) element of
   an Atom Feed Document, acting as a container for metadata and data
   associated with the feed.  Its element children consist of metadata
   elements followed by zero or more atom:entry child elements.

   atomFeed =
      element atom:feed {
         atomCommonAttributes,
         (atomAuthor*
          & atomCategory*
          & atomContributor*
          & atomGenerator?
          & atomIcon?
          & atomId
          & atomLink*
          & atomLogo?
          & atomRights?
          & atomSubtitle?
          & atomTitle
          & atomUpdated
          & extensionElement*),
         atomEntry*
      }

   This specification assigns no significance to the order of atom:entry
   elements within the feed.



Nottingham & Sayre          Standards Track                    [Page 11]

 
RFC 4287                      Atom Format                  December 2005


   The following child elements are defined by this specification (note
   that the presence of some of these elements is required):

   o  atom:feed elements MUST contain one or more atom:author elements,
      unless all of the atom:feed element's child atom:entry elements
      contain at least one atom:author element.
   o  atom:feed elements MAY contain any number of atom:category
      elements.
   o  atom:feed elements MAY contain any number of atom:contributor
      elements.
   o  atom:feed elements MUST NOT contain more than one atom:generator
      element.
   o  atom:feed elements MUST NOT contain more than one atom:icon
      element.
   o  atom:feed elements MUST NOT contain more than one atom:logo
      element.
   o  atom:feed elements MUST contain exactly one atom:id element.
   o  atom:feed elements SHOULD contain one atom:link element with a rel
      attribute value of "self".  This is the preferred URI for
      retrieving Atom Feed Documents representing this Atom feed.
   o  atom:feed elements MUST NOT contain more than one atom:link
      element with a rel attribute value of "alternate" that has the
      same combination of type and hreflang attribute values.
   o  atom:feed elements MAY contain additional atom:link elements
      beyond those described above.
   o  atom:feed elements MUST NOT contain more than one atom:rights
      element.
   o  atom:feed elements MUST NOT contain more than one atom:subtitle
      element.
   o  atom:feed elements MUST contain exactly one atom:title element.
   o  atom:feed elements MUST contain exactly one atom:updatedDate element.

   If multiple atom:entry elements with the same atom:id value appear in
   an Atom Feed Document, they represent the same entry.  Their
   atom:updatedDate timestamps SHOULD be different.  If an Atom Feed
   Document contains multiple entries with the same atom:id, Atom
   Processors MAY choose to display all of them or some subset of them.
   One typical behavior would be to display only the entry with the
   latest atom:updatedDate timestamp.
 */

public class AtomFeed extends AbstractAPPResource<FeedDocument> implements AtomElement {

	public AtomFeed() {
		this(FeedDocument.Factory.newInstance());
	}

	public AtomFeed(FeedDocument doc) {
		super(doc);
	}

	@Override
	public ResourceType getResourceType() {
		return ResourceType.FEED;
	}

}
