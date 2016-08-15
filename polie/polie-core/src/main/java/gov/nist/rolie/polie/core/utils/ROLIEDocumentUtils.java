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

public class ROLIEDocumentUtils {
	
	//Instantiation Protector
	private ROLIEDocumentUtils(){};
	
	public static Document buildDocFromText(String raw) throws ParserConfigurationException, SAXException, IOException
	{
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		dbFactory.setNamespaceAware(true);
		DocumentBuilder parser = dbFactory.newDocumentBuilder();
		Document document = parser.parse(new InputSource(new StringReader(raw)));
		return document;
	}
	
	public static Document buildDocFromFilePath(String path) throws ParserConfigurationException, SAXException, IOException
	{
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		dbFactory.setNamespaceAware(true);
		DocumentBuilder parser = dbFactory.newDocumentBuilder();
		File file = new File(path);
		Document document = parser.parse(file);
		return document;
	}
	
	public static Document buildDocFromFile(File file) throws ParserConfigurationException, SAXException, IOException
	{
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		dbFactory.setNamespaceAware(true);
		DocumentBuilder parser = dbFactory.newDocumentBuilder();
		Document document = parser.parse(file);
		return document;
	}
}
