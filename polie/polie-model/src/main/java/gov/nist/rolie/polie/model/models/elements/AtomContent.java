package gov.nist.rolie.polie.model.models.elements;

import java.net.URI;

import gov.nist.rolie.polie.model.models.constructs.AtomCommonAttributes;


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
	

	private String type;
	

	private URI src;
	/**
	 * @param commonAttributes
	 * @param type
	 * @param uri
	 */
	public AtomContent(AtomCommonAttributes commonAttributes,String type, URI uri) {
		super();
		this.commonAttributes = commonAttributes;
		this.type = type;
		this.src = uri;
	}
	
	public AtomContent(){}
	
	/**
	 * @return the commonAttributes
	 */
	public AtomCommonAttributes getCommonAttributes() {
		return commonAttributes;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @return the uri
	 */
	public URI getUri() {
		return src;
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
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @param uri the uri to set
	 */
	public void setUri(URI uri) {
		this.src = uri;
	}
	
	
	
}
