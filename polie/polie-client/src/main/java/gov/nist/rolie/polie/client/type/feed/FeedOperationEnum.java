
package gov.nist.rolie.polie.client.type.feed;

public enum FeedOperationEnum {
  GET(new FeedGet()), CREATE(new FeedCreate()), UPDATE(new FeedUpdate()), DELETE(new FeedDelete());

  private final AbstractFeedOperation operation;

  private FeedOperationEnum(AbstractFeedOperation operation) {
    this.operation = operation;
  }

  public AbstractFeedOperation getOperation() {
    return operation;
  }

}
