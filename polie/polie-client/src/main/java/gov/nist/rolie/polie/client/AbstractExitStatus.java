package gov.nist.rolie.polie.client;

import java.util.Objects;

public abstract class AbstractExitStatus implements ExitStatus {
  private final ExitCode exitCode;
  
  public AbstractExitStatus(ExitCode exitCode) {
    Objects.requireNonNull(exitCode, "exitCode");
    this.exitCode = exitCode;
  }

  @Override
  public ExitCode getExitCode() {
    return exitCode;
  }

}
