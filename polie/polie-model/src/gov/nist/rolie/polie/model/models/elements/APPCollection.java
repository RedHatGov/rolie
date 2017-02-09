package gov.nist.rolie.polie.model.models.elements;

import java.net.URI;
import java.util.ArrayList;

import gov.nist.rolie.polie.model.models.APPCategories;
import gov.nist.rolie.polie.model.models.constructs.AtomCommonAttributes;


/**
8.3.3.  The "app:collection" Element

   The "app:collection" element describes a Collection.  The app:
   collection element MUST contain one atom:title element.

   The app:collection element MAY contain any number of app:accept
   elements, indicating the types of representations accepted by the
   Collection.  The order of such elements is not significant.

   The app:collection element MAY contain any number of app:categories
   elements.

   appCollection =
      element app:collection {
         appCommonAttributes,
         attribute href { atomURI  },
         ( atomTitle
           & appAccept*
           & appCategories*
           & extensionSansTitleElement* )
      }
 */

public class APPCollection implements APPElement {

	private AtomCommonAttributes commonAttributes;
	
	/*
	 * 8.3.3.1.  The "href" Attribute

   The app:collection element MUST contain an "href" attribute, whose
   value gives the IRI of the Collection.
	 */

	private URI href;
	
	private String title;
	
	private ArrayList<APPCategories> categories;
	
	private ArrayList<String> accept;

	/**
	 * @return the commonAttributes
	 */
	public AtomCommonAttributes getCommonAttributes() {
		return commonAttributes;
	}

	
	
	/**
	 * @param commonAttributes
	 * @param href
	 * @param title
	 * @param categories
	 * @param accept
	 */
	public APPCollection(AtomCommonAttributes commonAttributes, URI href, String title,
			ArrayList<APPCategories> categories, ArrayList<String> accept) {
		super();
		this.commonAttributes = commonAttributes;
		this.href = href;
		this.title = title;
		this.categories = categories;
		this.accept = accept;
	}



	public APPCollection() {
		// TODO Auto-generated constructor stub
	}



	/**
	 * @return the href
	 */
	public URI getHref() {
		return href;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @return the categories
	 */
	public ArrayList<APPCategories> getCategories() {
		return categories;
	}

	/**
	 * @return the accept
	 */
	public ArrayList<String> getAccept() {
		return accept;
	}

	/**
	 * @param commonAttributes the commonAttributes to set
	 */
	public void setCommonAttributes(AtomCommonAttributes commonAttributes) {
		this.commonAttributes = commonAttributes;
	}

	/**
	 * @param href the href to set
	 */
	public void setHref(URI href) {
		this.href = href;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @param categories the categories to set
	 */
	public void setCategories(ArrayList<APPCategories> categories) {
		this.categories = categories;
	}

	/**
	 * @param accept the accept to set
	 */
	public void setAccept(ArrayList<String> accept) {
		this.accept = accept;
	}

	

}
