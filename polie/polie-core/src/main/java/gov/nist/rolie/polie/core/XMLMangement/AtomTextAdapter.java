package gov.nist.rolie.polie.core.XMLMangement;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import gov.nist.rolie.polie.core.models.constructs.AtomTextConstruct;

public class AtomTextAdapter extends XmlAdapter<String, AtomTextConstruct> {
	
    @Override
    public AtomTextConstruct unmarshal(String s) {
        return new AtomTextConstruct(s);
    }

    @Override
    public String marshal(AtomTextConstruct atc) {
        return atc != null ? atc.toString() : null;
    }
}
