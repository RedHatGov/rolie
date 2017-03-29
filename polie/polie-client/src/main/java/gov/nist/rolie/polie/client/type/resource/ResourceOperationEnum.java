
package gov.nist.rolie.polie.client.type.resource;

public enum ResourceOperationEnum {
  GET(new ResourceGet()), CREATE(new ResourceCreate()), UPDATE(new ResourceUpdate()), DELETE(new ResourceDelete());

  private final ResourceOperation operation;

  private ResourceOperationEnum(ResourceOperation operation) {
    this.operation = operation;
  }

  public ResourceOperation getOperation() {
    return operation;
  }

}
