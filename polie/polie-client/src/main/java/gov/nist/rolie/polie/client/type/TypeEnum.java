
package gov.nist.rolie.polie.client.type;

import gov.nist.rolie.polie.client.type.category.CategoryType;
import gov.nist.rolie.polie.client.type.entry.EntryType;
import gov.nist.rolie.polie.client.type.feed.FeedType;
import gov.nist.rolie.polie.client.type.resource.ResourceType;
import gov.nist.rolie.polie.client.type.service.ServiceType;

public enum TypeEnum {
  RESOURCE(new ResourceType()), ENTRY(new EntryType()), FEED(new FeedType()), SERVICE(new ServiceType()), CATEGORY(
      new CategoryType());

  private final Type type;

  private TypeEnum(Type type) {
    this.type = type;
  }

  public Type getType() {
    return type;
  }

  /**
   * .
   * @param lookupString
   *          lookupString
   * @return the TypeEnum
   */
  public static TypeEnum lookup(String lookupString) {
    switch (lookupString) {
    case "entry":
      return ENTRY;
    case "feed":
      return FEED;
    case "service":
      return SERVICE;
    case "category":
      return CATEGORY;
    default:
      return null;
    }
  }

}
