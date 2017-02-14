package gov.nist.rolie.polie.model.models;

import java.net.URI;

import org.apache.xmlbeans.XmlObject;

import gov.nist.rolie.polie.model.ResourceType;

//import gov.nist.rolie.polie.tools.XMLMangement.JAXBXMLResourceInterface;
//import gov.nist.rolie.polie.tools.XMLMangement.XMLResourceInterface;
//import gov.nist.rolie.polie.tools.exceptions.FailedToSerializeResourceException;

/**
 * An APPResource is any networked accessible data object or service
 * 
 * @author sab3
 *
 */
public interface APPResource {

	ResourceType getResourceType();
	XmlObject getXmlObject();
	URI getIRI();
}
