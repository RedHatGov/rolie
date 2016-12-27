package gov.nist.rolie.polie.core.utils;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public interface ROLIEValidator {

	public Boolean validate(String content) throws ParserConfigurationException, SAXException, IOException;
	
}
