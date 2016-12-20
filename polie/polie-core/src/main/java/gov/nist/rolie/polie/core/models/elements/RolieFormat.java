package gov.nist.rolie.polie.core.models.elements;

import gov.nist.rolie.polie.core.models.constructs.AtomCommonAttributes;
import gov.nist.rolie.polie.core.models.constructs.AtomMediaType;
import gov.nist.rolie.polie.core.models.constructs.AtomURI;

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
	private AtomURI ns;
	private String version;
	private AtomURI schemalocation;
	private AtomMediaType type;
	
}
