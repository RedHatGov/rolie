package gov.nist.rolie.polie.core.utils;

import java.net.URI;
import java.net.URISyntaxException;

import gov.nist.rolie.polie.core.models.APPResource;
import gov.nist.rolie.polie.core.models.constructs.AtomURI;
import gov.nist.rolie.polie.core.models.elements.APPCollection;

public class ResourceBuilder {

	public static APPResource buildFromText(String content) 
	{
		APPResource resource = new APPCollection();
		try {
			((APPCollection)resource).setHref(new AtomURI(new URI("www.google.com")));
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resource;
	}
	
}
