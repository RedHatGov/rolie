package gov.nist.rolie.polie.core.models;
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
public class AtomServiceDocument implements AtomDocument{

}
