package gov.nist.rolie.polie.client.type;

import java.util.function.Supplier;

@FunctionalInterface
public interface OperationFinder {
  Supplier<Operation> lookup(String name);
}
