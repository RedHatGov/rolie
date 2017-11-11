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

package gov.nist.rolie.polie.persistence.database;

import gov.nist.jrolie.model.resource.APPServiceDocument;
import gov.nist.jrolie.model.resource.AtomEntry;
import gov.nist.jrolie.model.resource.AtomFeed;
import gov.nist.jrolie.persistence.api.ResourceAlreadyExistsException;

import org.apache.xmlbeans.XmlException;
import org.w3.x2005.atom.EntryDocument;
import org.w3.x2005.atom.FeedDocument;
import org.w3.x2007.app.ServiceDocument;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class Bootstrap {
  /**
   * Create a new example feed using a target file as the content, and a target URL as the resulting resource location.
   * 
   * @param file
   *          the target content to load
   * @param targetURL
   *          the resulting resource location
   * @param persistenceMethod
   *          the persistence method to use to store the feed
   * @return the new feed
   * @throws XmlException
   *           if xml parsing error occurs while loading the xml from file.
   * @throws IOException
   *           if IO error occurs while loading the file.
   * @throws ResourceAlreadyExistsException
   *           If resource already exists at targetURL
   */
  public static AtomFeed bootstrapFeed(File file, String targetURL, PersistenceMethod persistenceMethod)
      throws XmlException, IOException, ResourceAlreadyExistsException {
    AtomFeed retval = new AtomFeed(FeedDocument.Factory.parse(file));
    retval = persistenceMethod.createFeed(retval, targetURL);
    return retval;
  }

  /**
   * Create a new example feed using a target file as an input stream as the content, and a target URL as the resulting
   * resource location.
   * 
   * @param is
   *          the target content to load
   * @param targetURL
   *          the resulting resource location
   * @param persistenceMethod
   *          the persistence method to use to store the feed
   * @return the new feed
   * @throws XmlException
   *           if xml parsing error occurs while loading the xml from file.
   * @throws IOException
   *           if IO error occurs while loading the file.
   * @throws ResourceAlreadyExistsException
   *           If resource already exists at targetURL
   */
  public static AtomFeed bootstrapFeed(InputStream is, String targetURL, PersistenceMethod persistenceMethod)
      throws XmlException, IOException, ResourceAlreadyExistsException {
    AtomFeed retval = new AtomFeed(FeedDocument.Factory.parse(is));
    retval = persistenceMethod.createFeed(retval, targetURL);
    return retval;
  }

  /**
   * Create a new example entry using a target file as the content, and a target URL as the resulting resource location.
   * 
   * @param file
   *          the target content to load
   * @param targetURL
   *          the resulting resource location
   * @param persistenceMethod
   *          the persistence method to use to store the feed
   * @return the new entry
   * @throws XmlException
   *           if xml parsing error occurs while loading the xml from file.
   * @throws IOException
   *           if IO error occurs while loading the file.
   * @throws ResourceAlreadyExistsException
   *           If resource already exists at targetURL
   */
  public static AtomEntry bootstrapEntry(File file, String targetURL, PersistenceMethod persistenceMethod)
      throws XmlException, IOException, ResourceAlreadyExistsException {
    AtomEntry retval = new AtomEntry(EntryDocument.Factory.parse(file));
    retval = persistenceMethod.createEntry(retval, targetURL);
    return retval;
  }

  /**
   * Create a new example entry using a target file as an input stream as the content, and a target URL as the resulting
   * resource location.
   * 
   * @param is
   *          the target content to load
   * @param targetURL
   *          the resulting resource location
   * @param persistenceMethod
   *          the persistence method to use to store the feed
   * @return the new entry
   * @throws XmlException
   *           if xml parsing error occurs while loading the xml from file.
   * @throws IOException
   *           if IO error occurs while loading the file.
   * @throws ResourceAlreadyExistsException
   *           If resource already exists at targetURL
   */
  public static AtomEntry bootstrapEntry(InputStream is, String targetURL, PersistenceMethod persistenceMethod)
      throws XmlException, IOException, ResourceAlreadyExistsException {
    AtomEntry retval = new AtomEntry(EntryDocument.Factory.parse(is));
    retval = persistenceMethod.createEntry(retval, targetURL);
    return retval;
  }

  /**
   * Create a new example service document using a target file as the content, and a target URL as the resulting
   * resource location.
   * 
   * @param file
   *          the target content to load
   * @param targetURL
   *          the resulting resource location
   * @param persistenceMethod
   *          the persistence method to use to store the feed
   * @return the new service document
   * @throws XmlException
   *           if xml parsing error occurs while loading the xml from file.
   * @throws IOException
   *           if IO error occurs while loading the file.
   * @throws ResourceAlreadyExistsException
   *           If resource already exists at targetURL
   */
  public static APPServiceDocument bootstrapServiceDocument(File file, String targetURL,
      PersistenceMethod persistenceMethod) throws XmlException, IOException, ResourceAlreadyExistsException {
    APPServiceDocument retval = new APPServiceDocument(ServiceDocument.Factory.parse(file));
    retval = persistenceMethod.createServiceDocument(retval, targetURL);
    return retval;
  }

  /**
   * Create a new example service document using a target file as an input stream as the content, and a target URL as
   * the resulting resource location.
   * 
   * @param is
   *          the target content to load
   * @param targetURL
   *          the resulting resource location
   * @param persistenceMethod
   *          the persistence method to use to store the feed
   * @return the new service document
   * @throws XmlException
   *           if xml parsing error occurs while loading the xml from file.
   * @throws IOException
   *           if IO error occurs while loading the file.
   * @throws ResourceAlreadyExistsException
   *           If resource already exists at targetURL
   */
  public static APPServiceDocument bootstrapServiceDocument(InputStream is, String targetURL,
      PersistenceMethod persistenceMethod) throws XmlException, IOException, ResourceAlreadyExistsException {
    APPServiceDocument retval = new APPServiceDocument(ServiceDocument.Factory.parse(is));
    retval = persistenceMethod.createServiceDocument(retval, targetURL);
    return retval;
  }

  /**
   * Create a set of bootstrapped example content.
   * 
   * @param pm
   *          the persistence method to use to write the boostrapped content to
   * @throws XmlException
   *           if xml parsing error occurs while loading the xml from file.
   * @throws IOException
   *           if IO error occurs while loading the file.
   * @throws ResourceAlreadyExistsException
   *           If resource already exists at targetURL
   */
  public Bootstrap(PersistenceMethod pm) throws XmlException, IOException, ResourceAlreadyExistsException {
    bootstrapFeed(getClass().getClassLoader().getResourceAsStream("/rolieexamples/examplePrivateIncidentFeed.xml"),
        "http://localhost:8080/polie-server/rolie/private/feed/examplePrivateIncidentFeed", pm);

    bootstrapFeed(getClass().getClassLoader().getResourceAsStream("/rolieexamples/examplePublicSWDFeed.xml"),
        "http://localhost:8080/polie-server/rolie/feed/examplePublicSWDFeed", pm);

    bootstrapFeed(getClass().getClassLoader().getResourceAsStream("/rolieexamples/examplePublicVulnFeed.xml"),
        "http://localhost:8080/polie-server/rolie/feed/examplePublicVulnFeed", pm);

    // vulnEntry = new AtomEntry(EntryDocument.Factory.parse(vulnEntryFile));
    // swdEntry = new AtomEntry(EntryDocument.Factory.parse(swdEntryFile));

    bootstrapEntry(getClass().getClassLoader().getResourceAsStream("/rolieexamples/exampleIncidentEntry1.xml"),
        "http://localhost:8080/polie-server/rolie/entry/exampleIncidentEntry1", pm);

    bootstrapEntry(getClass().getClassLoader().getResourceAsStream("/rolieexamples/exampleSWIDEntry.xml"),
        "http://localhost:8080/polie-server/rolie/entry/exampleSWIDEntry", pm);

    // createEntry(vulnEntry,
    // "http://localhost:8080/polie-server/rolie/entry/exampleVulnEntry1");
    // createEntry(swdEntry, "http://localhost:8080/polie-server/rolie/entry/exampleSWDEntry1");

    bootstrapServiceDocument(
        getClass().getClassLoader().getResourceAsStream("/rolieexamples/exampleServiceDocument.xml"),
        "http://localhost:8080/polie-server/rolie/.well-known/servicedocument", pm);

    String swidData;
    try (
        InputStream is = getClass().getClassLoader().getResourceAsStream(
            "/rolieexamples/data/gov.nist.nsrl.steam.linux.windows.291550.1678990.-2.demo20170424.swidtag");
        Scanner scanner = new Scanner(is)) {
      swidData = scanner.useDelimiter("\\Z").next();

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    pm.createData(swidData, "http://localhost:8080/polie-server/rolie/data/exampleSWID");

  }

}
