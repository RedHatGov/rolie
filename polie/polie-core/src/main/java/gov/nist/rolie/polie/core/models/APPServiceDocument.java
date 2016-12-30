package gov.nist.rolie.polie.core.models;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

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
@XmlRootElement(name="service" ,namespace="http://www.w3.org/2007/app")
@XmlAccessorType(XmlAccessType.NONE)
public class APPServiceDocument extends APPResource implements AtomElement {
	
	APPCommonAttributes APPCommonAttributes;
	
	@XmlElement(namespace="http://www.w3.org/2007/app")
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
