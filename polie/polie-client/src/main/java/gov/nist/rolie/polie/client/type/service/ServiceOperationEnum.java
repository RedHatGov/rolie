
package gov.nist.rolie.polie.client.type.service;

public enum ServiceOperationEnum {
  GET(new ServiceGet()), CREATE(new ServiceCreate()), UPDATE(new ServiceUpdate()), DELETE(new ServiceDelete());

  private final AbstractServiceOperation operation;

  private ServiceOperationEnum(AbstractServiceOperation operation) {
    this.operation = operation;
  }

  public AbstractServiceOperation getOperation() {
    return operation;
  }

}
