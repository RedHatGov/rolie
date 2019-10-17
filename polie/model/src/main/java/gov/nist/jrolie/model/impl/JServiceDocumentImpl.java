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

package gov.nist.jrolie.model.impl;

import java.net.URI;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;

import gov.nist.jrolie.model.Constants;
import gov.nist.jrolie.model.JAttribute;
import gov.nist.jrolie.model.JElement;
import gov.nist.jrolie.model.JServiceDocument;
import gov.nist.jrolie.model.JWorkspace;

@XmlRootElement(name = "service", namespace = Constants.APP_NS)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso(JWorkspaceImpl.class)
public class JServiceDocumentImpl implements JServiceDocument {

	@XmlTransient
	String path;

	@XmlTransient
	String id;

	@XmlTransient
	boolean isChanged;

	@XmlElement(type = JWorkspaceImpl.class, namespace = Constants.APP_NS, name = "workspace")
	ArrayList<JWorkspace> workspaces;

	@XmlTransient
	ArrayList<JElement> extensions;

	@Override
	public String getPath() {
		return this.path;
	}

	@Override
	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public String getId() {
		return this.id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public boolean isChanged() {
		return this.isChanged;
	}

	@Override
	public ArrayList<JWorkspace> getWorkspaces() {
		return this.workspaces;
	}

	@Override
	public void setWorkspaces(ArrayList<JWorkspace> workspaces) {
		this.workspaces = workspaces;
	}

	@Override
	public void setChanged(boolean changed) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getLang() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLang(String lang) {
		// TODO Auto-generated method stub

	}

	@Override
	public URI getBase() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setBase(URI base) {
		// TODO Auto-generated method stub

	}

	@Override
	public ArrayList<JAttribute> getExtensions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setExtensions(ArrayList<JAttribute> extensions) {
		// TODO Auto-generated method stub

	}

}
