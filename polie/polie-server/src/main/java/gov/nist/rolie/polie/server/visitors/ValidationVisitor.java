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
package gov.nist.rolie.polie.server.visitors;

import gov.nist.rolie.polie.model.models.AtomEntry;
import gov.nist.rolie.polie.server.event.Delete;
import gov.nist.rolie.polie.server.event.Get;
import gov.nist.rolie.polie.server.event.Post;
import gov.nist.rolie.polie.server.event.Put;

import org.apache.xmlbeans.XmlException;
import org.springframework.stereotype.Component;
import org.w3.x2005.atom.EntryDocument;

import java.util.Map;

import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

@Component
public class ValidationVisitor implements RESTEventVisitor {

  // static ROLIEValidator validator = new DefaultROLIEValidator();

  @Override
  public boolean visit(Post post, ResponseBuilder rb, Map<String, Object> data) {
    try {
      AtomEntry entry = new AtomEntry(EntryDocument.Factory.parse(post.getBody()));
      if (entry.getXmlObject().getEntry().getContentList().isEmpty()) {
        throw new XmlException("Missing Content Element.");
      }
      data.put(RESOURCE_KEY, entry);
    } catch (XmlException e) {
      rb.status(Status.NOT_ACCEPTABLE);
      rb.entity("The resource you are posting is invalid.\n" + e.getMessage() + "\n" + e.getError());
      return false;
    }

    return true;
  }

  @Override
  public boolean visit(Put put, ResponseBuilder rb, Map<String, Object> data) {
    try {
      AtomEntry entry = new AtomEntry(EntryDocument.Factory.parse(put.getBody()));
      if (entry.getXmlObject().getEntry().getContentList().isEmpty()) {
        throw new XmlException("Missing Content Element.");
      }
      data.put(RESOURCE_KEY, entry);
    } catch (XmlException e) {
      rb.status(Status.NOT_ACCEPTABLE);
      rb.entity("The resource you are posting is invalid.\n" + e.getMessage() + "\n" + e.getError());
      return false;
    }

    return true;
  }

  @Override
  public boolean visit(Delete delete, ResponseBuilder rb, Map<String, Object> data) {
    return true;
  }

  @Override
  public boolean visit(Get get, ResponseBuilder rb, Map<String, Object> data) {
    return false;
  }
}
