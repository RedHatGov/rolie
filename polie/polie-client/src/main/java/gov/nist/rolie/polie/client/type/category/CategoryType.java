
package gov.nist.rolie.polie.client.type.category;

import gov.nist.rolie.polie.client.type.Operation;
import gov.nist.rolie.polie.client.type.Type;

public class CategoryType implements Type {

  /**
   * Returns the correct Operation object given a lookup String.
   */
  public Operation lookup(String operationLookupString) {
    switch (operationLookupString) {
    case "get": {
      return CategoryOperationEnum.GET.getOperation();
    }
    case "create": {
      return CategoryOperationEnum.CREATE.getOperation();
    }
    case "update": {
      return CategoryOperationEnum.UPDATE.getOperation();
    }
    case "delete": {
      return CategoryOperationEnum.DELETE.getOperation();
    }
    default:
      return null;

    }
  }
}
