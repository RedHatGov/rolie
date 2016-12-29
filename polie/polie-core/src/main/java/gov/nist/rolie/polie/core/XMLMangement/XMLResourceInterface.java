package gov.nist.rolie.polie.core.XMLMangement;

import gov.nist.rolie.polie.core.exceptions.FailedToBuildResourceException;
import gov.nist.rolie.polie.core.exceptions.FailedToSerializeResourceException;
import gov.nist.rolie.polie.core.models.APPResource;

public interface XMLResourceInterface {

	public APPResource XMLToResource(Object xmlContent) throws FailedToBuildResourceException;
	public Object ResourceToXML(APPResource resource) throws FailedToSerializeResourceException;
	public String XMLToString(Object xmlContent);
	public Object StringToXML(String xmlContent);
}
