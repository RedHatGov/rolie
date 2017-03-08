package gov.nist.rolie.polie.atomLogic.modelServices;

import java.net.URI;

import gov.nist.rolie.polie.model.ResourceType;
import gov.nist.rolie.polie.model.models.APPResource;
import gov.nist.rolie.polie.persistence.ResourceAlreadyExistsException;
import gov.nist.rolie.polie.persistence.ResourceNotFoundException;

public interface ResourceService extends Service {
	
	ResourceType identifyResouceType(URI iri) throws ResourceNotFoundException;
	boolean resourceExists(URI iri);
	
	APPResource loadResource(URI iri) throws ResourceNotFoundException;;
	APPResource createResource(URI iri,APPResource resource) throws ResourceAlreadyExistsException;
	APPResource updateResource(URI iri, APPResource resource) throws ResourceNotFoundException;
	boolean deleteResource(URI iri)throws ResourceNotFoundException ;
}
