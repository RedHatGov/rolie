package gov.nist.rolie.polie.server.servlet;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;


/**
 * Provides configuration information for the webapp. Currently overrides none of the default values,
 * and sets the webapp root to the same as the server root.
 * 
 * @author sab3
 *
 */
@ApplicationPath("") 
public class ApplicationConfig extends Application {}