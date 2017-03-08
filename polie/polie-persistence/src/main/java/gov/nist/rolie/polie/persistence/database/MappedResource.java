package gov.nist.rolie.polie.persistence.database;

import gov.nist.rolie.polie.model.ResourceType;
import gov.nist.rolie.polie.model.models.APPResource;

public class MappedResource {

	APPResource resource;
	ResourceType type;

	/**
	 * @param resource
	 * @param type
	 */
	public MappedResource(APPResource resource, ResourceType type) {
		this.resource = resource;
		this.type = type;
	}

	/**
	 * @return the resource
	 */
	public APPResource getResource() {
		return resource;
	}

	/**
	 * @return the type
	 */
	public ResourceType getType() {
		return type;
	}

	/**
	 * @param resource
	 *            the resource to set
	 */
	public void setResource(APPResource resource) {
		this.resource = resource;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(ResourceType type) {
		this.type = type;
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof MappedResource)) {
			return false;
		}
		MappedResource mrOther = (MappedResource) other;
		if (mrOther.getType() != this.getType()) {
			return false;
		}
		else if (mrOther.getResource()!=this.getResource())
		{
			return false;
		}
		return true;
	}

}
