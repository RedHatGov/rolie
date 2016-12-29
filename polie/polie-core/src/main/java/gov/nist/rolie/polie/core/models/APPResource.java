package gov.nist.rolie.polie.core.models;

import java.io.Serializable;

import gov.nist.rolie.polie.core.XMLMangement.JAXBXMLResourceInterface;
import gov.nist.rolie.polie.core.XMLMangement.XMLResourceInterface;
import gov.nist.rolie.polie.core.exceptions.FailedToSerializeResourceException;

/**
 * An APPResource is any networked accessible data object or service
 * 
 * @author sab3
 *
 */
public abstract class APPResource{

	XMLResourceInterface ri = new JAXBXMLResourceInterface();
	
	public String toXML() throws FailedToSerializeResourceException
	{
		return ri.XMLToString(ri.ResourceToXML(this));
	}
	
}
