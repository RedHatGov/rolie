
package gov.nist.rolie.polie.client.connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public abstract class PolieAbstractRequest {

  private static final Logger log = LogManager.getLogger(PolieAbstractRequest.class);

  HttpURLConnection connection;
  URL targetURL;

  /**
   * .
   * 
   * @param targetURL
   *          .
   */
  public PolieAbstractRequest(URL targetURL) {
    this.targetURL = targetURL;
    try {
      this.connection = (HttpURLConnection) this.targetURL.openConnection();
    } catch (IOException e) {
      log.error(e);
    }

  }

  protected String getResponse() throws IOException {
    if (this.connection == null) {
      throw new NullPointerException("This request's connection is null");
    }

    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
    String inputLine;
    StringBuffer response = new StringBuffer();

    while ((inputLine = in.readLine()) != null) {
      response.append("\n" + inputLine);
    }
    in.close();
    return response.toString();
  }

  protected void handleResponseCode(int responseCode) throws IOException {
    if (responseCode % 100 == 4) {
      throw new IOException("The server has responded with Server HTTP error " + responseCode);
    }
  }

  public abstract String send() throws IOException;

}
