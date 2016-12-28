package gov.nist.rolie.polie.core.models.constructs;
/**
 * 
   # Whatever a media type is, it contains at least one slash
   atomMediaType = xsd:string { pattern = ".+/.+" }

 * @author sab3
 *
 */
public class AtomMediaType {
	
	private String type;

	/**
	 * @param type
	 */
	public AtomMediaType(String type) {
		super();
		this.type = type;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	
	
}
