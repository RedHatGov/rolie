package gov.nist.rolie.polie.atomLogic.modelServices;

import java.net.URI;

import gov.nist.rolie.polie.model.ResourceType;
import gov.nist.rolie.polie.model.models.APPResource;
import gov.nist.rolie.polie.persistence.ResourceNotFoundException;

public interface ResourceService extends Service {
	
	ResourceType identifyResouceType(URI iri) throws ResourceNotFoundException;
	APPResource retrieveResource(URI iri) throws ResourceNotFoundException;
}
