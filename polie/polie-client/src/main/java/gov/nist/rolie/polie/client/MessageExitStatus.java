
package gov.nist.rolie.polie.client;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MessageExitStatus extends AbstractExitStatus {
  private final List<Object> messageArguments;

  /**
   * Construct a new {@link ExitStatus} based on an array of message arguments.
   * 
   * @param code
   *          the exit code to use.
   * @param messageArguments
   *          the arguments that can be passed to a formatted string to generate the message
   */
  public MessageExitStatus(ExitCode code, Object... messageArguments) {
    super(code);
    if (messageArguments == null || messageArguments.length == 0) {
      this.messageArguments = Collections.emptyList();
    } else {
      this.messageArguments = Arrays.asList(messageArguments);
    }
  }

  public String getMessage() {
    String message = lookupMessageForCode(getExitCode());
    return String.format(message, messageArguments);
  }

  private String lookupMessageForCode(ExitCode exitCode) {
    // TODO: add message bundle support
    StringBuilder builder = new StringBuilder();
    builder.append(getExitCode()).append(":");
    for (int index = 1; index <= messageArguments.size(); index++) {
      builder.append(" $");
      builder.append(index);
    }
    return builder.toString();
  }
}
