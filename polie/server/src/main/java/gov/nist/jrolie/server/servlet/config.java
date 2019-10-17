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

package gov.nist.jrolie.server.servlet;

/**
 *
 * Configuration file for customizing default server organization.
 *
 *
 * @author sab3
 *
 */
public class config {

	/**
	 * The root of the server. This will vary based on your server implementation,
	 * library usage, etc
	 */
	public static final String SERVER_ROOT = "http://localhost:8080/server/rolie"; // TODO: Find a better way to do this

	// All standalone entry documents will be in SERVER_ROOT+ENTRY_PREFIX
	public static final String ENTRY_PREFIX = "/e/";

	// All feed documents will be in SERVER_ROOT+FEED_PREFIX
	public static final String FEED_PREFIX = "/f/";

	/**
	 * All service documents will be in SERVER_ROOT+SERVICE_PREFIX Every service
	 * document is in a one-to-one relationship with a ROLIE repository.
	 **/
	public static final String SERVICE_PREFIX = "/s/";

	// All standalaone categories documents will be in SERVER_ROOT+CAT_PREFIX
	public static final String CAT_PREFIX = "/c/";

	/**
	 * Should the server reject entries that have mis-matched categories?
	 */
	public static final String STRICT_CATEGORY_MATCHING = "FALSE";

	/**
	 * Sets the mode for automatic archiving of feeds.
	 *
	 * Valid settings: "NONE" - No archiving will happen "FULL" - Any updated to the
	 * feed will cause a full archive to be stored "HEADERONLY" - Only metadata
	 * updates trigger archiving. NOT IMPLEMENTED "TIMED" - Archive at certain time
	 * intervals. NOT IMPLMENTED
	 *
	 */
	public static final String FEED_ARCHIVE_MODE = "FULL";

	/**
	 * Sets the mode for automatic archiving of entries.
	 *
	 * Valid settings: "NONE" - No archiving will happen "FULL" - Any update to the
	 * entry will cause a full archive to be stored "TIMED" - Archive at certain
	 * time intervals. NOT IMPLMENTED
	 *
	 */
	public static final String ENTRY_ARCHIVE_MODE = "FULL";

	/**
	 * Should non-ROLIE requests be passed thru this server or handled elsewhere?
	 * When this is false the server only picks up requests from the set prefixes
	 * above.
	 *
	 * When this is true requests to paths not matching the prefixes are handled by
	 * this server. Data posted to URLs will be stored as text, and can be GETed as
	 * text
	 */
	public static final String MANAGE_DATA = "FALSE";
}
