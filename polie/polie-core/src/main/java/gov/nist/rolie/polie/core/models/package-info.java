@XmlSchema(
    namespace = "http://www.w3.org/2005/Atom",
    elementFormDefault = javax.xml.bind.annotation.XmlNsForm.QUALIFIED,
    xmlns = { 
        @XmlNs(prefix = "atom", namespaceURI = "http://www.w3.org/2005/Atom"),
        @XmlNs(prefix = "rolie", namespaceURI = "urn:ietf:params:xml:ns:rolie-1.0")
    }
)
    
package gov.nist.rolie.polie.core.models;

import javax.xml.bind.annotation.*;