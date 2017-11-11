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

package gov.nist.jrolie.model.element;

import java.net.URI;

import gov.nist.jrolie.model.construct.AtomCommonAttributes;

/*4.2.6.  The "atom:id" Element

The "atom:id" element conveys a permanent, universally unique
identifier for an entry or feed.

atomId = element atom:id {
   atomCommonAttributes,
   (atomUri)
}

Its content MUST be an IRI, as defined by [RFC3987].  Note that the
definition of "IRI" excludes relative references.  Though the IRI
might use a dereferencable scheme, Atom Processors MUST NOT assume it
can be dereferenced.

When an Atom Document is relocated, migrated, syndicated,
republished, exported, or imported, the content of its atom:id
element MUST NOT change.  Put another way, an atom:id element
pertains to all instantiations of a particular Atom entry or feed;
revisions retain the same content in their atom:id elements.  It is
suggested that the atom:id element be stored along with the
associated resource.

The content of an atom:id element MUST be created in a way that
assures uniqueness.

Because of the risk of confusion between IRIs that would be
equivalent if they were mapped to URIs and dereferenced, the
following normalization strategy SHOULD be applied when generating
atom:id elements:

o  Provide the scheme in lowercase characters.
o  Provide the host, if any, in lowercase characters.
o  Only perform percent-encoding where it is essential.
o  Use uppercase A through F characters when percent-encoding.
o  Prevent dot-segments from appearing in paths.
o  For schemes that define a default authority, use an empty
   authority if the default is desired.
o  For schemes that define an empty path to be equivalent to a path
   of "/", use "/".
o  For schemes that define a port, use an empty port if the default
   is desired.
o  Preserve empty fragment identifiers and queries.
o  Ensure that all components of the IRI are appropriately character
   normalized, e.g., by using NFC or NFKC.

4.2.6.1.  Comparing atom:id

Instances of atom:id elements can be compared to determine whether an
entry or feed is the same as one seen before.  Processors MUST
compare atom:id elements on a character-by-character basis (in a
case-sensitive fashion).  Comparison operations MUST be based solely
on the IRI character strings and MUST NOT rely on dereferencing the
IRIs or URIs mapped from them.

As a result, two IRIs that resolve to the same resource but are not
character-for-character identical will be considered different for
the purposes of identifier comparison.

For example, these are four distinct identifiers, despite the fact
that they differ only in case:

   http://www.example.org/thing
   http://www.example.org/Thing
   http://www.EXAMPLE.org/thing
   HTTP://www.example.org/thing

Likewise, these are three distinct identifiers, because IRI
%-escaping is significant for the purposes of comparison:

   http://www.example.com/~bob
   http://www.example.com/%7ebob
   http://www.example.com/%7Ebob
*/

public class AtomId implements AtomElement {

  private AtomCommonAttributes commonattributes;

  private URI uri;

  /**
   * @param commonattributes
   * @param uri
   */
  public AtomId(AtomCommonAttributes commonattributes, URI uri) {
    super();
    this.commonattributes = commonattributes;
    this.uri = uri;
  }

  public AtomId() {
    // TODO Auto-generated constructor stub
  }

  /**
   * @return the commonattributes
   */
  public AtomCommonAttributes getCommonattributes() {
    return commonattributes;
  }

  /**
   * @return the uri
   */

  public URI getUri() {
    return uri;
  }

  public String getUriAsString() {
    return getUri().toString();
  }

  /**
   * @param commonattributes
   *          the commonattributes to set
   */
  public void setCommonattributes(AtomCommonAttributes commonattributes) {
    this.commonattributes = commonattributes;
  }

  /**
   * @param uri
   *          the uri to set
   */
  public void setUri(URI uri) {
    this.uri = uri;
  }

}
