package gov.nist.rolie.polie.model.models.constructs;

import gov.nist.rolie.polie.model.models.elements.AtomElement;


public class AtomPerson implements AtomElement{
	

	private String name;
	

	private AtomURI uri;
	

	private AtomEmailAddress email;
	/**
	 * @param name
	 * @param uri
	 * @param email
	 */
	public AtomPerson(String name, AtomURI uri, AtomEmailAddress email) {
		super();
		this.name = name;
		this.uri = uri;
		this.email = email;
	}
	public AtomPerson() {
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @return the uri
	 */
	public AtomURI getUri() {
		return uri;
	}
	/**
	 * @return the email
	 */
	public AtomEmailAddress getEmail() {
		return email;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @param uri the uri to set
	 */
	public void setUri(AtomURI uri) {
		this.uri = uri;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(AtomEmailAddress email) {
		this.email = email;
	}
	

}
