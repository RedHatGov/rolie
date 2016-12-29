package gov.nist.rolie.polie.core.XMLMangement;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import gov.nist.rolie.polie.core.models.constructs.AtomDate;

public class AtomDateAdapter extends XmlAdapter<String, AtomDate> {
	
    @Override
    public AtomDate unmarshal(String s) {
    	AtomDate ad = new AtomDate();
    	ad.setDateTimeString(s);
        return ad;
    }

    @Override
    public String marshal(AtomDate ad) {
        return ad != null ? ad.getDateTimeString() : null;
    }
}
