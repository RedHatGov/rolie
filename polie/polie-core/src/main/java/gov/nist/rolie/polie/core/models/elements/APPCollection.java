package gov.nist.rolie.polie.core.models.elements;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import gov.nist.rolie.polie.core.XMLMangement.AtomURIAdapter;
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
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement
public class APPCollection implements APPElement {

	private AtomCommonAttributes commonAttributes;
	
	/*
	 * 8.3.3.1.  The "href" Attribute

   The app:collection element MUST contain an "href" attribute, whose
   value gives the IRI of the Collection.
	 */
	@XmlAttribute
	@XmlJavaTypeAdapter(AtomURIAdapter.class)
	private AtomURI href;
	
	@XmlElement
	private AtomTitle title;
	
	@XmlElement
	private List<APPCategories> categories;
	
	@XmlElement
	private List<APPAccept> accept;
	

	//private AtomPPAccept accept;
	
	public APPCollection(){}
	
	public APPCollection(AtomCommonAttributes commonAttributes, AtomURI href, AtomTitle title,
			List<APPCategories> categories, List<APPAccept> accepts) {
		super();
		this.commonAttributes = commonAttributes;
		this.href = href;
		this.title = title;
		this.categories = categories;
		this.accept = accepts;
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
		return accept;
	}
	public AtomURI getHref() {
		return href;
	}
	public void setHref(AtomURI href) {
		this.href = href;
	}
	public void setAccepts(List<APPAccept> accepts) {
		this.accept = accepts;
	}

}
