package gov.nist.rolie.polie.core;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
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

public class RolieModelValidator {
	private static final Logger log = LogManager.getLogger(RolieModelValidator.class);
	
	public static boolean isValid(Document document) throws ParserConfigurationException, IOException, SAXException
	{
		SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		
		InputStream is = RolieModelValidator.class.getClassLoader().getResourceAsStream("test.xsd");
		Source schemaFile = new StreamSource(is);
		Schema schema = factory.newSchema(schemaFile); 

		Validator validator = schema.newValidator();
		boolean retval;
		try {
			validator.validate(new DOMSource(document));
			retval = true;
		} catch (SAXException e) {
			log.error("Unable to validate XML instance", e);
			retval = false;
		}
		return retval;
	}

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		dbFactory.setNamespaceAware(true);
		DocumentBuilder parser = dbFactory.newDocumentBuilder();
		Document document = parser.parse(RolieModelValidator.class.getClassLoader().getResourceAsStream("testEntry.xml"));
		isValid(document);
	}
}
