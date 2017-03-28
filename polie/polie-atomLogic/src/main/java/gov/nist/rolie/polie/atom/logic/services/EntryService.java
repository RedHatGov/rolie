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
 * SHALL NASA BE LIABLE FOR ANY DAMAGES, INCLUDING, BUT NOT LIMITED TO, DIRECT,
 * INDIRECT, SPECIAL OR CONSEQUENTIAL DAMAGES, ARISING OUT OF, RESULTING FROM, OR
 * IN ANY WAY CONNECTED WITH THIS SOFTWARE, WHETHER OR NOT BASED UPON WARRANTY,
 * CONTRACT, TORT, OR OTHERWISE, WHETHER OR NOT INJURY WAS SUSTAINED BY PERSONS OR
 * PROPERTY OR OTHERWISE, AND WHETHER OR NOT LOSS WAS SUSTAINED FROM, OR AROSE OUT
 * OF THE RESULTS OF, OR USE OF, THE SOFTWARE OR SERVICES PROVIDED HEREUNDER.
 */

package gov.nist.rolie.polie.atom.logic.services;

import gov.nist.rolie.polie.atom.logic.LinkAlreadyExistsException;
import gov.nist.rolie.polie.model.models.AtomEntry;
import gov.nist.rolie.polie.persistence.InvalidResourceTypeException;
import gov.nist.rolie.polie.persistence.ResourceAlreadyExistsException;
import gov.nist.rolie.polie.persistence.ResourceNotFoundException;

import org.w3.x2005.atom.LinkDocument.Link;

import java.net.URI;
import java.net.URISyntaxException;

public interface EntryService {

  void publishEntry(AtomEntry entry);

  AtomEntry loadEntry(URI uri) throws ResourceNotFoundException, InvalidResourceTypeException;

  AtomEntry createEntry(AtomEntry entry, URI iri)
      throws ResourceAlreadyExistsException, LinkAlreadyExistsException, URISyntaxException;

  AtomEntry updateEntry(AtomEntry entry, URI iri) throws ResourceNotFoundException, InvalidResourceTypeException;

  boolean deleteEntry(URI iri) throws ResourceNotFoundException, InvalidResourceTypeException;

  AtomEntry updateDates(AtomEntry localEntry);

  AtomEntry stripEntry(AtomEntry localEntry);

  Link hasLink(AtomEntry entry, String rel, String href);

  AtomEntry cleanEntry(AtomEntry entry);

  AtomEntry addNewEntryLink(AtomEntry entry, String rel, String href) throws LinkAlreadyExistsException;
}
