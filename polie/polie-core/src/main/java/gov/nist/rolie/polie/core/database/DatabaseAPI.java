/*
 * 
 */
package gov.nist.rolie.polie.core.database;

// TODO: Auto-generated Javadoc
/**
 * The Class DatabaseAPI.
 */
public class DatabaseAPI {

	/**
	 * Store.
	 */
	public static boolean createCollection(String uri,String data)
	{
		return textPersist.put(uri,data);
	}
	
	public static boolean createEntry(String uri,String data)
	{
		return textPersist.put(uri,data);
	}
	
	public static boolean updateCollection(String uri,String data)
	{
		return textPersist.put(uri,data);
	}
	
	public static boolean updateEntry(String uri,String data)
	{
		return textPersist.put(uri,data);
	}

	public static boolean deleteCollection(String uri)
	{
		return textPersist.delete(uri);
	}
	
	public static boolean deleteEntry(String uri)
	{
		return textPersist.delete(uri);
	}
	
	public static String retrieveCollection(String uri)
	{
		return textPersist.get("collections/"+uri);
	}
	public static String retrieveEntry(String uri)
	{
		return textPersist.get("entries/"+uri);
	}
	public static String retrieveServiceDocument()
	{
		return textPersist.get("rolie/servicedocument");
	}
}
