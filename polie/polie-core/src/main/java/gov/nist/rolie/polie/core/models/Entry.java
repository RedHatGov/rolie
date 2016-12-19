/*
 * 
 */
package gov.nist.rolie.polie.core.models;
import gov.nist.rolie.polie.core.models.elements.*;
/**
 * The Class Entry.
 */
public class Entry {
	
	private AtomCommonAttributes comattr;
	
	private AtomAuthor[] authors;
	private AtomCategory[] categories;
	private AtomContributor[] contributors;
	private AtomId id;
	private AtomLink[] links;
	private AtomPublished published;
	private AtomRights rights;
	private AtomSource source;
	private AtomSummary summary;
	private AtomTitle title;
	private AtomUpdated updated;
	
	private RolieFormat format;
	private RolieProperty property;
	
	
	/**
	 * @return the comattr
	 */
	public AtomCommonAttributes getComattr() {
		return comattr;
	}
	/**
	 * @param comattr the comattr to set
	 */
	public void setComattr(AtomCommonAttributes comattr) {
		this.comattr = comattr;
	}
	/**
	 * @return the authors
	 */
	public AtomAuthor[] getAuthors() {
		return authors;
	}
	/**
	 * @param authors the authors to set
	 */
	public void setAuthors(AtomAuthor[] authors) {
		this.authors = authors;
	}
	/**
	 * @return the categories
	 */
	public AtomCategory[] getCategories() {
		return categories;
	}
	/**
	 * @param categories the categories to set
	 */
	public void setCategories(AtomCategory[] categories) {
		this.categories = categories;
	}
	/**
	 * @return the contributors
	 */
	public AtomContributor[] getContributors() {
		return contributors;
	}
	/**
	 * @param contributors the contributors to set
	 */
	public void setContributors(AtomContributor[] contributors) {
		this.contributors = contributors;
	}
	/**
	 * @return the id
	 */
	public AtomId getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(AtomId id) {
		this.id = id;
	}
	/**
	 * @return the links
	 */
	public AtomLink[] getLinks() {
		return links;
	}
	/**
	 * @param links the links to set
	 */
	public void setLinks(AtomLink[] links) {
		this.links = links;
	}
	/**
	 * @return the published
	 */
	public AtomPublished getPublished() {
		return published;
	}
	/**
	 * @param published the published to set
	 */
	public void setPublished(AtomPublished published) {
		this.published = published;
	}
	/**
	 * @return the rights
	 */
	public AtomRights getRights() {
		return rights;
	}
	/**
	 * @param rights the rights to set
	 */
	public void setRights(AtomRights rights) {
		this.rights = rights;
	}
	/**
	 * @return the source
	 */
	public AtomSource getSource() {
		return source;
	}
	/**
	 * @param source the source to set
	 */
	public void setSource(AtomSource source) {
		this.source = source;
	}
	/**
	 * @return the summary
	 */
	public AtomSummary getSummary() {
		return summary;
	}
	/**
	 * @param summary the summary to set
	 */
	public void setSummary(AtomSummary summary) {
		this.summary = summary;
	}
	/**
	 * @return the title
	 */
	public AtomTitle getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(AtomTitle title) {
		this.title = title;
	}
	/**
	 * @return the updated
	 */
	public AtomUpdated getUpdated() {
		return updated;
	}
	/**
	 * @param updated the updated to set
	 */
	public void setUpdated(AtomUpdated updated) {
		this.updated = updated;
	}
	/**
	 * @return the format
	 */
	public RolieFormat getFormat() {
		return format;
	}
	/**
	 * @param format the format to set
	 */
	public void setFormat(RolieFormat format) {
		this.format = format;
	}
	/**
	 * @return the property
	 */
	public RolieProperty getProperty() {
		return property;
	}
	/**
	 * @param property the property to set
	 */
	public void setProperty(RolieProperty property) {
		this.property = property;
	}

	
	
	
}
