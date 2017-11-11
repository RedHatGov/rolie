package gov.nist.jrolie.model;

import java.net.URI;

public interface JResource {
	ResourceType getResourceType();
	URI getLocation();
	boolean isChanged();
	void markChanged();
}
