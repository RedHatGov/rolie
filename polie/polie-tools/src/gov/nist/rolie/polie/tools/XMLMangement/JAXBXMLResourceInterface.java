package gov.nist.rolie.polie.tools.XMLMangement;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

import gov.nist.rolie.polie.model.models.APPResource;
import gov.nist.rolie.polie.tools.exceptions.FailedToBuildResourceException;
import gov.nist.rolie.polie.tools.exceptions.FailedToSerializeResourceException;
import gov.nist.rolie.polie.tools.utils.DefaultROLIEValidator;
import gov.nist.rolie.polie.tools.utils.ROLIEDocumentUtils;

public class JAXBXMLResourceInterface implements XMLResourceInterface {

	@Override
	public APPResource XMLToResource(Object xmlContent) throws FailedToBuildResourceException {

		SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		
		InputStream is = DefaultROLIEValidator.class.getClassLoader().getResourceAsStream("rolie.xsd");
		Source schemaFile = new StreamSource(is);
		Schema schema=null;
//		try {
//			schema = factory.newSchema(schemaFile);
//		} catch (SAXException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		} 
		
		try {
			String xml = (String) xmlContent;
			StringReader sr = new StringReader(xml);			
			JAXBContext context = JAXBContext.newInstance(ROLIEDocumentUtils.getXMLClass(xml));
			Unmarshaller unmarshaller = context.createUnmarshaller();
			unmarshaller.setSchema(schema);
			APPResource resource = (APPResource)(unmarshaller.unmarshal(sr));
			return resource;
		} catch (JAXBException | SAXException | IOException | ParserConfigurationException e) {
			e.printStackTrace();
			throw new FailedToBuildResourceException();
		}

	}

	@Override
	public String ResourceToXML(APPResource resource) throws FailedToSerializeResourceException {
		try {
			JAXBContext context = JAXBContext.newInstance(resource.getClass());
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			StringWriter sw = new StringWriter();
			marshaller.marshal(resource, sw);
			return sw.toString();
		} catch (JAXBException e) {
			e.printStackTrace();
			throw new FailedToSerializeResourceException();
		}
	}

	// Our XML content is a string anyway
	@Override
	public String XMLToString(Object xmlContent) {
		return (String) xmlContent;
	}

	// Our XML content is a string anyway
	@Override
	public String StringToXML(String xmlContent) {
		return xmlContent;
	}

}
