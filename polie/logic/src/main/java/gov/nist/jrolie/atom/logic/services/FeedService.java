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
import gov.nist.jrolie.atom.logic.MismatchedCategoriesException;
import gov.nist.jrolie.model.JEntry;
import gov.nist.jrolie.model.JFeed;
import gov.nist.jrolie.persistence.api.exceptions.InvalidResourceTypeException;
import gov.nist.jrolie.persistence.api.exceptions.ResourceAlreadyExistsException;
import gov.nist.jrolie.persistence.api.exceptions.ResourceNotFoundException;

public interface FeedService extends Service<JFeed> {

	/**
	 * Adds an Entry to the feed. Beyond just adding the entry to the array of
	 * Entries, the entry and the feed are both modified as part of this process.
	 * The Entry gets a rel="feed" link pointing to the feed.
	 *
	 * If strict category matching is desired, the category check will happen here.
	 * The Entry needs to have at least one explicitly defined category that matches
	 * at least one of the Feed's categories.
	 *
	 * @param f The feed that the entry will be added to.
	 * @param e The entry to add to the feed
	 * @throws InternalServerError           TODO: Shouldn't be thrown, but
	 *                                       sometimes is by malformed XML.
	 * @throws MismatchedCategoriesException Throws if strict category matching is
	 *                                       on and the entry has no matching
	 *                                       categories
	 */
	void addEntry(JFeed f, JEntry e) throws InternalServerError, MismatchedCategoriesException;

	/**
	 *
	 * Generates and saves an archive version of a feed. This should be called
	 * before any important changes to the feed. if the archive mode is set. The
	 * archives create a linked list through the prev-archive, next-archive, and
	 * current link relation. This means that this function causes changes to the
	 * feed passed to it, and the feeds it links to, if any
	 *
	 * @param f The feed to archive
	 * @return The archived feed. In "FULL" archive mode this is the entire feed.
	 * @throws InternalServerError
	 * @throws ResourceNotFoundException      Thrown if any feeds linked to by the
	 *                                        archive links does not exist
	 * @throws InvalidResourceTypeException
	 * @throws ResourceAlreadyExistsException Thrown if the generated archive ID
	 *                                        matches an existing ID. This only
	 *                                        happens when the archive suffix fails
	 *                                        to generate an appropriately random
	 *                                        ID, or if there is a collision.
	 */
	JFeed archive(JFeed f) throws InternalServerError, ResourceNotFoundException, InvalidResourceTypeException,
			ResourceAlreadyExistsException;

}
