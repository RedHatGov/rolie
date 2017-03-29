
package gov.nist.rolie.polie.client.type;

import gov.nist.rolie.polie.client.ExitStatus;

import org.apache.commons.cli.Options;

public interface Operation {
  
  public Options parseOptions(String[] args);

  public ExitStatus execute(String[] args);

  public ExitStatus execute(Options options);
}
