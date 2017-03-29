
package gov.nist.rolie.polie.client.type.entry;

import gov.nist.rolie.polie.client.ExitCode;
import gov.nist.rolie.polie.client.ExitStatus;
import gov.nist.rolie.polie.client.type.AbstractOperation;
import gov.nist.rolie.polie.client.type.Operation;

import org.apache.commons.cli.Options;

public abstract class AbstractEntryOperation extends AbstractOperation {

  @Override
  public Options parseOptions(String[] args) {
    return new Options();
  }

  @Override
  public ExitStatus execute(String[] args) {
    return execute(parseOptions(args));
  }

  @Override
  public ExitStatus execute(Options options) {
    return ExitCode.OK.toExitStatus();
  }

}
