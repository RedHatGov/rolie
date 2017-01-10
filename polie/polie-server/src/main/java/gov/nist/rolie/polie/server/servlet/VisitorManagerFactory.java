package gov.nist.rolie.polie.server.servlet;

public interface VisitorManagerFactory {
	VisitorManager GetGetVisitorManager();
	VisitorManager GetPostVisitorManager();
	VisitorManager GetPutVisitorManager();
	VisitorManager GetDeleteVisitorManager();
}
