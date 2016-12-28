package gov.nist.rolie.polie.core.models;

import java.util.List;

import gov.nist.rolie.polie.core.models.elements.APPWorkspace;
import gov.nist.rolie.polie.core.models.elements.AtomElement;
import gov.nist.rolie.polie.core.models.constructs.APPCommonAttributes;
/*
 * 8.3.1.  The "app:service" Element

   The root of a Service Document is the "app:service" element.

   The app:service element is the container for service information
   associated with one or more Workspaces.  An app:service element MUST
   contain one or more app:workspace elements.

   namespace app = "http://www.w3.org/2007/app"
   start = appService

   appService =
      element app:service {
         appCommonAttributes,
         ( appWorkspace+
           & extensionElement* )
      }
 */
public class APPServiceDocument implements APPResource,AtomElement {
	
	APPCommonAttributes APPCommonAttributes;
	List<APPWorkspace> workspaces;
	
	
	public APPServiceDocument(APPCommonAttributes APPCommonAttributes,
			List<APPWorkspace> workspaces) {
		super();
		this.APPCommonAttributes = APPCommonAttributes;
		this.workspaces = workspaces;
	}
	/**
	 * @return the aPPCommonAttributes
	 */
	public APPCommonAttributes getAPPCommonAttributes() {
		return APPCommonAttributes;
	}
	/**
	 * @return the workspaces
	 */
	public List<APPWorkspace> getWorkspaces() {
		return workspaces;
	}
	/**
	 * @param aPPCommonAttributes the aPPCommonAttributes to set
	 */
	public void setAPPCommonAttributes(APPCommonAttributes APPCommonAttributes) {
		this.APPCommonAttributes = APPCommonAttributes;
	}
	/**
	 * @param workspaces the workspaces to set
	 */
	public void setWorkspaces(List<APPWorkspace> workspaces) {
		this.workspaces = workspaces;
	}
	

}
