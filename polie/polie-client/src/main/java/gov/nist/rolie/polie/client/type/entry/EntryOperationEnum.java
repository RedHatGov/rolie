
package gov.nist.rolie.polie.client.type.entry;

public enum EntryOperationEnum {
  GET(new EntryGet()), CREATE(new EntryCreate()), UPDATE(new EntryUpdate()), DELETE(new EntryDelete());

  private final AbstractEntryOperation operation;

  private EntryOperationEnum(AbstractEntryOperation operation) {
    this.operation = operation;
  }

  public AbstractEntryOperation getOperation() {
    return operation;
  }

}
