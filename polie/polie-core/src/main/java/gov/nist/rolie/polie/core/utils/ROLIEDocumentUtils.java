/*
 * 
 */
package gov.nist.rolie.polie.core.utils;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

// TODO: Auto-generated Javadoc
/**
 * The Class ROLIEDocumentUtils.
 */
public class ROLIEDocumentUtils {
	
	/**
	 * Instantiates a new ROLIE document utils.
	 */
	//Instantiation Protector
	private ROLIEDocumentUtils(){};
	
	/**
	 * Builds the doc from text.
	 *
	 * @param raw the raw
	 * @return the document
	 * @throws ParserConfigurationException the parser configuration exception
	 * @throws SAXException the SAX exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static Document buildDocFromText(String raw) throws ParserConfigurationException, SAXException, IOException
	{
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		dbFactory.setNamespaceAware(true);
		DocumentBuilder parser = dbFactory.newDocumentBuilder();
		Document document = parser.parse(new InputSource(new StringReader(raw)));
		return document;
	}
	
	/**
	 * Builds the doc from file path.
	 *
	 * @param path the path
	 * @return the document
	 * @throws ParserConfigurationException the parser configuration exception
	 * @throws SAXException the SAX exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static Document buildDocFromFilePath(String path) throws ParserConfigurationException, SAXException, IOException
	{
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		dbFactory.setNamespaceAware(true);
		DocumentBuilder parser = dbFactory.newDocumentBuilder();
		File file = new File(path);
		Document document = parser.parse(file);
		return document;
	}
	
	/**
	 * Builds the doc from file.
	 *
	 * @param file the file
	 * @return the document
	 * @throws ParserConfigurationException the parser configuration exception
	 * @throws SAXException the SAX exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static Document buildDocFromFile(File file) throws ParserConfigurationException, SAXException, IOException
	{
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		dbFactory.setNamespaceAware(true);
		DocumentBuilder parser = dbFactory.newDocumentBuilder();
		Document document = parser.parse(file);
		return document;
	}
}
