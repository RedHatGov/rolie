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

package gov.nist.rolie.polie.client.operation.entry;

import gov.nist.rolie.polie.client.operation.Operation;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public enum EntryOperationEnum {
  GET("get", EntryGet::new),
  CREATE("create", EntryCreate::new),
  UPDATE("update", EntryUpdate::new),
  DELETE("delete", EntryDelete::new);

  private static final Map<String, EntryOperationEnum> nameToEnumMap;

  static {
    nameToEnumMap = new HashMap<>();
    for (EntryOperationEnum ev : EntryOperationEnum.values()) {
      nameToEnumMap.put(ev.getName(), ev);
    }
  }

  public static Supplier<Operation> lookup(String name) {
    return nameToEnumMap.get(name).supplier();
  }

  private final String name;
  private final Supplier<Operation> supplier;

  private EntryOperationEnum(String name, Supplier<Operation> supplier) {
    this.name = name;
    this.supplier = supplier;
  }

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @return the supplier
   */
  public Supplier<Operation> supplier() {
    return supplier;
  }
}
