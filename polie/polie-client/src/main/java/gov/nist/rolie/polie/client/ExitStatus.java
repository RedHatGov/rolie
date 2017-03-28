
package gov.nist.rolie.polie.client;

public interface ExitStatus {
  String getMessage();
  
  /**
   * Get the related status code for use with {@link System#exit(int)}.
   * 
   * @return the statusCode
   */
  ExitCode getExitCode();
}
