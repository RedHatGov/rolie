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
import gov.nist.jrolie.model.JEntry;

/**
 * Extends the service interface, providing specialized functionality for Entry
 * mangement
 *
 * @author sab3
 *
 */
public interface EntryService extends Service<JEntry> {

	/**
	 * Checks to see if the given entry has a link with Rel rel Used to drive
	 * setLink(), since that function doesnt care what the href value actually is If
	 * more than one link with Rel rel exists (It shouldn't) it picks the first one.
	 *
	 * @param e   The entry to check. Will not be modified.
	 * @param rel the rel to search for
	 * @return the index of the link in the entry link array
	 */
	int hasLink(JEntry e, String rel);

	/**
	 *
	 * Checks to see if the given entry has a link with Rel rel, and that it has a
	 * valid Href set. If more than one link with Rel rel exists (It shouldn't) it
	 * picks the first one.
	 *
	 * @param e   The entry to check. Will not be modified.
	 * @param rel The rel to search for
	 * @return the index of the link in the entry link array
	 */
	int hasValidLink(JEntry e, String rel);

	/**
	 * Given Entry e, set the Href of the link with Rel rel, to href. If no such
	 * link exists, create it. If more than one link with Rel rel exists (It
	 * shouldn't) it picks the first one.
	 *
	 * @param e    The entry to modify
	 * @param rel  The rel to "search" for, or create if none found
	 * @param href The href value Href will be set to
	 * @throws InternalServerError Shouldn't be thrown, but if it is, it's because
	 *                             of some URL generation edge case
	 */
	void setLink(JEntry e, String rel, String href) throws InternalServerError;

}
