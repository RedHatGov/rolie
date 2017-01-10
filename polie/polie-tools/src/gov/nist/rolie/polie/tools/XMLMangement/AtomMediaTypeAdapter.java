package gov.nist.rolie.polie.tools.XMLMangement;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import gov.nist.rolie.polie.model.models.constructs.AtomMediaType;


public class AtomMediaTypeAdapter extends XmlAdapter<String, AtomMediaType> {
	
    @Override
    public AtomMediaType unmarshal(String s) {
        return new AtomMediaType(s);
    }

    @Override
    public String marshal(AtomMediaType amt) {
        return amt != null ? amt.getType() : null;
    }
}
