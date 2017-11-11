package gov.nist.jrolie.model;

import java.util.List;

public interface JCollection extends JResource {
	List<JEntry> getEntries();
}
