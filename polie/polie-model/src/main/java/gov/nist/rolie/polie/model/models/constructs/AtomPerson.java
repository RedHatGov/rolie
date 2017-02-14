package gov.nist.rolie.polie.model.models.constructs;

import java.net.URI;

import gov.nist.rolie.polie.model.models.elements.AtomElement;


public class AtomPerson implements AtomElement{
	

	private String name;
	

	private URI uri;
	

	private String email;
	/**
	 * @param name
	 * @param uri
	 * @param email
	 */
	public AtomPerson(String name, URI uri, String email) {
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
	public URI getUri() {
		return uri;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
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
	public void setUri(URI uri) {
		this.uri = uri;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	

}
