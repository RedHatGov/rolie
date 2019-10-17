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

package gov.nist.jrolie.persistence.api;

import java.util.Date;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import gov.nist.jrolie.model.JResource;
import gov.nist.jrolie.persistence.api.exceptions.InvalidResourceTypeException;
import gov.nist.jrolie.persistence.api.exceptions.ResourceAlreadyExistsException;
import gov.nist.jrolie.persistence.api.exceptions.ResourceNotFoundException;

@Component
public class MapPC implements PersistenceContext {
	private final Logger log = LogManager.getLogger(MapPC.class);
	HashMap<String, JResource> idtor = new HashMap<String, JResource>();
	HashMap<String, String> ptoid = new HashMap<String, String>();

	public MapPC() throws ResourceAlreadyExistsException {
		//new DemoBootstrap(this);
	}

	@Override
	public boolean idExists(String id) {
		return this.idtor.get(id) != null;
	}

	@Override
	public <X extends JResource> X load(String id, Class<X> clazz)
			throws InvalidResourceTypeException, ResourceNotFoundException {
		if ((id == null) || !this.idExists(id)) {
			throw new ResourceNotFoundException(id);
		}
		final X r = (X) this.idtor.get(id);
		if (clazz.isAssignableFrom(r.getClass())) {
			this.log.debug("[" + new Date(System.currentTimeMillis()) + "] Loaded Object " + r.getId() + " at location "
					+ r.getPath() + " with type " + r.getClass().toString());
			return r;
		} else {
			throw new InvalidResourceTypeException();
		}
	}

	@Override
	public <X extends JResource> X create(X r) throws ResourceAlreadyExistsException {
		if (!this.idExists(r.getId())) {
			this.idtor.put(r.getId(), r);
			this.ptoid.put(r.getPath(), r.getId());
			this.log.debug("[" + new Date(System.currentTimeMillis()) + "] Created Object " + r.getId()
					+ " at location " + r.getPath() + " with type " + r.getClass().toString());
			return r;
		} else {
			throw new ResourceAlreadyExistsException();
		}
	}

	@Override
	public <X extends JResource> X update(X r) throws ResourceNotFoundException {
		if (!this.idExists(r.getId())) {
			throw new ResourceNotFoundException("todo");
		}
		this.ptoid.put(r.getPath(), r.getId());
		this.log.debug("[" + new Date(System.currentTimeMillis()) + "] Updated Object " + r.getId() + " at location "
				+ r.getPath() + " with type " + r.getClass().toString());
		return (X) this.idtor.put(r.getId(), r);
	}

	@Override
	public <X extends JResource> X delete(String id) throws ResourceNotFoundException {
		if (!this.idExists(id)) {
			throw new ResourceNotFoundException(id);
		}
		final X r = (X) this.idtor.remove(id);
		this.log.debug("[" + new Date(System.currentTimeMillis()) + "] Deleted Object " + r.getId() + " at location "
				+ r.getPath() + " with type " + r.getClass().toString());
		return r;
	}

	@Override
	public String pathToId(String path) {
		return this.ptoid.get(path);
	}
}
