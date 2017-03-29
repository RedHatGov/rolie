
package gov.nist.rolie.polie.client.type.resource;

public enum ResourceOperationEnum {
  GET(new ResourceGet()), CREATE(new ResourceCreate()), UPDATE(new ResourceUpdate()), DELETE(new ResourceDelete());

  private final AbstractResourceOperation operation;

  private ResourceOperationEnum(AbstractResourceOperation operation) {
    this.operation = operation;
  }

  public AbstractResourceOperation getOperation() {
    return operation;
  }

}
