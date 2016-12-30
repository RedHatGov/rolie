package gov.nist.rolie.polie.core.models.elements;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

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
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(namespace="http://www.w3.org/2007/app")
public class APPWorkspace implements APPElement{

	private APPCommonAttributes APPCommonAttributes;
	
	@XmlElement
	private AtomTitle title;
	
	@XmlElement(namespace="http://www.w3.org/2007/app")
	private List<APPCollection> collection;
	/**
	 * @param aPPCommonAttributes
	 * @param title
	 * @param collection
	 */
	public APPWorkspace(gov.nist.rolie.polie.core.models.constructs.APPCommonAttributes aPPCommonAttributes,
			AtomTitle title, List<APPCollection> collections) {
		super();
		APPCommonAttributes = aPPCommonAttributes;
		this.title = title;
		this.collection = collections;
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
	 * @return the collection
	 */
	public List<APPCollection> getCollections() {
		return collection;
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
	 * @param collection the collection to set
	 */
	public void setCollections(List<APPCollection> collections) {
		this.collection = collections;
	}
	
	
	
}
