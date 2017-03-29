
package gov.nist.rolie.polie.client.type.service;

public enum ServiceOperationEnum {
  GET(new ServiceGet()), CREATE(new ServiceCreate()), UPDATE(new ServiceUpdate()), DELETE(new ServiceDelete());

  private final ServiceOperation operation;

  private ServiceOperationEnum(ServiceOperation operation) {
    this.operation = operation;
  }

  public ServiceOperation getOperation() {
    return operation;
  }

}
