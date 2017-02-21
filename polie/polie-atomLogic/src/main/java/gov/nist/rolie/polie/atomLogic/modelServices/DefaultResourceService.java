package gov.nist.rolie.polie.atomLogic.modelServices;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import gov.nist.rolie.polie.model.ResourceType;
import gov.nist.rolie.polie.model.models.APPResource;
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
	public APPResource retrieveResource(URI iri) throws ResourceNotFoundException {
		return persistenceMethod.loadResource(iri);
	}

	@Override
	public void cleanup() {
		// TODO Auto-generated method stub
	}
}
