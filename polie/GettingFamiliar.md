# Getting familiar with JROLIE

This is a brief guide to the JROLIE codebase, and how requests are processed.

## A Request is Received

All basic request handling is done by RestEasy (A JAXRS implementation). JAXRS passes off handling to us in the gov.nist.jrolie.server.servlet package. Here, events are annotated with what paths and content types they handle.

AtomResourceEvent handles most/all of the ROLIE requests. Based on the request, a number of "visitors" are created and assigned in order to the request. Each visitor executes some operation on the request before passing it off. Each visitor has the right to cease execution and send the response right away.

## The Visitors

The visitors assigned vary based on the type of request (GET,POST,PUT,DELETE, etc.) but the default visitors and their functions are as follows (In rough typical order):

* AuthorizationVisitor - Handles authorization based on the request headers and requested resource
* ValidationVisitor - If there is content in the body of the request, validate it, and transform it into a useful Java object
* ResourceEventVisitor - Handles the actual request operation itself, and therefore represents the bulk of the actual code
* ResponseBuilderVisitor - Constructs the final response with final touches and header information

## A brief aside on Spring

Springworks automatically chooses an implementation of the Logic layer interfaces at runtime. A default implementation is provided for each interface. This can be seen in action with the "@Autowired" annotations at the top of the visitor classes.

## AtomResourceEvent

The ResourceEventVisitor does most of the request processing.

For a GET Request: A call is made to the Logic module class ResourceService to load the resource at the path requested. The ResourceService, in turn, makes a call out to the PersistenceContext interface to actually retrieve the resource. We don't care what this resource actually is, and we won't be making any changes to it. The client may already have this resource cached, as the ETag of the resource is provided along with the resource.

For a POST Request: POST requests are more complicated. In ROLIE, the only valid POST request is posting a new entry to a Feed. The Feed will be at the URL we sent the request to, and the Entry will be in the body of the request. We load the Feed in question first to make sure it exists. The Entry in the body is turned into a java object by the validation visitor, we pull it and start making modifications. As the server we have the full right to change the entry as we see fit. We create the entry in storage and modify the feed so that it now contains the entry. This causes changes to the Feed metadata. If we are archiving resources, that happens here as well. Finally, the newly created entry is returned as the response, along with its new location in the header.

Entries exist at standalone URLs for static retrieval, and are dynamically pulled in to create the Feed when requested. Internally, the Feed only contains a list of references to the Entries it holds, not the full Entries themselves.

PUT Requests are basically POST requests and DELETE requests are basically GET requests with side-effects.

## Modular Dependencies

The Model module is the most basic layer, and has no dependencies.
The Persistence-API module is only aware of the Model module.
The Logic module is aware of the Model module and the Persistence module
The Server module is aware of the Model module and the Logic module, it is insulated from the persistence-api


## License
  >Portions of this software was developed by employees of the National Institute of Standards and Technology (NIST), an agency of the Federal Government. Pursuant to title 17 United States Code Section 105, works of NIST employees are not subject to copyright protection in the United States and are considered to be in the public domain. Permission to freely use, copy, modify, and distribute this software and its documentation without fee is hereby granted, provided that this notice and disclaimer of warranty appears in all copies. 
  >
 > THE SOFTWARE IS PROVIDED 'AS IS' WITHOUT ANY WARRANTY OF ANY KIND, EITHER EXPRESSED, IMPLIED, OR STATUTORY, INCLUDING, BUT NOT LIMITED TO, ANY WARRANTY THAT THE SOFTWARE WILL CONFORM TO SPECIFICATIONS, ANY IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, AND FREEDOM FROM INFRINGEMENT, AND ANY WARRANTY THAT THE DOCUMENTATION WILL CONFORM TO THE SOFTWARE, OR ANY WARRANTY THAT THE SOFTWARE WILL BE ERROR FREE. IN NO EVENT SHALL NIST BE LIABLE FOR ANY DAMAGES, INCLUDING, BUT NOT LIMITED TO, DIRECT, INDIRECT, SPECIAL OR CONSEQUENTIAL DAMAGES, ARISING OUT OF, RESULTING FROM, OR IN ANY WAY CONNECTED WITH THIS SOFTWARE, WHETHER OR NOT BASED UPON WARRANTY, CONTRACT, TORT, OR OTHERWISE, WHETHER OR NOT INJURY WAS SUSTAINED BY PERSONS OR PROPERTY OR OTHERWISE, AND WHETHER OR NOT LOSS WAS SUSTAINED FROM, OR AROSE OUT OF THE RESULTS OF, OR USE OF, THE SOFTWARE OR SERVICES PROVIDED HEREUNDER.
