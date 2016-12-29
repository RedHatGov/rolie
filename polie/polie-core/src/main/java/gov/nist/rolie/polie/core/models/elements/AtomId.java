package gov.nist.rolie.polie.core.models.elements;

import java.net.URI;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import gov.nist.rolie.polie.core.XMLMangement.AtomURIAdapter;
import gov.nist.rolie.polie.core.models.constructs.AtomCommonAttributes;
import gov.nist.rolie.polie.core.models.constructs.AtomURI;

/*4.2.6.  The "atom:id" Element

The "atom:id" element conveys a permanent, universally unique
identifier for an entry or feed.

atomId = element atom:id {
   atomCommonAttributes,
   (atomUri)
}

Its content MUST be an IRI, as defined by [RFC3987].  Note that the
definition of "IRI" excludes relative references.  Though the IRI
might use a dereferencable scheme, Atom Processors MUST NOT assume it
can be dereferenced.

When an Atom Document is relocated, migrated, syndicated,
republished, exported, or imported, the content of its atom:id
element MUST NOT change.  Put another way, an atom:id element
pertains to all instantiations of a particular Atom entry or feed;
revisions retain the same content in their atom:id elements.  It is
suggested that the atom:id element be stored along with the
associated resource.

The content of an atom:id element MUST be created in a way that
assures uniqueness.

Because of the risk of confusion between IRIs that would be
equivalent if they were mapped to URIs and dereferenced, the
following normalization strategy SHOULD be applied when generating
atom:id elements:

o  Provide the scheme in lowercase characters.
o  Provide the host, if any, in lowercase characters.
o  Only perform percent-encoding where it is essential.
o  Use uppercase A through F characters when percent-encoding.
o  Prevent dot-segments from appearing in paths.
o  For schemes that define a default authority, use an empty
   authority if the default is desired.
o  For schemes that define an empty path to be equivalent to a path
   of "/", use "/".
o  For schemes that define a port, use an empty port if the default
   is desired.
o  Preserve empty fragment identifiers and queries.
o  Ensure that all components of the IRI are appropriately character
   normalized, e.g., by using NFC or NFKC.

4.2.6.1.  Comparing atom:id

Instances of atom:id elements can be compared to determine whether an
entry or feed is the same as one seen before.  Processors MUST
compare atom:id elements on a character-by-character basis (in a
case-sensitive fashion).  Comparison operations MUST be based solely
on the IRI character strings and MUST NOT rely on dereferencing the
IRIs or URIs mapped from them.

As a result, two IRIs that resolve to the same resource but are not
character-for-character identical will be considered different for
the purposes of identifier comparison.

For example, these are four distinct identifiers, despite the fact
that they differ only in case:

   http://www.example.org/thing
   http://www.example.org/Thing
   http://www.EXAMPLE.org/thing
   HTTP://www.example.org/thing

Likewise, these are three distinct identifiers, because IRI
%-escaping is significant for the purposes of comparison:

   http://www.example.com/~bob
   http://www.example.com/%7ebob
   http://www.example.com/%7Ebob
*/
@XmlAccessorType(XmlAccessType.NONE)
public class AtomId implements AtomElement{
	
	private AtomCommonAttributes commonattributes;
	
	@XmlValue
	@XmlJavaTypeAdapter(AtomURIAdapter.class)
	private AtomURI uri;
	/**
	 * @param commonattributes
	 * @param uri
	 */
	public AtomId(AtomCommonAttributes commonattributes, AtomURI uri) {
		super();
		this.commonattributes = commonattributes;
		this.uri = uri;
	}
	public AtomId() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the commonattributes
	 */
	public AtomCommonAttributes getCommonattributes() {
		return commonattributes;
	}
	/**
	 * @return the uri
	 */
	
	public AtomURI getUri() {
		return uri;
	}

	public String getUriAsString()
	{
		return getUri().toString();
	}
	/**
	 * @param commonattributes the commonattributes to set
	 */
	public void setCommonattributes(AtomCommonAttributes commonattributes) {
		this.commonattributes = commonattributes;
	}
	/**
	 * @param uri the uri to set
	 */
	public void setUri(AtomURI uri) {
		this.uri = uri;
	}

	

	
}
