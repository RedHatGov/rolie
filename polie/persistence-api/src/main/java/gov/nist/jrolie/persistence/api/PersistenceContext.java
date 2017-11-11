package gov.nist.jrolie.persistence.api;

import java.net.URI;

import gov.nist.jrolie.model.JResource;

public interface PersistenceContext {
	boolean exists(URI location);
	<X extends JResource> X load(URI location);
	<X extends JResource> X load(URI create, Class<X> clazz);
	<X extends JResource> X save(X resource);
}
