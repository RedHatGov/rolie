package gov.nist.rolie.polie.core.database;

public interface PersistanceMethod {
	Entry saveEntry(Entry entry);
	Entry loadEntry(String id);
}
