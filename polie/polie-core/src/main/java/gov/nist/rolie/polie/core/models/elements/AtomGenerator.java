package gov.nist.rolie.polie.core.models.elements;

import gov.nist.rolie.polie.core.models.constructs.AtomCommonAttributes;
import gov.nist.rolie.polie.core.models.constructs.AtomURI;

/*
 * 4.2.4.  The "atom:generator" Element

   The "atom:generator" element's content identifies the agent used to
   generate a feed, for debugging and other purposes.

   atomGenerator = element atom:generator {
      atomCommonAttributes,
      attribute uri { atomUri }?,
      attribute version { text }?,
      text
   }

   The content of this element, when present, MUST be a string that is a
   human-readable name for the generating agent.  Entities such as
   "&amp;" and "&lt;" represent their corresponding characters ("&" and
   "<" respectively), not markup.

   The atom:generator element MAY have a "uri" attribute whose value
   MUST be an IRI reference [RFC3987].  When dereferenced, the resulting
   URI (mapped from an IRI, if necessary) SHOULD produce a
   representation that is relevant to that agent.

   The atom:generator element MAY have a "version" attribute that
   indicates the version of the generating agent.
 */
public class AtomGenerator {

	private AtomCommonAttributes commonAttributes;
	private AtomURI uri;
	private String version;
	/**
	 * @param commonAttributes
	 * @param uri
	 * @param version
	 */
	public AtomGenerator(AtomCommonAttributes commonAttributes, AtomURI uri, String version) {
		super();
		this.commonAttributes = commonAttributes;
		this.uri = uri;
		this.version = version;
	}
	/**
	 * @return the commonAttributes
	 */
	public AtomCommonAttributes getCommonAttributes() {
		return commonAttributes;
	}
	/**
	 * @return the uri
	 */
	public AtomURI getUri() {
		return uri;
	}
	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}
	/**
	 * @param commonAttributes the commonAttributes to set
	 */
	public void setCommonAttributes(AtomCommonAttributes commonAttributes) {
		this.commonAttributes = commonAttributes;
	}
	/**
	 * @param uri the uri to set
	 */
	public void setUri(AtomURI uri) {
		this.uri = uri;
	}
	/**
	 * @param version the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}
	
	
}
