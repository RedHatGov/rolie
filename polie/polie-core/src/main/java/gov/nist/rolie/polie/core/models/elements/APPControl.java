package gov.nist.rolie.polie.core.models.elements;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * namespace app = "http://www.w3.org/2007/app"
 * 
 * pubControl = element app:control { atomCommonAttributes, pubDraft? &
 * extensionElement }
 * 
 * pubDraft = element app:draft { "yes" | "no" }
 * 
 * The "app:control" element MAY appear as a child of an atom:entry that is
 * being created or updated via the Atom Publishing Protocol. The app:control
 * element MUST appear only once in an Entry. The app:control element is
 * considered foreign markup as defined in Section 6 of [RFC4287].
 * 
 * The app:control element and its child elements MAY be included in Atom Feed
 * or Entry Documents.
 * 
 * The app:control element can contain an "app:draft" element as defined below,
 * and it can contain extension elements as defined in Section 6 of [RFC4287].
 * 
 * @author sab3
 *
 */
@XmlAccessorType(XmlAccessType.NONE)
public class APPControl implements APPElement{

}
