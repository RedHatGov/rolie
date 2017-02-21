package gov.nist.rolie.polie.model.models;

import org.w3.x2007.app.ServiceDocument;

import gov.nist.rolie.polie.model.ResourceType;
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

public class APPServiceDocument extends AbstractAPPResource<ServiceDocument> implements AtomElement {

	public APPServiceDocument() {
		this(ServiceDocument.Factory.newInstance());
	}

	public APPServiceDocument(ServiceDocument doc) {
		super(doc);
	}


	public ResourceType getResourceType() {
		return ResourceType.SERVICE;
	}
}
