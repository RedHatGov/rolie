package gov.nist.rolie.polie.atomLogic.modelServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import gov.nist.rolie.polie.persistence.NestedStringMaker;

@Component
public class ModuleStringMaker implements ModuleStringMakerInterface{
	
	@Autowired
	NestedStringMaker ns;
	
	public String makeModuleString()
	{
		return ns.makeNestedString();
	}
	
	

}
