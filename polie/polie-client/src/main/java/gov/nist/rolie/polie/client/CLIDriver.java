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
 * SHALL NASA BE LIABLE FOR ANY DAMAGES, INCLUDING, BUT NOT LIMITED TO, DIRECT,
 * INDIRECT, SPECIAL OR CONSEQUENTIAL DAMAGES, ARISING OUT OF, RESULTING FROM, OR
 * IN ANY WAY CONNECTED WITH THIS SOFTWARE, WHETHER OR NOT BASED UPON WARRANTY,
 * CONTRACT, TORT, OR OTHERWISE, WHETHER OR NOT INJURY WAS SUSTAINED BY PERSONS OR
 * PROPERTY OR OTHERWISE, AND WHETHER OR NOT LOSS WAS SUSTAINED FROM, OR AROSE OUT
 * OF THE RESULTS OF, OR USE OF, THE SOFTWARE OR SERVICES PROVIDED HEREUNDER.
 */

package gov.nist.rolie.polie.client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class CLIDriver {
//
//  static String repo = "Not Set";
//  static String input = "";
//  static Scanner scanner = new Scanner(System.in);
//
//  public static void main(String[] args) {
//
//    System.out.println("Welcome to the demo POLIE Client!");
//
//    while (input != "exit") {
//      mainMenu();
//      input = scanner.nextLine();
//      switch (input) {
//      case "1": {
//        setRepo();
//        break;
//      }
//      case "2": {
//        getBasicInfo();
//        break;
//      }
//      case "3": {
//        getResource();
//        break;
//      }
//      case "4": {
//        input = "exit";
//        break;
//      }
//      }
//    }
//
//    scanner.close();
//  }
//
//  private static void getResource() {
//    // TODO Auto-generated method stub
//
//  }
//
//  private static void getBasicInfo() {
//    try {
//      sendGetRequest(repo + "/rolie/servicedocument");
//    } catch (MalformedURLException e) {
//      // TODO Auto-generated catch block
//      e.printStackTrace();
//    } catch (IOException e) {
//      // TODO Auto-generated catch block
//      e.printStackTrace();
//    }
//  }
//
//  private static void setRepo() {
//    System.out.println("Enter the server root of ROLIE repo.");
//    String url = "";
//    while (url.equals("")) {
//      url = scanner.nextLine();
//      try {
//        new URL(url);
//        sendGetRequest(url + "/rolie/servicedocument");
//      } catch (MalformedURLException e) {
//        System.out.print("Invalid/Malformed URL, try again\n");
//        url = "";
//      } catch (IOException e) {
//        System.out.print("The server you've contacted is either no responding or not a ROLIE repo.\n");
//        // e.printStackTrace();
//        url = "";
//      }
//    }
//    repo = url;
//    System.out.println("Successfully set repo URL");
//  }
//
  private static String sendGetRequest(String targetURL) throws MalformedURLException, IOException {
    HttpURLConnection connection = null;
    URL url = new URL(targetURL);
    connection = (HttpURLConnection) url.openConnection();
    connection.setRequestMethod("GET");
    connection.setUseCaches(false);
    connection.setDoOutput(true);

    // Send request
    DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
    wr.close();

    // Get Response
    InputStream is = connection.getInputStream();
    BufferedReader rd = new BufferedReader(new InputStreamReader(is));
    StringBuffer response = new StringBuffer(); // or StringBuffer if Java version 5+
    String line;
    while ((line = rd.readLine()) != null) {
      response.append(line);
      response.append('\r');
    }
    rd.close();
    if (connection != null) {
      connection.disconnect();
    }
    return response.toString();

  }
//
//  public static void mainMenu() {
//    System.out.println();
//    System.out.println("Main Menu");
//    System.out.println("The current ROLIE Repo URL is:" + repo);
//    System.out.println("\nOptions:");
//    System.out.println(" \"1\": Set the repo URL");
//    System.out.println(" \"2\": Retrive basic repo info (Service Doc/Category Doc)");
//    System.out.println(" \"3\": GET a resource");
//    System.out.println(" \"4\": Exit");
//  }

}
