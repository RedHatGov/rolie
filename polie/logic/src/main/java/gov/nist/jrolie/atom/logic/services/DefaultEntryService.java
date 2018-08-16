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

package gov.nist.jrolie.atom.logic.services;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import gov.nist.jrolie.atom.logic.InternalServerError;
import gov.nist.jrolie.model.JEntry;
import gov.nist.jrolie.model.JFeed;
import gov.nist.jrolie.model.JLink;
import gov.nist.jrolie.model.impl.JLinkImpl;
import gov.nist.jrolie.persistence.api.PersistenceContext;
import gov.nist.jrolie.persistence.api.exceptions.InvalidResourceTypeException;
import gov.nist.jrolie.persistence.api.exceptions.ResourceAlreadyExistsException;
import gov.nist.jrolie.persistence.api.exceptions.ResourceNotFoundException;

@Component
public class DefaultEntryService implements EntryService {

  @Autowired
  PersistenceContext pc;

  @Autowired
  ResourceService rs;

  @Override
  public JEntry load(String id) throws ResourceNotFoundException, InvalidResourceTypeException {
    return pc.load(id, JEntry.class);
  }

  @Override
  public JEntry create(JEntry e) throws ResourceAlreadyExistsException, InternalServerError {
    addSelfLink(e);
    return pc.create(e);
  }

  @Override
  public void addFeedLink(JEntry e, JFeed f) throws InternalServerError {
    int linkIndex = hasLink(e, "feed");
    if (linkIndex != -1) {
      try {
        e.getLinks().get(1).setHref(new URI(System.getProperty("srvroot") + f.getPath()));
      } catch (URISyntaxException e1) {
        throw new InternalServerError(e1);
      }
    } else {
      JLink l = new JLinkImpl();
      try {
        l.setHref(new URI(System.getProperty("srvroot") + f.getPath()));
        l.setRel("feed");
        e.getLinks().add(l);
      } catch (URISyntaxException e1) {
        throw new InternalServerError(e1);
      }
    }
  }

  private void addSelfLink(JEntry e) throws InternalServerError {
    int linkIndex = hasLink(e, "self");
    if (linkIndex != -1) {
      try {
        e.getLinks().get(1).setHref(new URI(System.getProperty("srvroot") + e.getPath()));
      } catch (URISyntaxException e1) {
        throw new InternalServerError(e1);
      }
    } else {
      JLink l = new JLinkImpl();
      try {
        l.setHref(new URI(System.getProperty("srvroot") + e.getPath()));
        l.setRel("self");
        e.getLinks().add(l);
      } catch (URISyntaxException e1) {
        throw new InternalServerError(e1);
      }
    }
  }

  private int hasLink(JEntry e, String s) {
    ArrayList<JLink> ls = e.getLinks();
    for (int i = 0; i < ls.size(); i++) {
      if (ls.get(i).getRel().equals(s)) {
        return i;
      }
    }
    return -1;
  }

  @Override
  public JEntry delete(String id) throws ResourceNotFoundException {
    return pc.delete(id);
  }

  @Override
  public JEntry update(JEntry e) throws ResourceNotFoundException {
    String id = e.getId();
    JEntry old = null;
    System.out.println(System.getProperty("srvroot"));
    try {
      old = load(id);
    } catch (InvalidResourceTypeException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }
    old.setId(id + rs.generateArchiveSuffix());
    old.setPath("/e/" + old.getId());
    try {
      pc.create(old);
    } catch (ResourceAlreadyExistsException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }
    return pc.update(e);
  }

}
