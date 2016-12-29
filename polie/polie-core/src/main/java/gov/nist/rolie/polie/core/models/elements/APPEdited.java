package gov.nist.rolie.polie.core.models.elements;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import gov.nist.rolie.polie.core.XMLMangement.AtomDateAdapter;
import gov.nist.rolie.polie.core.models.constructs.AtomDate;

/**10.2 The "app:edited" Element

The "app:edited" element is a Date construct (as defined by [RFC4287]), whose content indicates the last time an Entry was edited. If the entry has not been edited yet, the content indicates the time it was created. Atom Entry elements in Collection Documents SHOULD contain one app:edited element, and MUST NOT contain more than one.

appEdited = element app:edited ( atomDateConstruct )

The server SHOULD change the value of this element every time an Entry Resource or an associated Media Resource has been edited.
*/
@XmlAccessorType(XmlAccessType.NONE)
public class APPEdited implements APPElement {
	@XmlValue
	@XmlJavaTypeAdapter(AtomDateAdapter.class)
	private AtomDate date;

}
