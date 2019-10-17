package gov.nist.jrolie.persistence.api;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.stax2.XMLStreamProperties;

import com.ctc.wstx.stax.WstxInputFactory;

import gov.nist.jrolie.model.JEntry;
import gov.nist.jrolie.model.JFeed;
import gov.nist.jrolie.model.impl.JEntryImpl;
import gov.nist.jrolie.model.impl.JFeedImpl;

public class FileLoader {
	private static final Logger log = LogManager.getLogger(FileLoader.class);

	public static JFeed loadFeed(String path) {
		FileInputStream fis = null;
		JFeed jf = null;
		try {
			fis = new FileInputStream(path);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			final JAXBContext jaxbContext = JAXBContext.newInstance(JFeedImpl.class);
			final WstxInputFactory wstx = new WstxInputFactory();
			wstx.setProperty(XMLStreamProperties.XSP_NAMESPACE_AWARE, true);
			final InputStream stream = fis;
			final XMLStreamReader xmlStreamReader = wstx.createXMLStreamReader(stream);
			final Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			jf = (JFeedImpl) unmarshaller.unmarshal(xmlStreamReader);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jf;
	}

	public static JEntry loadEntry(String path) {
		FileInputStream fis = null;
		JEntry je = null;
		try {
			fis = new FileInputStream(path);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			final JAXBContext jaxbContext = JAXBContext.newInstance(JEntryImpl.class);
			final WstxInputFactory wstx = new WstxInputFactory();
			wstx.setProperty(XMLStreamProperties.XSP_NAMESPACE_AWARE, true);
			final InputStream stream = fis;
			final XMLStreamReader xmlStreamReader = wstx.createXMLStreamReader(stream);
			final Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			je = (JEntryImpl) unmarshaller.unmarshal(xmlStreamReader);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return je;
	}

	public static void loadService() {
	}


	public static void loadCategory() {
	}

	public static String loadData(String path) {

		try {
			return Files.readString(Paths.get(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "failure";
	}
	
}
