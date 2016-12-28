package gov.nist.rolie.polie.core.models.elements;

import java.util.List;
import gov.nist.rolie.polie.core.models.constructs.APPCommonAttributes;
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
public class APPWorkspace implements APPElement{

	private APPCommonAttributes APPCommonAttributes;
	private AtomTitle title;
	private List<APPCollection> collections;
	/**
	 * @param aPPCommonAttributes
	 * @param title
	 * @param collections
	 */
	public APPWorkspace(gov.nist.rolie.polie.core.models.constructs.APPCommonAttributes aPPCommonAttributes,
			AtomTitle title, List<APPCollection> collections) {
		super();
		APPCommonAttributes = aPPCommonAttributes;
		this.title = title;
		this.collections = collections;
	}
	public APPWorkspace() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the aPPCommonAttributes
	 */
	public APPCommonAttributes getAPPCommonAttributes() {
		return APPCommonAttributes;
	}
	/**
	 * @return the title
	 */
	public AtomTitle getTitle() {
		return title;
	}
	/**
	 * @return the collections
	 */
	public List<APPCollection> getCollections() {
		return collections;
	}
	/**
	 * @param aPPCommonAttributes the aPPCommonAttributes to set
	 */
	public void setAPPCommonAttributes(APPCommonAttributes aPPCommonAttributes) {
		APPCommonAttributes = aPPCommonAttributes;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(AtomTitle title) {
		this.title = title;
	}
	/**
	 * @param collections the collections to set
	 */
	public void setCollections(List<APPCollection> collections) {
		this.collections = collections;
	}
	
	
	
}
