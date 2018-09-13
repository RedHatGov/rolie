package gov.nist.jrolie.model.impl;


import java.util.HashMap;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import gov.nist.jrolie.model.JEntry;

public class EntryAdapter extends XmlAdapter<JEntryImpl, JEntryWrapper> {

	HashMap<String,JEntryImpl> map = new HashMap<String,JEntryImpl>();
	public EntryAdapter(HashMap<String,JEntryImpl> map)
	{
		this.map=map;
	}
	
	@Override
	public JEntryImpl marshal(JEntryWrapper v) throws Exception {
		return map.get(v.getId());
	}

	@Override
	public JEntryWrapper unmarshal(JEntryImpl v) throws Exception {
		return new JEntryWrapper(v.getId());
	}

}
