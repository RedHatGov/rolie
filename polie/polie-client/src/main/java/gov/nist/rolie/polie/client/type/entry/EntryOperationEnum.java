
package gov.nist.rolie.polie.client.type.entry;

public enum EntryOperationEnum {
  GET(new EntryGet()), CREATE(new EntryCreate()), UPDATE(new EntryUpdate()), DELETE(new EntryDelete());

  private final EntryOperation operation;

  private EntryOperationEnum(EntryOperation operation) {
    this.operation = operation;
  }

  public EntryOperation getOperation() {
    return operation;
  }

}
