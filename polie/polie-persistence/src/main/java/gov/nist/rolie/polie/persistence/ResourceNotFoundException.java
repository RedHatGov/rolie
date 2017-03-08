package gov.nist.rolie.polie.persistence;

public class ResourceNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8791404101429752997L;

	/**
	 * initial serial version
	 */

	public ResourceNotFoundException (String resourceLocation)
	{
		this.resourceLocation=resourceLocation;
	}
	
	private String resourceLocation;

	/**
	 * @return the resourceLocation
	 */
	public String getResourceLocation() {
		return resourceLocation;
	}

	
}
