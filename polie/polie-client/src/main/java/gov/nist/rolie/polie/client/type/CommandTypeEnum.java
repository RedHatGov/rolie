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

package gov.nist.rolie.polie.client.type;

import gov.nist.rolie.polie.client.type.category.CategoryOperationEnum;
import gov.nist.rolie.polie.client.type.entry.EntryOperationEnum;
import gov.nist.rolie.polie.client.type.feed.FeedOperationEnum;
import gov.nist.rolie.polie.client.type.resource.ResourceOperationEnum;
import gov.nist.rolie.polie.client.type.service.ServiceOperationEnum;

import java.util.HashMap;
import java.util.Map;

public enum CommandTypeEnum {
  RESOURCE("resource", (name) -> {
    return ResourceOperationEnum.lookup(name);
  }),
  ENTRY("entry", (name) -> {
    return EntryOperationEnum.lookup(name);
  }),
  FEED("feed", (name) -> {
    return FeedOperationEnum.lookup(name);
  }),
  SERVICE("service", (name) -> {
    return ServiceOperationEnum.lookup(name);
  }),
  CATEGORY("category", (name) -> {
    return CategoryOperationEnum.lookup(name);
  });

  private static final Map<String, CommandTypeEnum> nameToTypeEnumMap;

  static {
    nameToTypeEnumMap = new HashMap<>();
    for (CommandTypeEnum type : CommandTypeEnum.values()) {
      nameToTypeEnumMap.put(type.getName(), type);
    }
  }

  /**
   * .
   * 
   * @param lookupString
   *          lookupString
   * @return the CommandTypeEnum
   */
  public static CommandTypeEnum lookup(String lookupString) {
    return nameToTypeEnumMap.get(lookupString);
  }

  private final String name;
  private final OperationFinder operationFinder;

  private CommandTypeEnum(String name, OperationFinder finder) {
    this.name = name;
    this.operationFinder = finder;
  }

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @return the operationFinder
   */
  public OperationFinder getOperationFinder() {
    return operationFinder;
  }

}