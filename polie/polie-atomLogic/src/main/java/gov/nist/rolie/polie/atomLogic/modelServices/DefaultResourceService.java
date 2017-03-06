package gov.nist.rolie.polie.atomLogic.modelServices;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import gov.nist.rolie.polie.model.ResourceType;
import gov.nist.rolie.polie.model.models.APPResource;
import gov.nist.rolie.polie.persistence.ResourceAlreadyExistsException;
import gov.nist.rolie.polie.persistence.ResourceNotFoundException;
import gov.nist.rolie.polie.persistence.database.PersistenceMethod;

@Component
public class DefaultResourceService implements ResourceService {

	@Autowired
	PersistenceMethod persistenceMethod;

	@Override
	public ResourceType identifyResouceType(URI iri) throws ResourceNotFoundException {
		return persistenceMethod.identifyResouceType(iri);
	}

	@Override
	public boolean resourceExists(URI iri) {
		return persistenceMethod.resourceExists(iri);
	}

	@Override
	public APPResource loadResource(URI iri) throws ResourceNotFoundException {
		return persistenceMethod.loadResource(iri);
	}

	@Override
	public APPResource createResource(URI iri, APPResource resource) throws ResourceAlreadyExistsException {
		return persistenceMethod.createResource(resource, iri);
	}

	@Override
	public APPResource updateResource(URI iri, APPResource resource) throws ResourceNotFoundException {
		return persistenceMethod.updateResource(resource, iri);
	}

	@Override
	public boolean deleteResource(URI iri) throws ResourceNotFoundException {
		return persistenceMethod.deleteResource(iri);
	}
}
