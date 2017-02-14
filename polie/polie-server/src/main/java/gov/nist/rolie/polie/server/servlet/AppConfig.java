package gov.nist.rolie.polie.server.servlet;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import gov.nist.rolie.polie.atomLogic.modelServices.DefaultResourceService;
import gov.nist.rolie.polie.atomLogic.modelServices.ResourceService;
import gov.nist.rolie.polie.persistence.database.DummyPersist;
import gov.nist.rolie.polie.persistence.database.PersistenceMethod;

@Configuration
public class AppConfig {
	@Bean(destroyMethod = "cleanup")
	public ResourceService resourceService() {
		return new DefaultResourceService();
	}

	@Bean(destroyMethod = "cleanup")
	public PersistenceMethod persistenceMethod() {
		return new DummyPersist();
	}
}
