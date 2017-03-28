/**
 * Portions of this software was developed by employees of the National Institute
 * of Standards and Technology (NIST), an agency of the Federal Government.
 * Pursuant to title 17 United States Code Section 105, works of NIST employees are
 * not subject to copyright protection in the United States and are considered to
 * be in the public domain. Permission to freely use, copy, modify, and distribute
 * this software and its documentation without fee is hereby granted, provided that
 * this notice and disclaimer of warranty appears in all copies.
 *
 * THE SOFTWARE IS PROVIDED 'AS IS' WITHOUT ANY WARRANTY OF ANY KIND, EITHER
 * EXPRESSED, IMPLIED, OR STATUTORY, INCLUDING, BUT NOT LIMITED TO, ANY WARRANTY
 * THAT THE SOFTWARE WILL CONFORM TO SPECIFICATIONS, ANY IMPLIED WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, AND FREEDOM FROM
 * INFRINGEMENT, AND ANY WARRANTY THAT THE DOCUMENTATION WILL CONFORM TO THE
 * SOFTWARE, OR ANY WARRANTY THAT THE SOFTWARE WILL BE ERROR FREE. IN NO EVENT
 * SHALL NIST BE LIABLE FOR ANY DAMAGES, INCLUDING, BUT NOT LIMITED TO, DIRECT,
 * INDIRECT, SPECIAL OR CONSEQUENTIAL DAMAGES, ARISING OUT OF, RESULTING FROM, OR
 * IN ANY WAY CONNECTED WITH THIS SOFTWARE, WHETHER OR NOT BASED UPON WARRANTY,
 * CONTRACT, TORT, OR OTHERWISE, WHETHER OR NOT INJURY WAS SUSTAINED BY PERSONS OR
 * PROPERTY OR OTHERWISE, AND WHETHER OR NOT LOSS WAS SUSTAINED FROM, OR AROSE OUT
 * OF THE RESULTS OF, OR USE OF, THE SOFTWARE OR SERVICES PROVIDED HEREUNDER.
 */

package gov.nist.rolie.polie.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CLI {
  public static void main(String[] args) {
    new CLI().process(args);
  }

  /**
   * Process a set of CLI arguments.
   * 
   * @param args
   *          the arguments to process
   */
  public void process(String[] args) {
    ExitStatus status = parseCommand(args);
    System.err.println(status.getMessage());
    System.exit(status.getExitCode().getStatusCode());
  }

  private ExitStatus parseCommand(String line) {
    String[] args = line.split("\\s");
    return parseCommand(args);
  }

  private ExitStatus parseCommand(String[] args) {
    ExitStatus status;
    // the first two arguments should be the <type> and <operation>, where <type> is the object type
    // the <operation> is performed against.
    if (args.length < 1) {
      status = ExitCode.INVALID_COMMAND.toExitStatus();
    } else if ("interactive".equals(args[0].toLowerCase())) {
      status = processInteractive();
    } else if (args.length < 2) {
      status = ExitCode.INVALID_COMMAND.toExitStatus();
    } else {
      status = processCommand(args);
    }
    return status;
  }

  private ExitStatus processCommand(String[] args) {
    // String typeString = args[0];
    // String operationString = args[1];
    //
    // TODO: add calls to setup the command execution
    // Type type = TypeEnum.lookup(typeString).getType();
    // Operation operation = type.lookup(operationString);
    // Options options = operation.parseOptions(options);
    //
    // return operation.execute(options);
    return ExitCode.OK.toExitStatus();
  }

  private ExitStatus processInteractive() {
    ExitStatus status = null;
    try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
      String line;
      while ((line = in.readLine()) != null) {
        status = parseCommand(line);
      }
    } catch (IOException e) {
      status = ExitCode.INPUT_ERROR.toExitStatus();
    }
    if (status == null) {
      status = ExitCode.OK.toExitStatus();
    }
    return status;
  }
}
