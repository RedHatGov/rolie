package gov.nist.rolie.polie.client.type;

import java.util.function.Supplier;

public interface OperationSupplier {
  Supplier<Operation> supplier();
}
