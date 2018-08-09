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

import org.springframework.stereotype.Component;

import gov.nist.jrolie.model.JResource;
import gov.nist.jrolie.persistence.api.exceptions.InvalidResourceTypeException;
import gov.nist.jrolie.persistence.api.exceptions.ResourceAlreadyExistsException;
import gov.nist.jrolie.persistence.api.exceptions.ResourceNotFoundException;

/**
 * Provides the API used for persistent CRUD operations 
 * @author sab3
 *
 */
@Component
/**
 * 
 * @author sab3
 *
 */
public interface PersistenceContext {

	/**
	 * 
	 * @param path
	 *            A resource path as given by JAXRS, generated from the request
	 * @return The unique ID value of the resource at that path. Null if none.
	 */
	String pathToId(String path);

	/**
	 * 
	 * @param id
	 *            Unique ID value to check
	 * @return True if ID is in database, false if not
	 */
	boolean idExists(String id);

	/**
	 * 
	 * @param id
	 *            Unique ID value of resource to load
	 * @param clazz
	 *            Expected class of resource
	 * @return The Resource associated with the given ID
	 * @throws InvalidResourceTypeException
	 *             Thrown if expected class does not match actual class
	 * 
	 * @throws ResourceNotFoundException
	 *             Thrown if there is no resource associated with this id
	 */
	<X extends JResource> X load(String id, Class<X> clazz)
			throws InvalidResourceTypeException, ResourceNotFoundException;

	/**
	 * 
	 * @param resource
	 *            The resource to create
	 * @return The resource exactly as it was created
	 * @throws ResourceAlreadyExistsException
	 *             Thrown if another resource with the same ID already exists.
	 */
	<X extends JResource> X create(X resource) throws ResourceAlreadyExistsException;

	/**
	 * 
	 * @param id
	 *            The ID value to delete from the database
	 * @return The deleted resource
	 * @throws ResourceNotFoundException
	 *             Thrown if there is no resource associated with this id
	 */
	<X extends JResource> X delete(String id) throws ResourceNotFoundException;

	/**
	 * 
	 * @param r
	 *            The resource to update
	 * @return The old resource at this ID value.
	 * @throws ResourceNotFoundException
	 *             Thrown if there is no resource associated with this id
	 */
	<X extends JResource> X update(X r) throws ResourceNotFoundException;
}
