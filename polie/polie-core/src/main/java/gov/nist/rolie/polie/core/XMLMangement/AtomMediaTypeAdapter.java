package gov.nist.rolie.polie.core.XMLMangement;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import gov.nist.rolie.polie.core.models.constructs.AtomMediaType;


public class AtomMediaTypeAdapter extends XmlAdapter<String, AtomMediaType> {
	
    @Override
    public AtomMediaType unmarshal(String s) {
        return new AtomMediaType(s);
    }

    @Override
    public String marshal(AtomMediaType atc) {
        return atc != null ? atc.getType() : null;
    }
}
