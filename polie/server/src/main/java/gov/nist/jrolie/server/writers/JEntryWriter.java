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
import java.io.Writer;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.springframework.stereotype.Component;

import com.ctc.wstx.api.WstxInputProperties;
import com.ctc.wstx.stax.WstxOutputFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import gov.nist.jrolie.model.JEntry;

@Provider
@Component
@Produces({ "application/xml", "application/atom+xml;type=entry", "application/atom+xml" })
public class JEntryWriter implements MessageBodyWriter<JEntry>{

	@Override
	public long getSize(JEntry arg0, Class<?> arg1, Type arg2, Annotation[] arg3, MediaType arg4) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isWriteable(Class<?> type, Type arg1, Annotation[] arg2, MediaType arg3) {
		return JEntry.class.equals(type.getInterfaces()[0]);
	}

	@Override
	public void writeTo(JEntry e, Class<?> arg1, Type arg2, Annotation[] arg3, MediaType arg4,
			MultivaluedMap<String, Object> arg5, OutputStream out) throws IOException, WebApplicationException {
		String message = "Hi there! I'm a entry at: " + e.getPath() + " with id:" + e.getId();
		  XmlMapper mapper = new XmlMapper();
		  // override default instance of WstxOutputFactory
		  mapper.getFactory().setXMLOutputFactory(new WstxOutputFactory() {
		    @Override
		    public XMLStreamWriter createXMLStreamWriter(Writer w) throws XMLStreamException {
		      mConfig.setProperty(WstxInputProperties.P_RETURN_NULL_FOR_DEFAULT_NAMESPACE,  false);
		      XMLStreamWriter result = super.createXMLStreamWriter(w);
		      result.setPrefix("xlink", "http://www.w3.org/1999/xlink");
		      return result;
		    }
		  });
		out.write(mapper.writeValueAsBytes(e));
		

		
	}

}
