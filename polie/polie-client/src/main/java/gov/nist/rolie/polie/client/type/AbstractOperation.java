
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

import java.util.logging.Level;

public abstract class AbstractOperation implements Operation {
  private static final String OPTION_DEBUG = "debug";
  private static final String OPTION_QUIET = "quiet";
  private static final String OPTION_VERSION = "version";
  private static final String OPTION_HELP = "h";

  private String target;

  public String getTarget() {
    return this.target;
  }

  @Override
  public ExitStatus execute(CommandLine options) {
    return ExitCode.OK.toExitStatus();
  }

  @Override
  public CommandLine parseOptions(String[] args) {
    CommandLineParser parser = new DefaultParser();

    ParseException parseException = null;
    CommandLine cmd;
    try {
      cmd = parser.parse(getOptions(), args);
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
      doShowHelp();
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
        DecimaLoggingConfigurationFactory.changeRootLogLevel(Level.DEBUG);
      } else if (cmd.hasOption(OPTION_QUIET)) {
        DecimaLoggingConfigurationFactory.changeRootLogLevel(Level.FATAL);
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
