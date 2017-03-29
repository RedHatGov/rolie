
package gov.nist.rolie.polie.client.type.entry;

import gov.nist.rolie.polie.client.type.Operation;
import gov.nist.rolie.polie.client.type.Type;

public class EntryType implements Type {
  /**
   * Returns the correct Operation object given a lookupString.
   */
  public Operation lookup(String operationLookupString) {
    switch (operationLookupString) {
    case "get": {
      return EntryOperationEnum.GET.getOperation();
    }
    case "create": {
      return EntryOperationEnum.CREATE.getOperation();
    }
    case "update": {
      return EntryOperationEnum.UPDATE.getOperation();
    }
    case "delete": {
      return EntryOperationEnum.DELETE.getOperation();
    }
    default:
      return null;

    }
  }
}
