package gov.nist.rolie.polie.tools.utils;

import gov.nist.rolie.polie.model.models.APPResource;
import gov.nist.rolie.polie.model.models.AtomEntry;
import gov.nist.rolie.polie.tools.exceptions.FailedToBuildResourceException;


public class ROLIEResourceBuilder {

	public static APPResource buildFromText(String content) throws FailedToBuildResourceException 
	{
		APPResource resource = new AtomEntry();
		((AtomEntry) resource).setTitle("I'm an entry that ROLIEResourceBuilder built");
		return resource;
	}
	
}
