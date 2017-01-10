package gov.nist.rolie.polie.tools.utils;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

/**
 * Interface for ROLIE Validators
 * 
 * @author sab3
 *
 */
public interface ROLIEValidator {

	public Boolean validate(String content) throws ParserConfigurationException, SAXException, IOException;
	
}
