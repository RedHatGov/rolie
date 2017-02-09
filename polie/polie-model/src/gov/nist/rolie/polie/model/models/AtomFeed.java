/*
 * 
 */
package gov.nist.rolie.polie.model.models;

import java.util.ArrayList;

import gov.nist.rolie.polie.model.models.constructs.AtomCommonAttributes;
import gov.nist.rolie.polie.model.models.constructs.AtomPerson;
import gov.nist.rolie.polie.model.models.elements.APPCollection;

import gov.nist.rolie.polie.model.models.elements.AtomCategory;

import gov.nist.rolie.polie.model.models.elements.AtomElement;
import gov.nist.rolie.polie.model.models.elements.AtomGenerator;
import gov.nist.rolie.polie.model.models.elements.AtomIcon;
import gov.nist.rolie.polie.model.models.elements.AtomId;
import gov.nist.rolie.polie.model.models.elements.AtomLink;
import gov.nist.rolie.polie.model.models.elements.AtomLogo;


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

public class AtomFeed extends APPResource implements AtomElement{

	private APPCollection collectionView;
	
	
	private AtomCommonAttributes comattr;
	

	private ArrayList<AtomPerson> author;
	

	private ArrayList<AtomCategory> category;
	

	private ArrayList<AtomPerson> contributor;
	

	private AtomGenerator generator;
	

	private AtomId id;
	

	private ArrayList<AtomLink> link;
	

	private String rights;
	

	private String title;
	

	private String subtitle;
	

	private String updatedDate;
	

	private AtomIcon icon;
	

	private AtomLogo logo;
	
	
	private ArrayList<AtomEntry> entries;


	
	
	/**
	 * @return the collectionView
	 */
	public APPCollection getCollectionView() {
		return collectionView;
	}




	/**
	 * @return the comattr
	 */
	public AtomCommonAttributes getComattr() {
		return comattr;
	}




	/**
	 * @return the author
	 */
	public ArrayList<AtomPerson> getAuthor() {
		return author;
	}




	/**
	 * @return the category
	 */
	public ArrayList<AtomCategory> getCategory() {
		return category;
	}




	/**
	 * @return the contributor
	 */
	public ArrayList<AtomPerson> getContributor() {
		return contributor;
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
	 * @return the link
	 */
	public ArrayList<AtomLink> getLink() {
		return link;
	}




	/**
	 * @return the rights
	 */
	public String getRights() {
		return rights;
	}




	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}




	/**
	 * @return the subtitle
	 */
	public String getSubtitle() {
		return subtitle;
	}




	/**
	 * @return the updatedDate
	 */
	public String getUpdatedDate() {
		return updatedDate;
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
	 * @return the entries
	 */
	public ArrayList<AtomEntry> getEntries() {
		return entries;
	}




	/**
	 * @param collectionView
	 * @param comattr
	 * @param author
	 * @param category
	 * @param contributor
	 * @param generator
	 * @param id
	 * @param link
	 * @param rights
	 * @param title
	 * @param subtitle
	 * @param updatedDate
	 * @param icon
	 * @param logo
	 * @param entries
	 */
	public AtomFeed(APPCollection collectionView, AtomCommonAttributes comattr, ArrayList<AtomPerson> author,
			ArrayList<AtomCategory> category, ArrayList<AtomPerson> contributor, AtomGenerator generator, AtomId id,
			ArrayList<AtomLink> link, String rights, String title, String subtitle, String updatedDate, AtomIcon icon,
			AtomLogo logo, ArrayList<AtomEntry> entries) {
		super();
		this.collectionView = collectionView;
		this.comattr = comattr;
		this.author = author;
		this.category = category;
		this.contributor = contributor;
		this.generator = generator;
		this.id = id;
		this.link = link;
		this.rights = rights;
		this.title = title;
		this.subtitle = subtitle;
		this.updatedDate = updatedDate;
		this.icon = icon;
		this.logo = logo;
		this.entries = entries;
	}




	public AtomFeed() {
		// TODO Auto-generated constructor stub
	}




	/**
	 * @param collectionView the collectionView to set
	 */
	public void setCollectionView(APPCollection collectionView) {
		this.collectionView = collectionView;
	}




	/**
	 * @param comattr the comattr to set
	 */
	public void setComattr(AtomCommonAttributes comattr) {
		this.comattr = comattr;
	}




	/**
	 * @param author the author to set
	 */
	public void setAuthor(ArrayList<AtomPerson> author) {
		this.author = author;
	}




	/**
	 * @param category the category to set
	 */
	public void setCategory(ArrayList<AtomCategory> category) {
		this.category = category;
	}




	/**
	 * @param contributor the contributor to set
	 */
	public void setContributor(ArrayList<AtomPerson> contributor) {
		this.contributor = contributor;
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
	 * @param link the link to set
	 */
	public void setLink(ArrayList<AtomLink> link) {
		this.link = link;
	}




	/**
	 * @param rights the rights to set
	 */
	public void setRights(String rights) {
		this.rights = rights;
	}




	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}




	/**
	 * @param subtitle the subtitle to set
	 */
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}




	/**
	 * @param updatedDate the updatedDate to set
	 */
	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
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
	 * @param entries the entries to set
	 */
	public void setEntries(ArrayList<AtomEntry> entries) {
		this.entries = entries;
	}




	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AtomFeed [comattr=" + comattr + ", author=" + author + ", category=" + category
				+ ", contributor=" + contributor + ", generator=" + generator + ", id=" + id + ", link=" + link
				+ ", rights=" + rights + ", title=" + title + ", subtitle=" + subtitle + ", updatedDate=" + updatedDate
				+ ", icon=" + icon + ", logo=" + logo + "]";
	}

}
