package gov.nist.rolie.polie.model.models.constructs;

public class APPCommonAttributes extends AtomCommonAttributes{

	private String xmlSpace;
	
	public APPCommonAttributes(String xmlBase, String xmlLang, String xmlSpace)
	{
		super(xmlBase,xmlLang);
		this.xmlSpace=xmlSpace;
	}

	/**
	 * @return the xmlSpace
	 */
	public String getXmlSpace() {
		return xmlSpace;
	}

	/**
	 * @param xmlSpace the xmlSpace to set
	 */
	public void setXmlSpace(String xmlSpace) {
		this.xmlSpace = xmlSpace;
	}
	
	
	
}
