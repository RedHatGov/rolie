
package gov.nist.rolie.polie.client.type.resource;

import gov.nist.rolie.polie.client.type.Operation;
import gov.nist.rolie.polie.client.type.Type;

public class ResourceType implements Type {
  /**
   * .
   */
  public Operation lookup(String operationLookupString) {
    switch (operationLookupString) {
    case "get": {
      return ResourceOperationEnum.GET.getOperation();
    }
    case "create": {
      return ResourceOperationEnum.CREATE.getOperation();
    }
    case "update": {
      return ResourceOperationEnum.UPDATE.getOperation();
    }
    case "delete": {
      return ResourceOperationEnum.DELETE.getOperation();
    }
    default:
      return null;

    }
  }
}
