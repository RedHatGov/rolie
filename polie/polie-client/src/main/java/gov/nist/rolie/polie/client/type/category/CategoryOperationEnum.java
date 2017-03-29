
package gov.nist.rolie.polie.client.type.category;

public enum CategoryOperationEnum {
  GET(new CategoryGet()), CREATE(new CategoryCreate()), UPDATE(new CategoryUpdate()), DELETE(new CategoryDelete());

  private final AbstractCategoryOperation operation;

  private CategoryOperationEnum(AbstractCategoryOperation operation) {
    this.operation = operation;
  }

  public AbstractCategoryOperation getOperation() {
    return operation;
  }

}
