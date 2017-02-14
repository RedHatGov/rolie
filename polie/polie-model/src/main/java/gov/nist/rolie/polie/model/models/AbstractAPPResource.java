package gov.nist.rolie.polie.model.models;

import java.net.URI;

import org.apache.xmlbeans.XmlObject;

public abstract class AbstractAPPResource<XML extends XmlObject> implements APPResource {
	private URI iri;
	private final XML xmlObject;
	
	/**
	 * @param xmlObject
	 */
	public AbstractAPPResource(XML xmlObject) {
		this.xmlObject = xmlObject;
	}

	@Override
	public XML getXmlObject() {
		return xmlObject;
	}

	/**
	 * @return the iri
	 */
	public URI getIRI() {
		return iri;
	}

	/**
	 * @param iri the iri to set
	 */
	public void setIRI(URI iri) {
		this.iri = iri;
	}


}
