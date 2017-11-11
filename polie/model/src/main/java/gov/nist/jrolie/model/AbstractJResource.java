package gov.nist.jrolie.model;

import java.net.URI;

public abstract class AbstractJResource implements JResource {
	private URI location;
	private boolean changed;
	
	public AbstractJResource() {
		this(null, true);
	}

	public AbstractJResource(URI location) {
		this(location, false);
	}

	public AbstractJResource(URI location, boolean isChanged) {
		this.location = location;
		this.changed = isChanged;
	}

	public URI getLocation() {
		return location;
	}

	public void setLocation(URI location) {
		this.location = location;
		markChanged();
	}

	public boolean isChanged() {
		return changed;
	}

	public void markChanged() {
		changed = true;
	}
	
}
