package gov.nist.rolie.polie.model.models.elements;

import java.util.List;

import gov.nist.rolie.polie.model.models.constructs.APPCommonAttributes;

public class APPWorkspace implements APPElement{

	private APPCommonAttributes APPCommonAttributes;
	

	private AtomTitle title;
	

	private List<APPCollection> collection;
	/**
	 * @param aPPCommonAttributes
	 * @param title
	 * @param collection
	 */
	public APPWorkspace(gov.nist.rolie.polie.model.models.constructs.APPCommonAttributes aPPCommonAttributes,
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
