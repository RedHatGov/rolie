# A Java ROLIE Implementation - JROLIE

JROLIE is a Java implementation of [RFC8322](https://tools.ietf.org/html/rfc8322) originally created as a prototype test-bed for the standard. The creators hope to incrementally improve this software until it serves as a reference implementation and starting point for others looking to create ROLIE servers and clients.

## Installation

JROLIE was created using Maven. Use Maven to build the source into a Java Webapp (.war) which can be mounted on a server of your choice. We use Apache Tomcat v9 managed by Eclipse. The project is broken up into modular parts:

Master - Contains the master POM for the project

Server - A Sever API (JAX-RS) that manages HTTP requests and calls functions from the Logic module. Also contains JAXB writers and marshalling logic.

Logic - Library of functions to manipulate ROLIE objects as described in the Model module

Model - Java Object model of ROLIE. A generic set of interfaces is provided along with a JAXB annotated set of implementations.

Persistence-API - Provides a interface into persistence systems. A memory mapped implementation is provided for testing.

Client - A currently WIP client library to build ROLIE clients

Use:

```bash
maven install
```

In the Master module to build out the project.

## Dependencies

JROLIE depends on the NIST OSS-Maven project to handle Maven logistics and some automated build features.

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License
  >Portions of this software was developed by employees of the National Institute of Standards and Technology (NIST), an agency of the Federal Government. Pursuant to title 17 United States Code Section 105, works of NIST employees are not subject to copyright protection in the United States and are considered to be in the public domain. Permission to freely use, copy, modify, and distribute this software and its documentation without fee is hereby granted, provided that this notice and disclaimer of warranty appears in all copies. 
  >
 > THE SOFTWARE IS PROVIDED 'AS IS' WITHOUT ANY WARRANTY OF ANY KIND, EITHER EXPRESSED, IMPLIED, OR STATUTORY, INCLUDING, BUT NOT LIMITED TO, ANY WARRANTY THAT THE SOFTWARE WILL CONFORM TO SPECIFICATIONS, ANY IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, AND FREEDOM FROM INFRINGEMENT, AND ANY WARRANTY THAT THE DOCUMENTATION WILL CONFORM TO THE SOFTWARE, OR ANY WARRANTY THAT THE SOFTWARE WILL BE ERROR FREE. IN NO EVENT SHALL NIST BE LIABLE FOR ANY DAMAGES, INCLUDING, BUT NOT LIMITED TO, DIRECT, INDIRECT, SPECIAL OR CONSEQUENTIAL DAMAGES, ARISING OUT OF, RESULTING FROM, OR IN ANY WAY CONNECTED WITH THIS SOFTWARE, WHETHER OR NOT BASED UPON WARRANTY, CONTRACT, TORT, OR OTHERWISE, WHETHER OR NOT INJURY WAS SUSTAINED BY PERSONS OR PROPERTY OR OTHERWISE, AND WHETHER OR NOT LOSS WAS SUSTAINED FROM, OR AROSE OUT OF THE RESULTS OF, OR USE OF, THE SOFTWARE OR SERVICES PROVIDED HEREUNDER.
