package gov.nist.rolie.polie.model.models.elements;


import java.net.URI;

import gov.nist.rolie.polie.model.models.constructs.AtomCommonAttributes;


/*
 * 6.2.3.  Use of the "rolie:format" Element

   As mentioned earlier, a key goal of this specification is to allow a
   consumer to review a set of published security automation information
   resources, and then identify and retrieve any resources of interest.
   The format of the data is a key criteria to consider when deciding
   what information to retrieve.  For a given type of security
   automation information, it is expected that a number of different
   formats may be used to represent this information.  To support this
   use case, both the serialization format and the specific data model
   expressed in that format must be known by the consumer.

   The rolie:format element is used to describe the data model used to
   express the information referenced in the atom:content element of an
   atom:entry.  It also allows a schema to be identified that can be
   used when parsing the content to verify or better understand the
   structure of the content.

   There MUST be exactly one rolie:format element in an atom:entry.  The
   element MUST adhere to this definition:

     rolieFormat =
       element rolie:format {
         atomCommonAttributes,
         attribute ns { atomURI },
         attribute version { text } ?,
         attribute schema-location { atomURI } ?,
         attribute schema-type { atomMediaType } ?,
         empty
     }

   The rolie:format element MUST provide a "ns" attribute that
   identifies the data model of the resource referenced by the
   atom:content element.  For example, the namespace used may be an XML
   namespace URI, or an identifier that represents a serialized JSON
   model.  The URI used for the "ns" attribute value MUST be an absolute
   or opaque URI.  The resource identified by the URI need not be
   resolvable.
 */

public class RolieFormat implements RolieElement{
	
	private AtomCommonAttributes commonattributes;
	

	private URI ns;

	private String version;
	

	private URI schemalocation;
	private String type;
	/**
	 * @param commonattributes
	 * @param ns
	 * @param version
	 * @param schemalocation
	 * @param type
	 */
	public RolieFormat(AtomCommonAttributes commonattributes, URI ns, String version, URI schemalocation,
			String type) {
		super();
		this.commonattributes = commonattributes;
		this.ns = ns;
		this.version = version;
		this.schemalocation = schemalocation;
		this.type = type;
	}
	
	public RolieFormat(){};
	/**
	 * @return the commonattributes
	 */
	public AtomCommonAttributes getCommonattributes() {
		return commonattributes;
	}
	/**
	 * @return the ns
	 */
	public URI getNs() {
		return ns;
	}
	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}
	/**
	 * @return the schemalocation
	 */
	public URI getSchemalocation() {
		return schemalocation;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param commonattributes the commonattributes to set
	 */
	public void setCommonattributes(AtomCommonAttributes commonattributes) {
		this.commonattributes = commonattributes;
	}
	/**
	 * @param ns the ns to set
	 */
	public void setNs(URI ns) {
		this.ns = ns;
	}
	/**
	 * @param version the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}
	/**
	 * @param schemalocation the schemalocation to set
	 */
	public void setSchemalocation(URI schemalocation) {
		this.schemalocation = schemalocation;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	
	
}
