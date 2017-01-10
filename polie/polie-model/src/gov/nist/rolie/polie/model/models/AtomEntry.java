/*
 * 
 */
package gov.nist.rolie.polie.model.models;
import java.util.ArrayList;

import gov.nist.rolie.polie.model.models.constructs.AtomCommonAttributes;
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
   o  atom:entry elements MUST NOT contain more than one atom:published
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
	

	private ArrayList<AtomAuthor> author;
	

	private ArrayList<AtomCategory> category;
	
	

	private ArrayList<AtomContributor> contributor;

	private AtomId id;

	private ArrayList<AtomLink> link;

	private AtomPublished published;

	private AtomRights rights;

	private AtomSource source;

	private AtomSummary summary;

	private AtomTitle title;

	private AtomUpdated updated;

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
	 * @param published
	 * @param rights
	 * @param source
	 * @param summary
	 * @param title
	 * @param updated
	 * @param format
	 * @param property
	 */
	public AtomEntry(AtomCommonAttributes comattr, ArrayList<AtomAuthor> authors, ArrayList<AtomCategory> categories,
			ArrayList<AtomContributor> contributors, AtomId id, ArrayList<AtomLink> links, AtomPublished published,
			AtomRights rights, AtomSource source, AtomSummary summary, AtomTitle title, AtomUpdated updated,
			RolieFormat format, RolieProperty property, AtomContent content) {
		super();
		this.comattr = comattr;
		this.author = authors;
		this.category = categories;
		this.contributor = contributors;
		this.id = id;
		this.link = links;
		this.published = published;
		this.rights = rights;
		this.source = source;
		this.summary = summary;
		this.title = title;
		this.updated = updated;
		this.format = format;
		this.property = property;
		this.content=content;
	}
	
	public AtomEntry() {
		// TODO Auto-generated constructor stub
	}

	
	/**
	 * @return the content
	 */
	public AtomContent getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(AtomContent content) {
		this.content = content;
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
	public ArrayList<AtomAuthor> getAuthors() {
		return author;
	}
	/**
	 * @return the category
	 */
	public ArrayList<AtomCategory> getCategories() {
		return category;
	}
	/**
	 * @return the contributor
	 */
	public ArrayList<AtomContributor> getContributors() {
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
	public ArrayList<AtomLink> getLinks() {
		return link;
	}
	/**
	 * @return the published
	 */
	public AtomPublished getPublished() {
		return published;
	}
	/**
	 * @return the rights
	 */
	public AtomRights getRights() {
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
	public AtomSummary getSummary() {
		return summary;
	}
	/**
	 * @return the title
	 */
	public AtomTitle getTitle() {
		return title;
	}
	/**
	 * @return the updated
	 */
	public AtomUpdated getUpdated() {
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
	 * @param comattr the comattr to set
	 */
	public void setComattr(AtomCommonAttributes comattr) {
		this.comattr = comattr;
	}
	/**
	 * @param author the author to set
	 */
	public void setAuthors(ArrayList<AtomAuthor> authors) {
		this.author = authors;
	}
	/**
	 * @param category the category to set
	 */
	public void setCategories(ArrayList<AtomCategory> categories) {
		this.category = categories;
	}
	/**
	 * @param contributor the contributor to set
	 */
	public void setContributors(ArrayList<AtomContributor> contributors) {
		this.contributor = contributors;
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
	public void setLinks(ArrayList<AtomLink> links) {
		this.link = links;
	}
	/**
	 * @param published the published to set
	 */
	public void setPublished(AtomPublished published) {
		this.published = published;
	}
	/**
	 * @param rights the rights to set
	 */
	public void setRights(AtomRights rights) {
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
	public void setSummary(AtomSummary summary) {
		this.summary = summary;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(AtomTitle title) {
		this.title = title;
	}
	/**
	 * @param updated the updated to set
	 */
	public void setUpdated(AtomUpdated updated) {
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AtomEntry [comattr=" + comattr + ", author=" + author + ", category=" + category
				+ ", contributor=" + contributor + ", id=" + id + ", link=" + link + ", published=" + published
				+ ", rights=" + rights + ", source=" + source + ", summary=" + summary + ", title=" + title
				+ ", updated=" + updated + ", format=" + format + ", property=" + property + "]";
	}

	public void setTitle(String string) {
		setTitle(new AtomTitle(string));
	}

	public void setSummary(String string) {
		this.setSummary(new AtomSummary(string));
		
	}
	
	
	
}
