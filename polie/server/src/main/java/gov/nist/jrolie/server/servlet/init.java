package gov.nist.jrolie.server.servlet;



public class init {
	
	/**
	 * Everything in this constructor runs at server start
	 */
	public init()
	{
		System.setProperty("SERVER_ROOT", config.SERVER_ROOT);
		System.setProperty("ENTRY_PREFIX", config.ENTRY_PREFIX);
		System.setProperty("FEED_PREFIX", config.FEED_PREFIX);
		System.setProperty("STRICT_CATEGORY_MATCHING", config.STRICT_CATEGORY_MATCHING);
		System.setProperty("FEED_ARCHIVE_MODE", config.FEED_ARCHIVE_MODE);
		System.setProperty("ENTRY_ARCHIVE_MODE", config.ENTRY_ARCHIVE_MODE);
		System.setProperty("MANAGE_DATA", config.MANAGE_DATA);
	}

}
