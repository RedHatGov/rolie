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

package gov.nist.rolie.polie.persistence.database;

import gov.nist.rolie.polie.model.ResourceType;
import gov.nist.rolie.polie.model.models.APPCategories;
import gov.nist.rolie.polie.model.models.APPResource;
import gov.nist.rolie.polie.model.models.APPServiceDocument;
import gov.nist.rolie.polie.model.models.AtomEntry;
import gov.nist.rolie.polie.model.models.AtomFeed;
import gov.nist.rolie.polie.persistence.InvalidResourceTypeException;
import gov.nist.rolie.polie.persistence.ResourceAlreadyExistsException;
import gov.nist.rolie.polie.persistence.ResourceNotFoundException;

import java.net.URI;

public interface PersistenceMethod {

  // Utility methods
  /**
   * 
   * @param iri
   * @return the type
   * @throws ResourceNotFoundException
   *           if the resource identified by the iri parameter does not exist
   */
  ResourceType identifyResouceType(URI iri) throws ResourceNotFoundException;

  ResourceType identifyResouceType(String id) throws ResourceNotFoundException;

  boolean resourceExists(URI iri);

  boolean resourceExists(String id);

  boolean resourceExists(APPResource resource, ResourceType type);

  // General resource methods. Use carefully
  APPResource loadResource(URI iri) throws ResourceNotFoundException;

  APPResource loadResource(String id) throws ResourceNotFoundException;

  APPResource createResource(APPResource resource, URI uri) throws ResourceAlreadyExistsException;

  APPResource createResource(APPResource resource, String id) throws ResourceAlreadyExistsException;

  APPResource updateResource(APPResource resource, URI uri) throws ResourceNotFoundException;

  APPResource updateResource(APPResource resource, String id) throws ResourceNotFoundException;

  boolean deleteResource(URI uri) throws ResourceNotFoundException;

  boolean deleteResource(String id) throws ResourceNotFoundException;

  // Service Document Methods
  APPServiceDocument loadServiceDocument(URI iri) throws ResourceNotFoundException, InvalidResourceTypeException;

  APPServiceDocument createServiceDocument(APPServiceDocument serviceDoc, URI uri)
      throws ResourceAlreadyExistsException;

  APPServiceDocument createServiceDocument(APPServiceDocument serviceDoc, String uri)
      throws ResourceAlreadyExistsException;

  APPServiceDocument updateServiceDocument(APPServiceDocument serviceDoc, URI uri)
      throws ResourceNotFoundException, InvalidResourceTypeException;

  boolean deleteServiceDocument(URI uri) throws ResourceNotFoundException, InvalidResourceTypeException;

  // Category Document Methods
  APPCategories loadCategoryDocument(URI iri) throws ResourceNotFoundException, InvalidResourceTypeException;

  APPCategories createCategoryDocument(APPCategories categoryDoc, URI uri) throws ResourceAlreadyExistsException;

  APPCategories updateCategoryDocument(APPCategories categoryDoc, URI uri)
      throws ResourceNotFoundException, InvalidResourceTypeException;

  boolean deleteCategoryDocument(URI uri) throws ResourceNotFoundException, InvalidResourceTypeException;

  // Feed methods
  AtomFeed loadFeed(URI iri) throws ResourceNotFoundException, InvalidResourceTypeException;

  AtomFeed loadFeed(String id) throws ResourceNotFoundException, InvalidResourceTypeException;

  AtomFeed createFeed(AtomFeed feed, URI uri) throws ResourceAlreadyExistsException;

  AtomFeed createFeed(AtomFeed feed, String id) throws ResourceAlreadyExistsException;

  AtomFeed updateFeed(AtomFeed feed, URI uri) throws ResourceNotFoundException, InvalidResourceTypeException;

  AtomFeed updateFeed(AtomFeed feed, String id) throws ResourceNotFoundException, InvalidResourceTypeException;

  boolean deleteFeed(URI uri) throws ResourceNotFoundException, InvalidResourceTypeException;

  boolean deleteFeed(String id) throws ResourceNotFoundException, InvalidResourceTypeException;

  // Entry methods
  AtomEntry loadEntry(URI iri) throws ResourceNotFoundException, InvalidResourceTypeException;

  AtomEntry loadEntry(String id) throws ResourceNotFoundException, InvalidResourceTypeException;

  AtomEntry createEntry(AtomEntry entry, URI uri) throws ResourceAlreadyExistsException;

  AtomEntry createEntry(AtomEntry entry, String id) throws ResourceAlreadyExistsException;

  AtomEntry updateEntry(AtomEntry entry, URI uri) throws ResourceNotFoundException, InvalidResourceTypeException;

  AtomEntry updateEntry(AtomEntry entry, String id) throws ResourceNotFoundException, InvalidResourceTypeException;

  boolean deleteEntry(URI uri) throws ResourceNotFoundException, InvalidResourceTypeException;

  boolean deleteEntry(String id) throws ResourceNotFoundException, InvalidResourceTypeException;

  String generateNewEntryID(AtomEntry entry);

}
