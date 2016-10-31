/*
 * 
 */
package gov.nist.rolie.polie.core.database;

// TODO: Auto-generated Javadoc
/**
 * The Class DatabaseAPI.
 */
public class DatabaseAPI {
	private final PersistanceMethod persistanceMethod;

	
	
	public DatabaseAPI(PersistanceMethod persistanceMethod) {
		super();
		this.persistanceMethod = persistanceMethod;
	}

	/**
	 * Store.
	 */
	public static boolean createCollection(String uri,String data)
	{
		return persistanceMethod.put(uri,data);
	}
	
	public static boolean createEntry(String uri,String data)
	{
		return TextPersist.put(uri,data);
	}
	
	public static boolean updateCollection(String uri,String data)
	{
		return TextPersist.put(uri,data);
	}
	
	public static boolean updateEntry(String uri,String data)
	{
		return TextPersist.put(uri,data);
	}

	public static boolean deleteCollection(String uri)
	{
		return TextPersist.delete(uri);
	}
	
	public static boolean deleteEntry(String uri)
	{
		return TextPersist.delete(uri);
	}
	
	public static String retrieveCollection(String uri)
	{
		return TextPersist.get("collections/"+uri);
	}
	public static String retrieveEntry(String uri)
	{
		return TextPersist.get("entries/"+uri);
	}
	public static String retrieveServiceDocument()
	{
		return TextPersist.get("rolie/servicedocument");
	}
}
