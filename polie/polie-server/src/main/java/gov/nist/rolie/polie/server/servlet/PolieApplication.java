package gov.nist.rolie.polie.server.servlet;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

public class PolieApplication extends Application{
	
	private Set<Object> singletons = new HashSet<Object>();

	public PolieApplication() {
		singletons.add(new resteasyServlet());
	}

	@Override
	public Set<Object> getSingletons() {
		return singletons;
	}

}
