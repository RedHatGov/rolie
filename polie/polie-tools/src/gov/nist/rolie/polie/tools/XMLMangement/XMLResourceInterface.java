package gov.nist.rolie.polie.tools.XMLMangement;

import gov.nist.rolie.polie.model.models.APPResource;
import gov.nist.rolie.polie.tools.exceptions.FailedToBuildResourceException;
import gov.nist.rolie.polie.tools.exceptions.FailedToSerializeResourceException;

public interface XMLResourceInterface {

	public APPResource XMLToResource(Object xmlContent) throws FailedToBuildResourceException;
	public Object ResourceToXML(APPResource resource) throws FailedToSerializeResourceException;
	public String XMLToString(Object xmlContent);
	public Object StringToXML(String xmlContent);
}
