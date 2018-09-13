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

package gov.nist.jrolie.server.writers;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ctc.wstx.stax.WstxOutputFactory;

import gov.nist.jrolie.atom.logic.InternalServerError;
import gov.nist.jrolie.atom.logic.services.EntryService;
import gov.nist.jrolie.model.JFeed;
import gov.nist.jrolie.model.impl.EntryAdapter;
import gov.nist.jrolie.model.impl.JEntryImpl;
import gov.nist.jrolie.model.impl.JEntryWrapper;
import gov.nist.jrolie.model.impl.JFeedImpl;
import gov.nist.jrolie.persistence.api.exceptions.InvalidResourceTypeException;
import gov.nist.jrolie.persistence.api.exceptions.ResourceNotFoundException;

@Provider
@Component
@Produces({ "application/xml", "application/atom+xml;type=entry", "application/atom+xml" })
public class JFeedWriter implements MessageBodyWriter<JFeed> {

  @Context
  UriInfo info;

  @Autowired
  EntryService es;

  @Override
  public long getSize(JFeed arg0, Class<?> arg1, Type arg2, Annotation[] arg3, MediaType arg4) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
    return JFeed.class.equals(type.getInterfaces()[0]);
  }

  @Override
  public void writeTo(JFeed f, Class<?> arg1, Type arg2, Annotation[] arg3, MediaType arg4,
      MultivaluedMap<String, Object> arg5, OutputStream out) throws IOException, WebApplicationException {
    JAXBContext jaxbContext;
    try {
      jaxbContext = JAXBContext.newInstance(JFeedImpl.class);
      WstxOutputFactory wstx = new WstxOutputFactory();
      wstx.setProperty(wstx.XSP_NAMESPACE_AWARE, true);
      wstx.setProperty(wstx.IS_REPAIRING_NAMESPACES, true);
      XMLStreamWriter xmlStreamWriter = wstx.createXMLStreamWriter(out);
      Marshaller marshaller = jaxbContext.createMarshaller();
      marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
      EntryAdapter ea = new EntryAdapter(loadEntries(f.getEntries()));
      marshaller.setAdapter(ea);
      marshaller.marshal(f, xmlStreamWriter);
    } catch (JAXBException | XMLStreamException | ResourceNotFoundException | InvalidResourceTypeException | InternalServerError e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }
  }

private HashMap<String,JEntryImpl> loadEntries(ArrayList<JEntryWrapper> entries) throws ResourceNotFoundException, InvalidResourceTypeException, InternalServerError {
	HashMap<String,JEntryImpl> map = new HashMap<String,JEntryImpl>();
	for (JEntryWrapper e : entries)
	{
		map.put(e.getId(), (JEntryImpl) es.load(e.getId()));
	}
	return map;	
}

}
