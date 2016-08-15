package gov.nist.rolie.polie.core.servlet;

import java.util.LinkedList;
import java.util.List;

import gov.nist.rolie.polie.core.event.RESTEventVisitor;

public class VisitorManager {

	private List<RESTEventVisitor> visitors = new LinkedList<>();
	
	public void addVisitor(RESTEventVisitor visitor)
	{
		visitors.add(visitor);
	}
	
	public void addVisitor(int index, RESTEventVisitor visitor)
	{
		visitors.add(index, visitor);
	}
	
	public List<RESTEventVisitor> build()
	{
		return this.visitors;
	}
}
