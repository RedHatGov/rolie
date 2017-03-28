
package gov.nist.rolie.polie.client;

public enum ExitCode {
  OK(0),
  INPUT_ERROR(-1),
  INVALID_COMMAND(-2);

  private final int statusCode;

  private ExitCode(int statusCode) {
    this.statusCode = statusCode;
  }

  /**
   * Get the related status code for use with {@link System#exit(int)}.
   * 
   * @return the statusCode
   */
  public int getStatusCode() {
    return statusCode;
  }

  public ExitStatus toExitStatus() {
    return new MessageExitStatus(this);
  }

  public ExitStatus toExitStatus(Object... messageArguments) {
    return new MessageExitStatus(this, messageArguments);
  }
}
