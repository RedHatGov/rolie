package gov.nist.rolie.polie.server.servlet;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import gov.nist.rolie.polie.atomLogic.modelServices.DefaultFeedService;
import gov.nist.rolie.polie.atomLogic.modelServices.DefaultResourceService;
import gov.nist.rolie.polie.atomLogic.modelServices.FeedService;
import gov.nist.rolie.polie.atomLogic.modelServices.ResourceService;
import gov.nist.rolie.polie.persistence.database.DummyPersist;
import gov.nist.rolie.polie.persistence.database.PersistenceMethod;
import gov.nist.rolie.polie.server.visitors.ResourceEventVisitor;

@Configuration
@ComponentScan(basePackages={"gov.nist.rolie.polie.atomLogic.modelServices",
		"gov.nist.rolie.polie.persistence.database","gov.nist.rolie.polie.server.servlet"})
public class AppConfig {
	
	
	@Bean(destroyMethod = "cleanup")
	public ResourceService resourceService() {
		return new DefaultResourceService();
	}

	@Bean(destroyMethod = "cleanup")
	public PersistenceMethod persistenceMethod() {
		return new DummyPersist();
	}
	
	@Bean(destroyMethod = "cleanup")
	public ResourceEventVisitor resourceEventVisitor() {
		return new ResourceEventVisitor();
	}
	
	@Bean(destroyMethod = "cleanup")
	public FeedService feedService() {
		return new DefaultFeedService();
	}
	
	@Bean(destroyMethod = "cleanup")
	public DefaultVisitorManagerFactory defaultVisitorManager() {
		return new DefaultVisitorManagerFactory();
	}
}
