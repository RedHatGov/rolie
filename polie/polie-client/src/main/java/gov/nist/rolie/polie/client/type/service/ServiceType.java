
package gov.nist.rolie.polie.client.type.service;

import gov.nist.rolie.polie.client.type.Operation;
import gov.nist.rolie.polie.client.type.Type;

public class ServiceType implements Type {
  /**
   * .
   */
  public Operation lookup(String operationLookupString) {
    switch (operationLookupString) {
    case "get": {
      return ServiceOperationEnum.GET.getOperation();
    }
    case "create": {
      return ServiceOperationEnum.CREATE.getOperation();
    }
    case "update": {
      return ServiceOperationEnum.UPDATE.getOperation();
    }
    case "delete": {
      return ServiceOperationEnum.DELETE.getOperation();
    }
    default:
      return null;

    }
  }
}
