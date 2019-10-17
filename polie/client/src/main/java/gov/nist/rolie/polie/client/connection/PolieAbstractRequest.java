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

package gov.nist.rolie.polie.client.connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
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
  
  protected String getError() throws IOException {
    if (this.connection == null) {
      throw new NullPointerException("This request's connection is null");
    }

    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
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
