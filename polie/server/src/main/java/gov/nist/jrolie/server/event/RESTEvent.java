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

package gov.nist.jrolie.server.event;

import java.util.Map;

import javax.ws.rs.core.Response.ResponseBuilder;

import gov.nist.jrolie.server.visitors.RESTEventVisitor;

/**
 * Each RESTEvent object represents one "REST Event". This /should/ be a RESTful
 * request (ex. GET POST PUT DELETE). This event object contains the base level
 * information about the request: HTTP headers and a break down of the request
 * URI.
 *
 * Each visitor represents a group of operations that are executed against this
 * information. When a RESTEvent accepts a visitor, some operation will occur
 * that may or may not result in changes being made to the response builder and
 * the data map.
 *
 * @author sab3
 *
 */
public interface RESTEvent {

	/**
	 * Execute the given visitor in the context of this REST operation.
	 *
	 * @param visitor The visitor to be executed, these are provided by the
	 *                gov.nist.jrolie.server.visitors package.
	 * @param rb      A persistence response builder object. Each visitor MAY change
	 *                the data stored in this object as a way to modify the eventual
	 *                response.
	 * @param data    A keyed map of objects to allow persistence of miscellaneous
	 *                information between visitors
	 * @return Will return false if the response should be sent right away. This
	 *         could be becuase of an error, or if the visitor has completed all
	 *         needed processing.
	 */
	public boolean accept(RESTEventVisitor visitor, ResponseBuilder rb, Map<String, Object> data);

}
