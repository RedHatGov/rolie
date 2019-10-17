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

package gov.nist.jrolie.atom.logic.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import gov.nist.jrolie.model.JResource;
import gov.nist.jrolie.persistence.api.PersistenceContext;
import gov.nist.jrolie.persistence.api.exceptions.InvalidResourceTypeException;
import gov.nist.jrolie.persistence.api.exceptions.ResourceAlreadyExistsException;
import gov.nist.jrolie.persistence.api.exceptions.ResourceNotFoundException;

//TODO: Add Resource Mapper function that calls to an injected Resource Mapper object
@Component
public class DefaultResourceService implements ResourceService {

	@Autowired
	PersistenceContext pc;

	@Override //TODO: Look at this, why data?
	public JResource create(JResource resource) throws ResourceAlreadyExistsException {
		if (resource.getPath() == null) {
			resource.setPath("/data/" + this.sanitize(resource.getId()));
		}
		return this.pc.create(resource);
	}

	@Override
	public JResource delete(String id) throws ResourceNotFoundException {
		return this.pc.delete(id);
	}

	@Override
	public String generateArchiveSuffix() {
		return "/archive/" + UUID.randomUUID().toString();
	}

	@Override
	public JResource load(String id) throws ResourceNotFoundException, InvalidResourceTypeException {
		return this.pc.load(id, JResource.class);
	}

	@Override
	public String pathToId(String path) {
		return this.pc.pathToId(path);
	}

	@Override
	public String sanitize(String string) {
		return string.replaceAll("http:", "").replaceAll("/", "").replaceAll("www.", "");
	}

	@Override
	public JResource update(JResource resource) throws ResourceNotFoundException {
		return this.pc.update(resource);
	}

}
