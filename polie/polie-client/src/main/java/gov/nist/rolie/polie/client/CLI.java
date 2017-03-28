
package gov.nist.rolie.polie.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CLI {
  public static void main(String[] args) {
    try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {

      String line;
      while ((line = in.readLine()) != null) {
        // parse(line)
      }
    } catch (IOException e) {
    }
  }
}
