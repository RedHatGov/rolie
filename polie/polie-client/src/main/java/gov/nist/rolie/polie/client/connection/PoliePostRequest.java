
package gov.nist.rolie.polie.client.connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class PoliePostRequest extends PolieAbstractRequest {

  private static final Logger log = LogManager.getLogger(PoliePostRequest.class);

  InputStream input;

  public PoliePostRequest(URL targetURL, InputStream input) {
    super(targetURL);
    this.input = input;
  }

  @Override
  public String send() throws IOException {

    this.connection.setRequestMethod("POST");
    this.connection.setDoOutput(true);
    this.connection.setRequestProperty("Content-Type", "application/atom+xml;type=entry");
    this.connection.setRequestProperty("User-Agent", "polie-client/1.0");
    this.setContent(input);

    log.debug("Sending 'POST' request to URL : " + targetURL);
    String response = this.getResponse();
    int responseCode = connection.getResponseCode();
    log.debug("Response Code : " + responseCode);

    log.debug(response);
    handleResponseCode(responseCode);

    connection.disconnect();

    return response;
  }

  private void setContent(InputStream input) throws IOException {

    if (this.connection == null) {
      throw new NullPointerException("This request's connection is null");
    }

    try (BufferedInputStream buffInput = new BufferedInputStream(input);
        BufferedOutputStream wr = new BufferedOutputStream(this.connection.getOutputStream())) {
      byte[] inputBytes = new byte[1000];

      int readSize;
      do {
        readSize = buffInput.read(inputBytes, 0, Math.min(1000, Math.max(buffInput.available(), 100)));
        if (readSize > 0) {
          wr.write(inputBytes, 0, readSize);
          System.out.print(inputBytes);
        }
      } while (readSize != -1);
    }
  }

}
