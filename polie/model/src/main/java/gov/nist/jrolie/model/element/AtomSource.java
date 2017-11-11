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

import java.util.ArrayList;

import gov.nist.jrolie.model.construct.AtomCommonAttributes;
import gov.nist.jrolie.model.construct.AtomPerson;

/*
 * 4.2.11.  The "atom:source" Element

   If an atom:entry is copied from one feed into another feed, then the
   source atom:feed's metadata (all child elements of atom:feed other
   than the atom:entry elements) MAY be preserved within the copied
   entry by adding an atom:source child element, if it is not already
   present in the entry, and including some or all of the source feed's
   Metadata elements as the atom:source element's children.  Such
   metadata SHOULD be preserved if the source atom:feed contains any of
   the child elements atom:author, atom:contributor, atom:rights, or
   atom:category and those child elements are not present in the source
   atom:entry.

   atomSource =
      element atom:source {
         atomCommonAttributes,
         (atomAuthor*
          & atomCategory*
          & atomContributor*
          & atomGenerator?
          & atomIcon?
          & atomId?
          & atomLink*
          & atomLogo?
          & atomRights?
          & atomSubtitle?
          & atomTitle?
          & atomUpdated?
          & extensionElement*)
      }

   The atom:source element is designed to allow the aggregation of
   entries from different feeds while retaining information about an
   entry's source feed.  For this reason, Atom Processors that are
   performing such aggregation SHOULD include at least the required
   feed-level Metadata elements (atom:id, atom:title, and atom:updated)
   in the atom:source element.
 */

public class AtomSource implements AtomElement {

  private AtomCommonAttributes comattr;

  private ArrayList<AtomPerson> authors;
  private ArrayList<AtomCategory> categories;
  private ArrayList<AtomPerson> contributors;
  private AtomGenerator generator;
  private AtomId id;
  private ArrayList<AtomLink> links;
  private String rights;
  private String title;
  private String subtitle;
  private String updated;

  /**
   * @param comattr
   * @param authors
   * @param categories
   * @param contributors
   * @param generator
   * @param id
   * @param links
   * @param rights
   * @param title
   * @param subtitle
   * @param updated
   */
  public AtomSource(AtomCommonAttributes comattr, ArrayList<AtomPerson> authors, ArrayList<AtomCategory> categories,
      ArrayList<AtomPerson> contributors, AtomGenerator generator, AtomId id, ArrayList<AtomLink> links, String rights,
      String title, String subtitle, String updated) {
    super();
    this.comattr = comattr;
    this.authors = authors;
    this.categories = categories;
    this.contributors = contributors;
    this.generator = generator;
    this.id = id;
    this.links = links;
    this.rights = rights;
    this.title = title;
    this.subtitle = subtitle;
    this.updated = updated;
  }

  /**
   * @return the comattr
   */
  public AtomCommonAttributes getComattr() {
    return comattr;
  }

  /**
   * @return the authors
   */
  public ArrayList<AtomPerson> getAuthors() {
    return authors;
  }

  /**
   * @return the categories
   */
  public ArrayList<AtomCategory> getCategories() {
    return categories;
  }

  /**
   * @return the contributors
   */
  public ArrayList<AtomPerson> getContributors() {
    return contributors;
  }

  /**
   * @return the generator
   */
  public AtomGenerator getGenerator() {
    return generator;
  }

  /**
   * @return the id
   */
  public AtomId getId() {
    return id;
  }

  /**
   * @return the links
   */
  public ArrayList<AtomLink> getLinks() {
    return links;
  }

  /**
   * @return the rights
   */
  public String getRights() {
    return rights;
  }

  /**
   * @return the title
   */
  public String getTitle() {
    return title;
  }

  /**
   * @return the subtitle
   */
  public String getSubtitle() {
    return subtitle;
  }

  /**
   * @return the updated
   */
  public String getUpdated() {
    return updated;
  }

  /**
   * @param comattr
   *          the comattr to set
   */
  public void setComattr(AtomCommonAttributes comattr) {
    this.comattr = comattr;
  }

  /**
   * @param authors
   *          the authors to set
   */
  public void setAuthors(ArrayList<AtomPerson> authors) {
    this.authors = authors;
  }

  /**
   * @param categories
   *          the categories to set
   */
  public void setCategories(ArrayList<AtomCategory> categories) {
    this.categories = categories;
  }

  /**
   * @param contributors
   *          the contributors to set
   */
  public void setContributors(ArrayList<AtomPerson> contributors) {
    this.contributors = contributors;
  }

  /**
   * @param generator
   *          the generator to set
   */
  public void setGenerator(AtomGenerator generator) {
    this.generator = generator;
  }

  /**
   * @param id
   *          the id to set
   */
  public void setId(AtomId id) {
    this.id = id;
  }

  /**
   * @param links
   *          the links to set
   */
  public void setLinks(ArrayList<AtomLink> links) {
    this.links = links;
  }

  /**
   * @param rights
   *          the rights to set
   */
  public void setRights(String rights) {
    this.rights = rights;
  }

  /**
   * @param title
   *          the title to set
   */
  public void setTitle(String title) {
    this.title = title;
  }

  /**
   * @param subtitle
   *          the subtitle to set
   */
  public void setSubtitle(String subtitle) {
    this.subtitle = subtitle;
  }

  /**
   * @param updated
   *          the updated to set
   */
  public void setUpdated(String updated) {
    this.updated = updated;
  }

}
