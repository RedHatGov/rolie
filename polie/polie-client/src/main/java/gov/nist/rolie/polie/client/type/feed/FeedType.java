
package gov.nist.rolie.polie.client.type.feed;

import gov.nist.rolie.polie.client.type.Operation;
import gov.nist.rolie.polie.client.type.Type;

public class FeedType implements Type {

  /**
   * .
   */
  public Operation lookup(String operationLookupString) {
    switch (operationLookupString) {
    case "get": {
      return FeedOperationEnum.GET.getOperation();
    }
    case "create": {
      return FeedOperationEnum.CREATE.getOperation();
    }
    case "update": {
      return FeedOperationEnum.UPDATE.getOperation();
    }
    case "delete": {
      return FeedOperationEnum.DELETE.getOperation();
    }
    default:
      return null;

    }
  }
}
