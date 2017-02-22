package gov.nist.rolie.polie.server.servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import org.springframework.stereotype.Component;
import org.w3.x2005.atom.impl.EntryDocumentImpl;

@Provider
@Component
@Produces("application/atom+xml;type=entry")
 public class EntryDocWriter implements MessageBodyWriter<EntryDocumentImpl> {

	@Override
	public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		return type == EntryDocumentImpl.class;
	}

	@Override
	public long getSize(EntryDocumentImpl t, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		return 0;
	}

	@Override
	public void writeTo(EntryDocumentImpl t, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream)
			throws IOException, WebApplicationException {
		t.save(entityStream);
	}

}
