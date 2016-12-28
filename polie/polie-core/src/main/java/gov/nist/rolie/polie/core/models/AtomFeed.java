/*
 * 
 */
package gov.nist.rolie.polie.core.models;

import java.util.ArrayList;

import gov.nist.rolie.polie.core.models.constructs.AtomCommonAttributes;
import gov.nist.rolie.polie.core.models.elements.AtomAuthor;
import gov.nist.rolie.polie.core.models.elements.AtomCategory;
import gov.nist.rolie.polie.core.models.elements.AtomContributor;
import gov.nist.rolie.polie.core.models.elements.AtomElement;
import gov.nist.rolie.polie.core.models.elements.AtomGenerator;
import gov.nist.rolie.polie.core.models.elements.AtomIcon;
import gov.nist.rolie.polie.core.models.elements.AtomId;
import gov.nist.rolie.polie.core.models.elements.AtomLink;
import gov.nist.rolie.polie.core.models.elements.AtomLogo;
import gov.nist.rolie.polie.core.models.elements.AtomRights;

import gov.nist.rolie.polie.core.models.elements.AtomSubTitle;

import gov.nist.rolie.polie.core.models.elements.AtomTitle;
import gov.nist.rolie.polie.core.models.elements.AtomUpdated;

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
   o  atom:feed elements MUST contain exactly one atom:updated element.

   If multiple atom:entry elements with the same atom:id value appear in
   an Atom Feed Document, they represent the same entry.  Their
   atom:updated timestamps SHOULD be different.  If an Atom Feed
   Document contains multiple entries with the same atom:id, Atom
   Processors MAY choose to display all of them or some subset of them.
   One typical behavior would be to display only the entry with the
   latest atom:updated timestamp.
 */
public class AtomFeed implements APPResource,AtomElement{

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
	private AtomIcon icon;
	private AtomLogo logo;
	

	/**
	 * @param comattr
	 * @param authors
	 * @param categories
	 * @param contributors
	 * @param generator
	 * @param id
	 * @param links
	 * @param rights
	 * @param title
	 * @param subtitle
	 * @param updated
	 * @param icon
	 * @param logo
	 */
	public AtomFeed(AtomCommonAttributes comattr, ArrayList<AtomAuthor> authors, ArrayList<AtomCategory> categories,
			ArrayList<AtomContributor> contributors, AtomGenerator generator, AtomId id, ArrayList<AtomLink> links,
			AtomRights rights, AtomTitle title, AtomSubTitle subtitle, AtomUpdated updated, AtomIcon icon,
			AtomLogo logo) {
		super();
		this.comattr = comattr;
		this.authors = authors;
		this.categories = categories;
		this.contributors = contributors;
		this.generator = generator;
		this.id = id;
		this.links = links;
		this.rights = rights;
		this.title = title;
		this.subtitle = subtitle;
		this.updated = updated;
		this.icon = icon;
		this.logo = logo;
	}
	
	
	public AtomFeed() {
		// TODO Auto-generated constructor stub
	}


	/**
	 * @return the icon
	 */
	public AtomIcon getIcon() {
		return icon;
	}


	/**
	 * @return the logo
	 */
	public AtomLogo getLogo() {
		return logo;
	}


	/**
	 * @param icon the icon to set
	 */
	public void setIcon(AtomIcon icon) {
		this.icon = icon;
	}


	/**
	 * @param logo the logo to set
	 */
	public void setLogo(AtomLogo logo) {
		this.logo = logo;
	}


	/**
	 * @return the comattr
	 */
	public AtomCommonAttributes getComattr() {
		return comattr;
	}
	/**
	 * @return the authors
	 */
	public ArrayList<AtomAuthor> getAuthors() {
		return authors;
	}
	/**
	 * @return the categories
	 */
	public ArrayList<AtomCategory> getCategories() {
		return categories;
	}
	/**
	 * @return the contributors
	 */
	public ArrayList<AtomContributor> getContributors() {
		return contributors;
	}
	/**
	 * @return the generator
	 */
	public AtomGenerator getGenerator() {
		return generator;
	}
	/**
	 * @return the id
	 */
	public AtomId getId() {
		return id;
	}
	/**
	 * @return the links
	 */
	public ArrayList<AtomLink> getLinks() {
		return links;
	}
	/**
	 * @return the rights
	 */
	public AtomRights getRights() {
		return rights;
	}
	/**
	 * @return the title
	 */
	public AtomTitle getTitle() {
		return title;
	}
	/**
	 * @return the subtitle
	 */
	public AtomSubTitle getSubtitle() {
		return subtitle;
	}
	/**
	 * @return the updated
	 */
	public AtomUpdated getUpdated() {
		return updated;
	}
	/**
	 * @param comattr the comattr to set
	 */
	public void setComattr(AtomCommonAttributes comattr) {
		this.comattr = comattr;
	}
	/**
	 * @param authors the authors to set
	 */
	public void setAuthors(ArrayList<AtomAuthor> authors) {
		this.authors = authors;
	}
	/**
	 * @param categories the categories to set
	 */
	public void setCategories(ArrayList<AtomCategory> categories) {
		this.categories = categories;
	}
	/**
	 * @param contributors the contributors to set
	 */
	public void setContributors(ArrayList<AtomContributor> contributors) {
		this.contributors = contributors;
	}
	/**
	 * @param generator the generator to set
	 */
	public void setGenerator(AtomGenerator generator) {
		this.generator = generator;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(AtomId id) {
		this.id = id;
	}
	/**
	 * @param links the links to set
	 */
	public void setLinks(ArrayList<AtomLink> links) {
		this.links = links;
	}
	/**
	 * @param rights the rights to set
	 */
	public void setRights(AtomRights rights) {
		this.rights = rights;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(AtomTitle title) {
		this.title = title;
	}
	/**
	 * @param subtitle the subtitle to set
	 */
	public void setSubtitle(AtomSubTitle subtitle) {
		this.subtitle = subtitle;
	}
	/**
	 * @param updated the updated to set
	 */
	public void setUpdated(AtomUpdated updated) {
		this.updated = updated;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AtomFeed [comattr=" + comattr + ", authors=" + authors + ", categories=" + categories
				+ ", contributors=" + contributors + ", generator=" + generator + ", id=" + id + ", links=" + links
				+ ", rights=" + rights + ", title=" + title + ", subtitle=" + subtitle + ", updated=" + updated
				+ ", icon=" + icon + ", logo=" + logo + "]";
	}
	
}
