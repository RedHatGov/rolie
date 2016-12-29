package gov.nist.rolie.polie.core.models.constructs;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import gov.nist.rolie.polie.core.XMLMangement.AtomURIAdapter;
/*
3.2.  Person Constructs

   A Person construct is an element that describes a person,
   corporation, or similar entity (hereafter, 'person').

   atomPersonConstruct =
      atomCommonAttributes,
      (element atom:name { text }
       & element atom:uri { atomUri }?
       & element atom:email { atomEmailAddress }?
       & extensionElement*)

   This specification assigns no significance to the order of appearance
   of the child elements in a Person construct.  Person constructs allow
   extension Metadata elements (see Section 6.4).

3.2.1.  The "atom:name" Element

   The "atom:name" element's content conveys a human-readable name for
   the person.  The content of atom:name is Language-Sensitive.  Person
   constructs MUST contain exactly one "atom:name" element.

3.2.2.  The "atom:uri" Element

   The "atom:uri" element's content conveys an IRI associated with the
   person.  Person constructs MAY contain an atom:uri element, but MUST
   NOT contain more than one.  The content of atom:uri in a Person
   construct MUST be an IRI reference [RFC3987].

3.2.3.  The "atom:email" Element

   The "atom:email" element's content conveys an e-mail address
   associated with the person.  Person constructs MAY contain an
   atom:email element, but MUST NOT contain more than one.  Its content
   MUST conform to the "addr-spec" production in [RFC2822].
 */
import gov.nist.rolie.polie.core.models.elements.AtomElement;

@XmlAccessorType(XmlAccessType.NONE)
public class AtomPerson implements AtomElement{
	
	@XmlElement
	private String name;
	
	@XmlElement
	@XmlJavaTypeAdapter(AtomURIAdapter.class)
	private AtomURI uri;
	
	@XmlElement
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
