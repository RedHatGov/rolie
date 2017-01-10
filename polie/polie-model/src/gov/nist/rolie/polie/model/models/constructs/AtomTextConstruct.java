package gov.nist.rolie.polie.model.models.constructs;
/*
 *    atomPlainTextConstruct =
      atomCommonAttributes,
      attribute type { "text" | "html" }?,
      text

   atomXHTMLTextConstruct =
      atomCommonAttributes,
      attribute type { "xhtml" },
      xhtmlDiv

   atomTextConstruct = atomPlainTextConstruct | atomXHTMLTextConstruct
 */
public class AtomTextConstruct {

	String content;
	
	public AtomTextConstruct(String content) {
		this.content = content;
	}

	public String getContent()
	{
		return this.content;
	}
	
	public void setContent(String string)
	{
		this.content = string;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return content;
	}

}
