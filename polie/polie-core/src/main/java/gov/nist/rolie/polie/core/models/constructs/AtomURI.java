package gov.nist.rolie.polie.core.models.constructs;

import java.net.URI;

import gov.nist.rolie.polie.core.models.elements.AtomElement;
/**
 *   # Unconstrained; it's not entirely clear how IRI fit into
   # xsd:anyURI so let's not try to constrain it here
   atomUri = text
 * @author sab3
 *
 */
public class AtomURI implements AtomElement{

	URI uri;
	
	public AtomURI(URI uri)
	{
		this.uri=uri;
	}
	
	public AtomURI(){};
	
	public void setURI(URI uri)
	{
		this.uri=uri;
	}
	
	public URI getURI()
	{
		return uri;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return uri.toString();
	}
	
	
	
}
