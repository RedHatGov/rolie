
package gov.nist.rolie.polie.client.connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URL;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

public class PoliePutRequest extends PolieAbstractRequest {

  private static final Logger log = LogManager.getLogger(PoliePutRequest.class);

  String input;

  public PoliePutRequest(URL targetURL, String input) {
    super(targetURL);
    this.input = input;
  }

  @Override
  public String send() {
    Client client = ClientBuilder.newClient();
    String url = targetURL.toString();
    Response response = client.target(url).request().put(Entity.entity(input, "application/atom+xml;type=entry"));
    String responseAsString = response.readEntity(String.class);
    return responseAsString;
  }

}
