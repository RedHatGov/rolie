package gov.nist.rolie.polie.core.models.elements;

import java.util.List;

import gov.nist.rolie.polie.core.models.APPResource;
import gov.nist.rolie.polie.core.models.constructs.AtomCommonAttributes;
import gov.nist.rolie.polie.core.models.constructs.AtomURI;

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
public class APPCollection implements APPElement,APPResource {

	private AtomCommonAttributes commonAttributes;
	
	/*
	 * 8.3.3.1.  The "href" Attribute

   The app:collection element MUST contain an "href" attribute, whose
   value gives the IRI of the Collection.
	 */
	private AtomURI href;
	
	//TODO
	private AtomTitle title;
	private List<APPCategories> categories;
	private List<APPAccept> accepts;
	

	//private AtomPPAccept accept;
	
	public APPCollection()
	{
		this.commonAttributes = null;
		this.href = null;
		this.title= null;
		this.categories= null;;
		this.accepts= null;
	}
	public APPCollection(AtomCommonAttributes commonAttributes, AtomURI href, AtomTitle title,
			List<APPCategories> categories, List<APPAccept> accepts) {
		super();
		this.commonAttributes = commonAttributes;
		this.href = href;
		this.title = title;
		this.categories = categories;
		this.accepts = accepts;
	}
	public AtomCommonAttributes getCommonAttributes() {
		return commonAttributes;
	}
	public void setCommonAttributes(AtomCommonAttributes commonAttributes) {
		this.commonAttributes = commonAttributes;
	}
	public AtomTitle getTitle() {
		return title;
	}
	public void setTitle(AtomTitle title) {
		this.title = title;
	}
	public List<APPCategories> getCategories() {
		return categories;
	}
	public void setCategories(List<APPCategories> categories) {
		this.categories = categories;
	}
	public List<APPAccept> getAccepts() {
		return accepts;
	}
	public AtomURI getHref() {
		return href;
	}
	public void setHref(AtomURI href) {
		this.href = href;
	}
	public void setAccepts(List<APPAccept> accepts) {
		this.accepts = accepts;
	}

}
