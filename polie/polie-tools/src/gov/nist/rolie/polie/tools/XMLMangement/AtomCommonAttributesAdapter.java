package gov.nist.rolie.polie.tools.XMLMangement;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import gov.nist.rolie.polie.model.models.constructs.AtomCommonAttributes;

public class AtomCommonAttributesAdapter extends XmlAdapter<HashMap<String,String>, AtomCommonAttributes> {
	
    @Override
    public AtomCommonAttributes unmarshal(HashMap<String,String> attrmap) {
    	System.out.print(attrmap);
        return new AtomCommonAttributes();
    }

    @Override
    public HashMap<String,String> marshal(AtomCommonAttributes aca) {
        HashMap<String,String> attrmap = new HashMap<String,String>();
        attrmap.put("xmlbase", aca.getXmlBase());
        attrmap.put("xmlLang", aca.getXmlLang());
        return attrmap;
    }
}
