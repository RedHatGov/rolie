package gov.nist.rolie.polie.core.utils;

import java.net.URI;
import java.net.URISyntaxException;

import gov.nist.rolie.polie.core.models.AtomResource;
import gov.nist.rolie.polie.core.models.constructs.AtomURI;
import gov.nist.rolie.polie.core.models.elements.AtomCollection;

public class ResourceBuilder {

	public static AtomResource buildFromText(String content) 
	{
		AtomResource resource = new AtomCollection();
		try {
			((AtomCollection)resource).setHref(new AtomURI(new URI("www.google.com")));
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resource;
	}
	
}
