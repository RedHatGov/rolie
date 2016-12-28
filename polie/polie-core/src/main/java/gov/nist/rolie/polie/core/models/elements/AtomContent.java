package gov.nist.rolie.polie.core.models.elements;

import gov.nist.rolie.polie.core.models.constructs.AtomCommonAttributes;
import gov.nist.rolie.polie.core.models.constructs.AtomMediaType;
import gov.nist.rolie.polie.core.models.constructs.AtomURI;

/*
 * 4.1.3.  The "atom:content" Element

   The "atom:content" element either contains or links to the content of
   the entry.  The content of atom:content is Language-Sensitive.

   atomInlineTextContent =
      element atom:content {
         atomCommonAttributes,
         attribute type { "text" | "html" }?,
         (text)*
      }

   atomInlineXHTMLContent =
      element atom:content {
         atomCommonAttributes,
         attribute type { "xhtml" },
         xhtmlDiv
      }

   atomInlineOtherContent =
      element atom:content {
         atomCommonAttributes,
         attribute type { atomMediaType }?,
         (text|anyElement)*
      }

   atomOutOfLineContent =
      element atom:content {
         atomCommonAttributes,
         attribute type { atomMediaType }?,
         attribute src { atomUri },
         empty
      }

   atomContent = atomInlineTextContent
    | atomInlineXHTMLContent
    | atomInlineOtherContent
    | atomOutOfLineContent

 */
public class AtomContent implements AtomElement{

	private AtomCommonAttributes commonAttributes;
	private AtomMediaType type;
	private AtomURI uri;
	/**
	 * @param commonAttributes
	 * @param type
	 * @param uri
	 */
	public AtomContent(AtomCommonAttributes commonAttributes, AtomMediaType type, AtomURI uri) {
		super();
		this.commonAttributes = commonAttributes;
		this.type = type;
		this.uri = uri;
	}
	/**
	 * @return the commonAttributes
	 */
	public AtomCommonAttributes getCommonAttributes() {
		return commonAttributes;
	}
	/**
	 * @return the type
	 */
	public AtomMediaType getType() {
		return type;
	}
	/**
	 * @return the uri
	 */
	public AtomURI getUri() {
		return uri;
	}
	/**
	 * @param commonAttributes the commonAttributes to set
	 */
	public void setCommonAttributes(AtomCommonAttributes commonAttributes) {
		this.commonAttributes = commonAttributes;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(AtomMediaType type) {
		this.type = type;
	}
	/**
	 * @param uri the uri to set
	 */
	public void setUri(AtomURI uri) {
		this.uri = uri;
	}
	
	
	
}
