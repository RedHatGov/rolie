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

import gov.nist.jrolie.atom.logic.InternalServerError;
import gov.nist.jrolie.persistence.api.exceptions.InvalidResourceTypeException;
import gov.nist.jrolie.persistence.api.exceptions.ResourceAlreadyExistsException;
import gov.nist.jrolie.persistence.api.exceptions.ResourceNotFoundException;

/**
 *
 * Provides a layer of functionality on top of the persistence context, and
 * serves as the base interface for the other service classes.
 *
 * @author sab3
 *
 * @param <X> The class of object this service will be dealing with
 */
public interface Service<X> {

	/**
	 * 
	 * Creates a resource in the persistence context. The ID is the primary
	 * identifier used in this process.
	 * 
	 * @param resource A resource of type X to create
	 * @return The resource as it was created. This is fed upwards from the
	 *         persistence context to make sure that the service layer is aware if
	 *         any changes were made.
	 * @throws ResourceAlreadyExistsException Thrown if a resource with the same ID
	 *                                        already exists
	 * @throws InternalServerError            Really shouldn't happen, but you know
	 */
	X create(X resource) throws ResourceAlreadyExistsException, InternalServerError;

	/**
	 * 
	 * Deletes the resource with ID id
	 * 
	 * @param id
	 * @return The resource that was deleted, not often needed
	 * @throws ResourceNotFoundException Thrown if there is no resource with ID id
	 * @throws InternalServerError       Really shouldn't happen, but you know
	 */
	X delete(String id) throws ResourceNotFoundException, InternalServerError;

	/**
	 * 
	 * Load the resource from the persistence context with the given ID
	 * 
	 * @param id
	 * @return THe resource with ID id and type X
	 * @throws ResourceNotFoundException    Thrown if no resource exists with ID id
	 * @throws InvalidResourceTypeException Thrown if the loaded resource doesnt
	 *                                      match expected type X
	 * @throws InternalServerError          Really shouldn't happen, but you know
	 */
	X load(String id) throws ResourceNotFoundException, InvalidResourceTypeException, InternalServerError;

	/**
	 * 
	 * Update a resource. This often includes functionality beyond simply saving the
	 * resource again, such as updating date fields or executing archiving.
	 * 
	 * @param resource The resource to update
	 * @return The resource as it was created. This is fed upwards from the
	 *         persistence context to make sure that the service layer is aware if
	 *         any changes were made.
	 * @throws ResourceNotFoundException Thrown if this resource (with this ID)
	 *                                   exists in the database
	 * @throws InternalServerError       Really shouldn't happen, but you know
	 */
	X update(X resource) throws ResourceNotFoundException, InternalServerError;

}
