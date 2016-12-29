package gov.nist.rolie.polie.core.XMLMangement;

import java.net.URI;
import java.net.URISyntaxException;

import javax.xml.bind.annotation.adapters.XmlAdapter;


import gov.nist.rolie.polie.core.models.constructs.AtomURI;

public class AtomURIAdapter extends XmlAdapter<String, AtomURI> {
	
    @Override
    public AtomURI unmarshal(String s) throws URISyntaxException {
        return new AtomURI(new URI(s));
    }

    @Override
    public String marshal(AtomURI aURI) {
        return aURI != null ? aURI.toString() : null;
    }
}
