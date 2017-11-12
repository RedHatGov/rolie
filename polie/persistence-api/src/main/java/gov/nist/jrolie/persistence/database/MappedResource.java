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

package gov.nist.jrolie.persistence.database;

import gov.nist.jrolie.model.ResourceType;
import gov.nist.jrolie.model.resource.APPResource;

public class MappedResource {

  APPResource resource;
  ResourceType type;
  String data = null;

  /**
   * @param resource
   * @param type
   */
  public MappedResource(APPResource resource, ResourceType type) {
    this.resource = resource;
    this.type = type;
  }

  public String getData() {
    return data;
  }

  public void setData(String data) {
    this.data = data;
  }

  /**
   * @return the resource
   */
  public APPResource getResource() {
    return resource;
  }

  /**
   * @return the type
   */
  public ResourceType getType() {
    return type;
  }

  /**
   * @param resource
   *          the resource to set
   */
  public void setResource(APPResource resource) {
    this.resource = resource;
  }

  /**
   * @param type
   *          the type to set
   */
  public void setType(ResourceType type) {
    this.type = type;
  }

  @Override
  public boolean equals(Object other) {
    if (!(other instanceof MappedResource)) {
      return false;
    }
    MappedResource mrOther = (MappedResource) other;
    if (mrOther.getType() != this.getType()) {
      return false;
    } else if (mrOther.getResource() != this.getResource()) {
      return false;
    }
    return true;
  }

}
