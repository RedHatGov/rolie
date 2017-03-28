/**
 * Portions of this software was developed by employees of the National Institute
 * of Standards and Technology (NIST), an agency of the Federal Government.
 * Pursuant to title 17 United States Code Section 105, works of NIST employees are
 * not subject to copyright protection in the United States and are considered to
 * be in the public domain. Permission to freely use, copy, modify, and distribute
 * this software and its documentation without fee is hereby granted, provided that
 * this notice and disclaimer of warranty appears in all copies.
 *
 * THE SOFTWARE IS PROVIDED 'AS IS' WITHOUT ANY WARRANTY OF ANY KIND, EITHER
 * EXPRESSED, IMPLIED, OR STATUTORY, INCLUDING, BUT NOT LIMITED TO, ANY WARRANTY
 * THAT THE SOFTWARE WILL CONFORM TO SPECIFICATIONS, ANY IMPLIED WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, AND FREEDOM FROM
 * INFRINGEMENT, AND ANY WARRANTY THAT THE DOCUMENTATION WILL CONFORM TO THE
 * SOFTWARE, OR ANY WARRANTY THAT THE SOFTWARE WILL BE ERROR FREE. IN NO EVENT
 * SHALL NASA BE LIABLE FOR ANY DAMAGES, INCLUDING, BUT NOT LIMITED TO, DIRECT,
 * INDIRECT, SPECIAL OR CONSEQUENTIAL DAMAGES, ARISING OUT OF, RESULTING FROM, OR
 * IN ANY WAY CONNECTED WITH THIS SOFTWARE, WHETHER OR NOT BASED UPON WARRANTY,
 * CONTRACT, TORT, OR OTHERWISE, WHETHER OR NOT INJURY WAS SUSTAINED BY PERSONS OR
 * PROPERTY OR OTHERWISE, AND WHETHER OR NOT LOSS WAS SUSTAINED FROM, OR AROSE OUT
 * OF THE RESULTS OF, OR USE OF, THE SOFTWARE OR SERVICES PROVIDED HEREUNDER.
 */
/*
 * 
 */

package gov.nist.rolie.polie.model.models;

import gov.nist.rolie.polie.model.ResourceType;
import gov.nist.rolie.polie.model.models.elements.AtomElement;

import org.w3.x2005.atom.FeedDocument;

/**
 * 4.1.1. The "atom:feed" Element
 * 
 * The "atom:feed" element is the document (i.e., top-level) element of an Atom Feed Document,
 * acting as a container for metadata and data associated with the feed. Its element children
 * consist of metadata elements followed by zero or more atom:entry child elements.
 * 
 * atomFeed = element atom:feed { atomCommonAttributes, (atomAuthor* & atomCategory* &
 * atomContributor* & atomGenerator? & atomIcon? & atomId & atomLink* & atomLogo? & atomRights? &
 * atomSubtitle? & atomTitle & atomUpdated & extensionElement*), atomEntry* }
 * 
 * This specification assigns no significance to the order of atom:entry elements within the feed.
 * 
 * 
 * 
 * Nottingham & Sayre Standards Track [Page 11]
 * 
 * 
 * RFC 4287 Atom Format December 2005
 * 
 * 
 * The following child elements are defined by this specification (note that the presence of some of
 * these elements is required):
 * 
 * o atom:feed elements MUST contain one or more atom:author elements, unless all of the atom:feed
 * element's child atom:entry elements contain at least one atom:author element. o atom:feed
 * elements MAY contain any number of atom:category elements. o atom:feed elements MAY contain any
 * number of atom:contributor elements. o atom:feed elements MUST NOT contain more than one
 * atom:generator element. o atom:feed elements MUST NOT contain more than one atom:icon element. o
 * atom:feed elements MUST NOT contain more than one atom:logo element. o atom:feed elements MUST
 * contain exactly one atom:id element. o atom:feed elements SHOULD contain one atom:link element
 * with a rel attribute value of "self". This is the preferred URI for retrieving Atom Feed
 * Documents representing this Atom feed. o atom:feed elements MUST NOT contain more than one
 * atom:link element with a rel attribute value of "alternate" that has the same combination of type
 * and hreflang attribute values. o atom:feed elements MAY contain additional atom:link elements
 * beyond those described above. o atom:feed elements MUST NOT contain more than one atom:rights
 * element. o atom:feed elements MUST NOT contain more than one atom:subtitle element. o atom:feed
 * elements MUST contain exactly one atom:title element. o atom:feed elements MUST contain exactly
 * one atom:updatedDate element.
 * 
 * If multiple atom:entry elements with the same atom:id value appear in an Atom Feed Document, they
 * represent the same entry. Their atom:updatedDate timestamps SHOULD be different. If an Atom Feed
 * Document contains multiple entries with the same atom:id, Atom Processors MAY choose to display
 * all of them or some subset of them. One typical behavior would be to display only the entry with
 * the latest atom:updatedDate timestamp.
 */

public class AtomFeed extends AbstractAPPResource<FeedDocument> implements AtomElement {

  public AtomFeed() {
    this(FeedDocument.Factory.newInstance());
  }

  public AtomFeed(FeedDocument doc) {
    super(doc);
  }

  public ResourceType getResourceType() {
    return ResourceType.FEED;
  }

}
