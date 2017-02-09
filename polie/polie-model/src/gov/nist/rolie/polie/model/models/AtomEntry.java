/*
 * 
 */
package gov.nist.rolie.polie.model.models;
import java.util.ArrayList;

import gov.nist.rolie.polie.model.models.constructs.AtomCommonAttributes;
import gov.nist.rolie.polie.model.models.constructs.AtomPerson;
import gov.nist.rolie.polie.model.models.elements.*;


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

public class AtomEntry extends APPResource implements AtomElement{
	
	private AtomCommonAttributes comattr;
	

	private ArrayList<AtomPerson> author;
	

	private ArrayList<AtomCategory> category;
	
	

	private ArrayList<AtomPerson> contributor;

	private AtomId id;

	private ArrayList<AtomLink> link;

	private String publishedDate;

	private String rights;

	private AtomSource source;

	private String summary;

	private String title;

	private String updated;

	private RolieFormat format;

	private RolieProperty property;
	

	private AtomContent content;
	


	/**
	 * @param comattr
	 * @param author
	 * @param category
	 * @param contributor
	 * @param id
	 * @param link
	 * @param publishedDate
	 * @param rights
	 * @param source
	 * @param summary
	 * @param title
	 * @param updated
	 * @param format
	 * @param property
	 * @param content
	 */
	public AtomEntry(AtomCommonAttributes comattr, ArrayList<AtomPerson> author, ArrayList<AtomCategory> category,
			ArrayList<AtomPerson> contributor, AtomId id, ArrayList<AtomLink> link, String publishedDate, String rights,
			AtomSource source, String summary, String title, String updated, RolieFormat format,
			RolieProperty property, AtomContent content) {
		super();
		this.comattr = comattr;
		this.author = author;
		this.category = category;
		this.contributor = contributor;
		this.id = id;
		this.link = link;
		this.publishedDate = publishedDate;
		this.rights = rights;
		this.source = source;
		this.summary = summary;
		this.title = title;
		this.updated = updated;
		this.format = format;
		this.property = property;
		this.content = content;
	}



	public AtomEntry() {
		// TODO Auto-generated constructor stub
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
	 * @return the publishedDate
	 */
	public String getPublishedDate() {
		return publishedDate;
	}



	/**
	 * @return the rights
	 */
	public String getRights() {
		return rights;
	}



	/**
	 * @return the source
	 */
	public AtomSource getSource() {
		return source;
	}



	/**
	 * @return the summary
	 */
	public String getSummary() {
		return summary;
	}



	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}



	/**
	 * @return the updated
	 */
	public String getUpdated() {
		return updated;
	}



	/**
	 * @return the format
	 */
	public RolieFormat getFormat() {
		return format;
	}



	/**
	 * @return the property
	 */
	public RolieProperty getProperty() {
		return property;
	}



	/**
	 * @return the content
	 */
	public AtomContent getContent() {
		return content;
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
	 * @param publishedDate the publishedDate to set
	 */
	public void setPublishedDate(String publishedDate) {
		this.publishedDate = publishedDate;
	}



	/**
	 * @param rights the rights to set
	 */
	public void setRights(String rights) {
		this.rights = rights;
	}



	/**
	 * @param source the source to set
	 */
	public void setSource(AtomSource source) {
		this.source = source;
	}



	/**
	 * @param summary the summary to set
	 */
	public void setSummary(String summary) {
		this.summary = summary;
	}



	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}



	/**
	 * @param updated the updated to set
	 */
	public void setUpdated(String updated) {
		this.updated = updated;
	}



	/**
	 * @param format the format to set
	 */
	public void setFormat(RolieFormat format) {
		this.format = format;
	}



	/**
	 * @param property the property to set
	 */
	public void setProperty(RolieProperty property) {
		this.property = property;
	}



	/**
	 * @param content the content to set
	 */
	public void setContent(AtomContent content) {
		this.content = content;
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AtomEntry [comattr=" + comattr + ", author=" + author + ", category=" + category
				+ ", contributor=" + contributor + ", id=" + id + ", link=" + link + ", publishedDate=" + publishedDate
				+ ", rights=" + rights + ", source=" + source + ", summary=" + summary + ", title=" + title
				+ ", updated=" + updated + ", format=" + format + ", property=" + property + "]";
	}
	
	
}
