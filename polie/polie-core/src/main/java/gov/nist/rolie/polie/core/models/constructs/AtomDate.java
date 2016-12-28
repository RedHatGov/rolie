package gov.nist.rolie.polie.core.models.constructs;
/**
 * 3.3.  Date Constructs

   A Date construct is an element whose content MUST conform to the
   "date-time" production in [RFC3339].  In addition, an uppercase "T"
   character MUST be used to separate date and time, and an uppercase
   "Z" character MUST be present in the absence of a numeric time zone
   offset.

   atomDateConstruct =
      atomCommonAttributes,
      xsd:dateTime

   Such date values happen to be compatible with the following
   specifications: [ISO.8601.1988], [W3C.NOTE-datetime-19980827], and
   [W3C.REC-xmlschema-2-20041028].

   Example Date constructs:

   <updated>2003-12-13T18:30:02Z</updated>
   <updated>2003-12-13T18:30:02.25Z</updated>
   <updated>2003-12-13T18:30:02+01:00</updated>
   <updated>2003-12-13T18:30:02.25+01:00</updated>

   Date values SHOULD be as accurate as possible.  For example, it would
   be generally inappropriate for a publishing system to apply the same
   timestamp to several entries that were published during the course of
   a single da
 * @author sab3
 *
 */
public class AtomDate {

	private AtomCommonAttributes commonAttributes;
	private String dateTimeString;
	//TODO DATETIME???
	/**
	 * @param commonAttributes
	 * @param dateTimeString
	 */
	public AtomDate(AtomCommonAttributes commonAttributes, String dateTimeString) {
		super();
		this.commonAttributes = commonAttributes;
		this.dateTimeString = dateTimeString;
	}
	/**
	 * @return the commonAttributes
	 */
	public AtomCommonAttributes getCommonAttributes() {
		return commonAttributes;
	}
	/**
	 * @return the dateTimeString
	 */
	public String getDateTimeString() {
		return dateTimeString;
	}
	/**
	 * @param commonAttributes the commonAttributes to set
	 */
	public void setCommonAttributes(AtomCommonAttributes commonAttributes) {
		this.commonAttributes = commonAttributes;
	}
	/**
	 * @param dateTimeString the dateTimeString to set
	 */
	public void setDateTimeString(String dateTimeString) {
		this.dateTimeString = dateTimeString;
	}
	
}
