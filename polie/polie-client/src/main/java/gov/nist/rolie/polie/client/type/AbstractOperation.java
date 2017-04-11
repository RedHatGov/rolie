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

package gov.nist.rolie.polie.client.type;

import gov.nist.rolie.polie.client.ExitCode;
import gov.nist.rolie.polie.client.ExitStatus;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionGroup;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public abstract class AbstractOperation implements Operation {
  private static final String OPTION_DEBUG = "debug";
  private static final String OPTION_QUIET = "quiet";
  private static final String OPTION_VERSION = "version";
  private static final String OPTION_HELP = "h";


  protected AbstractOperation(String[] args) throws ParseException {
    CommandLine cmd = parseOptions(args);

    handleOptions(cmd);
  }

  protected abstract void handleOptions(CommandLine cmd) throws ParseException;

  @Override
  public ExitStatus execute() {
    return ExitCode.OK.toExitStatus();
  }

  protected CommandLine parseOptions(String[] args) throws ParseException {
    Options options = getOptions();
    CommandLineParser parser = new DefaultParser();

    ParseException parseException = null;
    CommandLine cmd;
    try {
      cmd = parser.parse(options, args);
    } catch (ParseException e) {
      parseException = e;
      cmd = null;
    }

    if (cmd != null) {
      if (!validateOptions(cmd)) {
        cmd = null;
      }
    }

    CommandLine retval;
    if (cmd == null || cmd.hasOption(OPTION_HELP)) {
      doShowHelp("fixme", options);
      if (cmd == null) {
        if (parseException != null) {
          throw parseException;
        }
        // I've seen parseException null here if user provided options don't match
        // .addAllowedValue()
        throw new ParseException("There was a problem with the command line input.");
      } else {
        retval = null;
      }
    } else if (cmd.hasOption(OPTION_VERSION)) {
      retval = doShowVersion(cmd);
    } else {
      retval = cmd;
      if (cmd.hasOption(OPTION_DEBUG)) {
        // TODO: verbose logging configuration
//        DecimaLoggingConfigurationFactory.changeRootLogLevel(Level.DEBUG);
      } else if (cmd.hasOption(OPTION_QUIET)) {
        // TODO: limited logging configuration
//        DecimaLoggingConfigurationFactory.changeRootLogLevel(Level.FATAL);
      }
    }

    // TODO Auto-generated method stub
    return null;
  }

  protected CommandLine doShowVersion(CommandLine cmd) {
    String version = getVersion();
    if (version == null) {
      version = "unknown";
    }
    System.out.println("Version " + version);
    return null;
  }

  private String getVersion() {
    // TODO: add a class that gets the project version
    return "add support for this";
  }

  protected void doShowHelp(String cmdLineSyntax, Options options) {
    HelpFormatter formatter = new HelpFormatter();
    formatter.printHelp(cmdLineSyntax, options);
  }

  protected boolean validateOptions(CommandLine cmd) {
    // TODO Auto-generated method stub
    return false;
  }

  protected Options getOptions() {
    Options options = new Options();
    Option debugOption = Option.builder(OPTION_DEBUG).desc("Enable verbose output").build();

    Option quietOption = Option.builder(OPTION_QUIET).desc("Silence console output").build();

    OptionGroup outputControlGroup = new OptionGroup().addOption(debugOption).addOption(quietOption);

    Option versionOption = Option.builder(OPTION_VERSION).desc("Display the version of the tool").build();

    Option helpOption = Option.builder(OPTION_HELP).longOpt("help").desc("Display the available cli arguments").build();

    options.addOptionGroup(outputControlGroup).addOption(versionOption).addOption(helpOption);

    return options;
  }

}
