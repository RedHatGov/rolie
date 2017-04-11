
package gov.nist.rolie.polie.client.type;

import java.net.MalformedURLException;
import java.net.URL;

public class AbstractTargetedOperation extends AbstractOperation {
  private URL target;

  public URL getTarget() throws MalformedURLException {
    return this.target;
  }
}
