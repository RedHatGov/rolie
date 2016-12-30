/*
 * 
 */
package gov.nist.rolie.polie.core.utils;
import java.io.IOException;
import java.io.InputStream;
import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 *  The default implementation of the ROLIEValidator.
 */
public class DefaultROLIEValidator implements ROLIEValidator{
	
	/**
	 * This class cannot be instantiated
	 */
	public DefaultROLIEValidator(){};
	
	/** Provides logging. TODO */
	private static final Logger log = LogManager.getLogger(DefaultROLIEValidator.class);
	
	/**
	 * Validate.
	 *
	 * @param document the document
	 * @return true, if successful
	 * @throws ParserConfigurationException the parser configuration exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws SAXException the SAX exception
	 */
	public static void validate(Document document) throws ParserConfigurationException, IOException, SAXException
	{
		SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		
		InputStream is = DefaultROLIEValidator.class.getClassLoader().getResourceAsStream("rolie.xsd");
		Source schemaFile = new StreamSource(is);
		Schema schema = factory.newSchema(schemaFile); 

		Validator validator = schema.newValidator();
		validator.validate(new DOMSource(document));


	}

	@Override
	public Boolean validate(String content) throws ParserConfigurationException, SAXException, IOException 
	{
		Document contentDoc = ROLIEDocumentUtils.buildDocFromText(content);
		return true;
	}
}
