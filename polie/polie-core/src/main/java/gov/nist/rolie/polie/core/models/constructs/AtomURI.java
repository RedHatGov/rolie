package gov.nist.rolie.polie.core.models.constructs;

import java.net.URI;

import gov.nist.rolie.polie.core.models.elements.AtomElement;

public class AtomURI implements AtomElement{

	URI uri;
	
	public AtomURI(URI uri)
	{
		this.uri=uri;
	}
	
	public URI value()
	{
		return uri;
	}
	
}
