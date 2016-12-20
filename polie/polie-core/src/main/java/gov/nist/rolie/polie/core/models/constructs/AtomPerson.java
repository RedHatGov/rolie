package gov.nist.rolie.polie.core.models.constructs;
/*
 *    # Person Construct

   atomPersonConstruct =
      atomCommonAttributes,
      (element atom:name { text }
       & element atom:uri { atomUri }?
       & element atom:email { atomEmailAddress }?
       & extensionElement*)

 */
import gov.nist.rolie.polie.core.models.elements.AtomElement;

public class AtomPerson implements AtomElement{
	
	private String name;
	private AtomURI uri;
	private AtomEmailAddress email;
	

}
