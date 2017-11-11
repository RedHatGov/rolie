package gov.nist.jrolie.persistence.api;

import java.net.URI;

import gov.nist.jrolie.model.JCollection;
import gov.nist.jrolie.model.JEntry;

public class APIExamples {
	
	
	public void addNewEntryToCollection() {
		JEntry newEntry = null; // should be instantiated as a new Entry

		PersistenceContext pc = null;
//		// first save the entry
//		newEntry = pc.save(newEntry);

		JCollection col = pc.load(URI.create("location"), JCollection.class);
		// add the entry to the collection
		col.getEntries().add(newEntry);
		// now save the updated collection
		pc.save(col);

		
	}
	
	public void updateCollectionMetadata() {
		PersistenceContext pc = null;

		JCollection col = pc.load(URI.create("location"), JCollection.class);
		// Modify the collection metadata
		// TODO: uncomment once the property is created
		//		col.setTitle(Type.HTML, "<b>test</b>");
		// now save the updated collection
		pc.save(col);

		
	}
}
