package gov.nist.rolie.polie.model.models;

import java.util.List;

import gov.nist.rolie.polie.model.models.constructs.APPCommonAttributes;
import gov.nist.rolie.polie.model.models.elements.APPWorkspace;
import gov.nist.rolie.polie.model.models.elements.AtomElement;
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

public class APPServiceDocument extends APPResource implements AtomElement {
	
	APPCommonAttributes APPCommonAttributes;
	
	List<APPWorkspace> workspace;
	
	public APPServiceDocument(APPCommonAttributes APPCommonAttributes,
			List<APPWorkspace> workspaces) {
		super();
		this.APPCommonAttributes = APPCommonAttributes;
		this.workspace = workspaces;
	}
	public APPServiceDocument() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the aPPCommonAttributes
	 */
	public APPCommonAttributes getAPPCommonAttributes() {
		return APPCommonAttributes;
	}
	/**
	 * @return the workspace
	 */
	public List<APPWorkspace> getWorkspaces() {
		return workspace;
	}
	/**
	 * @param aPPCommonAttributes the aPPCommonAttributes to set
	 */
	public void setAPPCommonAttributes(APPCommonAttributes APPCommonAttributes) {
		this.APPCommonAttributes = APPCommonAttributes;
	}
	/**
	 * @param workspace the workspace to set
	 */
	public void setWorkspaces(List<APPWorkspace> workspaces) {
		this.workspace = workspaces;
	}
	

}
