package gov.nist.jrolie.model;

import java.util.LinkedList;
import java.util.List;

public abstract class AbstractJCollection extends AbstractJResource implements JCollection {
	private List<JEntry> entries = new LinkedList<JEntry>();

	@Override
	public List<JEntry> getEntries() {
		return new ChangeTrackingListProxy<JEntry>(this, entries);
	}
	
}
