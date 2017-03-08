package gov.nist.rolie.polie.model.models.elements;


import org.w3.x2007.app.CollectionType;

import gov.nist.rolie.polie.model.ResourceType;
import gov.nist.rolie.polie.model.models.AbstractAPPResource;

public class APPCollection  extends AbstractAPPResource<CollectionType> implements AtomElement{

public APPCollection() {
		this(CollectionType.Factory.newInstance());
	}

	public APPCollection(CollectionType collect) {
		super(collect);
	}


	public ResourceType getResourceType() {
		return ResourceType.SERVICE;
	}
	
}