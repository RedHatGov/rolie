
package gov.nist.rolie.polie.client.type.category;

public enum CategoryOperationEnum {
  GET(new CategoryGet()), CREATE(new CategoryCreate()), UPDATE(new CategoryUpdate()), DELETE(new CategoryDelete());

  private final CategoryOperation operation;

  private CategoryOperationEnum(CategoryOperation operation) {
    this.operation = operation;
  }

  public CategoryOperation getOperation() {
    return operation;
  }

}
