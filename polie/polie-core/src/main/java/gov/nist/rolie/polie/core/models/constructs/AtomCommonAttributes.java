package gov.nist.rolie.polie.core.models.constructs;
/*
 *    # Common attributes

   atomCommonAttributes =
      attribute xml:base { atomUri }?,
      attribute xml:lang { atomLanguageTag }?,
      undefinedAttribute*

 */
public class AtomCommonAttributes {

	
	private String xmlBase;
	private String xmlLang;
	
	public AtomCommonAttributes(String xmlBase,String xmlLang)
	{
		this.xmlBase=xmlBase;
		this.xmlLang=xmlLang;
	}

	public AtomCommonAttributes() {
	}

	/**
	 * @return the xmlBase
	 */
	public String getXmlBase() {
		return xmlBase;
	}

	/**
	 * @return the xmlLang
	 */
	public String getXmlLang() {
		return xmlLang;
	}

	/**
	 * @param xmlBase the xmlBase to set
	 */
	public void setXmlBase(String xmlBase) {
		this.xmlBase = xmlBase;
	}

	/**
	 * @param xmlLang the xmlLang to set
	 */
	public void setXmlLang(String xmlLang) {
		this.xmlLang = xmlLang;
	}
	
	
	
}
