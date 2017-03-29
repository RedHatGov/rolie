
package gov.nist.rolie.polie.client.type.feed;

public enum FeedOperationEnum {
  GET(new FeedGet()), CREATE(new FeedCreate()), UPDATE(new FeedUpdate()), DELETE(new FeedDelete());

  private final FeedOperation operation;

  private FeedOperationEnum(FeedOperation operation) {
    this.operation = operation;
  }

  public FeedOperation getOperation() {
    return operation;
  }

}
