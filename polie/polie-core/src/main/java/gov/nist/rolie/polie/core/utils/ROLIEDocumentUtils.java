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

import gov.nist.rolie.polie.core.models.APPCategories;
import gov.nist.rolie.polie.core.models.APPServiceDocument;
import gov.nist.rolie.polie.core.models.AtomEntry;
import gov.nist.rolie.polie.core.models.AtomFeed;

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
	
	public static Class<?> getXMLClass(String xml) throws SAXException, IOException, ParserConfigurationException
	{
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		dbFactory.setNamespaceAware(true);
		DocumentBuilder parser = dbFactory.newDocumentBuilder();
		Document document = parser.parse(new InputSource(new StringReader(xml)));
		String root = document.getDocumentElement().getLocalName();
		switch (root) {
		case "entry": return AtomEntry.class;
		case "feed": return AtomFeed.class;
		case "service": return APPServiceDocument.class;
		case "categories": return APPCategories.class;
		}
		return null;
	}
	
}
