package gov.nist.rolie.polie.core.utils;

import gov.nist.rolie.polie.core.exceptions.FailedToBuildResourceException;
import gov.nist.rolie.polie.core.models.APPResource;
import gov.nist.rolie.polie.core.models.AtomEntry;


public class ROLIEResourceBuilder {

	public static APPResource buildFromText(String content) throws FailedToBuildResourceException 
	{
		APPResource resource = new AtomEntry();
		((AtomEntry) resource).setTitle("I'm an entry that ROLIEResourceBuilder built");
		return resource;
	}
	
}
