package gov.nist.rolie.polie.core.models.elements;

/*
 * 8.3.2.  The "app:workspace" Element

   Workspaces are server-defined groups of Collections.  The "app:
   workspace" element contains zero or more app:collection elements
   describing the Collections of Resources available for editing.

   appWorkspace =
      element app:workspace {
         appCommonAttributes,
         ( atomTitle
           & appCollection*
           & extensionSansTitleElement* )
      }

   atomTitle = element atom:title { atomTextConstruct }
 */
public class AtomWorkspace implements AtomElement{

}
