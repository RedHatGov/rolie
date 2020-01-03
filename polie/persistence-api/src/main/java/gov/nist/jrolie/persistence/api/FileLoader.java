/**
 * Portions of this software was developed by employees of the National Institute
 * of Standards and Technology (NIST), an agency of the Federal Government.
 * Pursuant to title 17 United States Code Section 105, works of NIST employees are
 * not subject to copyright protection in the United States and are considered to
 * be in the public domain. Permission to freely use, copy, modify, and distribute
 * this software and its documentation without fee is hereby granted, provided that
 * this notice and disclaimer of warranty appears in all copies.
 *
 * THE SOFTWARE IS PROVIDED 'AS IS' WITHOUT ANY WARRANTY OF ANY KIND, EITHER
 * EXPRESSED, IMPLIED, OR STATUTORY, INCLUDING, BUT NOT LIMITED TO, ANY WARRANTY
 * THAT THE SOFTWARE WILL CONFORM TO SPECIFICATIONS, ANY IMPLIED WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, AND FREEDOM FROM
 * INFRINGEMENT, AND ANY WARRANTY THAT THE DOCUMENTATION WILL CONFORM TO THE
 * SOFTWARE, OR ANY WARRANTY THAT THE SOFTWARE WILL BE ERROR FREE. IN NO EVENT
 * SHALL NIST BE LIABLE FOR ANY DAMAGES, INCLUDING, BUT NOT LIMITED TO, DIRECT,
 * INDIRECT, SPECIAL OR CONSEQUENTIAL DAMAGES, ARISING OUT OF, RESULTING FROM, OR
 * IN ANY WAY CONNECTED WITH THIS SOFTWARE, WHETHER OR NOT BASED UPON WARRANTY,
 * CONTRACT, TORT, OR OTHERWISE, WHETHER OR NOT INJURY WAS SUSTAINED BY PERSONS OR
 * PROPERTY OR OTHERWISE, AND WHETHER OR NOT LOSS WAS SUSTAINED FROM, OR AROSE OUT
 * OF THE RESULTS OF, OR USE OF, THE SOFTWARE OR SERVICES PROVIDED HEREUNDER.
 */

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
