package gov.nist.rolie.polie.core.models.elements;

import java.util.ArrayList;

import gov.nist.rolie.polie.core.models.constructs.AtomCommonAttributes;

/*
 * 4.2.11.  The "atom:source" Element

   If an atom:entry is copied from one feed into another feed, then the
   source atom:feed's metadata (all child elements of atom:feed other
   than the atom:entry elements) MAY be preserved within the copied
   entry by adding an atom:source child element, if it is not already
   present in the entry, and including some or all of the source feed's
   Metadata elements as the atom:source element's children.  Such
   metadata SHOULD be preserved if the source atom:feed contains any of
   the child elements atom:author, atom:contributor, atom:rights, or
   atom:category and those child elements are not present in the source
   atom:entry.

   atomSource =
      element atom:source {
         atomCommonAttributes,
         (atomAuthor*
          & atomCategory*
          & atomContributor*
          & atomGenerator?
          & atomIcon?
          & atomId?
          & atomLink*
          & atomLogo?
          & atomRights?
          & atomSubtitle?
          & atomTitle?
          & atomUpdated?
          & extensionElement*)
      }

   The atom:source element is designed to allow the aggregation of
   entries from different feeds while retaining information about an
   entry's source feed.  For this reason, Atom Processors that are
   performing such aggregation SHOULD include at least the required
   feed-level Metadata elements (atom:id, atom:title, and atom:updated)
   in the atom:source element.
 */
public class AtomSource implements AtomElement{
	
	private AtomCommonAttributes comattr;
	
	private ArrayList<AtomAuthor> authors;
	private ArrayList<AtomCategory> categories;
	private ArrayList<AtomContributor> contributors;
	private AtomGenerator generator;
	private AtomId id;
	private ArrayList<AtomLink> links;
	private AtomRights rights;
	private AtomTitle title;
	private AtomSubTitle subtitle;
	private AtomUpdated updated;
	
}
